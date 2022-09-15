package store.model.items

abstract class Modifier (){
  def updatePrice(input: Double) : Double

  def computeTax(price: Double): Double

  def loyaltyPrice (input : Double): Double

  def removeSale(input:Double) : Double
}
