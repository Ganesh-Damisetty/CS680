package edu.umb.cs680.hw07;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DirectoryTest {
	LocalDateTime localTime = LocalDateTime.now();
	Directory root = new Directory(null, "root", 0, localTime);
    Directory applications = new Directory(root, "applications", 0, localTime);
	Directory home = new Directory(root, "home", 0, localTime);
	Directory code = new Directory(home, "code", 0, localTime);
	File a = new File(applications, "a", 5, localTime);
	File b = new File(home, "b", 30, localTime);
	File c = new File(code, "c", 15, localTime);
	File d = new File(code, "d", 15, localTime);
	
	private String[] dirToStringArray(Directory d) {
		String parentName = null;
		Directory parent = d.getParent();
		if (parent != null) {
			parentName = parent.getName();
		}
		String[] dirInfo = { Boolean.toString(d.isDirectory()), d.getName(), Integer.toString(d.getSize()),
				d.getCreationTime().toString(),parentName, Integer.toString(d.countChildren()),Integer.toString(d.getTotalSize()) };
		return dirInfo;
	}

	@Test
	public void testVerifyDirectory() {
		assertTrue(root.isDirectory());
		assertTrue(home.isDirectory());
		assertTrue(applications.isDirectory());
		assertTrue(code.isDirectory());
		assertFalse(a.isDirectory());
		assertFalse(b.isDirectory());
		assertFalse(c.isDirectory());
		assertFalse(d.isDirectory());	
	}
	
	@Test
	public void testVerifyFiles() {
		assertTrue(a.isFile());
		assertTrue(c.isFile());
		assertTrue(b.isFile());
		assertTrue(d.isFile());
	}
	
	@Test
	public void testVerifyGetChildren() {
		assertEquals(applications, root.getChildren().get(0));
		assertEquals(home, root.getChildren().get(1));
		assertEquals(code,home.getChildren().get(0));
	}
	
	@Test
	public void testVerifyFilesByDirectory() {
		assertSame("a", applications.getFiles().get(0).getName());
		assertSame("b", home.getFiles().get(0).getName());
		assertSame("c", code.getFiles().get(0).getName());
		assertSame("d", code.getFiles().get(1).getName());
	}
	
	@Test
	public void testVerifyCount() {
		assertEquals(2, root.countChildren());
		assertEquals(1, applications.countChildren());
		assertEquals(2, home.countChildren());
		assertEquals(2, code.countChildren());
	}

	@Test
	public void testVerifySize() {
		assertEquals(65, root.getTotalSize());
		assertEquals(60, home.getTotalSize());
		assertEquals(5, applications.getTotalSize());
		assertEquals(30, code.getTotalSize());
	}

	@Test
	public void testVerifySubDirectories() {
		assertSame("applications",root.getSubDirectories().get(0).getName());
		assertSame("home", root.getSubDirectories().get(1).getName());
		assertSame("code",home.getSubDirectories().get(0).getName());
	}

	@Test
	public void testVerifyDirectoryEqualityRoot() {
		String[] expected = { "true", "root", "0", localTime.toString(), null, "2","65" };
		Directory actual = root;
		assertArrayEquals(expected, dirToStringArray(actual));
	}

	@Test
	public void testVerifyDirectoryEqualityHome() {
		String[] expected = { "true", "home", "0", localTime.toString(),"root", "2","60" };
		Directory actual = home;
		assertArrayEquals(expected, dirToStringArray(actual));
	}

	@Test
	public void testVerifyDirectoryEqualityApplications() {
		String[] expected = { "true", "applications", "0", localTime.toString(), "root", "1","5" };
		Directory actual = applications;
		assertArrayEquals(expected, dirToStringArray(actual));
	}

	@Test
	public void testVerifyDirectoryEqualityCode() {
		String[] expected = { "true", "code", "0", localTime.toString(), "home", "2","30" };
		Directory actual = code;
		assertArrayEquals(expected, dirToStringArray(actual));
	}
}
