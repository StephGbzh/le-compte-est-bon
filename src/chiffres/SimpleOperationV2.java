package chiffres;

public class SimpleOperationV2 {

	private int operandA;
	private int operandB;
	private char operator;
	private int result;
	
	public SimpleOperationV2(int operandA, int operandB, char operator, int result) {
		this.operandA = operandA;
		this.operandB = operandB;
		this.operator = operator;
		this.result = result;
	}

	@Override
	public String toString() {
		return operandA +" "+ operator +" "+ operandB+" = "+result;
	}
	
}
