package store.model.items

class Sale (percentSale : Double) extends Modifier {
  override def updatePrice(input: Double) : Double = {
    input - (input * (percentSale / 100.0))
  }

  override def computeTax(price: Double): Double = {
    0.0
  }

  override def loyaltyPrice(input: Double): Double = {
    input
  }

  override def removeSale(input: Double): Double = {
    input + ((input*2)*(percentSale/100))
  }
}
