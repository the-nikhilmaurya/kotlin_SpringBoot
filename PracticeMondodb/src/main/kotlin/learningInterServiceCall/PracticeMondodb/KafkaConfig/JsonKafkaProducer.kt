package learningInterServiceCall.PracticeMondodb.KafkaConfig

import learningInterServiceCall.PracticeMondodb.Model.StudentDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class JsonKafkaProducer(val kafkaTemplate: KafkaTemplate<String, StudentDto>) {
    val logger: Logger = LoggerFactory.getLogger(JsonKafkaProducer::class.java)

   fun sendMessage(data:StudentDto){
       logger.info(String.format("Message sent %s " ,data.toString()))
       // here message must be of org.springframework.messaging.Message
       val message:Message<StudentDto> = MessageBuilder
           .withPayload(data)
           .setHeader(KafkaHeaders.TOPIC,"firstTopicJson")
           .build()
       kafkaTemplate.send(message)
   }
}