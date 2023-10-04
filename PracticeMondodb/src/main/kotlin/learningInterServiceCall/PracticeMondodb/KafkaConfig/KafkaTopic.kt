package learningInterServiceCall.PracticeMondodb.KafkaConfig

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder


@Configuration
class KafkaTopic {

    @Bean
    fun firstTopic():NewTopic{
        return TopicBuilder.name("firstTopic").build()  // we can create partition here also
    }

    @Bean
    fun firstTopicJson():NewTopic{
        return TopicBuilder.name("firstTopicJson").build()  // we can create partition here also
    }
}