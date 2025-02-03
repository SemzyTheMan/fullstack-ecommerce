package com.semzy_ecommerce.soft.controller;

import com.semzy_ecommerce.soft.dtos.response.OrdereditemsResponse;
import com.semzy_ecommerce.soft.service.OrderedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderedItemService orderedItemService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrdereditemsResponse>> getOrdersByUserId(@PathVariable int userId) {
        return new ResponseEntity<>(orderedItemService.getOrderedItem(userId), HttpStatus.OK);
    }
}
