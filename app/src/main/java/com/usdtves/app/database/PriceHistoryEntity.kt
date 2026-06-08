package com.usdtves.app.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Entidad que representa un registro histórico de precios USDT/VES
 * Se almacena un registro por día con el promedio de los precios
 */
@Entity(
    tableName = "price_history",
    indices = [Index(value = ["date"], unique = true)]
)
data class PriceHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,              // Formato: "yyyy-MM-dd"
    val avgAsk: Double,            // Precio de venta promedio del día
    val avgBid: Double,            // Precio de compra promedio del día
    val minAsk: Double,            // Precio de venta mínimo del día
    val maxAsk: Double,            // Precio de venta máximo del día
    val minBid: Double,            // Precio de compra mínimo del día
    val maxBid: Double,            // Precio de compra máximo del día
    val bestAskExchange: String,   // Exchange con mejor precio de venta
    val bestBidExchange: String,   // Exchange con mejor precio de compra
    val sampleCount: Int,          // Cantidad de muestras tomadas en el día
    val lastUpdatedAt: Long,       // Timestamp de la última actualización
)
