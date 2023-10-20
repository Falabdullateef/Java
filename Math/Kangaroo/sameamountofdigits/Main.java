package sameamountofdigits;

// Paper: 2009
// Year: 9-10 Junior
// Question: 9

public class Main {
	public static void main(String[] args) {
		int c =0;
		
		
		for(int i =1;i<10000;i++) {
			int num1= i*i;
			int num2 = i*i*i;
			int length1 = String.valueOf(num1).length(); 
			int length2 = String.valueOf(num2).length(); 
			if (length1==length2) {
				c++;
			}
			
			
		}
		System.out.println(c);
	}

}
