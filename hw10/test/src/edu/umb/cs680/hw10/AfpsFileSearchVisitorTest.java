package edu.umb.cs680.hw10;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class AfpsFileSearchVisitorTest {
	
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
	
	private String[] fileToStringArray(ApfsFile f) {
		String[] fileInfo = { Boolean.toString(f.isDirectory()), f.getName(), Integer.toString(f.getSize()),f.getCreationTime().toString(),
					f.getOwnerName(), f.getLastModified().toString() };
			return fileInfo;
	}

	@Test
	public void verifyFileA() {
		ApfsFileSearchVisitor visitor = new ApfsFileSearchVisitor();
		String[] expected = { "false", "a", "5", localTime.toString(), "ApfsFile", localTime.toString() };
		visitor.setFileName("a");
		root.accept(visitor);
		ApfsFile actual = visitor.getFile();
		assertArrayEquals(expected, fileToStringArray(actual));
	}
		
	@Test
	public void verifyFileB() {
		ApfsFileSearchVisitor visitor = new ApfsFileSearchVisitor();
		String[] expected = { "false", "b", "20", localTime.toString(), "ApfsFile", localTime.toString() };
		visitor.setFileName("b");
		root.accept(visitor);
		ApfsFile actual = visitor.getFile();
		assertArrayEquals(expected, fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileC() {
		ApfsFileSearchVisitor visitor = new ApfsFileSearchVisitor();
		String[] expected = { "false", "c", "30", localTime.toString(), "ApfsFile", localTime.toString() };
		visitor.setFileName("c");
		root.accept(visitor);
		ApfsFile actual = visitor.getFile();
		assertArrayEquals(expected, fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileD() {
		ApfsFileSearchVisitor visitor = new ApfsFileSearchVisitor();
		String[] expected = { "false", "d", "40", localTime.toString(), "ApfsFile", localTime.toString() };
		visitor.setFileName("d");
		root.accept(visitor);
		ApfsFile actual = visitor.getFile();
		assertArrayEquals(expected, fileToStringArray(actual));
	}


}
