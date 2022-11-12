package com.example.allianz.service.employee;

import com.example.allianz.entity.employee.Employee;
import com.example.allianz.exception.BaseException;
import com.example.allianz.exception.employee.EmployeeException;
import com.example.allianz.mapper.employee.EmployeeMapper;
import com.example.allianz.model.employee.*;
import com.example.allianz.model.util.*;
import com.example.allianz.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.allianz.service.token.TokenService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

   @Autowired
   private TokenService tokenService;
   @Autowired
   private EmployeeRepository employeeRepository;
   @Autowired
   private  PasswordEncoder passwordEncoder;
   
   @Autowired
   private EmployeeMapper employeeMapper;



   public RegisterResponse register(RegisterRequest request) throws BaseException {
      Employee employee = create(request.getEmail(), request.getPassword(), request.getName());
      return employeeMapper.toRegisterResponse(employee);
   }


   public DataResponse<LoginResponse> login(DataRequest<LoginRequest> request) throws BaseException {


      // Check Employee must exist
      Optional<Employee> opt = findByEmail(request.getBody().getData().getEmail());

      if (opt.isEmpty()) {
         throw EmployeeException.loginFailEmailNotFound();
      }

      Employee employee = opt.get();

      // Check password from request and password from database must equal
      if (!checkMatchPassword(request.getBody().getData().getPassword(), employee.getPassword())) {
         throw EmployeeException.loginFailPasswordIncorrect();
      }


      return getResponse(request,tokenService.generateToken(employee.getEmail()),employee.getId(),"login success");

   }



   public DataResponse<List<EmployeeData>> getAllEmployee(String token,LoginRequest request) throws BaseException {
      if(tokenService.verifyToken(token,request.getEmail())){
         return getListEmployeeData(employeeMapper.toListEmployeeData(findAll()),"");
      }
      return new DataResponse<>();
   }



   public DataResponse<EmployeeData>  getEmployee(String token,String email,String userId) throws BaseException {
      if(tokenService.verifyToken(token,email)){

         Optional<Employee> optionalEmployee = findById(userId);
         if(optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            return getEmployeeData(employeeMapper.toEmployeeData(employee),"");
         }

      }
      return new DataResponse<>();
   }



   public DataResponse<EmployeeData>  updateEmployee(String token, DataRequest<EmployeeData> request, String userId) throws BaseException {
      if(tokenService.verifyToken(token,request.getBody().getData().getEmail())){
         Employee employee = updateName(userId,request.getBody().getData().getName());
         return  getEmployeeData(employeeMapper.toEmployeeData(employee),"update success");
      }
      return new DataResponse<>();
   }



   public ResponseEntity<DataResponse<String>> deleteEmployee(String token, String email, String name, String employeeId) throws BaseException {
      if(tokenService.verifyToken(token,email)){
         //Delete 
         deleteById(employeeId);

         Optional<Employee> optionalEmployee= findById(employeeId);
         //Check if employee not found it's mean delete success
         if(!optionalEmployee.isPresent()){
            HeaderData headerData = new HeaderData();
            headerData.setResponseDateTime(LocalDateTime.now().toString());

            DataResponse<String> dataResponse = new DataResponse<>();
            dataResponse.setHeader(headerData);
            dataResponse.setStatus(new StatusData().successStatusData(null,"delete success"));
            return new ResponseEntity<>(dataResponse, HttpStatus.OK);
         }else{
            throw EmployeeException.deleteFail();
         }

      }
      return new ResponseEntity<>(new DataResponse<>(), HttpStatus.EXPECTATION_FAILED);
   }


   public List<Employee> findAll() {
      return employeeRepository.findAll();
   }

   public Optional<Employee> findById(String id) {
      return employeeRepository.findById(id);
   }

   public Optional<Employee> findByEmail(String email) {
      return employeeRepository.findByEmail(email);
   }

   public Employee update(Employee employee) {
      return employeeRepository.save(employee);
   }

   public Employee updateName(String id, String name) throws BaseException {

      Optional<Employee> opt = employeeRepository.findById(id);
      if (opt.isEmpty()) {
         throw EmployeeException.notFound();
      }

      Employee employee = opt.get();
      employee.setName(name);
      return employeeRepository.save(employee);

   }

   public void deleteById(String id) {
      employeeRepository.deleteById(id);
   }

   public boolean checkMatchPassword(String rawPassword, String encodedPassword) {
      return passwordEncoder.matches(rawPassword, encodedPassword);
   }

   public Employee create(String email, String password, String name) throws BaseException {
     //Check email mustn't null
      if (Objects.isNull(email)) {
         throw EmployeeException.createEmailNull();
      }

      //Check password mustn't null
      if (Objects.isNull(password)) {
         throw EmployeeException.creatPasswordNull();
      }

      //Check name mustn't null
      if (Objects.isNull(name)) {
         throw EmployeeException.createNameNull();
      }

      // Verify email
      if (employeeRepository.existsById(email)) {
         throw EmployeeException.createEmailDuplicate();
      }

      Employee entity = new Employee();
      entity.setEmail(email);
      entity.setPassword(passwordEncoder.encode(password));
      entity.setName(name);
      return employeeRepository.save(entity);
   }




   private static DataResponse<LoginResponse> getResponse(DataRequest<LoginRequest> request,String token,String employeeId,String msg){
      HeaderData headerData = new HeaderData();
      headerData.setResponseDateTime(LocalDateTime.now().toString());
      headerData.setSendDateTime(request.getHeader().getSendDateTime());

      LoginResponse loginResponse = new LoginResponse();
      loginResponse.setId(employeeId);
      loginResponse.setJwt(token);
      loginResponse.setEmail(request.getBody().getData().getEmail());

      BodyData<LoginResponse> responseBodyData = new BodyData<>();
      responseBodyData.setData(loginResponse);

      DataResponse<LoginResponse> dataResponse = new DataResponse<>();
      dataResponse.setHeader(headerData);
      dataResponse.setBody(responseBodyData);
      dataResponse.setStatus(new StatusData().successStatusData(null,msg));

      return dataResponse;
   }




   private static DataResponse<EmployeeData> getEmployeeData(EmployeeData registerResponse,String msg){
      HeaderData headerData = new HeaderData();
      headerData.setResponseDateTime(LocalDateTime.now().toString());

      BodyData<EmployeeData> responseBodyData = new BodyData<>();
      responseBodyData.setData(registerResponse);

      DataResponse<EmployeeData> dataResponse = new DataResponse<>();
      dataResponse.setHeader(headerData);
      dataResponse.setBody(responseBodyData);
      dataResponse.setStatus(new StatusData().successStatusData(null,msg));

      return dataResponse;
   }



   private static DataResponse<List<EmployeeData>> getListEmployeeData(List<EmployeeData> employeeDataList, String msg){
      HeaderData headerData = new HeaderData();
      headerData.setResponseDateTime(LocalDateTime.now().toString());


      BodyData<List<EmployeeData>> responseBodyData = new BodyData<>();
      responseBodyData.setData(employeeDataList);

      DataResponse<List<EmployeeData>> dataResponse = new DataResponse<>();
      dataResponse.setHeader(headerData);
      dataResponse.setBody(responseBodyData);
      dataResponse.setStatus(new StatusData().successStatusData(null,msg));

      return dataResponse;
   }


}