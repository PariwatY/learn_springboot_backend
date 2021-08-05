package com.example.leraning.business;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.example.leraning.entity.User;
import com.example.leraning.exception.BaseException;
import com.example.leraning.exception.FileException;
import com.example.leraning.exception.UserException;
import com.example.leraning.mapper.userMapper;

import java.io.IOException;
import java.lang.reflect.Array;

import org.springframework.security.core.Authentication;
import org.aspectj.apache.bcel.util.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.leraning.model.MLoginRegister;
import com.example.leraning.model.MRegisterRequest;
import com.example.leraning.model.MRegisterResponse;
import com.example.leraning.service.TokenService;
import com.example.leraning.service.UserService;
import com.example.leraning.util.SecurityUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserBusiness {

   @Autowired
   private UserService userService;
   @Autowired
   private userMapper userMapper;

   @Autowired
   private TokenService tokenService;

   // private final UserService userService;
   // private final userMapper userMapper;
   // private final TokenService tokenService;

   // public UserService(UserService userService, userMapper
   // userMapper,TokenService tokenService) {
   // this.userService = userService;
   // this.userMapper = userMapper;
   // this.tokenService = tokenService;
   // }

   // Login
   public String login(MLoginRegister request) throws BaseException {
      // Validate

      // Verify Database
      Optional<User> opt = userService.findByEmail(request.getEmail());

      if (opt.isEmpty()) {
         throw UserException.loginFailEmailNotFound();
      }

      User user = opt.get();

      if (!userService.matchPassword(request.getPassword(), user.getPassword())) {
         throw UserException.loginFailPasswordIncorrect();
      }

      // TODO :generate JWT

      return tokenService.tokennize(user);

   }

   public String refreshToken() throws BaseException {
      Optional<String> opt = SecurityUtil.getCurrentUserId();
      if (opt.isEmpty()) {
         throw UserException.unAuthorized();
      }

      String userId = opt.get();

      Optional<User> optUserId = userService.findById(userId);
      if (optUserId.isEmpty()) {
         throw UserException.notFound();
      }

      User user = optUserId.get();

      return tokenService.tokennize(user);
   }

   // Register
   public MRegisterResponse register(MRegisterRequest request) throws BaseException {

      User user = userService.create(request.getEmail(), request.getPassword(), request.getName());
      return userMapper.toRegisterResponse(user);

   }

   public String uploadProfilePicture(MultipartFile file) throws BaseException {

      // Validate file
      if (file == null) {
         throw FileException.fileNull();
      }
      // Validate Size
      if (file.getSize() > 1048576 * 2) {
         throw FileException.fileMaxSize();
      }

      String contentType = file.getContentType();

      if (contentType == null) {
         // throw error
      }

      List<String> supportTypes = Arrays.asList("image/jpeg", "image/png");
      if (!supportTypes.contains(contentType)) {
         throw FileException.unSupported();
      }

      // try{
      // byte[] bytes = file.getBytes();
      // }catch(IOException e){
      // e.printStackTrace();
      // }
      return "test upload file success";

   }

}