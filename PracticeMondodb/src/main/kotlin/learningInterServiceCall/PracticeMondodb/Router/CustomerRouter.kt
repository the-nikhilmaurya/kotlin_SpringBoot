package learningInterServiceCall.PracticeMondodb.Router

import learningInterServiceCall.PracticeMondodb.Handler.CustomerHandler
import learningInterServiceCall.PracticeMondodb.Model.CustomerDto
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux


@Configuration
class CustomerRouter(val handler: CustomerHandler) {


    fun routerFunction  (): RouterFunction<ServerResponse> {
        println("form routerrr")
        return RouterFunctions.route()
            .GET("/router/customers") { request ->
                println("from handler")
                val customerList: Flux<CustomerDto> = Flux.range(1, 10)
                    .map { i ->
                        println("Processing request using Flux : $i")
                        CustomerDto(i, "customer:$i")
                    }.log()
                ServerResponse.ok().body(customerList, CustomerDto::class.java)
            }
            .build()
    }

}