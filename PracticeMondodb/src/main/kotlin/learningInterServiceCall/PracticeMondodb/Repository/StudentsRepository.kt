package learningInterServiceCall.PracticeMondodb.Repository

import learningInterServiceCall.PracticeMondodb.Model.MyStudents
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentsRepository : ReactiveMongoRepository<MyStudents, String>
//interface StudentsRepository : MongoRepository<MyStudents,String>
