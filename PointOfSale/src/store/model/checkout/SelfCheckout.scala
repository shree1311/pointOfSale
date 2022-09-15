package store.model.checkout

import store.model.items.{Item, LoyaltySale, Sale, SalesTax}


class SelfCheckout extends State {
  var state : State = new initialiseCheckout(this)
  var map : Map[String, Item] = Map()
  var dispString : String = ""
  var dispStringMemory : String = ""
  var list : List[Item] = List()
  var subTotal : Double = 0.0
  var totalTax : Double = 0.0

  def addItemToStore(barcode: String, item: Item): Unit = {
    map = map + (barcode -> item)
  }

  def numberPressed(number: Int): Unit = {
   this.state.numberPressed(number)

  }

  def clearPressed(): Unit = {
    this.state.clearPressed()
  }

  def enterPressed(): Unit = {
    this.state.enterPressed()
    this.dispString = ""
  }

  def checkoutPressed(): Unit = {
    this.state.checkoutPressed()
  }

  def cashPressed(): Unit = {
    this.state.cashPressed()
  }

  def creditPressed(): Unit = {
    this.state.creditPressed()
  }

  def loyaltyCardPressed(): Unit = {
    this.state.loyaltyCardPressed()
  }

  def displayString(): String = {
    this.state.displayString()
  }

  def itemsInCart(): List[Item] = {
    list
  }

  def subtotal(): Double = {
    subTotal
  }

  def tax(): Double = {
    totalTax
  }

  def total(): Double = {
    subTotal+totalTax
  }

  def prepareStore(): Unit = {
    // Similar to openMap in the Pale Blue Dot assignment, this method is not required and is
    // meant to help you run manual tests.
    //
    // This method is called by the GUI during setup. Use this method to prepare your
    // items and call addItemToStore to add their barcodes. Also add any sales/tax/etc to your
    // items.
    //
    // This method will not be called during testing and you should not call it in your tests.
    // Each test must setup its own items to ensure compatibility in AutoLab. However, you can
    // write a similar method in your Test Suite classes.

    // Example usage:
    //val testItem: Item = new Item("test item", 100.0)
    //this.addItemToStore("472", testItem)
    val pineapple: Item = new Item("pineapple", 10.0)
    this.addItemToStore("0", pineapple)
    val apple: Item = new Item("apple", 10.0)
    this.addItemToStore("1", apple)

    val loyaltySale : LoyaltySale = new LoyaltySale(50.0)
    val tax : SalesTax = new SalesTax(10.0)
    val sale : Sale = new Sale (50.0)
    pineapple.addModifier(loyaltySale)
    pineapple.addModifier(tax)
    pineapple.addModifier(sale)




  }

}
