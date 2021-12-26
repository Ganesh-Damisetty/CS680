package edu.umb.cs680.hw14;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class FileSystemTest {
	
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
		String[] dirInfo = { parentName,Boolean.toString(d.isDirectory()), d.getName(), Integer.toString(d.getSize()),
				d.getCreationTime().toString(),Integer.toString(d.getTotalSize()), Integer.toString(d.countChildren()),
				ApfsFileSystem.getFileSystemName(), Integer.toString(ApfsFileSystem.getCapacity()), d.getOwnerName(),
				d.getLastModified().toString() };
			return dirInfo;
    }

	@Test
	public void verifyDirectoryEqualityHome() {
		String[] expected = {"root", "true", "home", "0", localTime.toString(),"90", "3", "Apfs", "500", "ApfsFile",localTime.toString() };
		ApfsDirectory actual = home;
		assertArrayEquals(expected, dirToStringArray(actual));
		}
}
