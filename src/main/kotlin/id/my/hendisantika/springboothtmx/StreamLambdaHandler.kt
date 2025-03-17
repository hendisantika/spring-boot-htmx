package id.my.hendisantika.springboothtmx

import com.amazonaws.serverless.exceptions.ContainerInitializationException
import com.amazonaws.serverless.proxy.model.AwsProxyResponse
import com.amazonaws.serverless.proxy.model.HttpApiV2ProxyRequest
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
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
    override fun handleRequest(inputStream: InputStream?, outputStream: OutputStream?, context: Context?) {
        handler!!.proxyStream(inputStream, outputStream, context)
    }

    companion object {

        private var handler: SpringBootLambdaContainerHandler<HttpApiV2ProxyRequest, AwsProxyResponse>? = null

        init {
            try {
                handler =
                    SpringBootLambdaContainerHandler.getHttpApiV2ProxyHandler(SpringBootHtmxApplication::class.java)
            } catch (e: ContainerInitializationException) {
                // if we fail here. We re-throw the exception to force another cold start
                e.printStackTrace()
                throw RuntimeException("Could not initialize Spring Boot application", e)
            }
        }
    }
}
