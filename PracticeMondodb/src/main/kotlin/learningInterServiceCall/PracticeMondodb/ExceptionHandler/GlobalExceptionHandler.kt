package learningInterServiceCall.PracticeMondodb.ExceptionHandler

import kotlinx.serialization.json.JsonElement
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(UserDefinedExceptions::class)
    fun handleCustomException(ex: UserDefinedExceptions): ResponseEntity<JsonElement> {
        return ResponseEntity.status(ex.status).body(ex.errorBody)
    }
}