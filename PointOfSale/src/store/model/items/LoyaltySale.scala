package store.model.items

class LoyaltySale (percentLoyaltySale : Double) extends Modifier {
  override def updatePrice(input: Double) : Double = {
    input
  }

  override def computeTax(price: Double): Double ={
    0.0
  }

  override def loyaltyPrice(input: Double): Double = {
    input - (percentLoyaltySale/100)*input
  }

  override def removeSale(input: Double): Double = {
    input
  }
}
