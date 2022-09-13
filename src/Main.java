public class Main {
    public static void main(String[] args) {

        checkAuthorizationParameters("java_skypro.go", "D_1hWiKjjP_9", "D_1hWiKjjP_9");
        checkAuthorizationParameters("java_skypro_go", "D_1hWiKjjP_9", "D_1hWiKjjP_9");
        checkAuthorizationParameters("01234567890123456789", "01234567890123456789", "01234567890123456789");

    }

    public static boolean checkAuthorizationParameters(String login, String password, String confirmPassword) {

        try {
            checkLogin(login);
            checkPassword(password, confirmPassword);
        } catch (WrongLoginException e) {
            System.out.println("Incorrect login");
            return false;
        } catch (WrongPasswordException e) {
            System.out.println("Incorrect password or password confirmation");
            return false;
        }

        System.out.println("Authorization is OK");
        return true;
    }

    public static void checkLogin(String login) {

        if (login.length() > 20) throw new WrongLoginException();

        for (int i = 0; i < login.length(); i++) {
            if (!checkChar(login.charAt(i))) throw new WrongLoginException();
        }

    }

    public static void checkPassword(String password, String confirmPassword) {

        if (password.length() >= 20 || !password.equals(confirmPassword)) {
            throw new WrongPasswordException();
        }

        for (int i = 0; i < password.length(); i++) {
            if (!checkChar(password.charAt(i))) throw new WrongPasswordException();
        }

    }

    public static boolean checkChar(char ch) {
        return (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '_';
    }
}