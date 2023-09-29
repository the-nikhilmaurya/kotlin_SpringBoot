package learningInterServiceCall.PracticeMondodb.Services

import kotlinx.serialization.json.Json
import learningInterServiceCall.PracticeMondodb.ExceptionHandler.UserDefinedExceptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.net.ConnectException


@Configuration
class WebClientConfiguration {

    @Bean
    fun webClientBuilder(): WebClient.Builder{
        try {
               return WebClient
                    .builder()
                    .filter(errorHandler())
                    .baseUrl("http://localhost:8081/students")

        } catch (e: ConnectException){
            println("from webClientConf: $e")
            throw UserDefinedExceptions(Json.parseToJsonElement("Unable to connect"), HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: Exception) {
            println("from webClientConf: $e")
            throw UserDefinedExceptions(Json.parseToJsonElement("Unable to connect"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }



    fun errorHandler() : ExchangeFilterFunction {
        return ExchangeFilterFunction.ofResponseProcessor { clientResponse: ClientResponse ->

            if (clientResponse.statusCode().is5xxServerError) {
                clientResponse.bodyToMono(String::class.java)
                    .flatMap { errorBody ->
//                        println(errorBody)
                        Mono.error(UserDefinedExceptions(Json.parseToJsonElement(errorBody),HttpStatus.INTERNAL_SERVER_ERROR))
                    }

            } else if (clientResponse.statusCode().is4xxClientError) {
                clientResponse.bodyToMono(String::class.java)
                    .flatMap { errorBody ->
//                        println(errorBody)
                        Mono.error(UserDefinedExceptions(Json.parseToJsonElement(errorBody),HttpStatus.NOT_FOUND))
                    }
            }
            else {
                Mono.just(clientResponse)
            }
        }
    }


    }
