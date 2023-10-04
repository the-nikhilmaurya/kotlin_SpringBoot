package learningInterServiceCall.PracticeMondodb

import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@SpringBootTest
@AutoConfigureMockMvc
class StudentsTest(val mockMvc: MockMvc) {

//    @Throws
    @Test
    fun test_add_Endpoint(){
        mockMvc.perform(MockMvcRequestBuilders.get("/students/add")
            .param("a", "5")
            .param("b", "3"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("8"));
    }

}