package si.matjazcerkvenik.test.javase.rename.files;

import java.io.File;

public class RenameMp3 {

	public static void main(String[] args) {
		
		File directory = new File("./test");
		File[] files = directory.listFiles();

		for (int i = 0; i < files.length; i++) {

			String fileName = files[i].getName();
			// System.out.println(fileName);
			
			if (fileName.contains("DS_Store")) {
				continue;
			}

			String newName = getNewName3(fileName);
			System.out.println(i + ": " + fileName + " -> " + newName);

			File f = new File("./test/" + newName);
			files[i].renameTo(f);

		}

	}

	
	public static String getNewName1(String name) {
//		String name = "099 El Amor Eres Tu (Carlos Manuel - Version Salsa).mp3";
		
		String name2 = name.substring(4);
//		System.out.println(name2);
		
		int i1 = name2.indexOf("(");
		int i2 = name2.indexOf(")");
		
		String pesem = name2.substring(0, i1).trim();
//		System.out.println(pesem);
		
		String avtor = name2.substring(i1+1, i2).trim();
//		System.out.println(avtor);
		
		return avtor + " - " + pesem + ".mp3";
	}
	
	public static String getNewName2(String name) {
//		String name = "009.Amores Como el Nuestro - Jerry Rivera.mp3";
		
		String name2 = name.substring(4, name.length()-4);
		System.out.println(name2);
		
		String[] temp = name2.split("-");
		
		String pesem = temp[0].trim();
//		System.out.println(pesem);
		
		String avtor = temp[1].trim();
//		System.out.println(avtor);
		
		return avtor + " - " + pesem + ".mp3";
	}
	
	public static String getNewName3(String name) {
		
		name = name.replace("..mp3", ".mp3");
		
		String name2 = name.substring(4, name.length()-4);
		System.out.println(name2);
		
		String[] temp = name2.split("-");
		
		String pesem = temp[0].trim();
//		System.out.println(pesem);
		
		String avtor = temp[1].trim();
//		System.out.println(avtor);
		
		return avtor + " - " + pesem + ".mp3";
	}
}
