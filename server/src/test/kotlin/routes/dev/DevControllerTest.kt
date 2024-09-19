package routes.dev

import http.Routes
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test
import testCore.AbstractTest

class DevControllerTest : AbstractTest() {
    @Test
    fun `Should respond to a health check request`() = testApplication {
        val response = client.post(Routes.DEV_COMMAND, ::buildRequest)

        response.status shouldBe HttpStatusCode.NoContent
        response.bodyAsText() shouldBe ""

        verifyLog("threads")
    }

    private fun buildRequest(builder: HttpRequestBuilder) {
        builder.contentType(ContentType.Application.Json)
        builder.setBody(
            """
                {
                    "command": "${DevCommand.DUMP_THREADS.value}"
                }
            """
                .trimIndent()
        )
    }
}