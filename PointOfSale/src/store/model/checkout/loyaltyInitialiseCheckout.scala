package store.model.checkout
import store.model.items.{Item, LoyaltySale, Sale, SalesTax}

class loyaltyInitialiseCheckout (selfCheckout: SelfCheckout) extends State {
  override def numberPressed(number: Int): Unit = {
    selfCheckout.dispString += number.toString
  }

  override def clearPressed(): Unit = {
    selfCheckout.dispString = ""
  }

  override def enterPressed(): Unit = {
    selfCheckout.state = new loyaltyPressedEnter(selfCheckout)
  }

  override def checkoutPressed(): Unit = {
    selfCheckout.state = new LoyaltyCheckout(selfCheckout)
  }

  override def cashPressed(): Unit = {
    ()
  }

  override def creditPressed(): Unit = {
    ()
  }

  override def loyaltyCardPressed(): Unit = {
    selfCheckout.state = new LoyaltyCart(selfCheckout)
  }

  override def displayString(): String = {
    selfCheckout.dispString
  }
}
