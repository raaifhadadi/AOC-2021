import scala.concurrent.Future.never.zipWith
import scala.io.Source

object Solver {

  private val filename = "src/main/scala/Input.txt"
  private var lines:List[String] = Source.fromFile(filename).getLines.toList
  private val noOfLines = lines.size
  private val lineSize = lines.head.length
  private val grid = lines.map((s:String) => s.toList).map((bs:List[Char]) => bs.map((b:Char) => Integer.parseInt(b.toString)))
  private val bitCount = grid.foldLeft(List.fill(lineSize)(0))((a: List[Int], b : List[Int]) => (a, b).zipped.map(_ + _))

  def calculate(bits:List[Int]): Int = {
    val bs = bits.map((bits:Int) => if (bits > noOfLines / 2) {1} else {0})
    val g = bs.foldLeft(0)((acc:Int, curr:Int) => acc * 2 + curr)
    val e = bs.map((b:Int) => 1-b).foldLeft(0)((acc:Int, curr:Int) => acc * 2 + curr)
    g * e
  }

  def getoxygen() : List[Int] = {
    var filtered = grid
    var tempRes = filtered.head
    var i = 0
    var common = getCommonBit(0, filtered)
    while (filtered.size > 1) {
      filtered = filtered.filter(_ (i) == common)
      if(filtered.isEmpty) {
        return tempRes
      }
      tempRes = filtered.head
      i += 1
      common = getCommonBit(i, filtered)
    }
    tempRes
  }


  def getco2() : List[Int] = {
    var filtered = grid
    var tempRes = filtered.head
    var i = 0
    var common = getUncommonBit(0, filtered)
    while (filtered.size > 1) {
      filtered = filtered.filter(_ (i) == common)
      if(filtered.isEmpty) {
        return tempRes
      }
      tempRes = filtered.head
      i += 1
      common = getUncommonBit(i, filtered)
    }
    tempRes
  }

  def getCommonBit(i : Int, grid:List[List[Int]]): Int = {
    val c = grid.foldLeft(0)((a: Int, b: List[Int]) => a + b(i))
    val diff = grid.size - c
    println(diff)
    println(c)
    if (c > grid.size / 2) {1} else {0}
  }

  def getUncommonBit(i : Int, grid:List[List[Int]]): Int = {
    val c = grid.foldLeft(0)((a: Int, b: List[Int]) => a + b(i))
    if (c <= grid.size / 2) {1} else {0}
  }

  def task2(): Int = {

    val o = getoxygen()
    println(o)

    val c = getco2()
    println(c)

    val oN = o.foldLeft(0)((acc:Int, curr:Int) => acc * 2 + curr)
    val cN = c.foldLeft(0)((acc:Int, curr:Int) => acc * 2 + curr)
    oN*cN
  }

  def task1(): Int = {
    calculate(bitCount)
  }

  def main(args: Array[String]): Unit = {
    println(task1())
    println(task2())
  }
}
