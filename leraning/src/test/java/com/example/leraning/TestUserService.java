package com.example.leraning;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TestUserService {

	@Autowired
	private UserService userService;

	@Autowired
	private SocialService socialService;

	@Autowired
	private AddressService addressService;

	@Order(1)
	@Test
	void testCreate() throws BaseException {

		User user = userService.create(TestCreateData.email, TestCreateData.password, TestCreateData.name);

		// Check Null
		Assertions.assertNotNull(user);
		Assertions.assertNotNull(user.getId());

		// Check Insert
		Assertions.assertEquals(TestCreateData.email, user.getEmail());

		boolean isMatched = userService.matchPassword(TestCreateData.password, user.getPassword());
		Assertions.assertTrue(isMatched);

		Assertions.assertEquals(TestCreateData.name, user.getName());

	}

	@Order(10)
	@Test
	void testUpdate() throws BaseException {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());
		User user = opt.get();

		User updateedUser = userService.updateName(user.getId(), TestUpdateData.name);
		Assertions.assertNotNull(updateedUser);
		Assertions.assertEquals(TestUpdateData.name, updateedUser.getName());

	}

	@Order(20)
	@Test
	void testCreateSocial() throws BaseException {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());
		User user = opt.get();

		Social social = user.getSocial();
		Assertions.assertNull(social);

		social = socialService.create(user, SocialTestCreateData.facebook, SocialTestCreateData.line,
				SocialTestCreateData.instagram, SocialTestCreateData.tiktok);

		Assertions.assertNotNull(social);
		Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());
	}

	@Order(30)
	@Test
	void testAddresscreate() throws BaseException {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		List<Address> addressed = user.getAddress();
		Assertions.assertTrue(addressed.isEmpty());

		createAddress(user, AddressTestCreateData.line1, AddressTestCreateData.line2, AddressTestCreateData.zipcode);
		createAddress(user, AddressTestCreateData2.line1, AddressTestCreateData2.line2, AddressTestCreateData2.zipcode);

	}

	private void createAddress(User user, String line1, String line2, String zipcode) {
		Address address = addressService.create(user, line1, line2, zipcode);

		Assertions.assertNotNull(address);
		Assertions.assertEquals(AddressTestCreateData.line1, address.getLine1());
	}

	@Order(40)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());
		User user = opt.get();
		// check social
		Social social = user.getSocial();
		Assertions.assertNotNull(social);
		Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());

		// check address
		List<Address> address = user.getAddress();
		Assertions.assertFalse(address.isEmpty());
		Assertions.assertEquals(2, address.size());

		userService.deleteById(user.getId());

		Optional<User> optDelete = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(optDelete.isEmpty());
	}

	interface TestCreateData {
		String email = "unittest@hotmail.com";
		String password = "123456";
		String name = "Pariwat";
	}

	interface TestUpdateData {
		String email = "unitUpdatetest@hotmail.com";
		String password = "123456";
		String name = "TEST002";
	}

	interface SocialTestCreateData {
		String facebook = "facebook0001";
		String line = "line0001";
		String instagram = "instagram0001";
		String tiktok = "tiktok0001";

	}

	interface AddressTestCreateData {

		String line1 = "123/5";
		String line2 = "samutsakhon province";
		String zipcode = "1040";

	}

	interface AddressTestCreateData2 {

		String line1 = "888/5";
		String line2 = "bangkok province";
		String zipcode = "1050";

	}

	interface AddressTestCreateData3 {

		String line1 = "888/5";
		String line2 = "bangkok province";
		String zipcode = "1050";

	}

}
