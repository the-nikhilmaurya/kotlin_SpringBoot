package learningInterServiceCall.PracticeMondodb.KafkaConfig

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(val kafkaTemplate: KafkaTemplate<String,String>)
{
    val logger: Logger = LoggerFactory.getLogger(KafkaProducer::class.java)

    fun sendMessage(message:String){
        logger.info(String.format("Message sent %s " ,message))         //%s for placeholder
        kafkaTemplate.send("firstTopic",message)
    }
}