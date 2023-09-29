package AuthPract2.practice2.Services

import AuthPract2.practice2.Modal.EmployeeTable
import org.springframework.http.HttpStatus

sealed class ApiResponseService
    data class SimpleResponse(val employeeList: List<EmployeeTable>,val status: HttpStatus):ApiResponseService()
    data class ErrorResponse(val status:HttpStatus,val message:String):ApiResponseService()
