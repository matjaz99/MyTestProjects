package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;


public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		
		// read text from console
		//ReadStandardIO r=new ReadStandardIO();
		//r.readStandardIO_Method();
		
		// create new file
		//CreateFile cf = new CreateFile();
		//cf.createFile1();
		
		// create new file part II.
		FilePath fp = new FilePath();
		fp.createFile2();
		fp.constructingFilePath();
		
		// before reading from file first write something to it
		WriteFile wf = new WriteFile();
		wf.writeToFile();
		
		// read from file - char by char
		// why is that '?' at the end of line generated?
		ReadFile rf = new ReadFile();
		rf.readFromFile();
		
		// write to file again
		//WriteFile2 wf2 = new WriteFile2();
		//wf2.writeToFile();
		
		// write to file 3
		WriteFile3 wf3 = new WriteFile3();
		wf3.writeTextToFile();
		
		// read from file - line by line
		ReadFile2 rf2 = new ReadFile2();
		rf2.readFromFile();
		
		// get file size in bytes
		FileSize fs = new FileSize();
		fs.getFileSize();
		
		// count the lines in the document
		CountLines cl = new CountLines();
		cl.countNumberOfLinesInTheFile();
		
		// change a file timestamp
		// ne dela?????
		//ChangeTimestamp ct = new ChangeTimestamp();
		//ct.changeTheFilestamp();
		
		// create and delete temp file
		//MyTemporaryFile mtf = new MyTemporaryFile();
		//mtf.createMyTempFile();
		//mtf.deleteMyTempFile();
		
		// create directory
		CreateDirectory cd = new CreateDirectory();
		cd.createMyTestDirectory();
		
		// copy directory
		//CopyDirectory cpydir = new CopyDirectory();
		//cpydir.copyMyTestDirectory();
		
		// traversing files and directories
		TraversingFilesAndDirectories tfad = new TraversingFilesAndDirectories();
		tfad.traversingFiles();
		
		// get working directory
		GetCurrentDir gcd = new GetCurrentDir();
		gcd.getWorkingDirectory();
		
		// get last modification time of file or directory
		GetDirectoryAndFileModifiedTime gdafmt = new GetDirectoryAndFileModifiedTime();
		gdafmt.getDirFileModifiedTime();
		
		// list files in a directory
		DirectoryListing dl = new DirectoryListing();
		dl.dirlist("testfolder");
		
		// delete file
		DeletingFile df = new DeletingFile();
		df.deleteFile();
		
		// write to file
		WriteFile4 wf4 = new WriteFile4();
		wf4.writeToFile();
		
		// read file
		ReadFile3 rf3 = new ReadFile3();
		rf3.readTheFile();
		
		FilterFiles ff = new FilterFiles();
		ff.filterFiles();
		
		//ReadWrite rw = new ReadWrite();
		//rw.readOrWrite();
	}

}
