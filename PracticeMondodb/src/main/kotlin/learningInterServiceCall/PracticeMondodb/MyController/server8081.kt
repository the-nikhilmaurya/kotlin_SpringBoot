package learningInterServiceCall.PracticeMondodb.MyController

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import learningInterServiceCall.PracticeMondodb.Model.StudentDto
import learningInterServiceCall.PracticeMondodb.Services.Server2
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("server")
class server8081 (val server:Server2){

    @GetMapping
    fun  gettingStudentList(): Mono<JsonElement> {
        return server.gettingStudentList().map { result-> Json.parseToJsonElement(result) }
    }

    @GetMapping("{id}")
    fun  gettingStudentById(@PathVariable id:Int): Mono<JsonObject> {
            return  server.gettingStudentById(id).map { result -> Json.parseToJsonElement(result).jsonObject }
    }

    @PostMapping()
    fun createStudent(@RequestBody student: StudentDto): Mono<JsonElement> {
        return server.createStudent(student).map { result->Json.parseToJsonElement(result) }
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable id:Int): Mono<JsonElement> {
        return server.deleteStudentById(id)
            .map { result->Json.parseToJsonElement(result) }
    }

    @PutMapping("{id}")
    fun updateStudent(@PathVariable id:Int,@RequestBody student:StudentDto): Mono<JsonElement> {
        return server.updateStudent(id,student).map { result-> Json.parseToJsonElement(result) }
    }


}