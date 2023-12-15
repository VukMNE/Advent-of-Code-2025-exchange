import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * see: https://adventofcode.com/2023/day/15
 */
public class Y23Day15 {

	/*
	 * Example:
	 * 
	 * rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7
	 * 
	 */

	private static final String INPUT_RX = "^(.*)$";
	
	public static record InputData(String[] words) {}
	
	public static class InputProcessor implements Iterable<InputData>, Iterator<InputData> {
		private Scanner scanner;
		public InputProcessor(String inputFile) {
			try {
				scanner = new Scanner(new File(inputFile));
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		@Override public Iterator<InputData> iterator() { return this; }
		@Override public boolean hasNext() { return scanner.hasNext(); }
		@Override public InputData next() {
			String line = scanner.nextLine().trim();
			while (line.length() == 0) {
				line = scanner.nextLine();
			}
			if (line.matches(INPUT_RX)) {
				String[] words = line.replaceFirst(INPUT_RX, "$1").split(",");
				return new InputData(words);
			}
			else {
				throw new RuntimeException("invalid line '"+line+"'");
			}
		}
	}

	static class HashCalc {
		int current;
		public HashCalc() {
			current = 0;
		}
		public void addAscii(char c) {
			current = ((current + c)*17)%256;
		}
		public int getHash() {
			return current;
		}
	}

	public static void mainPart1(String inputFile) {
		for (InputData data:new InputProcessor(inputFile)) {
			System.out.println(data);
			int sumHashes = 0;
			for (String word:data.words) {
				HashCalc hashCalc = new HashCalc();
				for (char c:word.toCharArray()) {
					hashCalc.addAscii(c);
				}
				System.out.println(word+" : "+hashCalc.getHash());
				sumHashes += hashCalc.getHash();
			}
			System.out.println("SUM HASHES: "+sumHashes);
		}
	}

	
	public static void mainPart2(String inputFile) {
	}


	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("--- PART I ---");
//		mainPart1("exercises/day15/Feri/input-example.txt");
		mainPart1("exercises/day15/Feri/input.txt");               
		System.out.println("---------------");                           
		System.out.println("--- PART II ---");
		mainPart2("exercises/day15/Feri/input-example.txt");
//		mainPart2("exercises/day15/Feri/input.txt");              
		System.out.println("---------------");    //
	}
	
}
