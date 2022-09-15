package store.model.checkout

import store.model.items.Item

class Enter (selfCheckout: SelfCheckout) extends State {
  var string: String = selfCheckout.dispString
  var itemToAdd : Item = selfCheckout.map.getOrElse(string,new Item("error", 0.0))
  selfCheckout.list :+= itemToAdd
  selfCheckout.subTotal += itemToAdd.price()
  selfCheckout.totalTax += itemToAdd.tax()

  override def numberPressed(number: Int): Unit = {
    selfCheckout.dispString += number.toString
    selfCheckout.state = new initialiseCheckout(selfCheckout)
  }

  override def clearPressed(): Unit= {
    selfCheckout.dispString = ""
    selfCheckout.state = new initialiseCheckout(selfCheckout)
  }

  override def enterPressed(): Unit = {
    selfCheckout.list :+= itemToAdd
    selfCheckout.subTotal += itemToAdd.price()
    selfCheckout.totalTax += itemToAdd.tax()
  }

  override def checkoutPressed(): Unit = {
    selfCheckout.state = new Checkout(selfCheckout)
  }

  override def cashPressed(): Unit = {
  }

  override def creditPressed(): Unit = {
  }

  override def loyaltyCardPressed(): Unit = {
    selfCheckout.state = new LoyaltyCart(selfCheckout)
  }

  override def displayString(): String = {
    selfCheckout.dispString
  }

}
