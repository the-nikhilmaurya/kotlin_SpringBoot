package learningInterServiceCall.PracticeMondodb.Services

import learningInterServiceCall.PracticeMondodb.Model.MyStudents
import learningInterServiceCall.PracticeMondodb.Model.StudentDto
import learningInterServiceCall.PracticeMondodb.Repository.MyStudentRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class MyStudentsServices (val repository: MyStudentRepository){

    fun getAllData(): ResponseEntity<ApiResponseService> {
        return try {
            ResponseEntity.ok().body(StudentResponse(repository.findAll()))
        }catch (e:Exception){
            ResponseEntity.internalServerError().body(ErrorResponse("Error Founding data",HttpStatus.FORBIDDEN))
        }
    }

    fun createStudent(student: MyStudents): ResponseEntity<ApiResponseService> {
        return try {
            if (repository.existsById(student.username)){
                return ResponseEntity.badRequest().body(ErrorResponse("student with username: ${student.username} exists",HttpStatus.BAD_REQUEST))
            }
            ResponseEntity.ok().body(Response(repository.save(student)))
        }catch (e:Exception){
            ResponseEntity.internalServerError().body(ErrorResponse("Error Founding data",HttpStatus.FORBIDDEN))
        }
    }


    fun updateStudent(username: String , student: StudentDto): ResponseEntity<ApiResponseService> {
        return try {
            if( repository.existsById(username) ){
                val data  = MyStudents(username,student.name,student.surname,student.section,student.age)
                return ResponseEntity.ok().body(Response(repository.save(data)))
            }
            ResponseEntity.ok().body(ErrorResponse("Student with id: $username not found ",HttpStatus.NOT_FOUND))
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
}
