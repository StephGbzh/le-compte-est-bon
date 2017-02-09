package chiffres;

public class SimpleOperation {

	private SimpleOperation operation1;
	private SimpleOperation operation2;
	private char operator;
	private int result;
	private boolean isNumber;
	
	public int getResult() {
		return result;
	}

	public boolean isNumber() {
		return isNumber;
	}

	public SimpleOperation(SimpleOperation operation1, char operator, SimpleOperation operation2, int result) {
		this.operation1 = operation1;
		this.operation2 = operation2;
		this.operator = operator;
		this.isNumber = false;
		this.result = result;
	}
	
	public SimpleOperation(int operand) {
		this.isNumber = true;
		this.result = operand;
	}
	
	@Override
	public String toString() {
		if (isNumber) {
			return "";
		} else {
			return operation1.toString() + operation2.toString() + "\n" +
			operation1.getResult() +" "+ operator +" "+ operation2.getResult()+" = "+result;
		}
	}
	
}
