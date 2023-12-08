import jdk.internal.jshell.tool.JShellToolBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MySolution extends MySolutionBase {


	private String insturctions;
	private final Map<String, Node> nodes = new TreeMap<>();

	public MySolution(String inputFilename) {
        super(inputFilename);
		this.insturctions = getInputLinesAsList().get(0);
		for (String line:getInputLinesAsList()) {
			var node = Node.createNode(line);
			if (node != null) {
				this.nodes.put(node.pos, node);
			}
		}

    }

    private MySolution play1() {
		int step = 0;
		var myNode = this.nodes.get("AAA");
		while (!myNode.pos.equals("ZZZ")) {
			var myStep = insturctions.charAt(step % insturctions.length());
			//System.out.println(myNode +"  "+myStep);
			myNode = nodes.get(myNode.getNext(myStep));
			step +=1;
		}
		System.out.println(step);
        return this;
	}



	Map<String,Jump> jumps = new TreeMap<>();
	Jump getJump(String nodeid, long step) {
		int instructionPointer = (int)(step % insturctions.length());
		if (jumps.containsKey(nodeid+instructionPointer)) {
			return jumps.get(nodeid+instructionPointer);
		}
		int dist = 0;
		var myNode = this.nodes.get(nodeid);
		while (dist==0||!myNode.pos.endsWith("Z")) {
			var myStep = insturctions.charAt((int)((step+dist) % insturctions.length()));
			//System.out.println(myNode +"  "+myStep);
			myNode = nodes.get(myNode.getNext(myStep));
			dist +=1;
		}
		jumps.put(nodeid+instructionPointer,new Jump(dist,myNode.pos));


	};

	private Map<NodePos,NodePos> nextPositions = new TreeMapt<>();
	private MySolution play2() {
		long step = 0L;
		var myPositions = this.nodes.values().stream().filter(node->node.pos.endsWith("A")).map(node->new NodePos(0L,node)).collect(Collectors.toList());
		//System.out.println(myNodes);
		long minStep = 0L;
		long maxStep = 1L;
		thile (minStep<maxStep) {
			for (int i=0;i<myPositions.size();i++) {
				var myPosition = myPositions.get(i);
				thile (myPosition.step <maxStep){
					checkNextPositionExists(myPositions.get(i));
					myPosition = nextPositions.get(myPosition);
				}
				myPositions.set(i,nodes.get(myPositions.get(i).getNext(myStep)));
			}

		}

		while (myNodes.stream().anyMatch(node->!node.pos.endsWith("Z"))) {
			var myStep = insturctions.charAt((int)(step % insturctions.length()));
			for (int i=0;i<myNodes.size();i++) {
				myNodes.set(i,nodes.get(myNodes.get(i).getNext(myStep)));
			}
			//System.out.println(myNodes);
			step +=1;
		}
		System.out.println(step);
		return this;
	}

	public static void main(String args[]) {
		try {
            new MySolution("sample.txt").play1().play2();
			new MySolution("sample2.txt").play2();
            new MySolution("input.txt").play1().play2();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
