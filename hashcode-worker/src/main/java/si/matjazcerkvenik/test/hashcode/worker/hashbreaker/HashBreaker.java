package si.matjazcerkvenik.test.hashcode.worker.hashbreaker;

import java.util.Calendar;

import si.matjazcerkvenik.test.hashcode.worker.model.Result;
import si.matjazcerkvenik.test.hashcode.worker.model.Task;

public class HashBreaker {
	
	public String word = "matjaz";
	
	public long counter = 0;
		
	public boolean running = true;
	
	public HashAlgorithm algorithm = null;
	
	public String[] chars = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
			"n", "o", "p", "q", "r", "s", "t", "u", "v", "z", "x", "y", "w",
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			"N", "O", "P", "Q", "R", "S", "T", "U", "V", "Z", "X", "Y", "W"};
	
	
	
	public HashBreaker(Task task) {
		this.word = task.getSearch();
		if (task.getAlgorithm().equals("MD5")) {
			algorithm = new MD5();
		} else if (task.getAlgorithm().equals("dummy")) {
			algorithm = new Dummy();
		} else {
			algorithm = new Hashcode();
		}
		chars = new String[task.getChars().length()];
		for (int i = 0; i < chars.length; i++) {
			chars[i] = task.getChars().substring(i, i+1);
		}
	}

	public Result process() {
		
		if (algorithm instanceof Dummy) {
			try {
				Thread.sleep(30*1000);
			} catch (InterruptedException e) {
			}
			Result r = new Result();
			r.setStatus("-- FINISHED --");
			r.setResult("TODO");
			r.setDuration(30);
			r.setCount(counter);
			
			return r;
		}
		
		String wHash = algorithm.encode(word);
		
		System.out.println("Searching: " + word + " with hash: " + wHash);
		long startTime = Calendar.getInstance().getTimeInMillis()/1000;
		
		int[] array = initArray();
		
		String s = "";
		
		while (s.length() < word.length() + 1) {
			
			array[0]++;
			
			for (int i = 0; i < array.length; i++) {
				
				if (array[i] != -1) {
					
					if (array[i] == chars.length) {
						array[i] = 0;
						if (i != array.length - 1) {
							array[i+1]++;
						}
					}
					
				}
				
				
			}
			
			s = getFullString(array);
			
			//System.out.println("" + s);
			
			if (wHash.equals(algorithm.encode(s))) {
				System.out.println("FOUND["+counter+"]: " + s);
			}
			
			// print counter every millionth
			counter++;
			if (counter % 10000000 == 0) {
				System.out.println("count: " + counter/1000000 + "M");
			}
			
		}
		
		long endTime = Calendar.getInstance().getTimeInMillis()/1000;
		System.out.println("Elapsed time: " + (endTime - startTime) + " seconds");
		System.out.println("-- FINISHED --");
		
		Result r = new Result();
		r.setStatus("-- FINISHED --");
		r.setResult("TODO");
		r.setDuration(endTime - startTime);
		r.setCount(counter);
		
		return r;
		
	}
	
	public int[] initArray() {
		int[] array = new int[chars.length];
		for (int i = 0; i < array.length; i++) {
			array[i] = -1;
		}
		return array;
	}
	
	
	public String getFullString(int[] array) {
		String s = "";
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] != -1) {
				s += chars[array[i]];
			}
		}
		return s;
		
	}
	
}
