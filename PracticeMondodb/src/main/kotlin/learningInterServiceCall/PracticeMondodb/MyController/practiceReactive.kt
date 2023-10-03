package learningInterServiceCall.PracticeMondodb.MyController

import learningInterServiceCall.PracticeMondodb.Model.CustomerDto
import learningInterServiceCall.PracticeMondodb.Services.CustomerService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("reactive")
class practiceReactive(val customerService: CustomerService) {

    @GetMapping("normal")
    fun normal():List<CustomerDto>{
        return customerService.getAllCustomer()
    }

    @GetMapping(value = arrayOf("/stream"), produces = arrayOf(MediaType.TEXT_EVENT_STREAM_VALUE))
    fun stream(): Flux<CustomerDto> {
        return  customerService.getAllCustomerFlux()
    }
// In the above approach, my browser is the subscriber and my service class is publisher
}


