package learningInterServiceCall.PracticeMondodb.MyController
import learningInterServiceCall.PracticeMondodb.Model.MyStudents
import learningInterServiceCall.PracticeMondodb.Model.StudentDto
import learningInterServiceCall.PracticeMondodb.Services.ApiResponseService
import learningInterServiceCall.PracticeMondodb.Services.MyStudentsServices
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("students")
class MyStudentsController (val service:MyStudentsServices){

    @GetMapping()
    fun getAllStudents(): ResponseEntity<ApiResponseService> {
        return service.getAllData()
    }

    @PostMapping()
    fun createStudent(@RequestBody student: MyStudents): ResponseEntity<ApiResponseService> {
            return service.createStudent(student)
    }


    @PutMapping("{username}")
    fun updateStudent(@PathVariable username:String ,@RequestBody student: StudentDto): ResponseEntity<ApiResponseService> {
        return service.updateStudent(username, student)
    }

    @DeleteMapping("{username}")
    fun deleteStudent(@PathVariable username:String): ResponseEntity<ApiResponseService> {
        return service.deleteStudent(username)
    }

    @GetMapping("monoget")
    fun getStudents() = service.getAllStudentMono()

    @PostMapping("monopost")
    fun saveStudents(@RequestBody student: MyStudents): Mono<MyStudents> {
        return service.saveStudents(student)
    }

    // testing pracice
    @GetMapping("add")
    fun add(@RequestParam a:String,@RequestParam b:String) = a+b


}