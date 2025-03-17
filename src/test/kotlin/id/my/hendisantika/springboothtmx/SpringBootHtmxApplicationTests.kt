package id.my.hendisantika.springboothtmx

import org.hamcrest.core.IsNot
import org.hamcrest.core.StringContains
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@AutoConfigureMockMvc
@SpringBootTest(classes = [SpringBootHtmxApplication::class])
class SpringBootHtmxApplicationTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun indexIsServed() {
        mockMvc.get("/")
            .andExpect {
                content { string(StringContains("<!doctype html>")) }
                status { isOk() }
            }
    }

    @Test
    fun indexContentIsServedWithHtmx() {
        mockMvc.get("/")
        {
            headers {
                set("HX-Request", "true")
            }
        }.andExpect {
            content { string(IsNot(StringContains("<!doctype html>"))) }
            status { isOk() }
        }
    }
}
