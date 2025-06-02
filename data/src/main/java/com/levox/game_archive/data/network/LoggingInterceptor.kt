package com.levox.game_archive.data.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Request
import okio.Buffer
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

class LoggingInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        logRequest(request)

        val startTimeNs = System.nanoTime()
        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            Log.e(TAG, "HTTP FAILED: $e")
            throw e
        }
        val endTimeNs = System.nanoTime()
        val durationMs = (endTimeNs - startTimeNs) / 1_000_000.0

        logResponse(response, durationMs)

        return response
    }

    private fun logRequest(request: Request) {
        val url = request.url
        val method = request.method
        val headers = request.headers
        val requestBody = request.body

        val logBuilder = StringBuilder()
        logBuilder.append("--> ${method.uppercase()} $url\n")

        headers.forEach { header ->
            logBuilder.append("    ${header.first}: ${header.second}\n")
        }

        if (requestBody != null) {
            logBuilder.append("    Body: (${requestBody.contentLength()}-byte body)\n")
            if (isPlainText(requestBody.contentType()?.toString())) {
                try {
                    val buffer = Buffer()
                    requestBody.writeTo(buffer)
                    logBuilder.append("        ${buffer.readUtf8()}\n")
                } catch (_: IOException) {
                    logBuilder.append("        (binary ${requestBody.contentLength()}-byte body omitted)\n")
                }
            } else {
                logBuilder.append("    (binary ${requestBody.contentLength()}-byte body omitted)\n")
            }
        }
        logBuilder.append("--> END ${method.uppercase()}")
        Log.d(TAG, logBuilder.toString())
    }

    private fun logResponse(response: Response, durationMs: Double) {
        val requestUrl = response.request.url
        val code = response.code
        val message = response.message
        val headers = response.headers
        val responseBody = response.body

        val logBuilder = StringBuilder()
        logBuilder.append("<-- $code $message $requestUrl (${String.format(Locale.getDefault(), "%.1fms", durationMs)})\n")

        headers.forEach { header ->
            logBuilder.append("    ${header.first}: ${header.second}\n")
        }

        if (responseBody != null) {
            logBuilder.append("    Body: (${responseBody.contentLength()}-byte body)\n")
            if (isPlainText(responseBody.contentType()?.toString())) {
                // We need to peek the body here, as reading it directly consumes it.
                // This might be memory-intensive for large responses.
                val source = responseBody.source()
                source.request(Long.MAX_VALUE) // Buffer the entire body.
                val buffer = source.buffer
                val bodyString = buffer.clone().readUtf8()
                if (bodyString.isNotEmpty()) {
                     logBuilder.append("        ${bodyString}\n")
                } else {
                    logBuilder.append("        (empty body)\n")
                }
            } else {
                logBuilder.append("    (binary ${responseBody.contentLength()}-byte body omitted)\n")
            }
        }
        logBuilder.append("<-- END HTTP")
        Log.d(TAG, logBuilder.toString())
    }

    private fun isPlainText(mediaType: String?): Boolean {
        if (mediaType == null) return false
        val type = mediaType.lowercase()
        return type.contains("text") || type.contains("json") || type.contains("xml") || type.contains("html") || type.contains("x-www-form-urlencoded")
    }

    companion object {
        private const val TAG = "OkHttpLogging"
    }
} 