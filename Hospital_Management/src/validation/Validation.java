package validation;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validation {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    private static final String MOBILE_REGEX = "^[0-9]{10}$";
    private static final String NAME_REGEX = "^[a-zA-Z]+$";
    private static final String USERNAME_REGEX = "^[a-zA-Z][a-zA-Z0-9]*$";

    public static boolean validateEmail(String email) {
        return validatePattern(email, EMAIL_REGEX);
    }

    public static boolean validatePassword(String password) {
        return validatePattern(password, PASSWORD_REGEX);
    }

    public static boolean validateMobile(String mobile) {
        return validatePattern(mobile, MOBILE_REGEX);
    }

    public static boolean validateName(String name) {
        return validatePattern(name, NAME_REGEX);
    }

    public static boolean validateUsername(String username) {
        return validatePattern(username, USERNAME_REGEX);
    }

    private static boolean validatePattern(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

 /*   public static void main(String[] args) {
        String email = "example@example.com";
        String password = "P@ssw0rd123";
        String mobile = "1234567890";
        String name = "JohnDoe";
        String username = "JohnDoe123";

        if (validateEmail(email)) {
            System.out.println("Valid email.");
        } else {
            System.out.println("Invalid email.");
        }

        if (validatePassword(password)) {
            System.out.println("Valid password.");
        } else {
            System.out.println("Invalid password.");
        }

        if (validateMobile(mobile)) {
            System.out.println("Valid mobile number.");
        } else {
            System.out.println("Invalid mobile number.");
        }

        if (validateName(name)) {
            System.out.println("Valid name.");
        } else {
            System.out.println("Invalid name.");
        }

        if (validateUsername(username)) {
            System.out.println("Valid username.");
        } else {
            System.out.println("Invalid username.");
        }
    } */
}
