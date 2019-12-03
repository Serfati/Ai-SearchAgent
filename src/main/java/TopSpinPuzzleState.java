import java.util.ArrayList;
import java.util.List;

public class TopSpinPuzzleState {
	TopSpinPuzzle _problem;        // The original problem
	int[] _TopSpinPuzzle;    // Current state
	TopSpinPuzzleMove _lastMove;        // The last move made


	public TopSpinPuzzleState
			(
					TopSpinPuzzle problem,
					int[] TopSpinPuzzle,
					TopSpinPuzzleMove lastMove
			) {
		_problem = problem;
		_TopSpinPuzzle = TopSpinPuzzle.clone();
		_lastMove = lastMove;
	}


	public List<TopSpinPuzzleState> getNeighborStates() {
		List<TopSpinPuzzleState> neighborStates = new ArrayList<TopSpinPuzzleState>();
		List<TopSpinPuzzleMove> legalMoves = getLegalMoves();

		for(TopSpinPuzzleMove move : legalMoves) {
			TopSpinPuzzleState newState = getChildState(move);
			neighborStates.add(newState);
		}
		return neighborStates;
	}

	private List<TopSpinPuzzleMove> getLegalMoves() {
		TopSpinPuzzleMove leftMove = TopSpinPuzzleMove.LEFT;
		TopSpinPuzzleMove rightMove = TopSpinPuzzleMove.RIGHT;
		TopSpinPuzzleMove swapMove = TopSpinPuzzleMove.SWAP;
		List<TopSpinPuzzleMove> newList = new ArrayList<TopSpinPuzzleMove>();
		newList.add(leftMove);
		newList.add(rightMove);
		newList.add(swapMove);
		return newList;
	}

	public TopSpinPuzzle Problem() {
		return _problem;
	}

	public boolean isGoalState() {
		int size = _problem._size;

		for(int cellIndex = 0; cellIndex < size; cellIndex++)
			if (_TopSpinPuzzle[cellIndex] != cellIndex)
				return false;
		return true;
	}

	public TopSpinPuzzleMove getStateLastMove() {
		return _lastMove;
	}

	public double getStateLastMoveCost() {
		if (_lastMove == TopSpinPuzzleMove.SWAP) {
			int startCellIndex = _problem._size / 2-2;
			int endCellIndex = startCellIndex+3;
			int cost = 0;
			for(int index = startCellIndex; index <= endCellIndex; index++)
				cost += _TopSpinPuzzle[index];
			return cost;
		}
		if (_lastMove == TopSpinPuzzleMove.RIGHT ||
				_lastMove == TopSpinPuzzleMove.LEFT)
			return 0;
		else
			return 0;
	}

	public TopSpinPuzzleState getChildState
			(
					TopSpinPuzzleMove topSpinPuzzleMove
			) {
		TopSpinPuzzleState newState = new TopSpinPuzzleState(_problem, _TopSpinPuzzle, topSpinPuzzleMove);
		newState.performMove(topSpinPuzzleMove);
		return newState;
	}

	public void performMove
			(
					TopSpinPuzzleMove topSpinPuzzleMove
			) {
		// Perform the action
		if (topSpinPuzzleMove == TopSpinPuzzleMove.LEFT)
			performLeftMove();
		else if (topSpinPuzzleMove == TopSpinPuzzleMove.RIGHT)
			performRightMove();
		else if (topSpinPuzzleMove == TopSpinPuzzleMove.SWAP)
			performSwapMove();
	}

	private void performLeftMove() {
		int cellZero = _TopSpinPuzzle[0];
		for(int cellIndex = 0; cellIndex < _problem.Size()-1; cellIndex++) {
			_TopSpinPuzzle[cellIndex] = _TopSpinPuzzle[cellIndex+1];
		}
		_TopSpinPuzzle[_problem.Size()-1] = cellZero;
	}

	private void performRightMove() {
		int lastCell = _TopSpinPuzzle[_problem.Size()-1];
		for(int cellIndex = _problem.Size()-1; cellIndex > 0; cellIndex--) {
			_TopSpinPuzzle[cellIndex] = _TopSpinPuzzle[cellIndex-1];
		}
		_TopSpinPuzzle[0] = lastCell;
	}

	private void performSwapMove() {
		int startCellIndex = _problem.Size() / 2-2;
		int endCellIndex = startCellIndex+3;
		int tempValue;

		tempValue = _TopSpinPuzzle[startCellIndex];
		_TopSpinPuzzle[startCellIndex] = _TopSpinPuzzle[endCellIndex];
		_TopSpinPuzzle[endCellIndex] = tempValue;

		tempValue = _TopSpinPuzzle[startCellIndex+1];
		_TopSpinPuzzle[startCellIndex+1] = _TopSpinPuzzle[endCellIndex-1];
		_TopSpinPuzzle[endCellIndex-1] = tempValue;
	}

	@Override
	public boolean equals
			(
					Object o
			) {
		if (this == o) return true;
		if (!(o instanceof TopSpinPuzzleState)) return false;

		TopSpinPuzzleState that = (TopSpinPuzzleState) o;

		return java.util.Arrays.equals(_TopSpinPuzzle, that._TopSpinPuzzle);

	}

	@Override
	public int hashCode() {
		return java.util.Arrays.hashCode(_TopSpinPuzzle);
	}


	private int[] getTopSpinPuzzleCopy() {
		int size = _problem._size;
		int[] newTopSpinPuzzle = new int[size];

		for(int index = 0; index < size; index++) {
			newTopSpinPuzzle[index] = _TopSpinPuzzle[index];
		}
		return newTopSpinPuzzle;
	}

	@Override
	public String toString() {
		int size = _problem._size;
		String toPrint = "| ";
		for(int index = 0; index < size; index++) {
			toPrint += _TopSpinPuzzle[index]+" | ";
		}
		return toPrint;
	}

}
