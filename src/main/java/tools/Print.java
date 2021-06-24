package tools;

public interface Print {
	public default void print (Object o ) {
		System.out.print(o);
	}
	public default void println (Object o){
		System.out.println(o);
	}
	public default void printBorder() {
		System.out.println("****************************************************************************");
	}
	public default void printBorder(int num) {
		for (int i=0 ; i<num; i++) {
			print("*");
		}
		println("");
	}
	public default void printBorder(int num, String sym) {
		for (int i=0 ; i<num; i++) {
			print(sym);
		}
		println("");
	}
}
