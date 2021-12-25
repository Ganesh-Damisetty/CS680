package edu.umb.cs680.hw10;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


class APfsCountingVisitorTest {
	
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
	

	@Test
	public void verifyVisitorRoot() {
		ApfsCountingVisitor visitor = new ApfsCountingVisitor();
		root.accept(visitor);
		assertEquals(4,visitor.getDirNumber());
		assertEquals(4,visitor.getFileNumber());
		assertEquals(2,visitor.getLinkNumber());
	}
	
	@Test
	public void verifyVisitorHome() {
		ApfsCountingVisitor visitor = new ApfsCountingVisitor();
		home.accept(visitor);
		assertEquals(2,visitor.getDirNumber());
		assertEquals(3,visitor.getFileNumber());
		assertEquals(2,visitor.getLinkNumber());
	}
	@Test
	public void verifyVisitorCode() {
		ApfsCountingVisitor visitor = new ApfsCountingVisitor();
		code.accept(visitor);
		assertEquals(1,visitor.getDirNumber());
		assertEquals(2,visitor.getFileNumber());
		assertEquals(1,visitor.getLinkNumber());
	}
	
}
