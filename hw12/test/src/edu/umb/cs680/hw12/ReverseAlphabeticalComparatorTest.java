package edu.umb.cs680.hw12;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class ReverseAlphabeticalComparatorTest {

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
	  public void GetChildrenRootTest() {
		 ApfsDirectory dir = root;
		 LinkedList<ApfsElement> actual = dir.getChildren(new ReverseAlphabeticalComparator());
		 ApfsElement[] expected = { home, applications };
		 assertArrayEquals(expected, actual.toArray()); 
	  }
	  
	  @Test
	  public void GetChildrenHomeTest() {
		 ApfsDirectory dir = home;
		 LinkedList<ApfsElement> actual = dir.getChildren(new ReverseAlphabeticalComparator());
		 ApfsElement[] expected = { x,code,b };
		 assertArrayEquals(expected, actual.toArray());
		}
	  
	  @Test
	  public void getSubDirectoriesRootTest() {
		  ApfsDirectory dir = root;
		  LinkedList<ApfsDirectory> actual = dir.getSubDirectories(new ReverseAlphabeticalComparator());
		  ApfsDirectory[] expected = { home,applications };
		  assertArrayEquals(expected, actual.toArray());  
	  }
	   
	  @Test
	  public void getFilesHomeTest() {
			ApfsDirectory dir = home;
			LinkedList<ApfsFile> actual = dir.getFiles(new ReverseAlphabeticalComparator());
			ApfsFile[] expected = { b };
			assertArrayEquals(expected, actual.toArray());
	  }	 
}
