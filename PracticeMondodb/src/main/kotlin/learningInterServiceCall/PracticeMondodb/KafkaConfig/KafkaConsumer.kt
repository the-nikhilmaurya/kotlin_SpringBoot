package learningInterServiceCall.PracticeMondodb.KafkaConfig

import kotlinx.serialization.json.Json
import learningInterServiceCall.PracticeMondodb.ExceptionHandler.UserDefinedExceptions
import learningInterServiceCall.PracticeMondodb.Model.StudentDto
import learningInterServiceCall.PracticeMondodb.Services.Server2
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumer(val server2:Server2) {

    val logger: Logger = LoggerFactory.getLogger(KafkaConsumer::class.java)

    @KafkaListener(topics= ["firstTopic"], groupId = "myGroup")
    fun consume(message:String){
        logger.info(String.format("Message received %s " ,message))
    }

    @KafkaListener(topics= ["firstTopicJson"], groupId = "myGroup")
    fun consumeJson(student:StudentDto) {
        logger.info(String.format("Json Message received  %s " ,student))
        try {
            server2.createStudent(student).subscribe()
        }catch (e:Exception){
            val json = """{"message": "Not able to perform"}"""
            throw UserDefinedExceptions(Json.parseToJsonElement(json), HttpStatus.CONFLICT)
        }

    }
}