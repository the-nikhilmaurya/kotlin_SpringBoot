package learningInterServiceCall.PracticeMondodb.MyController

import learningInterServiceCall.PracticeMondodb.KafkaConfig.JsonKafkaProducer
import learningInterServiceCall.PracticeMondodb.KafkaConfig.KafkaProducer
import learningInterServiceCall.PracticeMondodb.Model.StudentDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("kafka")
class KafkaController
    (val kafkaProducer: KafkaProducer,
     val jsonKafkaProducer: JsonKafkaProducer)
{


    @GetMapping("publish")
    fun publish(@RequestParam message:String):String{
        kafkaProducer.sendMessage(message)
        return "Message sent to the topic"
    }

    @PostMapping("publishJson")
    fun publishJson(@RequestBody data:StudentDto):ResponseEntity<String>{
        jsonKafkaProducer.sendMessage(data)
        return ResponseEntity.ok().body("Json Message sent to kafka topic : firstTopic")
    }
}