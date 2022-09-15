package tests
import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.{BottleDeposit, Item, Sale, SalesTax}

class Task3 extends FunSuite{
  test ("add 1 item to cart"){
    val checkout: SelfCheckout = new SelfCheckout()
    val pineapple: Item = new Item("pineapple", 10.0)
    checkout.addItemToStore("0", pineapple)
    val sale: Sale = new Sale(50.0)
    val tax : SalesTax = new SalesTax(10.0)
    pineapple.addModifier(tax)
    pineapple.addModifier(sale)
    checkout.numberPressed(0)
    checkout.enterPressed()

    val cart : List[Item] = checkout.itemsInCart()
    assert(cart.size == 1)
    assert(cart.head.description() == "pineapple")
    assert(Math.abs(cart.head.price()-5.0)<0.001)
    assert(Math.abs(cart.head.tax()-0.50)<0.001)
    assert(Math.abs(checkout.total()-5.50)<0.001)
  }

  test ("rescan item with enter"){
    val checkout: SelfCheckout = new SelfCheckout()
    val pineapple: Item = new Item("pineapple", 10.0)
    checkout.addItemToStore("0", pineapple)
    checkout.numberPressed(0)
    checkout.enterPressed()
    checkout.enterPressed()

    val cart : List[Item] = checkout.itemsInCart()
    assert(cart.size == 2)
    assert(cart.head.description() == "pineapple")
    assert(cart(1).description() == "pineapple")
    assert(Math.abs(cart.head.price()-10.0)<0.001)
    assert(Math.abs(cart(1).price()-10.0)<0.001)
  }

  test("checkout clears cart"){
    val checkout: SelfCheckout = new SelfCheckout()
    val pineapple: Item = new Item("pineapple", 10.0)
    checkout.addItemToStore("0", pineapple)
    checkout.numberPressed(0)
    checkout.enterPressed()
    checkout.enterPressed()

    val cart : List[Item] = checkout.itemsInCart()
    assert(cart.size == 2)
    assert(cart.head.description() == "pineapple")
    assert(cart(1).description() == "pineapple")

    checkout.checkoutPressed()
    assert(checkout.displayString()=="cash or credit")
    checkout.cashPressed()
    assert(checkout.itemsInCart()==List())
  }

//  test("pressing cash during scanning"){
//    val checkout: SelfCheckout = new SelfCheckout()
//    val apple : Item = new Item ("apple", 100.0)
//    checkout.addItemToStore("111",apple)
//
//    checkout.numberPressed(1)
//    checkout.numberPressed(1)
//    assert(checkout.displayString()=="11")
//    checkout.numberPressed(1)
//    checkout.cashPressed()
//
//   checkout.enterPressed()
//    assert(checkout.itemsInCart().length==1)
//    assert(checkout.itemsInCart().head.description()=="apple")
//    assert(Math.abs(checkout.itemsInCart().head.price()-100.0)<0.001)
//  }


//  test("pressing cash or credit during scanning"){
//    val checkout: SelfCheckout = new SelfCheckout()
//    val pineapple: Item = new Item("pineapple", 10.0)
//    checkout.addItemToStore("0", pineapple)
//    val apple : Item = new Item ("apple", 100.0)
//    checkout.addItemToStore("111",apple)
//    checkout.numberPressed(1)
//    checkout.numberPressed(1)
//    checkout.cashPressed()
//    assert(checkout.displayString()=="11")
//    checkout.enterPressed()
//
//    var cart : List[Item] = checkout.itemsInCart()
//    assert(cart.size == 1)
//    assert(cart.head.description()=="error")
//    assert(Math.abs(cart.head.price()-0.0)<0.000001)
//
//    checkout.numberPressed(1)
//    checkout.numberPressed(1)
//    checkout.numberPressed(1)
//    checkout.creditPressed()
//    assert(checkout.displayString()=="111")
//    checkout.enterPressed()
//
//    cart = checkout.itemsInCart()
//    assert(cart.size == 2)
//    assert(cart.head.description()=="error")
//    assert(Math.abs(cart.head.price()-0.0)<0.000001)
//    assert(cart(1).description()=="apple")
//    assert(Math.abs(cart(1).price()-100.0)<0.000001)
//  }

}
