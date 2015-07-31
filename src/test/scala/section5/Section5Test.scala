package section5

import org.scalacheck.Gen
import org.scalatest.prop.PropertyChecks
import org.scalatest.{Matchers, FunSuite}
import scala.util.{Failure, Success}
import java.net.URL
import java.io.ByteArrayOutputStream

class Section5Test extends FunSuite with Matchers with PropertyChecks {

  test("Math.abs must be >= 0") {
    forAll { (i: Int) =>
      whenever(i != Int.MinValue) {
        Math.abs(i) should be >= 0
      }
    }
  }

  val httpProtocolGen = Gen.oneOf("http", "https", "ftp")
  val validUrl = for {
    protocol <- httpProtocolGen
    domain <- Gen.alphaStr
  } yield protocol + "://" + domain

  test("parseURL can parse a regular URL bis") {
    forAll(validUrl) { url =>
      println(url)
      Section5.parseURL(url) shouldBe a [Success[_]]
    }
  }

  test("parseURL can parse a regular URL") {
    Section5.parseURL("http://en.wikipedia.org/") shouldBe a [Success[_]]
  }

  test("parseURL cannot parse a malformed URL") {
    Section5.parseURL("htp://en.wikipedia.org/") shouldBe a [Failure[_]]
  }

  test("defaultSearchEngine should use duckduckgo as default") {
    val duckduckgo = new URL("http://duckduckgo.com")
    Section5.defaultSearchEngine("garbage") shouldEqual duckduckgo
  }

  test("getProtocol should read a valid url") {
    Section5.getProtocol("http://en.wikipedia.org/") shouldEqual Success("http")
  }

  test("getProtocol should handle invalid url") {
    Section5.getProtocol("htp://en.wikipedia.org/") shouldBe a [Failure[_]]
  }

  test("readURLContent should output the error") {
    val output = new ByteArrayOutputStream
    Section5.readURLContent("garbage", output)
    output.toString shouldEqual "Problem rendering URL content: no protocol: garbage"
  }

  test("getURLContentWithErrorMessage should report invalid URL") {
    val result = Section5.getURLContentWithErrorMessage("garbage")
    result shouldBe a [Success[_]]
    result.get.next() shouldEqual "Please make sure to enter a valid URL"
  }

  test("getURLContentWithErrorMessage should report non existing page") {
    val result = Section5.getURLContentWithErrorMessage("http://en.wikipedia.org/blabla")
    result shouldBe a [Success[_]]
    result.get.next() shouldEqual "Requested page does not exist"
  }

  test("getURLContentWithErrorMessage should fetch website content") {
    val result = Section5.getURLContentWithErrorMessage("https://en.wikipedia.org/wiki/Scala_(programming_language)")
    result shouldBe a [Success[_]]
    result.get.next() shouldEqual "<!DOCTYPE html>"
  }

}
