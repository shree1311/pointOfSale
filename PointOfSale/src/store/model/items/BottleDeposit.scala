package store.model.items

class BottleDeposit (totalDeposit : Double) extends Modifier {
  override def updatePrice(price: Double): Double = {
    price
  }

  override def computeTax(price: Double): Double = {
    totalDeposit
  }

  override def loyaltyPrice(input: Double): Double = {
    input
  }

  override def removeSale(input: Double): Double = {
    input
  }
}