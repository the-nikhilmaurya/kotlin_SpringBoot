package learningInterServiceCall.PracticeMondodb.Services


import kotlinx.serialization.json.Json
import learningInterServiceCall.PracticeMondodb.ExceptionHandler.UserDefinedExceptions
import learningInterServiceCall.PracticeMondodb.Model.StudentDto
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono


@Service
class Server2(val webClientBuilder: WebClient.Builder) {

    fun callingServer2(): Mono<String> {

        return webClientBuilder
            .build()
            .get()
            .uri("/try")
            .retrieve()
            .bodyToMono(String::class.java)
    }


    fun gettingStudentById(id:Int) :Mono<String> {
      val response = webClientBuilder
            .build()
            .get()
            .uri("/${id}")
            .retrieve()
            .bodyToMono(String::class.java)

       return response
    }

    fun gettingStudentList(): Mono<String> {
        try {

            return webClientBuilder
                .build()
                .get()
                .uri("")
                .retrieve()
                .bodyToMono(String::class.java)
        }catch (e: WebClientResponseException){
            println(e)
            throw UserDefinedExceptions(Json.parseToJsonElement("Unable to connect"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    fun createStudent(student: StudentDto): Mono<String> {
      return webClientBuilder
            .build()
            .post()
            .uri("")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(student))
            .retrieve()
            .bodyToMono(String::class.java)
    }


    fun deleteStudentById(id:Int) :Mono<String>
    {
       return webClientBuilder
            .build()
            .delete()
            .uri("/${id}")
            .retrieve()
            .bodyToMono(String::class.java)

    }

    fun updateStudent(id: Int,student: StudentDto): Mono<String> {
        println("from update Student")
        return webClientBuilder
            .build()
            .put()
            .uri("/${id}")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(student))
            .retrieve()
            .bodyToMono(String::class.java)
    }


}

