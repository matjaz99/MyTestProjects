package si.matjazcerkvenik.test.hadoop;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class CopyFileToHdfs {

	public static void main(String[] args) throws Exception {
		
		// Source file in the local file system
		String localSrc = "/Users/matjaz/Developer/git-workspace/MyTestProjects/hadoophdfs/test.txt";
		// Destination file in HDFS
		String dst = "/my_storage/test-" + System.currentTimeMillis() + ".txt";
		
		// Input stream for the file in local file system to be written to HDFS
		InputStream in = new BufferedInputStream(new FileInputStream(localSrc));

		// Get configuration of Hadoop system
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://orion.home.si:9000");
		System.out.println("Connecting to -- " + conf.get("fs.defaultFS"));

		// Destination file in HDFS
		FileSystem fs = FileSystem.get(URI.create(dst), conf);
		OutputStream out = fs.create(new Path(dst));

		// Copy file from local to HDFS
		IOUtils.copyBytes(in, out, 4096, true);

		System.out.println(dst + " copied to HDFS");

	}

}
