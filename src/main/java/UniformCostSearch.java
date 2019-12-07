import java.util.HashSet;
import java.util.PriorityQueue;

public class UniformCostSearch extends ASearch {
    // Define lists here ...
    private PriorityQueue<ASearchNode> open_list;
    HashSet<ASearchNode> open_list_hash;
    HashSet<ASearchNode> closed_list_hash;

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
        open_list_hash = new HashSet<>();
        closed_list_hash = new HashSet<>();
    }


    @Override
    public ASearchNode getOpen(ASearchNode node) {
        return isOpen(node) ? node : null;
    }

    @Override
    public boolean isOpen(ASearchNode node) {
        return open_list_hash.contains(node);
    }

    @Override
    public boolean isClosed(ASearchNode node) {
        return closed_list_hash.contains(node);
    }

    @Override
    public void addToOpen(ASearchNode node) {
        open_list.add(node);
        open_list_hash.add(node);
    }

    @Override
    public void addToClosed(ASearchNode node) {
        closed_list_hash.add(node);
    }

    @Override
    public int openSize() {
        return open_list.size();
    }

    @Override
    public ASearchNode getBest() {
        ASearchNode bestNode = open_list.poll();
        open_list_hash.remove(bestNode);
        return bestNode;
    }
}