package learningInterServiceCall.PracticeMondodb

import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import java.util.*

//@Component
@EnableScheduling
class SchedulePractice {

//    @Scheduled(fixedDelay = 2000)
//    @Scheduled(fixedDelayString = "PT2S")    // java time duration doc
//    @Scheduled(fixedRate = 2000)        // Thread.sleep won;t work in this case
    @Scheduled(cron = "*/2 * * * * *")           // cron guru can help us continue
    fun job(){
        println("Job current time ${Date()}")
        Thread.sleep(1000)
    }

    @Scheduled(cron = "*/2 * * * * *")           // cron guru can help us continue
    fun job2(){
        println("Job2 current time ${Date()}")
        Thread.sleep(1000)
    }

    // at a time only single schedule can run the other will wait for their turn
}