package com.example.leraning.business;


import com.example.leraning.exception.BaseException;
import com.example.leraning.exception.ProductException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/product")
public class ProductBusiness {
    public String getProductById(String id) throws BaseException {

        if(Objects.equals("1234", id)){

            throw ProductException.notFound();
        }
        return id;
    }
}
