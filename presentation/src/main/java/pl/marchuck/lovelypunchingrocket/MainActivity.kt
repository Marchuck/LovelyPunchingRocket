package pl.marchuck.lovelypunchingrocket

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import pl.marchuck.lovelypunchingrocket.databinding.ComponentPasswordCheckerBinding
import pl.marchuck.lovelypunchingrocket.passwordChecker.PasswordCheckerViewModel
import pl.marchuck.lovelypunchingrocket.passwordChecker.PasswordStrengthCalculator
import pl.marchuck.lovelypunchingrocket.passwordChecker.PasswordStrengthCalculatorImpl

class MainActivity : AppCompatActivity() {

    //todo: use dagger here
    val calculator: PasswordStrengthCalculator = PasswordStrengthCalculatorImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ComponentPasswordCheckerBinding>(this,
                R.layout.component_password_checker)

        binding.viewModel = PasswordCheckerViewModel(calculator)
    }
}
