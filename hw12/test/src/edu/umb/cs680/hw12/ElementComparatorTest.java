package edu.umb.cs680.hw12;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class ElementComparatorTest {

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
	  ApfsLink x = new ApfsLink(home, "x", 0, localTime, "MyFile", localTime, applications);
	  ApfsLink y = new ApfsLink(code, "y", 0, localTime,"ApfsFile",localTime,a);
	  
	  @Test
	  public void GetChildrenTest() {
		ApfsDirectory dir = home;
		LinkedList<ApfsElement> actual = dir.getChildren(new ElementComparator());
		ApfsElement[] expected = { code,b,x};
		assertArrayEquals(expected, actual.toArray());
	}
	 
	  @Test
	  public void GetChildrenTestRoot() {
		ApfsDirectory dir = root;
		LinkedList<ApfsDirectory> actual = dir.getSubDirectories(new ElementComparator());
		ApfsDirectory[] expected = { applications,home };
		assertArrayEquals(expected, actual.toArray());
	}
	  
	  @Test
	  public void GetFileTestcode() {
		ApfsDirectory dir = code;
		LinkedList<ApfsFile> actual = dir.getFiles(new ElementComparator());
		ApfsFile[] expected = { c,d };
		assertArrayEquals(expected, actual.toArray());
	}
}
