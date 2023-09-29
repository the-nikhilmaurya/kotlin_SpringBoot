package AuthPract2.practice2.Services

import AuthPract2.practice2.Modal.EmployeeTable
import AuthPract2.practice2.Repository.EmployeeRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class EmployeeService(val repository:EmployeeRepository) {

    fun getAllEmployee():ResponseEntity<ApiResponseService>{
//        return  repository.findAll()
       return  try {
           val employeeList = repository.findAll()
            ResponseEntity.ok().body(SimpleResponse(employeeList,HttpStatus.ACCEPTED))
       }catch (e:Exception){
            ResponseEntity.status(401).body(ErrorResponse(HttpStatus.NOT_FOUND,"Error finding data"),)
       }

    }

    fun userExists(email: String):Boolean{
        return repository.existsById(email)
    }


    fun getByEmail(email:String):Any{
        return if(repository.existsById(email)) {
            repository.findByEmail(email)
        } else "email: $email not found"
    }

    fun createEmployee(employee:EmployeeTable): EmployeeTable {
        return repository.save(employee)
    }
}