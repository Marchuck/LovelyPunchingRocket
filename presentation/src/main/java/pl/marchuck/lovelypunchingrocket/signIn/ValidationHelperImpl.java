package pl.marchuck.lovelypunchingrocket.signIn;

import android.text.TextUtils;

import java.util.regex.Pattern;

public class ValidationHelperImpl implements ValidationHelper {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=\\S*[a-z])(?=\\S*[A-Z])(?=\\S*\\d)(?=\\S*[^\\w\\s])\\S{8,100}$"
    );

    @Override
    public boolean isEmailValid(String target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public boolean isPasswordValid(String password) {
        //at least 8 characters
        //at least 1 number
        //at least 1 capital letter
        //at least 1 special character

        return PASSWORD_PATTERN.matcher(password).matches();
    }

    @Override
    public int getPasswordStrength(String password) {
        if (isPasswordValid(password)) return 3;
        if (password.length() >= 8) return 2;
        return 1;
    }
}
