import java.util.ArrayList;
import java.util.List;

abstract public class ASearchNode {
	ASearchNode _prev;
	TopSpinPuzzleState _currentProblemState;

	public List<ASearchNode> getNeighbors() {
		List<ASearchNode> neighbors = new ArrayList<ASearchNode>();
		List<TopSpinPuzzleState> neighborStates = _currentProblemState.getNeighborStates();

		for(TopSpinPuzzleState state : neighborStates) {
			ASearchNode newNode = createSearchNode(state);
			neighbors.add(newNode);
		}
		return neighbors;
	}

	public boolean isGoal() {
		return _currentProblemState.isGoalState();
	}

	public TopSpinPuzzleMove getLastMove() {
		return _currentProblemState.getStateLastMove();
	}

	@Override
	public boolean equals
			(
					Object o
			) {
		if (this == o) return true;
		if (!(o instanceof ASearchNode))
			return false;

		ASearchNode that = (ASearchNode) o;
		return _currentProblemState.equals(that._currentProblemState);
	}

	@Override
	public int hashCode() {
		return _currentProblemState.hashCode();
	}

	abstract public double H();

	abstract public double G();

	abstract public double F();

	abstract public int Depth();

	abstract public ASearchNode createSearchNode(TopSpinPuzzleState currentProblemState);

}
