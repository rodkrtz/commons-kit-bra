package com.rodkrtz.commonskitbra.domain.sefaz.nfce

import com.rodkrtz.commonskit.core.formatEscaped

public const val LABEL_VALUE_SELECTOR_TEMPLATE: String =
    "label:matchesOwn(^\\s*%s\\s*$) + span"

public object NfceSectionId {
    public const val NFE: String = "NFe"
    public const val EMITENTE: String = "Emitente"
    public const val PROD: String = "Prod"
    public const val TOTAIS: String = "Totais"
    public const val COBRANCA: String = "Cobranca"
    public const val INF: String = "Inf"
}

public object NfceHtmlField {

    public object DadosGerais {

        public val CHAVE_ACESSO: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Chave de Acesso")

        public val NUMERO: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Número")

        public val SERIE: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Série")

        public val DATA_EMISSAO: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Data de Emissão")

        public val VALOR_TOTAL_NOTA_FISCAL: String =
            LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Valor Total da Nota Fiscal")

        public val PROTOCOLO: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Protocolo")

        public val DATA_AUTORIZACAO: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Data Autorização")
    }

    /** Dados do Emitente — seção #Emitente */
    public object Emitente {

        public val NOME_RAZAO_SOCIAL: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Nome / Razão Social")

        public val CNPJ: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("CNPJ")

        public val INSCRICAO_ESTADUAL: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Inscrição Estadual")

        public val ENDERECO: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Endereço")

        public val BAIRRO_DISTRITO: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Bairro / Distrito")

        public val MUNICIPIO: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Município")

        public val CEP: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("CEP")

        public val UF: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("UF")

        public val TELEFONE: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Telefone")
    }

    /** Produto — linha resumo (table.toggle.box) e detalhe (table.toggable.box) — seção #Prod */
    public object Produto {

        private const val CSS_QUERY = "td.fixo-prod-serv-%s span"

        /** Número sequencial do item na nota */
        public val NUMERO: String = CSS_QUERY.format("numero")

        public val DESCRICAO: String = CSS_QUERY.format("descricao")

        public val QUANTIDADE: String = CSS_QUERY.format("qtd")

        public val UNIDADE_COMERCIAL: String = CSS_QUERY.format("uc")

        public val VALOR: String = CSS_QUERY.format("vb")

        // --- Campos da tabela de detalhe (table.toggable.box > td > table.box) ---

        /** Alias de COD_PRODUTO — código interno do produto */
        public val CODIGO: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Código do Produto")

        public val COD_PRODUTO: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Código do Produto")

        public val COD_NCM: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Código NCM")

        public val COD_CEST: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Código CEST")

        public val INDICADOR_ESCALA_RELEVANTE: String =
            LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Indicador de Escala Relevante")

        public val CNPJ_FABRICANTE_MERCADORIA: String =
            LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("CNPJ do Fabricante da Mercadoria")

        public val COD_BENEFICIO_FISCAL_UF: String =
            LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Código de Benefício Fiscal na UF")

        public val COD_EX_TIPI: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Código EX da TIPI")

        public val CFOP: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("CFOP")

        public val VALOR_DESCONTO: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Valor do Desconto")

        public val COD_EAN_COMERCIAL: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Código EAN Comercial")

        public val VALOR_UNITARIO_COMERCIALIZACAO: String =
            LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Valor unitário de comercialização")

        public val VALOR_APROXIMADO_TRIBUTOS: String =
            LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Valor Aproximado dos Tributos")
    }

    /** Totais da nota — seção #Totais */
    public object Totais {

        public val BASE_CALCULO_ICMS: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Base de Cálculo ICMS")

        public val VALOR_ICMS: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Valor do ICMS")

        public val VALOR_ICMS_DESONERADO: String =
            LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Valor do ICMS Desonerado")

        public val VALOR_TOTAL_PRODUTOS: String =
            LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Valor Total dos Produtos")

        public val VALOR_TOTAL_DESCONTOS: String =
            LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Valor Total dos Descontos")

        public val VALOR_TOTAL_NFE: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Valor Total da NFe")

        public val VALOR_APROXIMADO_TRIBUTOS: String =
            LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Valor Aproximado dos Tributos")
    }

    /** Formas de Pagamento — seção #Cobranca */
    public object Cobranca {

        /**
         * CSS selector para o span da coluna "Ind. Forma de Pagamento" (células[0])
         * dentro de uma `table.toggle.box` de pagamento.
         */
        public const val IND_FORMA_PAGAMENTO: String = "td:nth-child(1) > span"

        /**
         * CSS selector para o span da coluna "Meio de Pagamento" (células[1])
         * dentro de uma `table.toggle.box` de pagamento.
         */
        public const val MEIO_PAGAMENTO: String = "td:nth-child(2) > span"

        /**
         * CSS selector para o span da coluna "Descrição do Meio de Pagamento" (células[2])
         * dentro de uma `table.toggle.box` de pagamento.
         */
        public const val DESCRICAO_MEIO_PAGAMENTO: String = "td:nth-child(3) > span"

        /** Valor do pagamento — primeira célula com span na tabela interna do toggable */
        public val VALOR_PAGAMENTO: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Valor do Pagamento")

        public val TROCO: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("Troco")
    }

    /** Informações Adicionais / Suplementares — seção #Inf */
    public object Inf {

        public val QR_CODE: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("QR-Code")

        public val URL_NFCE: String = LABEL_VALUE_SELECTOR_TEMPLATE.formatEscaped("URL NFC-e")
    }
}
