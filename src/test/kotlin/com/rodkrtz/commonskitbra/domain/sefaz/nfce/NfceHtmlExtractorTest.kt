package com.rodkrtz.commonskitbra.domain.sefaz.nfce

import com.rodkrtz.commonskit.core.extraction.HtmlExtractor
import org.jsoup.Jsoup
import org.junit.jupiter.api.Test

class NfceHtmlExtractorTest {

    @Test
    fun teste() {
        val html = Thread.currentThread().contextClassLoader.getResourceAsStream("cleaned.html").bufferedReader().use {it.readText()}

//        val element = HtmlExtractor(html).extractAllOuterHtml(("#Totais"))

//        val s = HtmlExtractor(html).extractFirstText(Selector.CHAVE_ACESSO.getValue())
        /*val s = Jsoup.parse(html).selectFirst("label:matchesOwn(^\\s*${Regex.escape("Totais")}\\s*$) + span")
            ?.text()
            ?.replace('\u00A0', ' ')
            ?.replace(Regex("\\s+"), " ")
            ?.trim()*/

        val doc = Jsoup.parse(html)

        println(doc.selectFirst(NfceHtmlField.DadosGerais.CHAVE_ACESSO))

        val element = doc.select("#Prod table.toggle.box")

        val element2 = doc.select("#Prod table.toggable.box")

        println(NfceHtmlField.Produto.NUMERO)
        println(element.first()!!.selectFirst(NfceHtmlField.Produto.DESCRICAO))
        println(element2.first()!!.selectFirst(NfceHtmlField.Produto.VALOR_DESCONTO))

//        println(element)
    }
}