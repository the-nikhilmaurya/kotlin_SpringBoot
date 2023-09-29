package learningInterServiceCall.PracticeMondodb.ExceptionHandler

import kotlinx.serialization.json.JsonElement
import org.springframework.http.HttpStatus


data class UserDefinedExceptions
    (val errorBody: JsonElement,val status:HttpStatus)
    : RuntimeException()

data class UserDefinedExceptions2
    (val errorBody: JsonElement,val status:HttpStatus)
    : RuntimeException()


