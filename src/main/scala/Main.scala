import org.jsoup.Jsoup
import scala.collection.JavaConversions._

object Main extends App {
  val site = Jsoup.connect("http://www.quantbet.com/quiz/dev").execute

  val numbers = site.parse.select("strong").map(x => BigInt.apply(x.text.toLong))

  val submission = Jsoup.connect("http://www.quantbet.com/submit")
                        .data("divisor", FindGCFUsingEuclids(numbers.head, numbers(1)).toString)
                        .header("Content-Type","application/x-www-form-urlencoded")
                        .cookie("laravel_session",  site.cookie("laravel_session"))
                        .post

  println(submission.body.select(".quiz-content"))

  def FindGCFUsingEuclids(x: BigInt, y: BigInt): BigInt = {
      if(y == 0)return x

    FindGCFUsingEuclids(y, x % y)
  }
}