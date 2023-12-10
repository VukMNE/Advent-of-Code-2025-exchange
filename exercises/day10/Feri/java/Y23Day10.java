import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * see: https://adventofcode.com/2023/day/10
 */
public class Y23Day10 {
 
	/*
	 * Example:
	 * 
	 * 7-F7-
	 * .FJ|7
	 * SJLL7
	 * |F--J
	 * LJ.LJ
	 * 
	 */

	private static final String INPUT_RX = "^([-F7LJ|.S]+)$";
	
	public static record InputData(String row) {}
	
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
				String row = line.replaceFirst(INPUT_RX, "$1");
				return new InputData(row);
			}
			else {
				throw new RuntimeException("invalid line '"+line+"'");
			}
		}
	}

	
	static String DIRS            = ">v<^";
	static int[]  DIR_ADD_X 	  = { 1,   0,  -1,   0};
	static int[]  DIR_ADD_Y 	  = { 0,   1,   0,  -1};
	
	static int DIR_EAST = 0;
	static int DIR_SOUTH = 0;
	static int DIR_WEST = 0;
	static int DIR_NORTH = 0;
	
	static int DIR_ROT_LEFT = -1;
	static int DIR_ROT_RIGHT = 1;
	
	static String[] NEXT_DIR_CHAR   = {  // "<LEFT><STRAIGHT><RIGHT>"
			// >   
			"J-7",
			// v
			"L|J",
			// <
			"F-L",
			// ^
			"7|F"
		};
	static int nextDir(int currentDir, char road) {
		int rotPos = NEXT_DIR_CHAR[currentDir].indexOf(road);
		if (rotPos == -1) {
			return -1;
		}
		return (currentDir+rotPos+3)%4;
	}
	
	
	static record Pos(int x, int y) {
		Pos move(int dir) {
			return new Pos(x+DIR_ADD_X[dir], y+DIR_ADD_Y[dir]);
		}
	}
	
	public static class World {
		List<String> field;
		int maxX;
		int maxY;
		Pos startPos;
		Pos currentPos;
		int dir;
		int ticks;
		public World() {
			this.field = new ArrayList<>();
			this.ticks = 0;
		}
		public char getRoad(Pos pos) {
			return getRoad(pos.x, pos.y);
		}
		public char getRoad(int x, int y) {
			if ((x<0) || (x>=maxX) || (y<0) || (y>=maxY)) {
				return '.';
			}
			return field.get(y).charAt(x);
		}
		public void addRow(String row) {
			field.add(row);
			maxY = field.size();
			if (row.contains("S")) {
				int x = row.indexOf('S');
				startPos = new Pos(x, maxY-1);
				currentPos = startPos;
				maxX = row.length();
				for (dir = DIR_EAST; dir<=DIR_NORTH; dir++) {
					char road = getRoad(startPos.move(dir));
					if (nextDir(dir, road) != -1) {
						break;
					}
				}
			}
		}
		public boolean reachedStart() {
			return getRoad(currentPos) == 'S';
		}
		public void tick() {
			ticks++;
			System.out.println("MOVE "+currentPos+" "+DIRS.charAt(dir));
			if (dir == -1) {
				throw new RuntimeException("invalid move at pos "+currentPos);
			}
			currentPos = currentPos.move(dir);
			char road = getRoad(currentPos);
			dir = nextDir(dir, road);
		}
	}
	
	public static void mainPart1(String inputFile) {
		World world = new World();
		for (InputData data:new InputProcessor(inputFile)) {
			System.out.println(data);
			world.addRow(data.row);
		}
		world.tick();
		while (!world.reachedStart()) {
			world.tick();
		}
		System.out.println("ticks="+world.ticks);
		System.out.println("MAX_DIST: "+world.ticks/2);
	}
	
	
	public static void mainPart2(String inputFile) {
	}

	

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("--- PART I ---");
//		mainPart1("exercises/day10/Feri/input-example.txt");
		mainPart1("exercises/day10/Feri/input.txt");               
		System.out.println("---------------");                           
		System.out.println("--- PART II ---");
		mainPart2("exercises/day10/Feri/input-example-2.txt");
//		mainPart2("exercises/day10/Feri/input.txt");                
		System.out.println("---------------");    //
	}
	
}
