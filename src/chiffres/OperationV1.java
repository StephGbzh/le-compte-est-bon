package chiffres;

import javax.script.ScriptException;

public class OperationV1 {
	
	private double operandA;
	private double operandB;
	private char operator;
	private double result;
	
	public OperationV1(double operandA, double operandB, char operator) {
		this.operandA = operandA;
		this.operandB = operandB;
		this.operator = operator;
		computeResult();
	}

	private void computeResult() {
		String script = "";
		try {
			script = "eval((" +operandA +")"+ operator +"("+ operandB + "))";
			result = (Double) SolverV1.jsEngine.eval(script);
		} catch (ScriptException e) {
			System.out.println("ScriptException when trying to eval this : "+script);
		}
	}

	public double getOperandA() {
		return operandA;
	}

	public double getOperandB() {
		return operandB;
	}

	public char getOperator() {
		return operator;
	}

	public double getResult() {
		return result;
	}
	
	public String toString() {
		return operandA +" "+ operator +" "+ operandB+" = "+result;
	}
}
