package pl.marchuck.lovelypunchingrocket.passwordChecker;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.Color;

import io.reactivex.functions.Function;
import pl.marchuck.lovelypunchingrocket.signIn.RxUtils;

public class PasswordCheckerViewModel {

    public final ObservableField<String> password = new ObservableField<>("");
    public final ObservableInt passwordStrengthLevel = new ObservableInt(0);
    public final ObservableField<String> passwordStrengthDescription = new ObservableField<>("");
    public final ObservableField<String> passwordStrengthColor = new ObservableField<>(String.valueOf(Color.BLACK));

    public PasswordCheckerViewModel(final PasswordStrengthCalculator calculator) {

        RxUtils.toObservable(password)
                .map(new Function<String, PasswordStrengthModel>() {
                    @Override
                    public PasswordStrengthModel apply(String s) throws Exception {
                        return calculator.calculate(s);
                    }
                })
                .subscribe(strength -> {
                    passwordStrengthDescription.set(strength.getName());
                    passwordStrengthLevel.set(strength.getLevel());
                    passwordStrengthColor.set(String.valueOf(strength.getColor()));
                }, System.err::println);
    }
}

