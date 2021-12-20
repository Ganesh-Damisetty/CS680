package edu.umb.cs680.hw08;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class FileTest {
	
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

    private String[] fileToStringArray(File f) {
		String[] fileInfo = { Boolean.toString(f.isDirectory()), f.getName(),f.getParent().getName(), Integer.toString(f.getSize()),
				f.getCreationTime().toString(), };
		return fileInfo;
	}

	@Test
	void verifyFile() {
		assertTrue(a.isFile());
		assertTrue(b.isFile());
		assertTrue(c.isFile());
		assertTrue(d.isFile());
	}

	@Test
    void verifyFileA() {
		String[] expected = { "false", "a", "applications","5", localTime.toString(),  };
		File actual = a;
		assertArrayEquals(expected, fileToStringArray(actual));
	}
	
	@Test
    void verifyFileB() {
		String[] expected = { "false", "b","home", "20", localTime.toString() };
		File actual = b;
		assertArrayEquals(expected, fileToStringArray(actual));
	}

	@Test
	void verifyFileC() {
		String[] expected = { "false", "c","code", "30", localTime.toString()};
		File actual = c;
		assertArrayEquals(expected, fileToStringArray(actual));
	}
	
	@Test
	 void verifyFileD() {
		String[] expected = { "false", "d","code", "40", localTime.toString() };
		File actual = d;
		assertArrayEquals(expected, fileToStringArray(actual));
	}
	

}
