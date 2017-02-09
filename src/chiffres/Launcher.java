package chiffres;

public class Launcher {
	
	public static void main(String[] args) {

		long start;
		
		start = System.nanoTime();
		for (int i=0; i<1; i++) {
			new Solver();
			/*SimpleOperation a = new SimpleOperation(2);
			SimpleOperation b = new SimpleOperation(5);
			SimpleOperation c = new SimpleOperation(a, '+', b, 7);
			
			System.out.println(c);*/
		}
		System.out.println("\nFinished in "+(System.nanoTime()-start)/1000000000.+"s");
	}
}
