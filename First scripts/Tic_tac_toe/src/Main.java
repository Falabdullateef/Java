import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter player's 1 name: ");
        String name1 = scanner.next();
        System.out.println("Welcome " + name1 + "!");

        System.out.print("Enter player's 2 name: ");
        Scanner scanner2 = new Scanner(System.in);
        String name2 = scanner2.next();
        System.out.println("Welcome " + name2 + "!");
        String turn1;
        String turn2;
        int random = (int) (Math.random() * 2 + 1);
        if (random == 1) {
            turn1 = name1;
            turn2 = name2;
        }
        else {
            turn1 = name2;
            turn2 = name1;
        }

        System.out.println("-------------------------------------");
        System.out.println("Game Has Started!");
        System.out.println(turn1+" starts first!");
        System.out.println(turn1 + ": X");
        System.out.println(turn2 + ": O");
        System.out.println("Enter the number of the cell you want to place your mark in.");
        System.out.println("Good Luck!");
        System.out.println("  1 | 2 | 3 ");
        System.out.println("----------------");
        System.out.println("  4 | 5 | 6  ");
        System.out.println("----------------");
        System.out.println("  7 | 8 | 9 ");
        String[] board = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

        for (int i = 0; i < 9; i++) {
            String turn = turn1;
            String turn_symbol = "X";

            System.out.println(turn + ", enter your move: ");
            Scanner scanner_for_move = new Scanner(System.in);
            int move = scanner_for_move.nextInt();

            if (i != 0) {
                turnchanger(turn, turn1, turn2);
                symbolchanger(turn_symbol);
            }



            if (move > 9 || move < 1) {
                System.out.println("Invalid move, try again!");
                i--;
            }
            else{
                int move_after = move - 1;
                board[move_after] = turn_symbol;
                turnchanger(turn, turn1, turn2);
                symbolchanger(turn_symbol);
                current_board(board);


                }
            }
        }

    public static void current_board(String[] board) {
        System.out.println("-----------------------------------------------------------");
        System.out.println("  " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("----------------");
        System.out.println("  " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("----------------");
        System.out.println("  " + board[6] + " | " + board[7] + " | " + board[8] + " ");
    }

    public static void symbolchanger(String symbol_turn) {
        if (symbol_turn.equalsIgnoreCase("x")) {
            symbol_turn = "O";
        }
        else {
            symbol_turn = "X";
        }
    }

    public static void turnchanger(String turn, String turn1, String turn2) {
        if (turn.equalsIgnoreCase(turn1)) {
            turn = turn2;
        }
        else {
            turn = turn1;
        }
    }
}





