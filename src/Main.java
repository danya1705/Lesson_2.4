import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        checkAuthorizationParameters("java_skypro.go", "D_1hWiKjjP_9", "D_1hWiKjjP_9");
        System.out.println();
        checkAuthorizationParameters("java_skypro_go", "D_1hWiKjjP-9", "D_1hWiKjjP_9");
        System.out.println();
        checkAuthorizationParameters("java_skypro_go", "D_1hWiKjjP_9", "D_1hWiKjjP_9");
        System.out.println();
        checkAuthorizationParameters("01234567890123456789", "01234567890123456789", "01234567890123456789");

    }

    public static boolean checkAuthorizationParameters(String login, String password, String confirmPassword) {

        boolean loginCorrect;
        boolean passwordCorrect;

        try {
            loginCorrect = checkLogin(login);
            passwordCorrect = checkPassword(password, confirmPassword);
        } catch (WrongLoginException e) {
            System.out.println("Incorrect login length");
            return false;
        } catch (WrongPasswordException e) {
            System.out.println("Password confirmation failed");
            return false;
        }

        if (loginCorrect && passwordCorrect) {
            System.out.println("Authorization is OK");
            return true;
        }

        return false;
    }

    public static boolean checkLogin(String login) {

        Pattern pattern = Pattern.compile("[a-zA-Z0-9_]+");
        Matcher matcher = pattern.matcher(login);
        if (!matcher.matches()) {
            System.out.println("Incorrect login characters");
            return false;
        }

        if (login.length() > 20) throw new WrongLoginException();

        return true;

    }

    public static boolean checkPassword(String password, String confirmPassword) {

        if (password.length() >= 20) {
            System.out.println("Incorrect password length");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException();
        }

        Pattern pattern = Pattern.compile("[a-zA-Z0-9_]+");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            System.out.println("Incorrect password characters");
            return false;
        }

        return true;
    }

}