package tests
import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.{BottleDeposit, Item, LoyaltySale, Sale, SalesTax}

class ApplicationObjective extends FunSuite{
  test("add loyalty sale to one item"){
    val checkout: SelfCheckout = new SelfCheckout()
    val pineapple: Item = new Item("pineapple", 10.0)
    checkout.addItemToStore("0", pineapple)
    val loyaltySale : LoyaltySale = new LoyaltySale(50.0)
    pineapple.addModifier(loyaltySale)
    checkout.numberPressed(0)
    checkout.enterPressed()
    checkout.checkoutPressed()
    checkout.loyaltyCardPressed()
    assert(checkout.itemsInCart().size == 1)
    assert(Math.abs(checkout.itemsInCart().head.price()-5.0)<0.001)
    assert(Math.abs(checkout.total()-5.0)<0.01)
  }

  test ("loyalty sale not pressed"){
    val checkout: SelfCheckout = new SelfCheckout()
    val pineapple: Item = new Item("pineapple", 10.0)
    checkout.addItemToStore("0", pineapple)
    val loyaltySale : LoyaltySale = new LoyaltySale(50.0)
    pineapple.addModifier(loyaltySale)
    checkout.numberPressed(0)
    checkout.enterPressed()
    assert(checkout.itemsInCart().size == 1)
    assert(Math.abs(checkout.total()-10.0)<0.01)
  }

  test ("resets for next customer"){
    val checkout: SelfCheckout = new SelfCheckout()
    val pineapple: Item = new Item("pineapple", 10.0)
    checkout.addItemToStore("0", pineapple)
    val loyaltySale : LoyaltySale = new LoyaltySale(50.0)
    val loyaltySale2 : LoyaltySale = new LoyaltySale(10.0)
    pineapple.addModifier(loyaltySale)
    pineapple.addModifier(loyaltySale2)

    checkout.numberPressed(0)
    checkout.enterPressed()
    checkout.checkoutPressed()
    checkout.loyaltyCardPressed()
    assert(checkout.itemsInCart().size == 1)
    assert(Math.abs(checkout.total()-4.5)<0.01)

    checkout.cashPressed()
    assert(checkout.itemsInCart().size==0)
    assert(Math.abs(checkout.subtotal()-0.0)<0.0001)
    assert(Math.abs(checkout.tax()-0.0)<0.0001)
    assert(Math.abs(checkout.total()-0.0)<0.0001)

    checkout.numberPressed(0)
    checkout.enterPressed()
    assert(checkout.itemsInCart().size==1)
    assert(Math.abs(checkout.itemsInCart().head.price()-10.0)<0.001)
    assert(Math.abs(10.0-checkout.total())<0.001)
  }

  test ("multiple loyalty sales"){
    val checkout: SelfCheckout = new SelfCheckout()
    val pineapple: Item = new Item("pineapple", 10.0)
    checkout.addItemToStore("0", pineapple)
    val apple: Item = new Item("apple", 10.0)
    checkout.addItemToStore("1", apple)
    val loyaltySale : LoyaltySale = new LoyaltySale(50.0)
    val loyaltySale2 : LoyaltySale = new LoyaltySale(10.0)
    pineapple.addModifier(loyaltySale)
    apple.addModifier(loyaltySale2)

    checkout.numberPressed(0)
    checkout.enterPressed()
    checkout.numberPressed(1)
    checkout.enterPressed()
    checkout.loyaltyCardPressed()


    assert(checkout.itemsInCart().size==2)
    assert(checkout.itemsInCart().head.description()=="pineapple")
    assert(checkout.itemsInCart()(1).description()=="apple")
    assert(Math.abs(14.0-checkout.total())<0.001)
    assert(Math.abs(14.0-checkout.subtotal())<0.001)
  }

  test ("press loyalty multiple times"){
    val checkout: SelfCheckout = new SelfCheckout()
    val pineapple: Item = new Item("pineapple", 10.0)
    checkout.addItemToStore("0", pineapple)
    val apple: Item = new Item("apple", 10.0)
    checkout.addItemToStore("1", apple)
    val loyaltySale : LoyaltySale = new LoyaltySale(50.0)
    val tax : SalesTax = new SalesTax(10.0)
    val loyaltySale2 : LoyaltySale = new LoyaltySale(10.0)
    pineapple.addModifier(loyaltySale)
    apple.addModifier(loyaltySale2)
    pineapple.addModifier(tax)

    checkout.numberPressed(0)
    checkout.enterPressed()
    checkout.numberPressed(1)
    checkout.enterPressed()
    checkout.numberPressed(0)
    checkout.enterPressed()
    checkout.enterPressed()
    checkout.loyaltyCardPressed()
    checkout.loyaltyCardPressed()
    checkout.loyaltyCardPressed()


    assert(checkout.itemsInCart().size==4)
    assert(checkout.itemsInCart().head.description()=="pineapple")
    assert(Math.abs(checkout.itemsInCart().head.tax()-0.5)<0.001)
    assert(checkout.itemsInCart()(1).description()=="apple")
    assert(Math.abs(checkout.itemsInCart()(1).tax()-0.0)<0.01)
    assert(Math.abs(25.5-checkout.total())<0.001)
    assert(Math.abs(24.0-checkout.subtotal())<0.001)
    assert(Math.abs(1.50-checkout.tax())<0.001)
  }

  test ("add all modifiers"){
    val checkout: SelfCheckout = new SelfCheckout()

    val pineapple: Item = new Item("pineapple", 10.0)
    checkout.addItemToStore("0", pineapple)
    val apple: Item = new Item("apple", 10.0)
    checkout.addItemToStore("1", apple)

    val loyaltySale : LoyaltySale = new LoyaltySale(50.0)
    val tax : SalesTax = new SalesTax(10.0)
    val sale : Sale = new Sale (50.0)
    pineapple.addModifier(loyaltySale)
    pineapple.addModifier(tax)
    pineapple.addModifier(sale)

    checkout.numberPressed(0)
    checkout.enterPressed()
    checkout.loyaltyCardPressed()

    assert(Math.abs(checkout.itemsInCart().head.price()-2.5)<0.001)
    assert(Math.abs(checkout.itemsInCart().head.tax()-0.25)<0.001)
    assert(Math.abs(checkout.subtotal()-2.5)<0.001)
    assert(Math.abs(checkout.tax()-0.25)<0.001)
    assert(Math.abs(checkout.total()-2.75)<0.001)
  }

}
