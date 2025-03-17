package id.my.hendisantika.springboothtmx

import io.micrometer.observation.Observation
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-htmx
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/03/25
 * Time: 23.34
 * To change this template use File | Settings | File Templates.
 */
class StreamLambdaHandler : RequestStreamHandler {
    @Throws(IOException::class)
    override fun handleRequest(inputStream: InputStream, outputStream: OutputStream, context: Observation.Context) {
        handler!!.proxyStream(inputStream, outputStream, context)
    }

}
