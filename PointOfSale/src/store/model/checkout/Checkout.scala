package store.model.checkout

class Checkout (selfCheckout: SelfCheckout) extends State {
  var dispString : String = ""
  def numberPressed(number: Int): Unit = {}

  def clearPressed(): Unit = {}

  def enterPressed(): Unit = {}

  def checkoutPressed(): Unit = {}

  def cashPressed(): Unit = {
    selfCheckout.list = List()
    selfCheckout.subTotal = 0.0
    selfCheckout.totalTax = 0.0
    selfCheckout.state = new initialiseCheckout(selfCheckout)
  }

  def creditPressed(): Unit = {
    selfCheckout.list = List()
    selfCheckout.subTotal = 0.0
    selfCheckout.totalTax = 0.0
    selfCheckout.state = new initialiseCheckout(selfCheckout)
  }

  def loyaltyCardPressed(): Unit = {
    selfCheckout.state = new LoyaltyCheckout(selfCheckout)
  }

  def displayString(): String = {
    "cash or credit"
  }
}
