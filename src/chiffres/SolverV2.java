package chiffres;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolverV2 {
	
//	protected List<Integer> theDraw = Arrays.asList(10, 5, 6, 1, 75, 7);
//	protected List<Integer> theDraw = Arrays.asList(75, 10, 5, 6, 7, 1);
//	protected List<Integer> theDraw = Arrays.asList(10, 5, 6, 7);

//	protected List<Integer> theDraw = Arrays.asList(5, 7, 2, 4, 9, 8);
	protected List<Integer> theDraw = Arrays.asList(10, 100, 25, 75, 10, 7);
	protected int theResult = 688;

//	protected int theResult = 757; //easy
//	protected int theResult = 788; //hard
	
//	protected List<Integer> theDraw = Arrays.asList(10, 5, 6, 7);
//	protected List<Integer> theDraw = Arrays.asList(2, 7, 75, 6);
//	protected int theResult = 95;
	protected ArrayList<SimpleOperationV2> solution = new ArrayList<SimpleOperationV2>();
	
	SolverV2() {
		if (findSolution(theDraw)) {
			for (SimpleOperationV2 operation : solution) {
				System.out.println(operation);
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
	protected boolean findSolution(List<Integer> draw) {
		
		// Most simple case : result is one of the figures
		for (int i=0; i<draw.size(); i++) {
			if (Math.abs(theResult - draw.get(i)) < 1E-10) {
				return true;
			}
		}
		
		// All other cases
		for (int i=0; i<draw.size(); i++) {
			for (int j=0; j<draw.size(); j++) {
				if (i==j) {
					continue;
				}
				
				List<Integer> newDraw = new ArrayList<Integer>(draw);
				int m = Math.min(i, j);
				int n = Math.max(i, j);
				newDraw.remove(n);
				
				newDraw.set(m, draw.get(i) * draw.get(j));
				solution.add(new SimpleOperationV2(draw.get(i), draw.get(j), 'x', draw.get(i) * draw.get(j)));
				if (findSolution(newDraw)) {
					return true;
				}
				
				newDraw.set(m, draw.get(i) + draw.get(j));
				solution.add(new SimpleOperationV2(draw.get(i), draw.get(j), '+', draw.get(i) + draw.get(j)));
				if (findSolution(newDraw)) {
					return true;
				}
				
				newDraw.set(m, draw.get(i) - draw.get(j));
				solution.add(new SimpleOperationV2(draw.get(i), draw.get(j), '-', draw.get(i) - draw.get(j)));
				if (findSolution(newDraw)) {
					return true;
				}
				
				if (draw.get(j) != 0 && Math.IEEEremainder(draw.get(i), draw.get(j)) == 0) {
					newDraw.set(m, draw.get(i) / draw.get(j));
					solution.add(new SimpleOperationV2(draw.get(i), draw.get(j), '/', draw.get(i) / draw.get(j)));
					if (findSolution(newDraw)) {
						return true;
					}
				}
				
			}
		}
		
		// No solution found
		if (solution.size() > 0) {
			solution.remove(solution.size()-1);
		}
		return false;
	}

}
