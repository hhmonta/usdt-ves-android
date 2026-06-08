package com.usdtves.app.network

import retrofit2.http.GET

/**
 * API de CriptoYa - https://criptoya.com/api
 * Endpoint: GET /api/USDT/VES
 * Devuelve cotizaciones de USDT/VES de múltiples exchanges P2P
 */
interface CriptoYaApiService {

    @GET("api/USDT/VES")
    suspend fun getUsdtVesRates(): Map<String, ExchangeData>
}
