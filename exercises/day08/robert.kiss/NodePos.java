import java.util.Objects;

public class NodePos {
    public long pos;
    public Node node;
    public NodePos nextpos;

    public NodePos(long step, Node node) {
        this.step = step;
        this.node = node;
    }

    @Override
    public int hashCode() {
        return node.hashCode()+Long.hashCode(this.step);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodePos nodePos = (NodePos) o;
        return step == nodePos.step && Objects.equals(node, nodePos.node);
    }
}
