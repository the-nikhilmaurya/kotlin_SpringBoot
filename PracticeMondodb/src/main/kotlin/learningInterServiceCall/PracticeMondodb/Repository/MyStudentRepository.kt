package learningInterServiceCall.PracticeMondodb.Repository

import learningInterServiceCall.PracticeMondodb.Model.MyStudents
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MyStudentRepository : MongoRepository<MyStudents,String>
{
    fun findByusername(username:String):List<MyStudents>
}
