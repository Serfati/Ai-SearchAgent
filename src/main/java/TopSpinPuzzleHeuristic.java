import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class TopSpinPuzzleHeuristic {
	int maxCost;
	private int boardSize;
	private int patternsNum;
	private List<HashMap<Integer, Integer>> patternDatabases;
	private ArrayList<byte[]> patternList;

	public double getHeuristic(TopSpinPuzzleState problemState) {
//		TopSpinPuzzleState state = problemState;
//		double manhattan = 0;
//		int[] tileArray = problemState._TopSpinPuzzle;
//		int N = tileArray.length;
//		int number = 1;
//		for(int row = 0; row < N; ++row) {
//			for(int col = 0; col < N; ++col) {
//				int value = tileArray[row][col];
//				if (value != 0 && number != value)
//					manhattan = manhattan+value * (Math.abs(row-getRaw(N, value))+Math.abs(col-getCol(N, value)));
//				number++;
//			}
//		}
//
//		manhattan = (manhattan * 1.23);
//		double H = 2.9 * (linerCol(tileArray, N)+linerRow(tileArray, N));
//		return (manhattan+H);
//		if (problemState.isGoalState())
		TopSpinPuzzleState state = problemState;
		int[] tileArray = problemState._TopSpinPuzzle;
		int h=0;
		for(int i=0; i<tileArray.length; i++){
			int a=i;
			int b;
			int c;
			int d;
			if(i>=7)
				b=3+i-10;
			else
				b=i+3;
			if(i==9)
				c=1;
			else
				c=i+1;
			if(i>=8)
				d=i+2-10;
			else
				d=i+2;
			if(tileArray[a]>tileArray[b] && tileArray[c]>tileArray[d] && !((tileArray[c]==9 && tileArray[d]==0) || (tileArray[a]==9 && tileArray[b]==2) || (tileArray[a]==8 && tileArray[b]==1) || (tileArray[a]==7 && tileArray[b]==0))){
				h+=tileArray[a]+tileArray[b]+tileArray[c]+tileArray[d];
			}
		}
		return h;
	}

	public int calculateHeu(byte[] board) {
		ArrayList<byte[]> boardsToCheck = new ArrayList<>(patternsNum);
		for(int i = 0; i < patternsNum; ++i) {
			byte[] temp = board.clone();
			boardsToCheck.add(temp);
		}
		for(int i = 0; i < patternsNum; ++i) {
			for(int j = 0; j < boardSize * boardSize; ++j) {
				if (!patternContains(patternList.get(i), boardsToCheck.get(i)[j])) {
					boardsToCheck.get(i)[j] = (byte) (boardSize * boardSize);
				}
			}

		}

		int heurValue = 0;
		Integer tempValue = new Integer(0);
		for(int i = 0; i < patternsNum; ++i) {
			if ((tempValue = patternDatabases.get(i).get(Arrays.hashCode(boardsToCheck.get(i)))) != null)
				heurValue += tempValue;
		}

		return heurValue;
	}


	private boolean patternContains(byte[] pattern, byte x) {
		for(int i = 0; i < pattern.length; ++i) {
			if (pattern[i] == x) {
				return true;
			}
		}
		return false;
	}
}
