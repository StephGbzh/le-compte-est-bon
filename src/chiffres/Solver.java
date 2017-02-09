package chiffres;

public class Solver {
	
//	int[] theDraw = {10, 5, 6, 1, 75, 7};
//	int[] theDraw = {75, 10, 5, 6, 7, 1};
//	int[] theDraw = {10, 5, 6, 7};

//	int[] theDraw = {5, 7, 2, 4, 9, 8};
	
//	int[] theDraw = {10, 100, 25, 75, 10, 7};
//	int theResult = 688; // impossible

//	int[] theDraw = {25, 50, 75, 100, 3, 6};
//	int theResult = 952; // 23850 !!!

//	int[] theDraw = {12, 87, 8, 3, 4, 8};
//	int theResult = 724;

//	int[] theDraw = {10, 1, 8, 4, 5, 9};
//	int theResult = 653;
	
	int[] theDraw = {1, 1, 1, 1, 1, 1};
	int theResult = 653;
	
//	int[] theDraw = {6, 5, 10, 2, 9, 75};
//	int theResult = 397;
	
//	int[] theDraw = {2,3,4,5};
//	int theResult = 28;
	
//	int[] theDraw = {4, 2, 75, 9, 6, 1};
//	int theResult = 857;
//	int theResult = 5;

//	int[] theDraw = {5, 3, 75, 9, 6, 1};
//	int theResult = 8;

//	int[] theDraw = {10, 5, 6, 7};
//	int[] theDraw = {2, 7, 75, 6};
//	int theResult = 95;
	SimpleOperation solution;
	
	Solver() {
		SimpleOperation[] drawBig = new SimpleOperation[theDraw.length];
		for (int i=0; i<theDraw.length; i++) {
			drawBig[i] = new SimpleOperation(theDraw[i]);
		}
		
		if (findSolution(drawBig)) {
			System.out.println(solution);
		} else {
			System.out.println("Could not find a solution");
		}
	}
	
	/**
	 * Browse the figures of the draw until result is found
	 * @param draw
	 * @return true if a solution is found, false otherwise
	 */
	boolean findSolution(SimpleOperation[] draw) {
		
		// Most simple case : result is one of the figures
		for (int i=0; i<draw.length; i++) {
			if (theResult == draw[i].getResult()) {
				solution = draw[i];
				return true;
			}
		}
		
		// All other cases
		for (int i=0; i<draw.length; i++) {
			for (int j=0; j<draw.length; j++) {
				if (i==j) {
					continue;
				}
				
				SimpleOperation[] newDraw = new SimpleOperation[draw.length-1];
				int m = Math.min(i, j);
				int n = Math.max(i, j);
				
				for (int k=0; k<newDraw.length; k++) {
					if (k>=n) {
						newDraw[k] = draw[k+1];
					} else if (k!=m) {
						newDraw[k] = draw[k];
					}
				}
				
				newDraw[m] = new SimpleOperation(draw[i], 'x', draw[j], draw[i].getResult() * draw[j].getResult());
				if (findSolution(newDraw)) {
					return true;
				}
				
				newDraw[m] = new SimpleOperation(draw[i], '+', draw[j], draw[i].getResult() + draw[j].getResult());
				if (findSolution(newDraw)) {
					return true;
				}
				
				if (draw[i].getResult() > draw[j].getResult()) {
					newDraw[m] = new SimpleOperation(draw[i], '-', draw[j], draw[i].getResult() - draw[j].getResult());
					if (findSolution(newDraw)) {
						return true;
					}
				}
				
				if (draw[j].getResult() != 0 && Math.IEEEremainder(draw[i].getResult(), draw[j].getResult()) == 0) {
					newDraw[m] = new SimpleOperation(draw[i], '/', draw[j], draw[i].getResult() / draw[j].getResult());
					if (findSolution(newDraw)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
