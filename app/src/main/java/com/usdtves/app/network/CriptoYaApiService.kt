package com.usdtves.app.network

import retrofit2.http.GET

/**
 * API de CriptoYa - https://criptoya.com/api
 * Endpoint: GET /v2/USDT/VES
 * Devuelve cotizaciones de USDT/VES de múltiples exchanges
 */
interface CriptoYaApiService {

    @GET("v2/USDT/VES")
    suspend fun getUsdtVesRates(): Map<String, ExchangeData>
}
