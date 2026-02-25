package com.rodkrtz.commonskitbra.domain.shared.tax

import com.rodkrtz.commonskit.core.Arrays
import com.rodkrtz.commonskit.core.Chars
import com.rodkrtz.commonskit.domain.shared.privacy.DisplayMode
import com.rodkrtz.commonskit.domain.shared.region.Country
import com.rodkrtz.commonskit.domain.shared.tax.IndividualTaxId

@JvmInline
public value class CPF(override val value: String) : IndividualTaxId {
    override val country: Country
        get() = Country.BRA

    public val digitsOnly: String get() = value

    public val baseDigits: String get() = value.substring(0, 9)

    public val verifierDigits: String get() = value.substring(9, 11)

    public fun display(displayMode: DisplayMode): String = when (displayMode) {
        DisplayMode.RAW -> value
        DisplayMode.FORMATTED -> buildString(14) {
            append(value, 0, 3).append('.')
                .append(value, 3, 6).append('.')
                .append(value, 6, 9).append('-')
                .append(value, 9, 11)
        }
        DisplayMode.MASKED -> buildString(14) {
            append(value, 0, 3)
                .append(".***.***-") // 123.***.***-11
                .append(value, 9, 11)
        }
    }

    public fun asLong(): Long = value.toLong()

    override fun toString(): String = value

    public fun fromString(input: String): CPF = tryParse(input).getOrThrow()

    public companion object {

        public fun parse(input: String): CPF = tryParse(input).getOrThrow()

        public fun tryParse(input: String): Result<CPF> {
            val digits = Chars.extractDigits(input, 11)
            if (!isValidDigits(digits)) {
                return Result.failure(IllegalArgumentException("Invalid CPF: $input"))
            }
            return Result.success(CPF(digits))
        }

        public fun isValid(input: String): Boolean =
            isValidDigits(Chars.extractDigits(input, 11))

        public fun fromBase(base9: String): CPF {
            require(base9.length == 9 && base9.all { it.isDigit() }) {
                "The base must contain 9 numeric digits"
            }
            val d1 = calcDigit(base9, 9, 10)
            val d2 = calcDigit(base9 + d1, 10, 11)
            return CPF("$base9$d1$d2")
        }

        public fun generateRandom(): CPF {
            val digits = Arrays.generateRandoms(9)

            if (digits.all { it == digits[0] }) return generateRandom()

            val base = digits.joinToString("")
            val d1 = calcDigit(base, 9, 10)
            val d2 = calcDigit(base + d1, 10, 11)

            return CPF("$base$d1$d2")
        }

        public fun generateRandomList(count: Int): List<CPF> {
            require(count >= 0)
            return List(count) { generateRandom() }
        }

        private fun isValidDigits(d: String): Boolean {
            if (d.length != 11)
                return false

            val f = d[0]
            var rep = true

            for (i in 1 until 11) if (d[i] != f) { rep = false; break }
            if (rep) return false
            val d1 = calcDigit(d, 9, 10)
            if (d[9] != ('0' + d1)) return false
            val d2 = calcDigit(d, 10, 11)
            return d[10] == ('0' + d2)
        }

        private fun calcDigit(d: String, len: Int, wStart: Int): Int {
            var sum = 0
            for (i in 0 until len) {
                sum += Character.getNumericValue(d[i]) * (wStart - i)
            }
            val r = sum % 11
            return if (r < 2) 0 else 11 - r
        }
    }
}