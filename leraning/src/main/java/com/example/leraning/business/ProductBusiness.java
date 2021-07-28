package com.example.leraning.api;
import java.io.IOException;
import java.util.Objects;

import com.example.leraning.exception.BaseException;
import com.example.leraning.exception.ProductException;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/product")

public class ProductBusiness {

   public String getProductById(String id) throws BaseException{
      
    if(Objects.equals("1234", id)){

       throw ProductException.notFound();
   }
   return id;
}
}