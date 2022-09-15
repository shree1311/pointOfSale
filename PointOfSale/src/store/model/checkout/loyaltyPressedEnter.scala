package store.model.checkout

import store.model.items.Item

class loyaltyPressedEnter (selfCheckout: SelfCheckout) extends State {
  var string: String = selfCheckout.dispString
  var itemToAdd : Item = selfCheckout.map.getOrElse(string,new Item("error", 0.0))
  selfCheckout.list :+= new Item(itemToAdd.description(),itemToAdd.addLoyaltySale())
  selfCheckout.subTotal += itemToAdd.addLoyaltySale()
  selfCheckout.totalTax += itemToAdd.loyaltyTax()

  override def numberPressed(number: Int): Unit = {
    selfCheckout.dispString += number.toString
    selfCheckout.state = new loyaltyPressedInitalCheckout(selfCheckout)
  }

  override def clearPressed(): Unit= {
    selfCheckout.dispString = ""
    selfCheckout.state = new loyaltyPressedInitalCheckout(selfCheckout)
  }

  override def enterPressed(): Unit = {
    selfCheckout.list :+= new Item(itemToAdd.description(),itemToAdd.addLoyaltySale())
    selfCheckout.subTotal += itemToAdd.addLoyaltySale()
    selfCheckout.totalTax += itemToAdd.loyaltyTax()
  }

  override def checkoutPressed(): Unit = {
    selfCheckout.state = new loyaltyPressedCheckout(selfCheckout)
  }

  override def cashPressed(): Unit = {
  }

  override def creditPressed(): Unit = {
  }

  override def loyaltyCardPressed(): Unit = {
  }

  override def displayString(): String = {
    selfCheckout.dispString
  }

}
