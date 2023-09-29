package AuthPract2.practice2.Repository

import AuthPract2.practice2.Modal.EmployeeTable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface EmployeeRepository : JpaRepository<EmployeeTable, String> {
    fun  findByEmail(email:String):Optional<EmployeeTable>
    fun deleteByEmail(email: String)
}