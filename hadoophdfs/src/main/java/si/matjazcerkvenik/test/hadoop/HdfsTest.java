package si.matjazcerkvenik.test.hadoop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

/**
 * Browse files in HDFS
 * 
 * @author matjaz
 *
 */
public class HdfsTest {

	public static void main(String[] args) throws Exception {

		String dst = "/my_storage";
		Path my_storage_dir = new Path("/my_storage");

		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://orion.home.si:9000");
		
		FileSystem hdfs = FileSystem.get(URI.create(dst), conf);
		
		System.out.println("Connected to: " + conf.get("fs.defaultFS"));
		
		if (hdfs.isDirectory(my_storage_dir)) {
			System.out.println(my_storage_dir + " is directory");
		}

		System.out.println("Files in " + my_storage_dir);
		RemoteIterator<LocatedFileStatus> listFiles = hdfs.listFiles(my_storage_dir, true);
		while (listFiles.hasNext()) {
			LocatedFileStatus lfs = (LocatedFileStatus) listFiles.next();
			System.out.println("\t" + lfs.getPath().toString());

		}
		
		// home directory
		Path homeDir = hdfs.getHomeDirectory();
		System.out.println("Home dir: " + homeDir);
		
		// working directory
		Path workingDir = hdfs.getWorkingDirectory();
		System.out.println("Working dir: " + workingDir);
		
		
		Path testDir = new Path("/test_folder");
		testDir = Path.mergePaths(workingDir, testDir);
		
		// delete folder
		if (hdfs.exists(testDir)) {
			hdfs.delete(testDir, true);
			System.out.println("Deleted folder: " + testDir);
		}
		
		// create folder
		hdfs.mkdirs(testDir);
		System.out.println("Created folder: " + testDir);
		
		//Copying File from local to HDFS
		Path localFilePath = new Path("/Users/matjaz/Developer/git-workspace/MyTestProjects/hadoophdfs/test.txt");
		Path hdfsFilePath= new Path(testDir+"/test.txt");

		hdfs.copyFromLocalFile(localFilePath, hdfsFilePath);
		System.out.println("File copied to HDFS");
		
		// print files
		System.out.println("Files in " + testDir);
		RemoteIterator<LocatedFileStatus> listFiles1 = hdfs.listFiles(testDir, true);
		while (listFiles1.hasNext()) {
			LocatedFileStatus lfs = (LocatedFileStatus) listFiles1.next();
			System.out.println("\t" + lfs.getPath().toString());

		}
		
		//Copying File from HDFS to local
		localFilePath=new Path("/Users/matjaz/Developer/git-workspace/MyTestProjects/hadoophdfs/test-from.txt");
		hdfs.copyToLocalFile(hdfsFilePath, localFilePath);
		System.out.println("Files copied from HDFS");
		
		// delete a file
		hdfs.delete(hdfsFilePath, true);
		
		RemoteIterator<LocatedFileStatus> listFiles2 = hdfs.listFiles(testDir, true);
		if (listFiles2.hasNext()) {
			System.out.println(testDir + " is not empty");
		} else {
			System.out.println(testDir + " is empty");
		}
		
		// create a file in HDFS
		Path newFilePath = new Path(testDir + "/newFile.txt");
		hdfs.createNewFile(newFilePath);

		// write data to a HDFS file
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=5; i++) {
			sb.append("Data");
			sb.append(i);
			sb.append("\n");
		}

		byte[] byt = sb.toString().getBytes();
		FSDataOutputStream fsOutStream = hdfs.create(newFilePath);
		fsOutStream.write(byt);
		fsOutStream.close();
		System.out.println("Written data to HDFS file");
		
		//Reading data From HDFS File
		System.out.println("Reading from HDFS file " + newFilePath);

		BufferedReader bfr = new BufferedReader(new InputStreamReader(hdfs.open(newFilePath)));

		String str = null;
		while ((str = bfr.readLine())!= null) {
			System.out.println(str);
		}
		
		// print hdfs statistics
		FileSystem.printStatistics();
		
		hdfs.close();
		
		// https://tutorials.techmytalk.com/2014/08/16/hadoop-hdfs-java-api/
		
	}

}
