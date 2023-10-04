package learningInterServiceCall.PracticeMondodb.Model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document



@Document
data class MyStudents (

    @Id
    val username:String,
    val name:String ,
    val surname:String,
    val section:String,
    val age:Int

    )