package pl.marchuck.lovelypunchingrocket.signIn;

public interface ValidationHelper {

    boolean isEmailValid(String email);

    boolean isPasswordValid(String password);

    int getPasswordStrength(String password);
}
