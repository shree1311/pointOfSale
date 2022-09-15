package store.model.checkout
import store.model.items.Item

class initialiseCheckout (selfCheckout: SelfCheckout) extends State {

  override def numberPressed(number: Int): Unit = {
    selfCheckout.dispString += number.toString
  }

  override def clearPressed(): Unit = {
    selfCheckout.dispString = ""
  }

  override def enterPressed(): Unit = {
    selfCheckout.state = new Enter(selfCheckout)
  }

  override def checkoutPressed(): Unit = {
    selfCheckout.state = new Checkout(selfCheckout)
  }

  override def cashPressed(): Unit = {
   ()
  }

  override def creditPressed(): Unit = {
    ()
  }

  override def loyaltyCardPressed(): Unit = {
    selfCheckout.state = new loyaltyInitialiseCheckout(selfCheckout)
    selfCheckout.dispString=""
  }

  override def displayString(): String = {
    selfCheckout.dispString
  }
}
