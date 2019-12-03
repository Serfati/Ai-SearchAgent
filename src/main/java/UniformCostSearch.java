import java.util.ArrayList;
import java.util.PriorityQueue;

public class UniformCostSearch extends ASearch {
    // Define lists here ...
    private PriorityQueue<ASearchNode> open_list;
    private ArrayList<ASearchNode> closed_list;

    @Override
    public String getSolverName() {
        return "UCS";
    }

    @Override
    public ASearchNode createSearchRoot(TopSpinPuzzleState problemState) {
        return new BlindSearchNode(problemState);
    }

    @Override
    public void initLists() {
        open_list = new PriorityQueue<>((o1, o2) -> (int) (o1.G()-o2.G()));
        closed_list = new ArrayList<>();
    }

    @Override
    public ASearchNode getOpen(ASearchNode node) {
        return open_list.stream().filter(node::equals).findFirst().orElse(null);
    }

    @Override
    public boolean isOpen(ASearchNode node) {
        return open_list.contains(node);
    }

    @Override
    public boolean isClosed(ASearchNode node) {
        return closed_list.contains(node);
    }

    @Override
    public void addToOpen(ASearchNode node) {
        open_list.add(node);
    }

    @Override
    public void addToClosed(ASearchNode node) {
        closed_list.add(node);
    }

    @Override
    public int openSize() {
        return open_list.size();
    }

    @Override
    public ASearchNode getBest() {
        return open_list.poll();
    }
}