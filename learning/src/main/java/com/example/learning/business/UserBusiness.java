package com.example.learning.business;

import org.springframework.web.multipart.MultipartFile;
import com.example.learning.entity.User;
import com.example.learning.exception.BaseException;
import com.example.learning.exception.FileException;
import com.example.learning.exception.UserException;
import com.example.learning.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.learning.model.MLoginRegister;
import com.example.learning.model.MRegisterRequest;
import com.example.learning.model.MRegisterResponse;
import com.example.learning.service.TokenService;
import com.example.learning.service.UserService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserBusiness {

   @Autowired
   private UserService userService;
   @Autowired
   private userMapper userMapper;

   @Autowired
   private TokenService tokenService;

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
      if (file.getSize() > 1_048_576 * 2) {
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