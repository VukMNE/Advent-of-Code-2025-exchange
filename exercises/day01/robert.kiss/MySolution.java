import java.util.ArrayList;
import java.util.List;

public class MySolution extends MySolutionBase {

    public MySolution(String inputFilename) {
        super(inputFilename);
    }

    private MySolution play1() {
		List<Integer> values = new ArrayList<Integer>();
		getInputLinesAsList().forEach(line -> {
			Integer cc[]={null,null};
			line.chars().boxed().forEach(c->{
				if (Character.isDigit(c)) {
					if (cc[0]==null) {
						cc[0]= c;
					}
					cc[1]= c;
				}
			});
			values.add(10*(cc[0]-48)+cc[1]-48);
		});
		System.out.println(values.stream().mapToInt(Integer::intValue).sum());
        return this;
	}

	private MySolution play2() {
		List<Integer> values = new ArrayList<Integer>();
		getInputLinesAsList().forEach(line -> {
			line = this.replacewords(line);
			Integer cc[]={null,null};
			line.chars().boxed().forEach(c->{
				if (Character.isDigit(c)) {
					if (cc[0]==null) {
						cc[0]= c;
					}
					cc[1]= c;
				}
			});
			values.add(10*(cc[0]-48)+cc[1]-48);
		});
		System.out.println(values.stream().mapToInt(Integer::intValue).sum());
        return this;
	}

	private String replacewords(String line) {
		//System.out.println(line);
		line = line.replaceAll("one", "one1one");
		line = line.replaceAll("two", "two2two");
		line = line.replaceAll("three", "three3three");
		line = line.replaceAll("four", "four4four");
		line = line.replaceAll("five", "five5five");
		line = line.replaceAll("six", "six6six");
		line = line.replaceAll("seven", "seven7seven");
		line = line.replaceAll("eight", "eight8eight");
		line = line.replaceAll("nine", "nine9nine");
		//System.out.println(line);
		return line;
	}

	public static void main(String args[]) {
		try {
            new MySolution("sample1.txt").play1();
            new MySolution("sample2.txt").play2();
            new MySolution("input.txt").play1().play2();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
