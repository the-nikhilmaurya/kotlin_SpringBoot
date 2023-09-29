package secondapp.tryingcrud.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import secondapp.tryingcrud.Entity.Students


@Repository
interface StudentRepository:JpaRepository<Students,Long>

