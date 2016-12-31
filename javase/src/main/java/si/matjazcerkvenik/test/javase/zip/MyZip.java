package si.matjazcerkvenik.test.javase.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class MyZip {

	public static void main(String[] args) throws Exception {
		MyZip z = new MyZip();
		z.zip1();
	}

	public void zip1() throws Exception {

		// input file

		FileInputStream in = new FileInputStream("testfile.log");
		// out put file

		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				"testfile.zip"));
		// name the file inside the zip file

		out.putNextEntry(new ZipEntry("zippedfile.txt"));

		byte[] b = new byte[1024];

		int count;

		while ((count = in.read(b)) > 0) {
			System.out.println();

			out.write(b, 0, count);
		}
		out.close();
		in.close();

	}

	public void zip2() throws IOException {
		System.out.print("Please enter file name to zip : ");
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		String filesToZip = input.readLine();
		File f = new File(filesToZip);
		if (!f.exists()) {
			System.out.println("File not found.");
			System.exit(0);
		}
		System.out.print("Please enter zip file name : ");
		String zipFileName = input.readLine();
		if (!zipFileName.endsWith(".zip"))
			zipFileName = zipFileName + ".zip";
		byte[] buffer = new byte[18024];
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					zipFileName));
			out.setLevel(Deflater.DEFAULT_COMPRESSION);
			FileInputStream in = new FileInputStream(filesToZip);
			out.putNextEntry(new ZipEntry(filesToZip));
			int len;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			out.closeEntry();
			in.close();
			out.close();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			System.exit(0);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			System.exit(0);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(0);
		}
	}

	final int BUFFER = 2048;

	/**
	 * http://java.sun.com/developer/technicalArticles/Programming/compression/
	 */
	public void unzip1() {
		try {
			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream("testfile.zip");
			ZipInputStream zis = new ZipInputStream(
					new BufferedInputStream(fis));
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				System.out.println("Extracting: " + entry);
				int count;
				byte data[] = new byte[BUFFER];
				// write the files to the disk
				FileOutputStream fos = new FileOutputStream(entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = zis.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
			}
			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void unzip2() {
		try {
			BufferedOutputStream dest = null;
			BufferedInputStream is = null;
			ZipEntry entry;
			ZipFile zipfile = new ZipFile("testfile.zip");
			Enumeration e = zipfile.entries();
			while (e.hasMoreElements()) {
				entry = (ZipEntry) e.nextElement();
				System.out.println("Extracting: " + entry);
				is = new BufferedInputStream(zipfile.getInputStream(entry));
				int count;
				byte data[] = new byte[BUFFER];
				FileOutputStream fos = new FileOutputStream(entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = is.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void unzip3() {
		try {
			BufferedOutputStream out = null;
			ZipInputStream in = new ZipInputStream(new BufferedInputStream(
					new FileInputStream("")));
			ZipEntry entry;
			while ((entry = in.getNextEntry()) != null) {
				int count;
				byte data[] = new byte[1000];
				out = new BufferedOutputStream(new FileOutputStream("out.txt"),
						1000);
				while ((count = in.read(data, 0, 1000)) != -1) {
					out.write(data, 0, count);
				}
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
