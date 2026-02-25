package com.rodkrtz.commonskitbra.domain.shared.contact

import com.rodkrtz.commonskit.domain.shared.contact.Phone
import com.rodkrtz.commonskit.domain.shared.region.Country

@JvmInline
public value class PhoneBRA(override val value: String) : Phone {

    override val country: Country
        get() = Country.BRA

    // TODO Tem que pegar o valor do ddd antes de usar esse método
    private fun isValidDdd(): Boolean {
        val dddInt = 22

        // Lista de DDDs que NÃO existem no Brasil (buracos na tabela)
        val invalid = intArrayOf(20, 23, 25, 26, 29, 30, 36, 39, 50, 52, 56, 57, 58, 59, 60, 70, 72, 76, 78, 80, 90)
        return dddInt !in invalid
    }
}