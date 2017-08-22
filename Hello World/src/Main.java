
public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World");
		System.out.println("Hello World");
		int myInt = 4;
		float myFloat = (float) 4.5;
		char c = 'g';
		
		String myString1 = "String is implemented as Class";
		String myString2 = "No operator Overloading in Java except String Class";
		
		String myString3 = "St1 + St2 = " + myInt + myString1 + myString2;
		System.out.println(myString3);
		
		boolean b = false;
		boolean newboolean = b||b;
		System.out.println(newboolean);
		
		int result = myInt == 4 ? 1:8;   // Single Line If Statement 
		System.out.println(result);
		
		String ast = "Wow";
		String bst = "Wow";
		
		
		
		boolean test = ast==bst;
		test = ast.equals(bst);
		System.out.println(test);
		
		// Arrays are implemented as Object 
		int[] array;         // First declared
		array = new int[10]; // Then Created
		System.out.println(array.length);
		
		/*int[] array = {1,2,3,4,5};
		int i;
		for (i = 0; i < array.length; i++) {
			if (i>=3) {
				break;
			}
			System.out.println("Yuthu");
			if (i>=1) {
				continue;
			}
			System.out.println("Ta Ta");
		}
		System.out.println(i);*/
		
		/*for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);*/
		

	}

}
