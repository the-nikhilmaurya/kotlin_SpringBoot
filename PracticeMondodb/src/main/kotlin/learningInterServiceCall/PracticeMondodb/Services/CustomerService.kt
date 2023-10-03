package learningInterServiceCall.PracticeMondodb.Services

import learningInterServiceCall.PracticeMondodb.Model.CustomerDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.Duration

@Service
class CustomerService {



    fun getAllCustomer():List<CustomerDto> {
        return  (1..10).map { i ->
            println("Processing request : $i")
            Thread.sleep(2000)
            CustomerDto(i, "customer:$i")
        }
    }

    fun getAllCustomerFlux(): Flux<CustomerDto> {
        return Flux.range(1, 10)
            .delayElements(Duration.ofSeconds(1))
            .map { i ->
                println("Processing request using Flux : $i")
                CustomerDto(i, "customer:$i")
            }
    }

}