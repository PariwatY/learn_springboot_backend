package com.example.leraning.api;

import java.io.IOException;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.leraning.exception.BaseException;
import com.example.leraning.exception.UserException;
import com.example.leraning.business.UserBusiness;
import com.example.leraning.entity.User;
import com.example.leraning.model.MLoginRegister;
import com.example.leraning.model.MRegisterRequest;
import com.example.leraning.model.MRegisterResponse;
import com.example.leraning.model.TestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    private UserBusiness business;

    // @GetMapping
    // public TestResponse test() {

    // TestResponse response = new TestResponse();
    // response.setName("Nat");
    // response.setFood("KFC");

    // return response;
    // }
    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<String> login(@RequestBody MLoginRegister request) throws BaseException {
        String response = business.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<MRegisterResponse> register(@RequestBody MRegisterRequest request) throws BaseException {

        MRegisterResponse response = business.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseException {
        String response = business.uploadProfilePicture(file);
        return ResponseEntity.ok(response);
    }

}