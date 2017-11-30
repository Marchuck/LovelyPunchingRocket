package pl.marchuck.lovelypunchingrocket.passwordChecker

import android.graphics.Color

import javax.inject.Inject

class PasswordStrengthCalculatorImpl @Inject
constructor() : PasswordStrengthCalculator {

    override fun calculate(password: String): PasswordStrengthModel = calculateStrength(password)

    private fun calculateStrength(password: String): PasswordStrengthModel {
        val score = PasswordScore(password)
                .withMinimalRequirements()
                .determine()

        return when (score.currentScore) {

            0 -> PasswordStrengthModel("Weak", Color.RED, 25)
            1 -> PasswordStrengthModel("Medium", Color.argb(255, 220, 185, 0), 50)
            2 -> PasswordStrengthModel("Strong", Color.GREEN, 75)
            else -> PasswordStrengthModel("Very strong", Color.BLUE, 100)
        }
    }

    internal class PasswordScore(private val password: String) {

        companion object {

            //--------REQUIREMENTS--------
            private val REQUIRED_LENGTH = 8
            private val MAXIMUM_LENGTH = 15
            private val REQUIRE_SPECIAL_CHARACTERS = true
            private val REQUIRE_DIGITS = true
            private val REQUIRE_LOWER_CASE = true
            private val REQUIRE_UPPER_CASE = false
        }

        var sawUpper = false
        var sawLower = false
        var sawDigit = false
        var sawSpecial = false

        var currentScore: Int = 0

        fun withMinimalRequirements(): PasswordScore {
            for (i in 0 until password.length) {
                val c = password[i]

                if (!sawSpecial && !Character.isLetterOrDigit(c)) {
                    currentScore += 1
                    sawSpecial = true
                } else {
                    if (!sawDigit && Character.isDigit(c)) {
                        currentScore += 1
                        sawDigit = true
                    } else {
                        if (!sawUpper || !sawLower) {
                            if (Character.isUpperCase(c))
                                sawUpper = true
                            else
                                sawLower = true
                            if (sawUpper && sawLower)
                                currentScore += 1
                        }
                    }
                }
            }
            return this
        }

        fun determine(): PasswordScore {
            if (password.length > REQUIRED_LENGTH) {
                if (REQUIRE_SPECIAL_CHARACTERS && !sawSpecial
                        || REQUIRE_UPPER_CASE && !sawUpper
                        || REQUIRE_LOWER_CASE && !sawLower
                        || REQUIRE_DIGITS && !sawDigit) {
                    currentScore = 1
                } else {
                    currentScore = 2
                    if (password.length > MAXIMUM_LENGTH) {
                        currentScore = 3
                    }
                }
            } else {
                currentScore = 0
            }
            return this
        }
    }
}
