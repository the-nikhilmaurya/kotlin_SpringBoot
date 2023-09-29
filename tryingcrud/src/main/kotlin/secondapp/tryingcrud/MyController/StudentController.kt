package secondapp.tryingcrud.MyController

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import secondapp.tryingcrud.Entity.Students
import secondapp.tryingcrud.Services.ApiResponseService
import secondapp.tryingcrud.Services.StudentsService

@RequestMapping("students")
@RestController
class StudentController(val service:StudentsService)  {

    @GetMapping("try")
    fun trying():String{
        return "try successfulll"
    }


    @PostMapping("try")
    fun tryingPost(@RequestBody data:String):String{
        println("from try post data: $data")
        return "trying post successful"
    }


    @PostMapping("try2")
    fun tryingPost2(@RequestBody data:Students):Students{
        println("from try post data: $data")
        return data
    }

    // will give all the students
    @GetMapping("")
    fun  getAllStudents(): ResponseEntity<ApiResponseService> =  service.getAllStudents()

    // will create a new student
    @PostMapping("")
    fun postStudent(@RequestBody student:Students):Students = service.createStudent(student)


    // will give particular student based on id
    @GetMapping("{id}")
    fun getStudentById(@PathVariable id:Long):ResponseEntity<ApiResponseService>
    = service.getStudentByID(id)


    // will the delete the student if id found
    @DeleteMapping("{id}")
    fun deleteStudent(@PathVariable id:Long)= service.deleteStudent(id)


    // will update the existing student details
    @PutMapping("{id}")
    fun updateStudent(@PathVariable id: Long,@RequestBody  student: Students):ApiResponseService
            = service.updateStudent(id,student)

    // sorting based on column name

    @GetMapping("sort/{field}")
    fun gettingStudentBySorting(@PathVariable field:String):ApiResponseService{
        return service.gettingStudentBySorting(field)
    }

    @GetMapping("pagination")
    fun gettingStudentsByPagination(@RequestParam offset:Int,@RequestParam limit:Int): Page<Students> {
        return service.gettingStudentsByPagination(offset, limit)
    }

    // pagination and sorting
    @GetMapping("practice")
    fun gettingSortedDataUsingPagination(@RequestParam field: String,@RequestParam  offset: Int,@RequestParam  limit:Int):Any{
        return  service.gettingSortedDataUsingPagination(field,offset, limit)
    }


}