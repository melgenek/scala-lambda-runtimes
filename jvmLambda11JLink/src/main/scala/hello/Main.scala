package hello

import java.net.URI
import java.net.http.{HttpClient, HttpRequest, HttpResponse}

object Main extends App {
  val api = sys.env("AWS_LAMBDA_RUNTIME_API")

  while (true) {
    val response = HttpClient.newBuilder().build().send(
      HttpRequest.newBuilder()
        .uri(new URI(s"http://$api/2018-06-01/runtime/invocation/next"))
        .GET()
        .build(),
      HttpResponse.BodyHandlers.discarding()
    )
    val requestId = response.headers().firstValue("Lambda-Runtime-Aws-Request-Id").get()

    HelloFunction.handle()

    HttpClient.newBuilder().build().send(
      HttpRequest.newBuilder()
        .uri(new URI(s"http://$api/2018-06-01/runtime/invocation/$requestId/response"))
        .POST(HttpRequest.BodyPublishers.ofString("SUCCESS!!!"))
        .build(),
      HttpResponse.BodyHandlers.discarding()
    )
  }
}
