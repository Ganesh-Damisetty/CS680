package edu.umb.cs680.hw14;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


class ApfsLinkTest {

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
	  ApfsLink y = new ApfsLink(code, "y", 0, localTime,"ApfsFile",localTime,b);
	
	  
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
			assertEquals(0, x.getSize());
			assertEquals(0,y.getSize());
	  }
	    
	  @Test
	  public void verifyEqualityOfLinkX() {
			ApfsDirectory expected = applications;
			FSElement actual = x.getTarget();
			assertSame(expected, actual);
		}
	  
	  @Test
	  public void verifyEqualityOfLinkY() {
			ApfsFile expected = b;
			FSElement actual = y.getTarget();
			assertSame(expected, actual);
		}
	  
	}


