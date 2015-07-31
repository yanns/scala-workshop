package section5

import java.net._
import scala.util.{Failure, Success, Try}
import scala.io.Source
import java.io.{FileNotFoundException, ByteArrayOutputStream}
import scala.util.Success
import scala.util.Failure

object Section5 {

  /**
   * parse an URL
   * @return
   *         Success[URL] when the url is valid
   *         Failure[Throwable] when the url is not valid
   */
  def parseURL1(url: String): Try[URL] =
    try {
      val _url = new URL(url)
      Success(_url)
    } catch {
      case e: Exception => Failure(e)
    }

  def parseURL(url: String): Try[URL] =
    Try(new URL(url))

  /**
   * uses the URL parsed from the user input
   * If the user input is not a valid URL, use 'http://duckduckgo.com' instead
   */
  def defaultSearchEngine1(userInput: String): URL =
    parseURL(userInput) match {
      case Success(value) => value
      case Failure(_) => new URL("http://duckduckgo.com")
    }

  def defaultSearchEngine(userInput: String): URL =
    parseURL(userInput).getOrElse(new URL("http://duckduckgo.com"))


  /**
   * @return the protocol ('http', 'ftp'...) of the URL if valid
   */
  def getProtocol1(url: String): Try[String] =
    parseURL(url).map(url => url.getProtocol)

  def getProtocol(url: String): Try[String] =
    parseURL(url) match {
      case Success(v) => Success(v.getProtocol)
      case Failure(e) => Failure(e)
    }


  /**
   * reads the content of an url and send each line into output.
   * Use getURLContent for this.
   * You can use java.lang.String.getBytes
   * In case of error, output s"Problem rendering URL content: ${ex.getMessage}".getBytes
   * @param url url to read the content from
   * @param output to write the read lines from website
   */
  def readURLContent(url: String, output: ByteArrayOutputStream) = {
    getURLContent(url) match {
      case Failure(ex) => output.write(s"Problem rendering URL content: ${ex.getMessage}".getBytes)
      case Success(v) => v.foreach(line => output.write(line.getBytes))
    }
  }


  /**
   * reads the content of an url and returns an iterator of all read lines.
   * Use getURLContent for this.
   * In case of FileNotFoundException, delivers one line "Requested page does not exist"
   * In case of MalformedURLException, delivers one line "Please make sure to enter a valid URL"
   */
  def getURLContentWithErrorMessage1(url: String): Try[Iterator[String]] =
    getURLContent(url).recover {
      case _: FileNotFoundException => Iterator("Requested page does not exist")
      case _: MalformedURLException => Iterator("Please make sure to enter a valid URL")
    }

  def getURLContentWithErrorMessage(url: String): Try[Iterator[String]] =
    getURLContent(url) match {
      case Success(v) => Success(v)
      case Failure(e: FileNotFoundException) => Success(Iterator("Requested page does not exist"))
      case Failure(e: MalformedURLException) => Success(Iterator("Please make sure to enter a valid URL"))
    }

  /**
   * @param url webpage to read
   * @return iterator of all read lines from url
   */
  def getURLContent(url: String): Try[Iterator[String]] = {
    for {
      url <- parseURL(url)
      connection <- Option(System.getenv("http_proxy")).map { systemProxy =>
        println(s"Using system proxy '$systemProxy'")
        val uri = new java.net.URI(systemProxy)
        val proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(uri.getHost, uri.getPort))
        Try(url.openConnection(proxy))
      } getOrElse Try(url.openConnection())
      httpConnection <- httpConnectionIfOK(connection)
      is <- Try(httpConnection.getInputStream)
      source = Source.fromInputStream(is)
    } yield {
      source.getLines()
    }
  }

  private def httpConnectionIfOK(connection: URLConnection): Try[HttpURLConnection] = {
    connection match {
      case http: HttpURLConnection => Try(http.getResponseCode) flatMap {
        case 200 => Success(http)
        case badStatus => Failure(new FileNotFoundException(s"http connection delivers status '$badStatus'"))
      }
      case _ => Failure(new Exception("not an http connection"))
    }
  }

}
