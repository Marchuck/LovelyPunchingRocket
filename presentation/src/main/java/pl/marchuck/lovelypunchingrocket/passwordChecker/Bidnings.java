package pl.marchuck.lovelypunchingrocket.passwordChecker;


import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.widget.ProgressBar;
import android.widget.TextView;

@InverseBindingMethods(value = {
        @InverseBindingMethod(type = TextView.class,
                attribute = "color",
                method = "getColor"),
        @InverseBindingMethod(type = ProgressBar.class,
                attribute = "level",
                method = "getLevel")
})
@BindingMethods(value = {
        @BindingMethod(type = TextView.class,
                attribute = "color",
                method = "setColor"),
        @BindingMethod(type = ProgressBar.class,
                attribute = "level",
                method = "setLevel")
})
public class Bidnings {

    @InverseBindingAdapter(attribute = "color")
    public static int getColor(TextView view) {
        return view.getCurrentTextColor();
    }

    @BindingAdapter("color")
    public static void setColor(TextView view, int color) {
        view.setTextColor(color);
    }

    @InverseBindingAdapter(attribute = "level")
    public static int getLevel(ProgressBar progressBar) {
        return progressBar.getProgress();
    }

    @BindingAdapter("level")
    public static void setLevel(ProgressBar progressBar, int level) {
        if (progressBar.getProgress() != level) {
            progressBar.setProgress(level);
        }
    }
}
