package store.model.checkout

import com.sun.javafx.scene.traversal.SceneTraversalEngine
import store.model.items.{Item, LoyaltySale, Modifier}

class LoyaltyCart (selfCheckout: SelfCheckout) extends State {
  var cart : List[Item] = selfCheckout.list
  selfCheckout.subTotal = 0.0
  selfCheckout.totalTax = 0.0
  selfCheckout.list = List()

  
  for (item <- cart){
    var newItem : Item = new Item(item.description(),item.addLoyaltySale())
    newItem.modlist = item.modlist
    selfCheckout.list :+= newItem
    newItem.removeSalePrice()
    selfCheckout.subTotal += item.addLoyaltySale()
    selfCheckout.totalTax += item.loyaltyTax()
  }


  override def numberPressed(number: Int): Unit = {
    selfCheckout.dispString += number.toString
    selfCheckout.state = new loyaltyPressedInitalCheckout(selfCheckout)
  }

  override def clearPressed(): Unit = {
    selfCheckout.dispString = ""
    selfCheckout.state = new loyaltyInitialiseCheckout(selfCheckout)
  }

  override def enterPressed(): Unit = {
    selfCheckout.state = new loyaltyPressedEnter(selfCheckout)
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
    "loyalty sales applied!"
  }
}
