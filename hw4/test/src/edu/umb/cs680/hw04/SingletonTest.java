package edu.umb.cs680.hw04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SingletonTest {

	@Test
    public void instanceAreEqual() {
        Singleton object1 = Singleton.getInstance();
        Singleton object2 = Singleton.getInstance();
//      object1.hashCode();
//      object2.hashCode();
        assertSame(object1, object2);

}
	
	@Test
    public void instanceUsingHashCode() {
        Singleton object1 = Singleton.getInstance();
        Singleton object2 = Singleton.getInstance();
//        object1.hashCode();
//        object2.hashCode();
        assertEquals(object1.hashCode(), object2.hashCode());

}
	@Test
	public void instanceNotNull() {
		Singleton object1 = Singleton.getInstance();
		Assertions.assertNotNull(object1);
	}
	
}
