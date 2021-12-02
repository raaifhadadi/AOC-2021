import scala.io.Source

object Day2 {

  private val filename = "src/main/scala/Input.txt"
  private var hPos = 0
  private var depth = 0
  private var aim = 0

  private def down1(n : Int): Unit = { depth += n }
  private def up1(n : Int): Unit = { depth -= n }
  private def forward1(n : Int): Unit = { hPos += n }


  private def down2(n : Int): Unit = { aim += n }
  private def up2(n : Int): Unit = { aim -= n }
  private def forward2(n : Int): Unit = {
    hPos += n
    depth += aim * n
  }


  val map1 = Map(
    "down" -> down1 _,
    "up" -> up1 _,
    "forward" -> forward1 _,
  )

  val map2 = Map(
    "down" -> down2 _,
    "up" -> up2 _,
    "forward" -> forward2 _,
  )

  private def task1(): Unit = {
    for (line <- Source.fromFile(filename).getLines) {
      val splitline = line.split(" ")
      val fn = map1(splitline(0))
      fn(splitline(1).toInt)
    }
    println(hPos * depth)
  }

  private def task2(): Unit = {
    for (line <- Source.fromFile(filename).getLines) {
      val splitline = line.split(" ")
      val fn = map2(splitline(0))
      fn(splitline(1).toInt)
    }
    println(hPos * depth)
  }

  def main(args: Array[String]): Unit = {}
    task2()
}
