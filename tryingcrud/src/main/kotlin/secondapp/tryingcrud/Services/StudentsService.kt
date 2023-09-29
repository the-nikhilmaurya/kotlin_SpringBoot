package secondapp.tryingcrud.Services
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


import secondapp.tryingcrud.Entity.Students
import secondapp.tryingcrud.Repository.StudentRepository
import kotlin.reflect.full.memberProperties

@Service
class StudentsService(val repository:StudentRepository) {



    fun getAllStudents():ResponseEntity<ApiResponseService>{
       return try {
             ResponseEntity.ok().body(StudentListResponse(repository.findAll()))
        }catch (e:Exception){
            ResponseEntity.internalServerError().body(MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR,"internal server error"))
        }
    }

    fun createStudent(student: Students):Students{
        println(student)
        return repository.save(student)
    }


    fun getStudentByID(id:Long): ResponseEntity<ApiResponseService> {
        println("get by id")
         try {
         if (repository.existsById(id)) {
                val student:Students = repository.getReferenceById(id)
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(StudentResponse(student))
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageResponse(HttpStatus.NOT_FOUND,"id : $id not found"))
        }catch(e:Exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Internal server error"))
        }
    }

    fun deleteStudent(id:Long):ApiResponseService{
        return if(repository.existsById(id)){
            repository.deleteById(id)
            MessageResponse(HttpStatus.ACCEPTED,"student with id : $id deleted")
        }
        else return MessageResponse(HttpStatus.NOT_FOUND,"id = $id not found")
    }

    fun updateStudent(id: Long,student: Students):ApiResponseService{
        println("from update")
        return if (repository.existsById(id)){
            student.id = id
            StudentResponse(repository.save(student))
        }
        else return MessageResponse(HttpStatus.NOT_FOUND,"id = $id not found")
    }


    // using sorting to get students details
    fun gettingStudentBySorting(field:String):ApiResponseService{
        val properties = Students::class.memberProperties.map { it.name }
        println(properties.lastIndexOf(field))
        return if(properties.lastIndexOf(field) != -1) {
            val studentList = repository.findAll(Sort.by(Sort.Direction.ASC, field))
            StudentListResponse(studentList)
        }
        else
            return MessageResponse(HttpStatus.NOT_FOUND,"Wrong Field Name")
    }


    // using pagination to get students details
    fun gettingStudentsByPagination(offset:Int,limit:Int): Page<Students> {
        println("$offset  $limit")
        return repository.findAll(PageRequest.of(offset,limit))
    }

    // sorting with pagination
    fun gettingSortedDataUsingPagination(field:String,offset: Int,limit: Int):ApiResponseService{
        val properties = Students::class.memberProperties.map { it.name }
        return if(properties.lastIndexOf(field) != -1) {
            val page: Page<Students> =  repository.findAll(PageRequest.of(offset,limit,Sort.by(Sort.Direction.ASC,field)))
            StudentPageResponse(page)
        }
        else
            return MessageResponse(HttpStatus.NOT_FOUND,"Wrong Field Name")
    }

}


