package secondapp.tryingcrud.Entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table
data class Students (

    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long,
    val name:String,
    val surname:String,
    val section:String,
    val age:Int
)
