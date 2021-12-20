package edu.umb.cs680.hw08;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;


class LinkTest {

	LocalDateTime localTime = LocalDateTime.now();
	Directory root = new Directory(null, "root", 0, localTime);
	Directory applications = new Directory(root, "applications", 0, localTime);
	Directory home = new Directory(root, "home", 0, localTime);
    Directory code = new Directory(home, "code", 0, localTime);
	File a = new File(applications, "a", 5, localTime);
	File b = new File(home, "b", 20, localTime);
	File c = new File(code, "c", 30, localTime);
	File d = new File(code, "d", 40, localTime);
    Link x = new Link(home, "x", 0, localTime, applications);
	Link y = new Link(code, "y", 0, localTime, a);

	
	@Test
	public void verifyLink() {
		assertTrue(x.isLink());
		assertTrue(y.isLink());
    }
	
	@Test
	public void verifyLinksInDirectory() {
		assertSame("x", home.getLinks().get(0).getName());
		assertSame("y", code.getLinks().get(0).getName());
	}
	
	@Test
	public void verifySize() {
		assertSame(0, home.getLinks().get(0).getSize());
		assertSame(0, code.getLinks().get(0).getSize());
	}
	
	@Test
	public void verifyLinkEqualityX() {
		Directory expected = applications;   
		FSElement actual = x.getTarget();
		assertEquals(expected,actual);
	}
	
	@Test
	public void verifyLinkEqualityY() {
		File expected = a;
		FSElement actual = y.getTarget();
		assertSame(actual, expected);
	}
}
