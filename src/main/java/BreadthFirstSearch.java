import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

public class BreadthFirstSearch extends ASearch {
    private Queue<BlindSearchNode> open_list;
    HashMap<ASearchNode,ASearchNode> open_list_hash;
    HashSet<ASearchNode> closed_list_hash;


    @Override
    public String getSolverName() {
        return "BFS";
    }

    @Override
    public ASearchNode createSearchRoot(TopSpinPuzzleState problemState) {
        return new BlindSearchNode(problemState);
    }

    @Override
    public void initLists() {
        open_list = new ArrayDeque<>();
        open_list_hash = new HashMap<>();
        closed_list_hash = new HashSet<>();
    }

    @Override
    public ASearchNode getOpen(ASearchNode node) {
        return isOpen(node) ? open_list_hash.get(node) : null;
    }

    @Override
    public boolean isOpen(ASearchNode node) {
        return open_list_hash.containsKey(node);
    }

    @Override
    public boolean isClosed(ASearchNode node) {
        return closed_list_hash.contains(node);
    }

    @Override
    public void addToOpen(ASearchNode node) {
        open_list.add((BlindSearchNode) node);
        open_list_hash.put(node,node);
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