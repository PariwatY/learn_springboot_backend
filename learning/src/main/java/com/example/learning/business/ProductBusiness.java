package com.example.learning.business;
import java.io.IOException;
import java.util.Objects;

import com.example.learning.exception.BaseException;
import com.example.learning.exception.ProductException;
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