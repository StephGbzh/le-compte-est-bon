package chiffres;

public class SimpleOperationV3 {

	private int operandA;
	private int operandB;
	private char operator;
	
	public SimpleOperationV3(int operandA, char operator, int operandB) {
		this.operandA = operandA;
		this.operandB = operandB;
		this.operator = operator;
	}
	
	public int getResult() {
		switch(operator) {
			case 'x' : return operandA * operandB;
			case '+' : return operandA + operandB;
			case '-' : return operandA - operandB;
			case '/' : return operandA / operandB;
			default  : return 0;
		}
	}

	@Override
	public String toString() {
		return operandA +" "+ operator +" "+ operandB+" = "+getResult();
	}
	
}
