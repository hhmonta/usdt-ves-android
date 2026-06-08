package com.usdtves.app.network

import com.google.gson.annotations.SerializedName

/**
 * Respuesta de la API de CriptoYa para USDT/VES
 * Ejemplo: https://api.criptoya.com/v2/USDT/VES
 *
 * Retorna un mapa donde cada clave es el nombre del exchange
 * y el valor contiene ask (venta), bid (compra), etc.
 */
data class CriptoYaResponse(
    val binance: ExchangeData? = null,
    @SerializedName("mercadobitcoin")
    val mercadobitcoin: ExchangeData? = null,
    @SerializedName("bybit")
    val bybit: ExchangeData? = null,
    @SerializedName("kucoin")
    val kucoin: ExchangeData? = null,
    @SerializedName("okx")
    val okx: ExchangeData? = null,
    @SerializedName("bitso")
    val bitso: ExchangeData? = null,
    @SerializedName("eldorado")
    val eldorado: ExchangeData? = null,
    @SerializedName("foxbit")
    val foxbit: ExchangeData? = null,
    @SerializedName("decrypto")
    val decrypto: ExchangeData? = null,
    @SerializedName("lemoncash")
    val lemoncash: ExchangeData? = null,
    @SerializedName("ripio")
    val ripio: ExchangeData? = null,
    @SerializedName("satoshitango")
    val satoshitango: ExchangeData? = null,
    @SerializedName("buenbit")
    val buenbit: ExchangeData? = null,
    @SerializedName("cryptomkt")
    val cryptomkt: ExchangeData? = null,
    @SerializedName("fiwind")
    val fiwind: ExchangeData? = null,
    @SerializedName("letsbit")
    val letsbit: ExchangeData? = null,
    @SerializedName("tiendacrypto")
    val tiendacrypto: ExchangeData? = null,
    @SerializedName("yadio")
    val yadio: ExchangeData? = null,
    @SerializedName("velvet")
    val velvet: ExchangeData? = null,
    @SerializedName("bela")
    val bela: ExchangeData? = null,
    @SerializedName("mantaro")
    val mantaro: ExchangeData? = null,
    @SerializedName("fluyez")
    val fluyez: ExchangeData? = null,
    @SerializedName("monedas127")
    val monedas127: ExchangeData? = null,
    @SerializedName("calypso")
    val calypso: ExchangeData? = null,
    @SerializedName("trendee")
    val trendee: ExchangeData? = null,
    @SerializedName("biswapay")
    val biswapay: ExchangeData? = null,
    @SerializedName("amber")
    val amber: ExchangeData? = null,
    @SerializedName("bnc")
    val bnc: ExchangeData? = null,
    @SerializedName("orionx")
    val orionx: ExchangeData? = null,
    @SerializedName("bingx")
    val bingx: ExchangeData? = null,
    @SerializedName("p2p")
    val p2p: ExchangeData? = null,
    @SerializedName("bitget")
    val bitget: ExchangeData? = null,
    @SerializedName("gateio")
    val gateio: ExchangeData? = null,
    @SerializedName("htx")
    val htx: ExchangeData? = null,
    @SerializedName("mexc")
    val mexc: ExchangeData? = null,
    @SerializedName("phemex")
    val phemex: ExchangeData? = null,
    @SerializedName("bloqlabs")
    val bloqlabs: ExchangeData? = null,
    @SerializedName("coinex")
    val coinex: ExchangeData? = null,
    @SerializedName("alita")
    val alita: ExchangeData? = null,
    @SerializedName("btcpoint")
    val btcpoint: ExchangeData? = null,
    @SerializedName("dvila")
    val dvila: ExchangeData? = null,
    @SerializedName("zulu")
    val zulu: ExchangeData? = null,
    @SerializedName("bod")
    val bod: ExchangeData? = null,
    @SerializedName("ibanwallet")
    val ibanwallet: ExchangeData? = null,
    @SerializedName("cryptobuyer")
    val cryptobuyer: ExchangeData? = null,
    @SerializedName("paydeer")
    val paydeer: ExchangeData? = null,
    @SerializedName("skully")
    val skully: ExchangeData? = null,
    @SerializedName("novadax")
    val novadax: ExchangeData? = null,
    @SerializedName("paribu")
    val paribu: ExchangeData? = null,
    @SerializedName("omnibtc")
    val omnibtc: ExchangeData? = null,
    @SerializedName("koinfy")
    val koinfy: ExchangeData? = null,
    @SerializedName("ankex")
    val ankex: ExchangeData? = null,
    @SerializedName("globiance")
    val globiance: ExchangeData? = null,
    @SerializedName("ffe")
    val ffe: ExchangeData? = null,
    @SerializedName("ticoex")
    val ticoex: ExchangeData? = null,
    @SerializedName("universal")
    val universal: ExchangeData? = null,
    @SerializedName("trocame")
    val trocame: ExchangeData? = null,
    @SerializedName("cambioya")
    val cambioya: ExchangeData? = null,
    @SerializedName("bcv")
    val bcv: ExchangeData? = null,
    @SerializedName("enparalelovzla")
    val enparalelovzla: ExchangeData? = null,
)

data class ExchangeData(
    val ask: Double = 0.0,       // Precio de venta (lo que cobran por venderte)
    val bid: Double = 0.0,       // Precio de compra (lo que te pagan por tu USDT)
    val totalAsk: Double = 0.0,  // Precio de venta con comisiones incluidas
    val totalBid: Double = 0.0,  // Precio de compra con comisiones incluidas
    val time: Long = 0L,         // Timestamp de la cotización
)
