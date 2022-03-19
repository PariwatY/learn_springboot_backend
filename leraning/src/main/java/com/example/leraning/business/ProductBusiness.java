package com.example.leraning.business;
import java.io.IOException;
import java.util.Objects;

import com.example.leraning.exception.BaseException;
import com.example.leraning.exception.ProductException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;



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