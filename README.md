# USDT/VES - Cotizaciones en Tiempo Real

App de Android que muestra las cotizaciones de compra y venta de USDT/VES (Tether / Bolívar Venezolano) utilizando la API de CriptoYa.

## Características

- **Precios en tiempo real**: Cotizaciones de compra y venta de USDT/VES de múltiples exchanges
- **Historial diario**: Tabla con el registro histórico de precios por día
- **Auto-refresh**: Actualización automática cada 60 segundos
- **Búsqueda**: Filtrar exchanges por nombre
- **Resumen de precios**: Mejor precio de venta, compra, promedios y spread
- **Tema oscuro/claro**: Soporte para ambos temas del sistema

## API

Esta app utiliza la API pública de [CriptoYa](https://criptoya.com/api):

- Endpoint: `GET https://criptoya.com/api/USDT/VES`
- Retorna cotizaciones de múltiples exchanges P2P (binancep2p, okexp2p, bybitp2p, etc.)

## Tecnologías

- **Kotlin** + **Jetpack Compose**
- **Material Design 3**
- **Retrofit** + **OkHttp** para networking
- **Room** para base de datos local
- **MVVM** con ViewModel + StateFlow
- **Coroutines** para operaciones asíncronas

## Capturas de pantalla

| Precios | Historial |
|---------|-----------|
| Lista de exchanges con precios de venta y compra | Tabla diaria con promedios, mínimos y máximos |

## Compilar

1. Clonar el repositorio
2. Abrir en Android Studio
3. Sincronizar Gradle
4. Compilar y ejecutar

```bash
./gradlew assembleDebug
```

## Licencia

MIT
