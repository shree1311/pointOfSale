package store.model.checkout

import store.model.items.Item

class loyaltyEnter (selfCheckout: SelfCheckout) extends State {
  var string: String = selfCheckout.dispString
  var itemToAdd : Item = selfCheckout.map.getOrElse(string,new Item("error", 0.0))
  var newItem : Item = new Item(itemToAdd.description(),itemToAdd.addLoyaltySale())
  newItem.modlist = itemToAdd.modlist
  selfCheckout.list :+= newItem
  selfCheckout.subTotal += itemToAdd.addLoyaltySale()
  selfCheckout.totalTax += itemToAdd.loyaltyTax()

  override def numberPressed(number: Int): Unit = {
    selfCheckout.dispString += number.toString
    selfCheckout.state = new loyaltyInitialiseCheckout(selfCheckout)
  }

  override def clearPressed(): Unit= {
    selfCheckout.dispString = ""
    selfCheckout.state = new loyaltyInitialiseCheckout(selfCheckout)
  }

  override def enterPressed(): Unit = {
    selfCheckout.list :+= newItem
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
