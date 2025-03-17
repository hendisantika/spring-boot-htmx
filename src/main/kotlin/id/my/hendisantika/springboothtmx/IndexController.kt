package id.my.hendisantika.springboothtmx

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-htmx
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/03/25
 * Time: 23.31
 * To change this template use File | Settings | File Templates.
 */
@Controller
class IndexController {
    @GetMapping("/", headers = ["HX-Request=true"])
    fun getIndexHtmx(): ModelAndView {
        return ModelAndView("fragments/index")
    }
}
