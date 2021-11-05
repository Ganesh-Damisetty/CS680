package edu.umb.cs680.hw03;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PrimeGeneratorTest {

	@Test
	public void testgeneratePrime() {
		PrimeGenerator gen = new PrimeGenerator(1,10);
		gen.generatePrimes();
		Long[] expectedPrimes = {2L, 3L, 5L, 7L};
		assertArrayEquals(expectedPrimes, gen.getPrimes().toArray());
	}
	@Test
    public void testNegativeRangeNumber() {
        try {
            PrimeGenerator gen = new PrimeGenerator(-10, 10);
            gen.generatePrimes();
        } catch (RuntimeException ex) {
            assertEquals("Wrong input values: from=-10 to=10", ex.getMessage());
        }
    }
}
