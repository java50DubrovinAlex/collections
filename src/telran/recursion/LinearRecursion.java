package telran.recursion;

import org.hamcrest.core.SubstringMatcher;

public class LinearRecursion {
	static int count = 0;
	public static void function(int a) {
		if(a > 3) {
			function(a - 1);
		}
		
	}
	public static long factorial(int n) {
		long res = 1;
		if (n < 0) {
			throw new IllegalArgumentException("factorial exists only for positive numbers and 0");
		}
		if (n > 0) {
			res = n * factorial(n - 1);
		}
		return res;
		
	}
	public static long pow (int a, int b) {
		long res = 0;
		if(b > 1) {
			res = square(a) + pow(a, b - 1);
		}
		
		return res;
		//TODO
		//HW #17 definition
		//Write method pow with following limitations
		//No cycles
		//Arithmetic operations allowed +; - only
		//if Additional functions applied then only with the same limitations
	}
	public static void displayArray(int[] ar) {
		displayArray(0, ar, false);
	}
private static void displayArray(int index, int[] ar, boolean isReverse) {
		if (index < ar.length) {
			if (isReverse) {
				displayArray(index + 1, ar, isReverse);
				System.out.print(ar[index] + " ");
			} else {
				System.out.print(ar[index] + " ");
				displayArray(index + 1, ar, isReverse);
			}
		}
		
	}
public static void displayReversedArray(int[] ar) {
	displayArray(0, ar, true);
	}

public static long sumArray(int []array) {
	return sumArray(0, array);
}
private static long sumArray(int index, int[] array) {
	long res = 0;
	if (index < array.length) {
		res = array[index] + sumArray(index + 1, array);
	}
	return res;
}
public static void reverseArray(int[] array) {
	reverseArray(0, array, array.length -1);
}
private static void reverseArray(int left, int[] array, int right) {
	if(left < right) {
		int tmp = array[left];
		array[left] = array[right];
		array[right] = tmp;
		reverseArray(left + 1, array, right - 1);
	}
	
}
public static int square(int x) {
	//TODO 
	//returns x ^ 2
	//With following limitations
	//No cycles
	//No any additional methods 
	//No static fields
	// Only + ; - arithmetic operations
	if (x == 0) {
        return 0;
    } else if (x > 0) {
        return x + x - 1 + square(x - 1);
    } else {
        return  - x - x - 1 + square(x + 1);
    }
}
public static boolean isSubstring(String string, String substr) { 

	int strIndex = 0;
	

		return stringItr(string, substr, strIndex);
	}
private static boolean stringItr(String str, String sub, int strIndex) {
	int subIndex = 0;
	
	if(strIndex > str.length() - sub.length()) {
		return false;
	}
	
	if( subAndStrItr(str, sub, strIndex, subIndex)) {
		return true;
	}else {
		return stringItr(str, sub, strIndex + 1);
	}
 	
}
private static boolean subAndStrItr(String str, String sub, int strIndex, int subIndex) {
	if( subIndex >= sub.length()) {
		return true;
	}
	if(strIndex >= str.length() || str.charAt(strIndex) != sub.charAt(subIndex)) {
		return false;
	}
	return subAndStrItr(str, sub, strIndex + 1, subIndex + 1);
}
}