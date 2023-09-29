package learningInterServiceCall.PracticeMondodb.Services

import learningInterServiceCall.PracticeMondodb.Model.MyStudents
import org.springframework.http.HttpStatus

sealed class ApiResponseService
    data class StudentResponse(val StudentList:List<MyStudents>):ApiResponseService()
    data class Response(val Student:MyStudents):ApiResponseService()
    data class ErrorResponse(val message:String,val status: HttpStatus):ApiResponseService()
