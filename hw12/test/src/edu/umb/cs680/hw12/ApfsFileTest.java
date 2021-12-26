package edu.umb.cs680.hw12;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class ApfsFileTest {
	
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
	
	private String[] filetoStringArray(ApfsFile f) {
			String[] fileInfo = { Boolean.toString(f.isDirectory()), f.getName(),f.getParent().getName(),
					Integer.toString(f.getSize()),f.getCreationTime().toString(),f.getOwnerName(), f.getLastModified().toString() };
			return fileInfo;
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
	public void verifyFilesByDirectory() {
		assertSame("a", applications.getFiles().get(0).getName());
		assertSame("b", home.getFiles().get(0).getName());
		assertSame("c", code.getFiles().get(0).getName());
		assertSame("d", code.getFiles().get(1).getName());
	}
		
	@Test
	public void verifyFileA() {
		String[] expected = { "false", "a", "applications", "5", localTime.toString(), "ApfsFile",localTime.toString() };
		ApfsFile actual = a;
		assertArrayEquals(expected, filetoStringArray(actual));
	}
		
	@Test
	public void verifyFileB() {
		String[] expected = { "false", "b", "home", "20", localTime.toString(), "ApfsFile",localTime.toString() };
		ApfsFile actual = b;
		assertArrayEquals(expected, filetoStringArray(actual));
		}
		
	@Test
	public void verifyFileC() {
		String[] expected = { "false", "c", "code", "30", localTime.toString(), "ApfsFile",localTime.toString() };
		ApfsFile actual = c;
		assertArrayEquals(expected, filetoStringArray(actual));
	}
	@Test
	public void verifyFileD() {
		String[] expected = { "false", "d", "code","40", localTime.toString(), "ApfsFile",localTime.toString() };
		ApfsFile actual = d;
		assertArrayEquals(expected, filetoStringArray(actual));
	}
	
}
