package learningInterServiceCall.PracticeMondodb.Services

import kotlinx.serialization.json.Json
import learningInterServiceCall.PracticeMondodb.ExceptionHandler.UserDefinedExceptions
import learningInterServiceCall.PracticeMondodb.Model.MyStudents
import learningInterServiceCall.PracticeMondodb.Model.StudentDto
import learningInterServiceCall.PracticeMondodb.Repository.MyStudentRepository
import learningInterServiceCall.PracticeMondodb.Repository.StudentsRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class MyStudentsServices(
    val repository: MyStudentRepository,
    val reactiveRepo:StudentsRepository){

    fun getAllData(): ResponseEntity<ApiResponseService> {
        return try {
            ResponseEntity.ok().body(StudentResponse(repository.findAll()))
        }catch (e:Exception){
            ResponseEntity.internalServerError().body(ErrorResponse("Error Founding data",HttpStatus.FORBIDDEN))
        }
    }

    fun createStudent(student: MyStudents): ResponseEntity<ApiResponseService> {
         try {
            if (repository.existsById(student.username)){
                return ResponseEntity.badRequest().body(ErrorResponse("student with username: ${student.username} exists",HttpStatus.BAD_REQUEST))
            }
             return ResponseEntity.ok().body(Response(repository.save(student)))
        }catch (e:Exception){
            return ResponseEntity.internalServerError().body(ErrorResponse("Error Founding data",HttpStatus.FORBIDDEN))
        }
    }


    fun updateStudent(username: String , student: StudentDto): ResponseEntity<ApiResponseService> {
        return try {
            if( repository.existsById(username) ){
                val data  = MyStudents(username,student.name,student.surname,student.section,student.age)
                return ResponseEntity.ok().body(Response(repository.save(data)))
            }
            ResponseEntity.ok().body(ErrorResponse("Student with id: $username not found ", HttpStatus.NOT_FOUND))
        }catch (e:Exception){
            ResponseEntity.internalServerError().body(ErrorResponse("Error Founding data",HttpStatus.FORBIDDEN))
        }
    }

    fun deleteStudent(username: String): ResponseEntity<ApiResponseService> {
        return try {
            if (!repository.existsById(username)){
                return ResponseEntity.badRequest().body(ErrorResponse("student with username: ${username} does not exists",HttpStatus.BAD_REQUEST))
            }
            repository.deleteById(username)
            return ResponseEntity.ok().body(ErrorResponse("student with username: $username deleted successfully",HttpStatus.ACCEPTED))
        }catch (e:Exception){
            ResponseEntity.internalServerError().body(ErrorResponse("Error Founding data",HttpStatus.FORBIDDEN))
        }
    }

    // reactive approach one
    fun getAllStudentMono(): Flux<MyStudents> {
        try {
            return reactiveRepo.findAll()
        }catch (e:Exception){
            val json = """{"message": "Internal Server Error"}"""
            throw UserDefinedExceptions(Json.parseToJsonElement(json), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun saveStudents(student: MyStudents): Mono<MyStudents> {
        try {
            val check = reactiveRepo.existsById(student.username)
            check.subscribe()
            val result:Boolean? = check.block()
            if (result !=null && result == false){
                return reactiveRepo.save(student)
            } else{
                val json = """{"message": "username: ${student.username} already Exists"}"""
                throw UserDefinedExceptions(Json.parseToJsonElement(json), HttpStatus.BAD_REQUEST)
                }
        }catch (error:UserDefinedExceptions){
            throw error
        }catch (e:Exception){
            val json = """{"message": "Internal Server Error"}"""
            throw UserDefinedExceptions(Json.parseToJsonElement(json), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}
