package org.todo.classic.data.network

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.serialization.JsonConvertException
import kotlinx.io.IOException
import org.todo.classic.data.dto.ErrorResponseDTO

suspend inline fun <T> safeApiCall(crossinline apiCall: suspend ()-> T): ApiResult<T> {
    return try {
        ApiResult.Success(apiCall())
    } catch (e: ClientRequestException) {
        val error = try {
            e.response.body<ErrorResponseDTO>()
        } catch (_: Exception) {
            null
        }
        ApiResult.Error(
            code = error?.code,
            message = error?.message ?: "Something went wrong."
        )
    }catch (e: ServerResponseException){
        ApiResult.Error(
            message = "Server error. Please try again."
        )
    } catch (e: IOException){
        ApiResult.Error(
            message = "Unable to connect to the server."
        )
    } catch (e: JsonConvertException) {
        println("Inside nested catch ${e.message}")
        ApiResult.Error(
            message = "Unable to parse server response."
        )
    } catch (e: Exception){
        ApiResult.Error(
            message = e.message ?: "Unknown error."
        )
    }
}