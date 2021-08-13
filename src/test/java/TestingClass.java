import org.junit.gen5.api.AfterEach;
import org.junit.gen5.api.Assertions;
import org.junit.gen5.api.BeforeEach;
import org.junit.gen5.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import Revature.Employee.MyController;

public class TestingClass {

	@BeforeEach
	static void BeforeAll() {
		System.out.println("Testing of the bank functions");
	}
	@AfterEach
	static void AfterAll() {
		System.out.println("Testing Completed");
	}
	@Test
	void AgeTest() throws JsonProcessingException, ClassNotFoundException {
		System.out.println("Testing Age Function....");
		Assertions.assertEquals(true,MyController.getHelloParam("{\"uid\":100022,\"pwd\":\"123\"}"));
	}
}
