import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * see: https://adventofcode.com/2023/day/04
 */
public class Y23Day04 {
 
	/*
	 * example input: 
	 * 
	 * Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
	 * Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
	 * Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
	 * Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
	 * Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
	 * Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
	 * 
	 */

	private static final String INPUT_RX   = "^Card ([0-9]+): ([ 0-9][0-9]) ([ 0-9][0-9]) ([ 0-9][0-9]) ([ 0-9][0-9]) ([ 0-9][0-9]) | ([ 0-9][0-9]) ([ 0-9][0-9]) ([ 0-9][0-9]) ([ 0-9][0-9]) ([ 0-9][0-9]) ([ 0-9][0-9]) ([ 0-9][0-9]) ([ 0-9][0-9])$";
	
	public static record InputData(int cardnum, Set<Integer> winNums, Set<Integer> ownNums) {}
	
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
				int cardNum = Integer.parseInt(line.replaceFirst(INPUT_RX, "$1"));
				Set<Integer> winNums = new HashSet();
				winNums.add(Integer.parseInt(line.replaceFirst(INPUT_RX, "$2")));
				winNums.add(Integer.parseInt(line.replaceFirst(INPUT_RX, "$3")));
				winNums.add(Integer.parseInt(line.replaceFirst(INPUT_RX, "$4")));
				winNums.add(Integer.parseInt(line.replaceFirst(INPUT_RX, "$5")));
				winNums.add(Integer.parseInt(line.replaceFirst(INPUT_RX, "$6")));
				
				Set<Integer> ownNums = new HashSet();
				ownNums.add(Integer.parseInt(line.replaceFirst(INPUT_RX, "$7")));
				winNums.add(Integer.parseInt(line.replaceFirst(INPUT_RX, "$8")));
				winNums.add(Integer.parseInt(line.replaceFirst(INPUT_RX, "$9")));
				winNums.add(Integer.parseInt(line.replaceFirst(INPUT_RX, "$10")));
				winNums.add(Integer.parseInt(line.replaceFirst(INPUT_RX, "$11")));
				winNums.add(Integer.parseInt(line.replaceFirst(INPUT_RX, "$12")));
				winNums.add(Integer.parseInt(line.replaceFirst(INPUT_RX, "$13")));
				winNums.add(Integer.parseInt(line.replaceFirst(INPUT_RX, "$14")));
				
				return new InputData(cardNum, winNums, ownNums);
			}
			else {
				throw new RuntimeException("invalid line '"+line+"'");
			}
		}
	}

	
	public static void mainPart1(String inputFile) {
		for (InputData data:new InputProcessor(inputFile)) {
			System.out.println(data);
		}
	}

	
	public static void mainPart2(String inputFile) {
	}

	

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("--- PART I ---");
		mainPart1("exercises/day04/Feri/input-example.txt");
//		mainPart1("exercises/day04/Feri/input.txt");
		System.out.println("---------------");
		System.out.println("--- PART II ---");
		mainPart2("exercises/day04/Feri/input-example.txt");
//		mainPart2("exercises/day04/Feri/input.txt");     
		System.out.println("---------------");    // 
	}
	
}
