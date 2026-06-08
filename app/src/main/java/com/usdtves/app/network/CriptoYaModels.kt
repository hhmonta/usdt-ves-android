package com.usdtves.app.network

/**
 * Modelo de datos para cada exchange en la respuesta de CriptoYa
 * La API retorna un mapa dinámico: { "exchangeName": { ask, bid, totalAsk, totalBid, time } }
 *
 * Ejemplo de respuesta real:
 * {
 *   "binancep2p": { "ask": 763.5, "totalAsk": 763.5, "bid": 768, "totalBid": 768, "time": 1780884689 },
 *   "okexp2p":    { "ask": 763.4, "totalAsk": 763.4, "bid": 759, "totalBid": 759, "time": 1780884735 },
 *   ...
 * }
 *
 * Se usa Map<String, ExchangeData> en el servicio Retrofit para manejar
 * los nombres de exchanges dinámicamente.
 */
data class ExchangeData(
    val ask: Double = 0.0,       // Precio de venta (lo que cobran por venderte USDT)
    val bid: Double = 0.0,       // Precio de compra (lo que te pagan por tu USDT)
    val totalAsk: Double = 0.0,  // Precio de venta con comisiones incluidas
    val totalBid: Double = 0.0,  // Precio de compra con comisiones incluidas
    val time: Long = 0L,         // Timestamp de la cotización
)
