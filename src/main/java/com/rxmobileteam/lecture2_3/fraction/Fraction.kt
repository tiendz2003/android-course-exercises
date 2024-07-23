package com.rxmobileteam.lecture2_3.fraction

import kotlin.math.roundToInt
import kotlin.math.roundToLong

class Fraction private constructor(
  val tuSo:Int,
  val mauSo:Int
):Comparable<Fraction>{
  private val decimal = tuSo.toDouble()/mauSo
  override fun compareTo(other: Fraction): Int {
    return decimal.compareTo(other.decimal)
  }
  init {
    // TODO: Check validity of numerator and denominator (throw an exception if invalid)
    //Mẫu số != 0
    require(mauSo!= 0){ println("Mẫu số phải khác 0")}
  }
  //region unary operators
  // TODO: "+fraction" operator
  operator fun unaryPlus(): Fraction = this
  operator fun unaryMinus(): Fraction = Fraction(-tuSo,mauSo)
  // TODO: "fraction+fraction" operator
  operator fun plus(other: Fraction):Fraction {
    val mausoChung = mauSo*(other.mauSo)
    val tusoMoi = tuSo*other.mauSo+other.tuSo*mauSo
    return Fraction.of(tusoMoi,mausoChung)
  }
  operator fun plus(other:Int):Fraction {
    return Fraction(tuSo+other*mauSo,mauSo)
  }
  operator fun times(other:Fraction):Fraction{
    val mausoMoi = mauSo*other.mauSo
    val tusoMoi = tuSo*other.tuSo
    return Fraction.of(tusoMoi,mausoMoi)
  }
  operator fun times(other:Int):Fraction {
    return Fraction(tuSo*other*tuSo,mauSo)
  }
  operator fun div(other:Fraction) :Fraction{
    val tusoMoi = tuSo* other.mauSo
    val mausoMoi =mauSo*other.tuSo
    return Fraction(tusoMoi,mausoMoi)
  }
  operator fun div(other:Int):Fraction {
    return Fraction(tuSo,mauSo*other)
  }
  // TODO: Implement copy
  fun copy(
    numerator: Int = this.tuSo,
    denominator: Int = this.mauSo
  ): Fraction = Fraction(numerator,denominator)
  //endregion
  companion object {
    @JvmStatic
    fun ofInt(number: Int): Fraction {
      return Fraction(number, 1)

    }

    @JvmStatic
    fun of(tuSo: Int, mauSo: Int): Fraction {
      // TODO: Check validity of numerator and denominator
      // TODO: Simplify fraction using the greatest common divisor
      // TODO: Finally, return the fraction with the correct values
      require(mauSo != 0)
      val ucln = timUCLN(tuSo,mauSo)
      return Fraction(tuSo/ucln,mauSo/ucln)
    }
    private fun timUCLN(a:Int,b:Int):Int{
      var num1 =a
      var num2 =b
      while (num2 !=0){
        val temp = num2
        num2 =num1 % num2
        num1 = temp
      }
      return num1
    }
  }
  override fun toString(): String {
    return "$tuSo/$mauSo"
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Fraction

    if (tuSo != other.tuSo) return false
    if (mauSo != other.mauSo) return false
    if (decimal != other.decimal) return false

    return true
  }

  override fun hashCode(): Int {
    var result = tuSo
    result = 31 * result + mauSo
    result = 31 * result + decimal.hashCode()
    return result
  }
  // TODO: return a Fraction representing "this/denominator"
  infix fun Int.over(mauSo: Int): Fraction =
    Fraction.of(this,mauSo)
  //region get extensions
// TODO: get the numerator, eg. "val (numerator) = Fraction.of(1, 2)"
  operator fun component1(): Int = tuSo

  // TODO: get the denominator, eg. "val (_, denominator) = Fraction.of(1, 2)"
  operator fun component2(): Int = mauSo

  // TODO: get the decimal, index must be 0 or 1
// TODO: eg. "val numerator = Fraction.of(1, 2)[0]"
// TODO: eg. "val denominator = Fraction.of(1, 2)[1]"
// TODO: eg. "val denominator = Fraction.of(1, 2)[2]" should throw an exception
  operator fun get(index: Int): Int = when(index){
    0-> tuSo
    1->mauSo
    else -> throw IndexOutOfBoundsException("Chỉ số $index không hợp lệ")

  }
  //region các hàm chuyển đổi số
  fun roundToInt(): Int = decimal.roundToInt()

  fun roundToLong(): Long = decimal.roundToLong()

  fun toFloat(): Float = decimal.toFloat()

  fun toDouble(): Double = decimal


//endregion

}


fun main() {
  // Plus operators
  println("1/2 + 2/3: ${Fraction.of(1, 2) + Fraction.of(2, 3)}")
  println("1/2 + 1: ${Fraction.of(1, 2) + 1}")
  println("-".repeat(80))
  // times operators
  println("1/2 * 2/3: ${Fraction.of(1, 2) * Fraction.of(2, 3)}")
  println("1/2 * 1: ${Fraction.of(1, 2) * 1}")
  println("-".repeat(80))
  // div operators
  println("1/2 : 2/3: ${Fraction.of(1, 2) / Fraction.of(2, 3)}")
  println("1/2 : 2: ${Fraction.of(1, 2) / 2}")
  println("-".repeat(80))
  // So sánh
  println("3/2 > 2/2: ${Fraction.of(3, 2) > Fraction.of(2, 2)}")
  println("1/2 <= 2/4: ${Fraction.of(1, 2) <= Fraction.of(2, 4)}")
  println("4/6 >= 2/3: ${Fraction.of(4, 6) >= Fraction.of(2, 3)}")
  println("-".repeat(80))
  // hashCode
  println("hashCode 1/2 == 2/4: ${Fraction.of(1, 2).hashCode() == Fraction.of(2, 4).hashCode()}")
  println("hashCode 1/2 == 1/2: ${Fraction.of(1, 2).hashCode() == Fraction.of(1, 2).hashCode()}")
  println("hashCode 1/3 == 3/5: ${Fraction.of(1, 3).hashCode() == Fraction.of(3, 5).hashCode()}")
  println("-".repeat(80))
  // equals
  println("1/2 == 2/4: ${Fraction.of(1, 2) == Fraction.of(2, 4)}")
  println("1/2 == 1/2: ${Fraction.of(1, 2) == Fraction.of(1, 2)}")
  println("1/3 == 3/5: ${Fraction.of(1, 3) == Fraction.of(3, 5)}")
  println("-".repeat(80))
  // Copy
  println("Copy 1/2: ${Fraction.of(1, 2).copy()}")
  println("Copy 1/2 với tử số 2: ${Fraction.of(1, 2).copy(numerator = 2)}")
  println("Copy 1/2 với mẫu số 3: ${Fraction.of(1, 2).copy(denominator = 3)}")
  println("Copy 1/2 với tử số 2 và mẫu số 3: ${Fraction.of(1, 2).copy(numerator = 2, denominator = 3)}")
  println("-".repeat(80))
  // Toán tử Component1, Component2
  val (tuSo, mauSo) = Fraction.of(1, 2)
  println("Component1, Component2 của 1/2: $tuSo, $mauSo")
  val (tuSo2, _) = Fraction.of(10, 30)
  println("Component1 của 10/30: $tuSo2")
  val (_, mauSo2) = Fraction.of(10, 79)
  println("Component2 của 10/79: $mauSo2")
  println("-".repeat(80))
  // Toán tử get
  println("Get 0 của 1/2: ${Fraction.of(1, 2)[0]}")
  println("Get 1 của 1/2: ${Fraction.of(1, 2)[1]}")
  println("Get 2 của 1/2: ${runCatching { Fraction.of(1, 2)[2] }}") // Sẽ in "Failure(...)"
  println("-".repeat(80))

  // toInt, toLong, toFloat, toDouble
  println("toInt 1/2: ${Fraction.of(1, 2).roundToInt()}")
  println("toLong 1/2: ${Fraction.of(1, 2).roundToLong()}")
  println("toFloat 1/2: ${Fraction.of(1, 2).toFloat()}")
  println("toDouble 1/2: ${Fraction.of(1, 2).toDouble()}")
  println("-".repeat(80))

  // Range
  // Because we implemented Comparable<Fraction>, we can use Fraction in ranges
  val range = Fraction.of(1, 2)..Fraction.of(2, 3)
  println("1/2 in range 1/2..2/3: ${Fraction.of(1, 2) in range}") // "in" operator is "contains"
  println("2/3 in range 1/2..2/3: ${Fraction.of(2, 3) in range}")
  println("7/12 in range 1/2..2/3: ${Fraction.of(7, 12) in range}")
  println("-".repeat(80))
}
