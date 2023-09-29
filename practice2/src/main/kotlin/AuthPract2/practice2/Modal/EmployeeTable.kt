package AuthPract2.practice2.Modal

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table



@Entity
@Table(name="employee")
data class EmployeeTable (

    @Id val email:String,
    val name:String,
    var password:String
)