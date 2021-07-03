package tools;

public interface Print {
	public static void print (Object o ) {
		System.out.print(o);
	}
	public static void println (Object o){
		System.out.println(o);
	}
	public static void printBorder() {
		System.out.println("****************************************************************************");
	}
	public static void printBorder(int num) {
		for (int i=0 ; i<num; i++) {
			print("*");
		}
		println("");
	}
	public static void printBorder(int num, String sym) {
		for (int i=0 ; i<num; i++) {
			print(sym);
		}
		println("");
	}
}
