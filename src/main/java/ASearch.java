import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract public class ASearch {
	public List<TopSpinPuzzleMove> solve
			(
					TopSpinPuzzle problem
			) {
		TopSpinPuzzleState problemState = problem.StartState();
		ASearchNode goal = abstractSearch(problemState);
		List<TopSpinPuzzleMove> solution = goalNodeToSolutionPath(goal);
		return solution;
	}

	private ASearchNode abstractSearch
			(
					TopSpinPuzzleState problemState
			) {
		initLists();
		ASearchNode Vs = createSearchRoot(problemState);
		ASearchNode current = null;
		addToOpen(Vs);

		while(openSize() > 0) {
			current = getBest();
			if (current.isGoal())
				return current;
			List<ASearchNode> neighbors = current.getNeighbors();
			for(ASearchNode Vn : neighbors) {
				if (isClosed(Vn))
					continue;

				if (!isOpen(Vn) || getOpen(Vn).G() > Vn.G())
					addToOpen(Vn);
			}
			addToClosed(current);
		}
		return null;
	}

	private List<TopSpinPuzzleMove> goalNodeToSolutionPath
			(
					ASearchNode goal
			) {
		if (goal == null)
			return null;
		ASearchNode currentNode = goal;
		List<TopSpinPuzzleMove> solutionPath = new ArrayList<>();
		while(currentNode._prev != null) {
			solutionPath.add(currentNode.getLastMove());
			currentNode = currentNode._prev;
		}
		Collections.reverse(solutionPath);
		return solutionPath;
	}

	abstract public String getSolverName();

	abstract public void initLists();

	abstract public ASearchNode getOpen(ASearchNode node);

	abstract public boolean isOpen(ASearchNode node);

	abstract public boolean isClosed(ASearchNode node);

	abstract public ASearchNode createSearchRoot(TopSpinPuzzleState problemState);

	abstract public void addToOpen(ASearchNode node);

	abstract public void addToClosed(ASearchNode node);

	abstract public int openSize();

	abstract public ASearchNode getBest();


}
