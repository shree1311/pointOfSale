package store.model.checkout

import store.model.items.Item
import store.model.items.LoyaltySale
import store.model.items.Sale

class LoyaltyCheckout (selfCheckout: SelfCheckout) extends State {
  var cart : List[Item] = selfCheckout.itemsInCart()
  selfCheckout.subTotal = 0.0
  selfCheckout.totalTax = 0.0
  selfCheckout.list=List()

  for (item <- cart){
    var newItem : Item = new Item(item.description(),item.addLoyaltySale())
    newItem.modlist = item.modlist
    selfCheckout.list :+= newItem
    newItem.removeSalePrice()
    selfCheckout.subTotal += item.addLoyaltySale()
    selfCheckout.totalTax += item.loyaltyTax()
  }


  override def numberPressed(number: Int): Unit = {
  }

  override def clearPressed(): Unit = {
  }

  override def enterPressed(): Unit = {
  }

  override def checkoutPressed(): Unit = {
  }

  override def cashPressed(): Unit = {
    selfCheckout.list = List()
    selfCheckout.subTotal = 0.0
    selfCheckout.totalTax = 0.0
    selfCheckout.state = new initialiseCheckout(selfCheckout)
  }

  override def creditPressed(): Unit = {
    selfCheckout.list = List()
    selfCheckout.subTotal = 0.0
    selfCheckout.totalTax = 0.0
    selfCheckout.state = new initialiseCheckout(selfCheckout)
  }

  override def loyaltyCardPressed(): Unit = {
  }

  override def displayString(): String = {
    "loyalty sales applied!"
  }
}
