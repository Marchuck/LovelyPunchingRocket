package pl.marchuck.lovelypunchingrocket.passwordChecker

interface PasswordStrengthCalculator {

    fun calculate(password: String): PasswordStrengthModel
}
