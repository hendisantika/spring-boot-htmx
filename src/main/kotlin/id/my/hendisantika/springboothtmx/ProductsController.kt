package id.my.hendisantika.springboothtmx

import org.springframework.stereotype.Controller

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-htmx
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/03/25
 * Time: 23.32
 * To change this template use File | Settings | File Templates.
 */
@Controller
class ProductsController {
    val products = listOf(
        Product(id = "one"),
        Product(desc = "The detail page for this product will only work when the requests lands on the same lamda.")
    )

}
