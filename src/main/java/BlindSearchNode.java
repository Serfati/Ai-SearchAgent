
public class BlindSearchNode extends ASearchNode {
    double _g;
    int _depth;

    public BlindSearchNode
            (
                    TopSpinPuzzleState currentProblemState
            ) {
        _prev = null;
        _currentProblemState = currentProblemState;
        _g = 0;
        _depth = 0;
    }

    public BlindSearchNode
            (
                    ASearchNode prev,
                    TopSpinPuzzleState currentProblemState,
                    double g,
                    int depth
            ) {
        _prev = prev;
        _currentProblemState = currentProblemState;
        _g = g;
        _depth = depth;
    }

    @Override
    public double H() {
        return 0;
    }

    @Override
    public double G() {
        return _g;
    }

    @Override
    public double F() {
        return _g;
    }

    public int Depth() {
        return _depth;
    }

    @Override
    public ASearchNode createSearchNode
            (
                    TopSpinPuzzleState currentProblemState
            ) {
        double g = _g+currentProblemState.getStateLastMoveCost();
        int depth = _depth+1;
        ASearchNode newNode = new BlindSearchNode(this, currentProblemState, g, depth);
        return newNode;
    }

}
