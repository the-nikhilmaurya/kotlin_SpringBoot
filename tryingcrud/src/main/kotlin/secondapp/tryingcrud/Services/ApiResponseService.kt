package secondapp.tryingcrud.Services

import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import secondapp.tryingcrud.Entity.Students

sealed class ApiResponseService
    data class StudentResponse(val student: Students):ApiResponseService()
    data class MessageResponse(val status:HttpStatus,val message:String):ApiResponseService()
    data class StudentListResponse(val listOfStudent: List<Students>):ApiResponseService()
    data class StudentPageResponse(val listOfStudent: Page<Students>):ApiResponseService()
