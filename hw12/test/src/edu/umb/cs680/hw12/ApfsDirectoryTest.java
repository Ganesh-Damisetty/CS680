package edu.umb.cs680.hw12;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class ApfsDirectoryTest {
	LocalDateTime localTime = LocalDateTime.now();
	APFS ApfsFileSystem = APFS.getAPFSFileSystem();
	ApfsDirectory root = (ApfsDirectory) ApfsFileSystem.initFileSystem("Apfs", 500);
	ApfsDirectory applications = new ApfsDirectory(root, "applications", 0, localTime,"ApfsFile",localTime);
	ApfsDirectory home = new ApfsDirectory(root, "home", 0, localTime,"ApfsFile",localTime);
	ApfsDirectory code = new ApfsDirectory(home, "code", 0, localTime,"ApfsFile",localTime);
	ApfsFile a = new ApfsFile(applications, "a", 5, localTime,"ApfsFile",localTime);
	ApfsFile b = new ApfsFile(home, "b", 20, localTime,"ApfsFile",localTime);
	ApfsFile c = new ApfsFile(code, "c", 30, localTime,"ApfsFile",localTime);
	ApfsFile d = new ApfsFile(code, "d", 40, localTime,"ApfsFile",localTime);
	ApfsLink x = new ApfsLink(home, "x", 0, localTime, "ApfsFile", localTime, applications);
	ApfsLink y = new ApfsLink(code, "y", 0, localTime,"ApfsFile",localTime,a);
	
	
	private String[] dirToStringArray(ApfsDirectory d) {
		String parentName = null;
		ApfsDirectory parent = d.getParent();
		if (parent != null) {
			parentName = parent.getName();
		}
		String[] dirInfo = {parentName, Boolean.toString(d.isDirectory()), d.getName(), Integer.toString(d.getSize()),
				d.getCreationTime().toString(),Integer.toString(d.getTotalSize()), Integer.toString(d.countChildren()), d.getOwnerName(), d.getLastModified().toString()  };
		return dirInfo;
	}	
	
	@Test
	public void verifyDirectory() {
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
	public void verifyFiles() {
		assertTrue(a.isFile());
		assertTrue(c.isFile());
		assertTrue(b.isFile());
		assertTrue(d.isFile());
	}

	@Test
	public void verifyFilesInDirectory() {
		assertSame("a", applications.getFiles().get(0).getName());
		assertSame("b", home.getFiles().get(0).getName());
		assertSame("c", code.getFiles().get(0).getName());
		assertSame("d", code.getFiles().get(1).getName());

	}
	
	@Test
	public void verifySize() {
		assertEquals(95, root.getTotalSize());
		assertEquals(90, home.getTotalSize());
		assertEquals(5, applications.getTotalSize());
		assertEquals(70, code.getTotalSize());
	}
	
	@Test
	public void verifyFileByDirectory() {
		assertSame("a", applications.getFiles().get(0).getName());
		assertSame("b", home.getFiles().get(0).getName());
		assertSame("c", code.getFiles().get(0).getName());
		assertSame("d", code.getFiles().get(1).getName());
	}

	@Test
	public void verifySubDirectory() {
		assertSame("applications", root.getSubDirectories().get(0).getName());
		assertSame("home", root.getSubDirectories().get(1).getName());
		assertSame("code",home.getSubDirectories().get(0).getName());
	}

	@Test
	public void verifyCount() {
		assertEquals(2, root.countChildren());
		assertEquals(1, applications.countChildren());
		assertEquals(3, home.countChildren());
		assertEquals(3, code.countChildren());
	}
	
	
	@Test
	public void verifyDirectoryEqualityHome() {
		String[] expected = { "root","true", "home", "0", localTime.toString(), "90", "3", "ApfsFile",localTime.toString() };
		ApfsDirectory actual = home;
		assertArrayEquals(expected, dirToStringArray(actual));
	}
	
	@Test
	public void verifyDirectoryEqualityApplications() {
		String[] expected = { "root","true", "applications", "0", localTime.toString(), "5", "1", "ApfsFile",localTime.toString() };
		ApfsDirectory actual = applications;
		assertArrayEquals(expected,dirToStringArray(actual));
	}
	
	@Test
	public void verifyDirectoryEqualityCode() {
		String[] expected = { "home","true", "code", "0", localTime.toString(), "70", "3", "ApfsFile",localTime.toString() };
		ApfsDirectory actual = code;
		assertArrayEquals(expected, dirToStringArray(actual));
	}
	

}
