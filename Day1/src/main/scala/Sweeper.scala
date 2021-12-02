import scala.io.Source

object Sweeper {

  // change to 1 for part 1 sol, 3 for part 2
  val offset = 3

  def main(args: Array[String]): Unit = {
    val filename = "src/main/scala/Input.txt"
    val lines = io.Source.fromFile(filename).getLines.size
    var ms = new Array[Int](lines)
    var c = 0
    var i = 0
    for (line <- Source.fromFile(filename).getLines) {
      ms(i) = line.toInt
      if (i > offset -1 ) {
        println(ms(i) - ms(i-offset))
        if ((ms(i) - ms(i-offset)) > 0)
          c += 1
      }
      i += 1
    }
    println(c)

  }
}
