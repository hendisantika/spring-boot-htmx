package id.my.hendisantika.springboothtmx

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.servlet.ModelAndView

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

    @GetMapping("/products")
    fun getProducts(): ModelAndView {
        return ModelAndView("sites/products", mapOf("products" to products))
    }

    @GetMapping("/products", headers = ["HX-Request=true"])
    fun getProductsHtmx(): ModelAndView {
        return ModelAndView("fragments/products", mapOf("products" to products))
    }

    @GetMapping("/products/{id}")
    fun getProduct(@PathVariable id: String): ModelAndView {
        return ModelAndView("sites/product", mapOf("product" to getProductById(id)))
    }
}

