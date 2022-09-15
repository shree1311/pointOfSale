package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.Item

class Task1 extends FunSuite {

  test("your test name") {
    var testSelfCheckout: SelfCheckout = new SelfCheckout()
    var testItem: Item = new Item("test item", 100.0)
    testSelfCheckout.addItemToStore("123", testItem)
    // TODO

  }

  test("test item description"){
    val testItem : Item = new Item ("test Item", 100.0)
    assert(testItem.description()=="test Item")
  }

  test("pushing number buttons") {
    var testSelfCheckout : SelfCheckout = new SelfCheckout()
    assert(testSelfCheckout.displayString()=="")
    testSelfCheckout.numberPressed(4)
    assert(testSelfCheckout.displayString()=="4")
    testSelfCheckout.numberPressed(7)
    assert(testSelfCheckout.displayString()=="47")
    testSelfCheckout.numberPressed(2)
    assert(testSelfCheckout.displayString()=="472")
  }

  test("buying an item"){
    val checkout : SelfCheckout = new SelfCheckout()
    var testItem : Item = new Item("cheese", 12.0)
    checkout.addItemToStore("472", testItem)

    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()

    val cart: List[Item] = checkout.itemsInCart()
    assert(cart.size == 1)
    assert(cart.head.description()== "cheese")
    assert(Math.abs(cart.head.price() -12.0) < 0.001)
  }

  test("clear barcode"){
    val checkout : SelfCheckout = new SelfCheckout()
    checkout.numberPressed(4)
    checkout.numberPressed(3)
    checkout.clearPressed()

    assert(checkout.displayString()=="")
  }

  test ("same item multiple times"){
    val checkout : SelfCheckout = new SelfCheckout()
    var testItem : Item = new Item("cheese", 12.0)
    var testItem2 : Item = new Item("coal", 1.0)
    checkout.addItemToStore("472", testItem)
    checkout.addItemToStore("473", testItem2)

    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()

    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()

    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(3)
    checkout.enterPressed()

    val cart: List[Item] = checkout.itemsInCart()
    var retList: List[String] = List()
    assert(cart.size == 3)
    assert(cart.head.description()== "cheese")
    for (element <- cart){
      retList = retList :+ (element.description())
    }
    assert (retList == List("cheese","cheese","coal"))
  }

  test("invalid barcode"){
    val checkout : SelfCheckout = new SelfCheckout()

    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()

    val cart: List[Item] = checkout.itemsInCart()
    assert(cart.size == 1)
    assert(cart.head.description() == "error")
  }

  test("price change in cart"){
    val checkout : SelfCheckout = new SelfCheckout()
    var testItem : Item = new Item("cheese", 12.0)
    checkout.addItemToStore("472", testItem)

    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()

    testItem.setBasePrice(10.0)

    val cart: List[Item] = checkout.itemsInCart()
    assert(cart.size == 1)
    assert(cart.head.description()== "cheese")
    assert(Math.abs(cart.head.price() - 10.0) < 0.001)
  }

  test("change price") {
    var testSelfCheckout: SelfCheckout = new SelfCheckout()
    var testItem: Item = new Item("cheese", 12.0)
    testSelfCheckout.addItemToStore("472", testItem)
    testItem.setBasePrice(10.0)

    assert(Math.abs(testItem.price()-10.0)<0.00001)
  }

  test("leading zeros") {
    var testSelfCheckout: SelfCheckout = new SelfCheckout()
    var testItem: Item = new Item("cheese", 12.0)
    testSelfCheckout.addItemToStore("047", testItem)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(4)
    testSelfCheckout.numberPressed(7)
    testSelfCheckout.enterPressed()

    val cart : List[Item] = testSelfCheckout.itemsInCart()
    assert(cart.size == 1)
    assert(cart.head.description() == "cheese")
  }

}
