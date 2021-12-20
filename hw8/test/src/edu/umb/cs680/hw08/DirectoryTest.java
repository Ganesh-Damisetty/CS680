package edu.umb.cs680.hw08;


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
	File b = new File(home, "b", 20, localTime);
	File c = new File(code, "c", 30, localTime);
	File d = new File(code, "d", 40, localTime);
    Link x = new Link(home, "x", 0, localTime, applications);
	Link y = new Link(code, "y", 0, localTime, a);


	private String[] dirToStringArray(Directory d) {
		String parentName = null;
		Directory parent = d.getParent();
		if (parent != null) {
			parentName = parent.getName();
		}
		String[] dirInfo = { Boolean.toString(d.isDirectory()), d.getName(), Integer.toString(d.getSize()),
				d.getCreationTime().toString(),parentName,
				Integer.toString(d.getTotalSize()), Integer.toString(d.countChildren()) };
		return dirInfo;
	}
	
	@Test
	void verifyDirectory() {
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
	void verifyFiles() {
		assertTrue(a.isFile());
		assertTrue(c.isFile());
		assertTrue(b.isFile());
		assertTrue(d.isFile());
	}
	
	@Test
	void verifyFilesByDirectory() {
		assertSame("a", applications.getFiles().get(0).getName());
		assertSame("b", home.getFiles().get(0).getName());
		assertSame("c", code.getFiles().get(0).getName());
		assertSame("d", code.getFiles().get(1).getName());
	}
	

	@Test
	void verifyCount() {
		assertEquals(2, root.countChildren());
		assertEquals(1, applications.countChildren());
		assertEquals(3, home.countChildren());
		assertEquals(3, code.countChildren());
	}
	
	
	@Test
	void verifySize() {
		assertEquals(95, root.getTotalSize());
		assertEquals(90, home.getTotalSize());
		assertEquals(5, applications.getTotalSize());
		assertEquals(70, code.getTotalSize());
	}
	
	@Test
	public void verifyGetChildren() {
		assertEquals(applications, root.getChildren().get(0));
		assertEquals(home, root.getChildren().get(1));
		assertEquals(code,home.getChildren().get(0));
	}

	@Test
	void verifySubDirectories() {
		assertSame("applications",root.getSubDirectories().get(0).getName());
		assertSame("home", root.getSubDirectories().get(1).getName());
		assertSame("code",home.getSubDirectories().get(0).getName());
	}

	@Test
	public void verifyDirectoryEqualityRoot() {
		String[] expected = { "true", "root", "0", localTime.toString(), null, "95","2" };
		Directory actual = root;
		assertArrayEquals(expected, dirToStringArray(actual));
	}

	@Test
	public void verifyDirectoryEqualityHome() {
		String[] expected = { "true", "home", "0", localTime.toString(),"root", "90", "3"};
		Directory actual = home;
		assertArrayEquals(expected, dirToStringArray(actual));
	}

	@Test
	public void verifyDirectoryEqualityApplications() {
		String[] expected = { "true", "applications", "0", localTime.toString(), "root","5", "1"};
		Directory actual = applications;
		assertArrayEquals(expected, dirToStringArray(actual));
	}

	@Test
	public void verifyDirectoryEqualityCode() {
		String[] expected = { "true", "code", "0", localTime.toString(), "home","70","3"};
		Directory actual = code;
		assertArrayEquals(expected, dirToStringArray(actual));
		
	}

}
