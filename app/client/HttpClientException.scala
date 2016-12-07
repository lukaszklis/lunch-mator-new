package client

case class HttpClientException(requestUrl: String, requestBody: String, response: String, responseStatus: Int) extends RuntimeException {
}