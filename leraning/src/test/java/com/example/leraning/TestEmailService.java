package com.example.leraning;

import com.example.leraning.business.EmailBusiness;
import com.example.leraning.entity.Address;
import com.example.leraning.entity.Social;
import com.example.leraning.entity.User;
import com.example.leraning.exception.BaseException;
import com.example.leraning.service.AddressService;
import com.example.leraning.service.SocialService;
import com.example.leraning.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TestEmailService {

	@Autowired
	private EmailBusiness emailBusiness;



	@Order(1)
	@Test
	void testSendActivateEmail() throws BaseException {
		emailBusiness.sendActivateUserEmail(TestData.email,TestData.name,TestData.token);

	}


	interface TestData {
		String email = "lampekza999@gmail.com";
		String name = "Pariwat Yimpayoon";
		String token = "qn#@ddfsf";

	}

}
