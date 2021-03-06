package com.example.leraning.api;

import com.example.leraning.business.ProductBusiness;
import com.example.leraning.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/product")

public class ProductApi {

    
    @Autowired
     private ProductBusiness business;

    @GetMapping("{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") String id) throws BaseException{
        String response = business.getProductById(id);
        return ResponseEntity.ok(response);
    }

}