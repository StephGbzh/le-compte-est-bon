package chiffres;

public class SolverV3 {
	
//	int[] theDraw = {10, 5, 6, 1, 75, 7};
//	int[] theDraw = {75, 10, 5, 6, 7, 1};
//	int[] theDraw = {10, 5, 6, 7};

//	int[] theDraw = {5, 7, 2, 4, 9, 8};
	
//	int[] theDraw = {10, 100, 25, 75, 10, 7};
//	int theResult = 688; // impossible

//	int[] theDraw = {4, 2, 75, 9, 6, 1};
//	int theResult = 857;
//	int theResult = 5;

	int[] theDraw = {5, 3, 75, 9, 6, 1};
	int theResult = 8;

//	int[] theDraw = {10, 5, 6, 7};
//	int[] theDraw = {2, 7, 75, 6};
//	int theResult = 95;
	SimpleOperationV3[] solution = new SimpleOperationV3[5];
	int solIndex = 0;
	
	SolverV3() {
		if (findSolution(theDraw)) {
			for (SimpleOperationV3 op : solution) {
				if (op != null) {
					System.out.println(op);
				}
			}
		} else {
			System.out.println("Could not find a solution");
		}
	}
	
	/**
	 * Browse the figures of the draw until result is found
	 * @param draw
	 * @return true if a solution is found, false otherwise
	 */
	boolean findSolution(int[] draw) {
		
		// Most simple case : result is one of the figures
		for (int i=0; i<draw.length; i++) {
			if (Math.abs(theResult - draw[i]) < 1E-10) {
				if (solIndex == 0) {
					System.out.println(draw[i]+" = "+theResult);
				}
				return true;
			}
		}
		
		// All other cases
		for (int i=0; i<draw.length; i++) {
			for (int j=0; j<draw.length; j++) {
				if (i==j) {
					continue;
				}
				
				int[] newDraw = new int[draw.length-1];
				int m = Math.min(i, j);
				int n = Math.max(i, j);
				
				for (int k=0; k<newDraw.length; k++) {
					if (k>=n) {
						newDraw[k] = draw[k+1];
					} else if (k!=m) {
						newDraw[k] = draw[k];
					}
				}
				
				newDraw[m] = draw[i] * draw[j];
				solution[solIndex++] = new SimpleOperationV3(draw[i], 'x', draw[j]);
				if (findSolution(newDraw)) {
					return true;
				}
				solution[--solIndex] = null;
				
				newDraw[m] = draw[i] + draw[j];
				solution[solIndex++] = new SimpleOperationV3(draw[i], '+', draw[j]);
				if (findSolution(newDraw)) {
					return true;
				}
				solution[--solIndex] = null;
				
				if (draw[i] > draw[j]) {
					newDraw[m] = draw[i] - draw[j];
					solution[solIndex++] = new SimpleOperationV3(draw[i], '-', draw[j]);
					if (findSolution(newDraw)) {
						return true;
					}
					solution[--solIndex] = null;
				}
				
				if (draw[j] != 0 && Math.IEEEremainder(draw[i], draw[j]) == 0) {
					newDraw[m] = draw[i] / draw[j];
					solution[solIndex++] = new SimpleOperationV3(draw[i], '/', draw[j]);
					if (findSolution(newDraw)) {
						return true;
					}
					solution[--solIndex] = null;
				}
				
			}
		}
		
		return false;
	}
}
