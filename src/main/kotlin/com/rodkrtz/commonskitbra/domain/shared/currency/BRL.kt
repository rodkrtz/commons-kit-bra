package com.rodkrtz.commonskitbra.domain.shared.currency

import java.math.BigDecimal

/**
 * Utilitários de formatação e parsing para moeda brasileira (BRL).
 *
 * A convenção adotada e a mesma dos apps bancários brasileiros:
 * os dígitos brutos representam centavos (os 2 últimos dígitos são a parte decimal).
 *
 * Exemplos de formatação:
 * ```
 * BRL.format("")        // ""
 * BRL.format("1")       // "R$ 0,01"
 * BRL.format("12")      // "R$ 0,12"
 * BRL.format("123")     // "R$ 1,23"
 * BRL.format("1500")    // "R$ 15,00"
 * BRL.format("1234567") // "R$ 12.345,67"
 * ```
 */
public object BRL {

    private const val MAX_DIGITS: Int = 9

    /**
     * Formata dígitos brutos como valor BRL.
     * Os 2 últimos dígitos são tratados como centavos.
     *
     * @param rawDigits string contendo apenas dígitos (ex: "12345")
     * @return string formatada (ex: "R$ 123,45") ou vazio se entrada vazia
     */
    public fun format(rawDigits: String): String {
        val digits = rawDigits.filter(Char::isDigit).take(MAX_DIGITS)
        if (digits.isEmpty()) return ""

        val padded = digits.padStart(3, '0')
        val integerPart = padded.dropLast(2).toLong()
        val decimalPart = padded.takeLast(2)

        val groupedInteger = integerPart.toString()
            .reversed()
            .chunked(3)
            .joinToString(".")
            .reversed()

        return "R$ $groupedInteger,$decimalPart"
    }

    /**
     * Converte dígitos brutos (centavos) para [BigDecimal].
     *
     * @param rawDigits string contendo apenas dígitos (ex: "12345")
     * @return BigDecimal com 2 casas decimais (ex: 123.45) ou null se vazio
     */
    public fun parse(rawDigits: String): BigDecimal? {
        val digits = rawDigits.filter(Char::isDigit).take(MAX_DIGITS)
        if (digits.isEmpty()) return null
        return BigDecimal(digits).movePointLeft(2)
    }
}
