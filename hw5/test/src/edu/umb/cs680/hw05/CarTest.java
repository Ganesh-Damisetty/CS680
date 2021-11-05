package edu.umb.cs680.hw05;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarTest {
	
	private String[] carToStringArray(Car car) {
		String[] carDetails = {car.getMake(), car.getModel(), Integer.toString(car.getYear())};
		return carDetails;
	}
	
	@Test
	public void verifyCarEqualityWithMakeModelYear() {
		String[] expected = {"Toyota", "RAV4", "2018"};
		Car actual = new Car("Toyota", "RAV4", 30, 2018, 20000);
		assertArrayEquals(expected, carToStringArray(actual) );
	}

}
