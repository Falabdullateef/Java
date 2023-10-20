package newsletter;

// Paper: 2009
// Year: 9-10 Junior
// Question: 4

public class newsletter{
	public static void main(String[] args) {
		int c = 0;
		for(int i =15; i<=53;i++) {
			if (i%2!=0){
				c++;
			}else {
				System.out.println("no");
			}
		}
		System.out.println(c);
	}
	
}
