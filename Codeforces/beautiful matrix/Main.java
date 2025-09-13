import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int row=1;row<6;row++) { // run loop 5 times 1,2,3,4,5
            for (int Column=1;Column<6;Column++) { // run loop 5 times 1,2,3,4,5
                int x = sc.nextInt();
                if (x==1) {
                    System.out.println(Math.abs(row-3)+Math.abs(Column-3));
                    return;
                }
            }
        }
    }
}
