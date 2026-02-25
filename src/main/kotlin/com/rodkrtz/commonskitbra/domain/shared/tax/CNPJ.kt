package com.rodkrtz.commonskitbra.domain.shared.tax

import com.rodkrtz.commonskit.core.Chars
import com.rodkrtz.commonskit.domain.shared.privacy.DisplayMode
import com.rodkrtz.commonskit.domain.shared.region.Country
import com.rodkrtz.commonskit.domain.shared.tax.BusinessTaxId

@JvmInline
public value class CNPJ(override val value: String) : BusinessTaxId {
    override val country: Country get() = Country.BRA

    public fun display(displayMode: DisplayMode): String = when (displayMode) {
        DisplayMode.RAW -> value
        DisplayMode.FORMATTED -> buildString(18) {
            append(value, 0, 2).append('.')
            append(value, 2, 5).append('.')
            append(value, 5, 8).append('/')
            append(value, 8, 12).append('-')
            append(value, 12, 14)
        }
        DisplayMode.MASKED -> buildString(18) {
            append(value, 0, 2).append(".***.***/****-")
            append(value, 12, 14)
        }
    }

    public companion object {
        private val PESOS_D1 = intArrayOf(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2)
        private val PESOS_D2 = intArrayOf(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2)

        public fun tryParse(input: String): Result<CNPJ> {
            val cleaned = Chars.extractBase36(input, 14)
            return if (isValidDigits(cleaned)) Result.success(CNPJ(cleaned))
            else Result.failure(IllegalArgumentException("Invalid CNPJ: $input"))
        }

        private fun isValidDigits(d: String): Boolean {
            if (d.length != 14 || Chars.isRepeated(d)) return false

            val d1 = calcDigit(d.substring(0, 12), PESOS_D1)
            if (Chars.base36Value(d[12]) != d1) return false

            val d2 = calcDigit(d.substring(0, 13), PESOS_D2)
            return Chars.base36Value(d[13]) == d2
        }

        private fun calcDigit(base: String, pesos: IntArray): Int {
            var sum = 0
            for (i in base.indices) {
                sum += Chars.base36Value(base[i]) * pesos[i]
            }
            val r = sum % 11
            return if (r < 2) 0 else 11 - r
        }
    }
}