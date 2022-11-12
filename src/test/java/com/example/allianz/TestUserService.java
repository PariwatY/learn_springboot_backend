package com.example.allianz;


import java.util.Optional;


import com.example.allianz.entity.employee.Employee;
import com.example.allianz.exception.BaseException;
import com.example.allianz.service.employee.EmployeeServiceImpl;
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
	private EmployeeServiceImpl userServiceImpl;


	@Order(1)
	@Test
	void testCreate() throws BaseException {

		Employee user = userServiceImpl.create(TestCreateData.email, TestCreateData.password, TestCreateData.name);

		// Check Null
		Assertions.assertNotNull(user);
		Assertions.assertNotNull(user.getId());

		// Check Insert
		Assertions.assertEquals(TestCreateData.email, user.getEmail());

		boolean isMatched = userServiceImpl.checkMatchPassword(TestCreateData.password, user.getPassword());
		Assertions.assertTrue(isMatched);

		Assertions.assertEquals(TestCreateData.name, user.getName());

	}

	@Order(10)
	@Test
	void testUpdate() throws BaseException {
		Optional<Employee> opt = userServiceImpl.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());
		Employee user = opt.get();

		Employee updatedUser = userServiceImpl.updateName(user.getId(), TestUpdateData.name);
		Assertions.assertNotNull(updatedUser);
		Assertions.assertEquals(TestUpdateData.name, updatedUser.getName());

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

}
