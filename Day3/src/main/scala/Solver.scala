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

  def getByCriteria(criteria : List[Int]) : List[Int] = {
    var filtered = grid
    var tempRes = grid.head
    for (i <- criteria.indices) {
      filtered = filtered.filter(_ (i) == criteria(i))
      if(filtered.isEmpty) {
        return tempRes
      }
      tempRes = filtered.head
    }
    tempRes
  }

  def task2(): Int = {
    val oCriteria = bitCount.map((bits:Int) => if (bits >= noOfLines / 2) {1} else {0})
    val cCriteria = bitCount.map((bits:Int) => if (bits > noOfLines / 2) {0} else {1})
    val o = getByCriteria(oCriteria)
    val c = getByCriteria(cCriteria)
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
