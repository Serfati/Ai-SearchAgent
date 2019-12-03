
public class HeuristicSearchNode extends BlindSearchNode {
	double _h;
	TopSpinPuzzleHeuristic _heuristic;

	public HeuristicSearchNode
			(
					TopSpinPuzzleState currentProblemState
			) {
		super(currentProblemState);
		_heuristic = currentProblemState.Problem().Heuristic();
		_h = _heuristic.getHeuristic(currentProblemState);
	}


	public HeuristicSearchNode
			(
					ASearchNode prev,
					TopSpinPuzzleState currentProblemState,
					double g,
					int depth,
					TopSpinPuzzleHeuristic heuristic
			) {
		super(prev, currentProblemState, g, depth);
		_heuristic = heuristic;
		_h = _heuristic.getHeuristic(currentProblemState);
	}

	@Override
	public double H() {
		return _h;
	}


	@Override
	public double F() {
		return _g+_h;
	}


	@Override
	public ASearchNode createSearchNode
			(
					TopSpinPuzzleState currentProblemState
			) {
		double g = _g+currentProblemState.getStateLastMoveCost();
		int depth = _depth+1;
		ASearchNode newNode = new HeuristicSearchNode(this, currentProblemState, g, depth, _heuristic);
		return newNode;
	}
}
