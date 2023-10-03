package learningInterServiceCall.PracticeMondodb.Handler

import learningInterServiceCall.PracticeMondodb.Model.CustomerDto
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CustomerHandler {


    fun getAllCustomer(request: ServerRequest): Mono<ServerResponse> {
        println("from handler")
        val customerList:Flux<CustomerDto> = Flux.range(1, 10)
            .map { i ->
                println("Processing request using Flux : $i")
                CustomerDto(i, "customer:$i")
            }
        return ServerResponse.ok().body(customerList,CustomerDto::class.java)
    }


    fun getAllCustomerFlux(): Flux<CustomerDto> {
        return Flux.range(1, 10)
            .map { i ->
                println("Processing request using Flux : $i")
                CustomerDto(i, "customer:$i")
            }
    }
}