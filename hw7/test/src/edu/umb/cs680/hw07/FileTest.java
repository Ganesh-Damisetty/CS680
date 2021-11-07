package edu.umb.cs680.hw07;

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
    File b = new File(home, "b", 30, localTime);
    File c = new File(code, "c", 15, localTime);
    File d = new File(code, "d", 15, localTime);

	private String[] fileToStringArray(File f) {
		String[] fileInfo = { Boolean.toString(f.isDirectory()), f.getName(),f.getParent().getName(), Integer.toString(f.getSize()),
				f.getCreationTime().toString(), };
		return fileInfo;
	}

	@Test
	public void verifyFile() {
		assertTrue(a.isFile());
		assertTrue(b.isFile());
		assertTrue(c.isFile());
		assertTrue(d.isFile());
	}

	@Test
    public void testVerifyFileA() {
		String[] expected = { "false", "a", "applications","5", localTime.toString(),  };
		File actual = a;
		assertArrayEquals(expected, fileToStringArray(actual));
	}
	
	@Test
    public void testVerifyFileB() {
		String[] expected = { "false", "b","home", "30", localTime.toString() };
		File actual = b;
		assertArrayEquals(expected, fileToStringArray(actual));
	}

	@Test
	public void testVerifyFileC() {
		String[] expected = { "false", "c","code", "15", localTime.toString()};
		File actual = c;
		assertArrayEquals(expected, fileToStringArray(actual));
	}
	
	@Test
	public void testVerifyFileD() {
		
		String[] expected = { "false", "d","code", "15", localTime.toString() };
		File actual = d;
		assertArrayEquals(expected, fileToStringArray(actual));
	}
}
