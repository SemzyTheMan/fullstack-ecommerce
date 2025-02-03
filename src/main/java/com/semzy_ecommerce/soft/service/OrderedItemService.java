package com.semzy_ecommerce.soft.service;

import com.semzy_ecommerce.soft.dao.ProductRepository;
import com.semzy_ecommerce.soft.dao.PurchaseDetailsRepository;
import com.semzy_ecommerce.soft.dtos.response.MyItems;
import com.semzy_ecommerce.soft.dtos.response.OrdereditemsResponse;
import com.semzy_ecommerce.soft.entity.PurchaseDetails;
import com.semzy_ecommerce.soft.entity.PurchasedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderedItemService {

    @Autowired
    private PurchaseDetailsRepository purchaseDetailsRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<OrdereditemsResponse> getOrderedItem(int userId) {
        List<PurchaseDetails> tempPurchase = purchaseDetailsRepository.findByUserId(userId);
        List<OrdereditemsResponse> newResponse = new ArrayList<>();
        for (PurchaseDetails details : tempPurchase) {
            List<PurchasedItem> items = details.getPurchasedItems();
            OrdereditemsResponse response = new OrdereditemsResponse();
            List<MyItems> itemsRes = items.stream().map(i -> new MyItems(i.getQuantity()
                    , i.getProduct().getName(), i.getProduct().getPrice())).toList();
            response.setProducts(itemsRes);
            response.setOrderId(details.getId());
            response.setStatus(details.getStatus());
            if (details.getAmount() != null) {
                response.setTotalAmount(details.getAmount());
            } else {
                response.setTotalAmount(0.0);
            }

            newResponse.add(response);
        }
        return newResponse;
    }
}
