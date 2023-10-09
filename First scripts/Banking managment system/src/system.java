import java.util.Scanner;

public class system {
    Userdata user = new Userdata("","","","","");
    Scanner input = new Scanner(System.in);
    Ask_If_Has_acount();
    String answer_for_has = input.nextLine();

    //Check if user has an account
        switch (answer_for_has) {
        case "y" -> {
            HasAccountChecker();
        }
        case "n" -> {
            askForFirstName();
            user.firstName = input.nextLine();
            askForLastName();
            user.lastName = input.nextLine();
            askForEmail();
            user.email = input.nextLine();
            askForPassword();
            user.password = input.nextLine();
            askForSSN();
            user.SSN = input.nextLine();
            System.out.println("Welcome, " + user.firstName + " " + user.lastName);
        }
        default -> {
            System.out.println("Invalid input only y/n");

        }
    }


}
    public static void Ask_If_Has_acount() {
        System.out.println("Do you have an account? (y/n)");
    }
    public static void askForEmail() {
        System.out.println("Enter your email: ");
    }
    public static void askForPassword() {
        System.out.println("Enter your password: ");
    }
    public static void askForSSN() {
        System.out.println("Enter your SSN: ");
    }
    public static void askForFirstName() {
        System.out.println("Enter your first name: ");
    }
    public static void askForLastName() {
        System.out.println("Enter your last name: ");
    }
    public static void HasAccountChecker() {
        askForEmail();
        Scanner input = new Scanner(System.in);
        String email = input.nextLine();
        askForPassword();
        String password = input.nextLine();
        if (email.equals("") && password.equals("")) {
            System.out.println("Welcome, " + email);
        } else {
            System.out.println("Invalid email or password");
        }


    }
}
