package chiffres;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;



public class SolverV1 {
	
	protected double[] theDraw = {10, 5, 6, 1, 75, 7};
	protected List<Double> theDraw2 = Arrays.asList(10.0, 5.0, 6.0, 1.0, 75.0, 7.0);
	//protected List<Double> theDraw2 = Arrays.asList(10.0, 5.0, 6.0, 7.0);

	protected double theResult = 757; //easy
//	protected double theResult = 788; //hard
//	protected double[] theDraw = {10, 5, 6, 7};
//	protected double[] theDraw = {2, 7, 75, 6};
//	protected double theResult = 95;
	protected char[] theOperators = {'*', '+', '-', '/'};
//	protected char[] theOperators = {'*', '+'};
	protected ArrayList<OperationV1> solution = new ArrayList<OperationV1>();
	
	protected static ScriptEngine jsEngine;
	
	private static void initJSEngine() {
		ScriptEngineManager factory = new ScriptEngineManager();
		jsEngine = factory.getEngineByName("JavaScript");
	}
	
	SolverV1() {
		initJSEngine();
		if (findSolution(theDraw)) {
			for (OperationV1 operation : solution) {
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
	protected boolean findSolution(double[] draw) {
		
		if (findEvidentSolution(draw)) {
			return true;
		}
		
		if (findSimpleSolution(draw)) {
			return true;
		}
		
		// All other cases
		for (int i=0; i<draw.length; i++) {
			for (int j=0; j<draw.length; j++) {
				if (i==j) {
					continue;
				}
				for (char operator : theOperators) {
					if (operator == '/' && draw[j] == 0) {
						continue;
					}
					double[] newDraw = new double[draw.length-1];
					/*if (operator == '*' && (draw[i] == 1 || draw[j] == 1)) {
						newDraw[0] = draw[i] * draw[j];
					} else if (operator == '+' && (draw[i] == 0 || draw[j] == 0)) {
						newDraw[0] = draw[i] + draw[j];
					} else if (operator == '/' && draw[j] == 1
							|| operator == '-' && draw[j] == 0) {
						newDraw[0] = draw[i];
					} else {*/
						OperationV1 operation = new OperationV1(draw[i], draw[j], operator);
						solution.add(operation);
						newDraw[0] = operation.getResult();
					//}
					for (int m=0, k=1; m<draw.length; m++) {
						if (m==i || m==j) {
							continue;
						}
						newDraw[k]=draw[m];
						k++;
					}
					if (findSolution(newDraw)) {
						return true;
					}
				}
			}
		}
		solution.remove(solution.size()-1);
		return false;
	}
	
	private boolean findEvidentSolution(double[] draw) {
		// Most simple case : result is one of the figures
		for (int i=0; i<draw.length; i++) {
			if (theResult == draw[i]) {
				solution.add(new OperationV1(draw[i], 0, '+'));
				return true;
			}
		}
		return false;
	}

	private boolean findSimpleSolution(double[] draw) {
		OperationV1 operation;
		
		// Second most simple case : result can be found with only ONE operation
		for (int i=0; i<draw.length; i++) {
			for (int j=0; j<draw.length; j++) {
				if (i==j) {
					continue;
				}
				operation = tryOperators(draw[i], draw[j]);
				if (operation != null) {
					solution.add(operation);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Tries all operators on the operands until result is found
	 * @param operandA
	 * @param operandB
	 * @return The operation if successful, null otherwise
	 */
	protected OperationV1 tryOperators(double operandA, double operandB) {
		for (char operator : theOperators) {
			if (operator == '/' && operandB == 0) {
				continue;
			}
			OperationV1 operation = new OperationV1(operandA, operandB, operator);
			if (Math.abs(theResult - operation.getResult()) < 1E-10) {
				return operation;
			}
		}
		return null;
	}
}
