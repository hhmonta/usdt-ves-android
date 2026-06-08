package com.usdtves.app.data

import com.usdtves.app.network.ExchangeData

/**
 * Modelo para representar un exchange con sus datos de precio
 */
data class ExchangeRate(
    val name: String,
    val data: ExchangeData,
)

/**
 * Resumen de precios para la vista principal
 */
data class PriceSummary(
    val bestAsk: Double,           // Mejor precio de venta (más bajo = más barato comprar)
    val bestAskExchange: String,
    val bestBid: Double,           // Mejor precio de compra (más alto = más te pagan)
    val bestBidExchange: String,
    val averageAsk: Double,        // Promedio de venta
    val averageBid: Double,        // Promedio de compra
    val spread: Double,            // Diferencia entre ask y bid promedio
    val exchangeCount: Int,        // Cantidad de exchanges disponibles
    val exchanges: List<ExchangeRate>,
)

/**
 * Filtro de exchanges disponibles
 */
enum class ExchangeFilter(val displayName: String) {
    ALL("Todos"),
    P2P("P2P"),
    EXCHANGES("Exchanges"),
    BANK("Bancos"),
}
