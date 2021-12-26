package edu.umb.cs680.hw11;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class ParetoComparatorTest {
	
	 LinkedList<Car> usedCars = new LinkedList<Car>();

	@Test
	public void verifyParetoCompare1() {
		Car car1 = new Car("Tesla", "Model S", 2020, 33, 85000);
		Car car2 = new Car("Mercedecs", "Q3", 2019, 35, 100000);
		Car car3 = new Car("BMW", "X7", 2019, 35, 90000);
		usedCars.add(car1);
		usedCars.add(car2);
		usedCars.add(car3);
		for (Car car : usedCars) {
			car.setDominationCount(usedCars);
		}
		Collections.sort(usedCars, new ParetoComparator());

		assertEquals(85000, usedCars.get(0).getPrice());
		assertEquals(90000, usedCars.get(1).getPrice());
		assertEquals(100000, usedCars.get(2).getPrice());
		
	}
}
