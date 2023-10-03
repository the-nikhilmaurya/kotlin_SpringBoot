package learningInterServiceCall.PracticeMondodb

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class pract {

    @Test
    fun testMono(){
        val monoString: Mono<String> = Mono.just("hellooo worllldddd")
            .then(Mono.just("hlll"))
            .then<String>(Mono.error(RuntimeException("jjjjj")))
            .log()
        monoString.subscribe({value -> println(value)},{e -> println(e.message) })
    }

    @Test
    fun testFlux(){
        val fluxpract : Flux<String> = Flux.just("hello","hi","namste","salam")
            .concatWithValues("sasriyakal")
            .concatWith(Flux.error(RuntimeException("error occured")))
            .log()

        fluxpract.subscribe({value -> println(value) })
    }
}