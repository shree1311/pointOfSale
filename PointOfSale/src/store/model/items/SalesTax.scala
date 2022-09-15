package store.model.items

class SalesTax (percentTax : Double) extends Modifier {
  override def updatePrice(price: Double): Double = {
    price
  }

  override def computeTax(price: Double): Double = {
    price * (percentTax/100.0)
  }

  override def loyaltyPrice(input: Double): Double = {
    input
  }

  override def removeSale(input: Double): Double = {
    input
  }

}
