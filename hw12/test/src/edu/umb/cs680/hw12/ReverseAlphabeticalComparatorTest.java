package edu.umb.cs680.hw12;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw12.ApfsDirectory;
import edu.umb.cs680.hw12.ApfsElement;
import edu.umb.cs680.hw12.ApfsFile;
import edu.umb.cs680.hw12.ApfsLink;

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
	  ApfsLink x = new ApfsLink(home, "x", 0, localTime, "MyFile", localTime, applications);
	  ApfsLink y = new ApfsLink(code, "y", 0, localTime,"ApfsFile",localTime,a);

	@Test
	public void getSubDirectoriesTest() {
		ApfsDirectory dir = root;
		LinkedList<ApfsDirectory> actual = dir.getSubDirectories((ApfsElement o1, ApfsElement o2) -> 
			 o2.getName().compareTo(o1.getName()));
		ApfsDirectory[] expected = { home, applications };
		assertArrayEquals(expected, actual.toArray());
	}

	@Test
	public void getFilesTest() {
		ApfsDirectory dir = home;
		LinkedList<ApfsFile> actual = dir.getFiles((ApfsElement o1, ApfsElement o2) -> 
			 o2.getName().compareTo(o1.getName()));
		ApfsFile[] expected = { b};
		assertArrayEquals(expected, actual.toArray());
	}
	
	
	  @Test
	  public void GetChildrenTest() {
		 ApfsDirectory dir = home;
		 LinkedList<ApfsElement> actual = dir.getChildren((ApfsElement o1, ApfsElement o2) -> 
		      o2.getName().compareTo(o1.getName()));
		 ApfsElement[] expected = { x,code,b  };
		 assertArrayEquals(expected, actual.toArray());
		}

}
