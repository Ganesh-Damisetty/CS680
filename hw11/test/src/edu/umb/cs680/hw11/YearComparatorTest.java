package edu.umb.cs680.hw11;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class YearComparatorTest {

	LinkedList<Car> carList = new LinkedList<Car>();

    @Test
	void verifyYearComparator() {
		
	    Car car1 = new Car("Tesla", "Model S", 2020, 33, 85000);
		Car car2 = new Car("Mercedes", "GLs", 2021, 40, 150000);
		Car car3 = new Car("BMW", "Sport", 2019, 30, 95000);
		carList.add(car1);
		carList.add(car2);
		carList.add(car3);
		Collections.sort(carList,new YearComparator());
		assertEquals(car3, carList.get(0));
		assertEquals(car1, carList.get(1));
		assertEquals(car2, carList.get(2));
		
	}

}
