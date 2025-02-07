package com.semzy_ecommerce.soft.service;

import com.semzy_ecommerce.soft.dao.CartRepository;
import com.semzy_ecommerce.soft.dao.ProductRepository;
import com.semzy_ecommerce.soft.dao.PurchaseDetailsRepository;
import com.semzy_ecommerce.soft.dao.UserRepository;
import com.semzy_ecommerce.soft.dtos.requests.Customer;
import com.semzy_ecommerce.soft.dtos.requests.PaymentRequest;
import com.semzy_ecommerce.soft.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TransactionService {
    @Value("${flutterwave.secret-key}")
    private String flwSecretKey;
    private final String baseUrl = "https://api.flutterwave.com/v3";

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public PurchaseDetailsRepository purchaseDetailsRepository;

    @Autowired
    public CartRepository cartRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Transactional
    public ResponseEntity<?> initiatePayment(int userId) {
        String transactionRef = UUID.randomUUID().toString();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Customer customer = new Customer(user.getUsername(), user.getEmail());
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setTx_ref(transactionRef);
        paymentRequest.setAmount(user.getCart().getTotalAmount());
        paymentRequest.setRedirect_url("http://localhost:3000https://semzy-audiophile.vercel.app");
        paymentRequest.setCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + flwSecretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<PaymentRequest> requestEntity = new HttpEntity<>(paymentRequest, headers);

        try {

            ResponseEntity<?> response = restTemplate.exchange(
                    "https://api.flutterwave.com/v3/payments",
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            PurchaseDetails purchaseDetails = new PurchaseDetails();
            purchaseDetails.setTransactionId(transactionRef);
            purchaseDetails.setAmount(user.getCart().getTotalAmount());

            List<PurchasedItem> purchasedItems = new ArrayList<>();
            for (CartItem item : user.getCart().getCartItems()) {
                Product product = productRepository.findById(item.getProductId());
                PurchasedItem tempItem = new PurchasedItem(product, item.getQuantity());
                purchasedItems.add(tempItem);
            }

            for (PurchasedItem item : purchasedItems) {
                purchaseDetails.addPurchasedItem(item);
            }

            purchaseDetails.setStatus("pending");
            purchaseDetails.setUser(user);
            purchaseDetailsRepository.save(purchaseDetails);

            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        } catch (HttpClientErrorException e) {

            System.err.println("Error Code: " + e.getStatusCode());
            System.err.println("Error Response: " + e.getResponseBodyAsString());
        }

        return null;
    }


    @Transactional
    public Map<String, Object> verifyTransaction(String transactionId) throws Exception {

        String url = baseUrl + "/transactions/" + transactionId + "/verify";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + flwSecretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    Map.class
            );
            Map<String, Object> tempResponse = response.getBody();
            Map<String,Object> data = (Map<String, Object>) tempResponse.get("data");
            String txRef = (String) data.get("tx_ref");
            String status = (String) data.get("status");
            Integer tempAmount =  (Integer) data.get("amount");
            Double amount = tempAmount.doubleValue();

            PurchaseDetails purchaseDetails = purchaseDetailsRepository.findByTransactionId(txRef);

            User user = userRepository.findById(purchaseDetails.getUser().getId())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            Cart userCart = user.getCart();
            List<PurchasedItem> itemsBought = purchaseDetails.getPurchasedItems();
            List<Product> productsBought =new ArrayList<>();


            for(PurchasedItem item : itemsBought){
                Product tempProduct = item.getProduct();
                tempProduct.setQuantityAvailable(tempProduct.getQuantityAvailable()-item.getQuantity());
                productsBought.add(tempProduct);


            }
            if(status.equalsIgnoreCase("successful") && userCart.getTotalAmount()==amount){
                purchaseDetails.setStatus("success");
                user.setCart(null);
                cartRepository.deleteById(userCart.getId());
                userRepository.save(user);
                productsBought.forEach(p->productRepository.save(p));
            }


            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new Exception("Failed to verify transaction: " + e.getMessage());
        }


    }
}
