package AuthPract2.practice2.MyController

import AuthPract2.practice2.Modal.EmployeeTable
import AuthPract2.practice2.Services.ApiResponseService
import AuthPract2.practice2.Services.EmployeeService
import AuthPract2.practice2.Services.SimpleResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/private")
class EmployeeController(val service:EmployeeService) {

    @GetMapping
    fun getAllEmployee(): ResponseEntity<ApiResponseService> {
        println(SecurityContextHolder.getContext())
       return service.getAllEmployee()
    }

    @PostMapping
    fun createEmployee(@RequestBody employee:EmployeeTable):EmployeeTable{
        return  service.createEmployee(employee)
    }

    @GetMapping("{email}")
    fun getByEmail(@PathVariable email:String):Any{
        return service.getByEmail(email)
    }


    @PostMapping("logout")
    fun onLogout(){
        val token =
        SecurityContextHolder.clearContext()
    }

}