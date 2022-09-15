package tests
import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.{BottleDeposit, Item, Sale, SalesTax}

class Task2 extends FunSuite {

  test ("2 sales and tax"){
    val checkout: SelfCheckout = new SelfCheckout()
    val pineapple: Item = new Item("pineapple", 10.0)
    checkout.addItemToStore("0", pineapple)
    val sale: Sale = new Sale(50.0)
    val tax : SalesTax = new SalesTax(10.0)
    pineapple.addModifier(tax)
    pineapple.addModifier(sale)
    checkout.numberPressed(0)
    checkout.enterPressed()
    val sale2: Sale = new Sale(50.0)
//    pineapple.addModifier(sale2)

    val cart : List[Item] = checkout.itemsInCart()
    assert(cart.size == 1)
    assert(cart.head.description()=="pineapple")
    assert(Math.abs(cart.head.price()-5.0)<0.001)
    assert(Math.abs(checkout.subtotal()-5.0)<0.001)
    assert(Math.abs(checkout.tax()-0.5)<0.001)
    assert(Math.abs(checkout.total()-5.5)<0.01)
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

  test ("1 percent tax"){
    val checkout: SelfCheckout = new SelfCheckout()
    val pineapple: Item = new Item("pineapple", 100.0)
    checkout.addItemToStore("0", pineapple)
    val tax : SalesTax = new SalesTax(1.0)
    pineapple.addModifier(tax)
    checkout.numberPressed(0)
    checkout.enterPressed()

    val cart : List[Item] = checkout.itemsInCart()
    assert(cart.size == 1)
    assert(cart.head.description()=="pineapple")
    assert(Math.abs(cart.head.price()-100.0)<0.001)
    assert(Math.abs(checkout.subtotal()-100.0)<0.001)
    assert(Math.abs(checkout.tax()-1.0)<0.001)
    assert(Math.abs(checkout.total()-101.0)<0.01)
  }

  test ("call price twice"){
    val checkout: SelfCheckout = new SelfCheckout()
    val pineapple: Item = new Item("pineapple", 100.0)
    checkout.addItemToStore("0", pineapple)
    val sale: Sale = new Sale(50.0)
    val tax : SalesTax = new SalesTax(1.0)
    pineapple.addModifier(tax)
    pineapple.addModifier(sale)

    pineapple.price()
    pineapple.price()

    checkout.numberPressed(0)
    checkout.enterPressed()

    val cart : List[Item] = checkout.itemsInCart()
    assert(cart.size == 1)
    assert(cart.head.description()=="pineapple")
    assert(Math.abs(cart.head.price()-50.0)<0.001)
    assert(Math.abs(checkout.subtotal()-50.0)<0.001)
    assert(Math.abs(checkout.tax()-0.5)<0.001)
    assert(Math.abs(checkout.total()-50.5)<0.01)
  }

  test ("two items"){
    val checkout : SelfCheckout = new SelfCheckout()
    val pineapple : Item = new Item ("pineapple", 10.0)
    checkout.addItemToStore("0",pineapple)
    val apple : Item = new Item ("apple", 100.0)
    checkout.addItemToStore("1",apple)
    val sale : Sale = new Sale (50.0)
    pineapple.addModifier(sale)
    val tax : SalesTax = new SalesTax(10.0)
    apple.addModifier(sale)
    apple.addModifier(tax)
    pineapple.addModifier(tax)

    checkout.numberPressed(0)
    checkout.enterPressed()
    checkout.numberPressed(0)
    checkout.enterPressed()
    checkout.numberPressed(1)
    checkout.enterPressed()

    val cart : List[Item] = checkout.itemsInCart()
    assert(cart.size == 3)
    assert(cart.head.description()=="pineapple")
    assert(Math.abs(cart(2).price()-50.0)< 0.01)
  }

  test("odd prices"){
    val checkout : SelfCheckout = new SelfCheckout()
    val pineapple : Item = new Item ("pineapple", 12.99)
    checkout.addItemToStore("0",pineapple)
    val apple : Item = new Item ("apple", 14.63)
    checkout.addItemToStore("1",apple)
    val sale : Sale = new Sale(13.6)
    val tax : SalesTax = new SalesTax(7.5)
    pineapple.addModifier(tax)
    pineapple.addModifier(sale)
    apple.addModifier(tax)


    checkout.numberPressed(0)
    checkout.enterPressed()
    checkout.numberPressed(0)
    checkout.enterPressed()
    checkout.numberPressed(1)
    checkout.enterPressed()
    checkout.numberPressed(1)
    checkout.enterPressed()
    checkout.numberPressed(0)
    checkout.enterPressed()
    checkout.numberPressed(1)
    checkout.enterPressed()

    val cart : List[Item] = checkout.itemsInCart()
    var list : List[Double] = List()
    var expected : List[Double] = List(11.22336,11.22336,14.63,14.63,11.22336,14.63)
    assert(cart.size == 6)
    var count = 0
    for (item <- cart){
      list :+= item.price()
      assert(Math.abs(item.price()-expected(count))<0.001)
      count += 1
    }
  }

  test("bottle deposit"){
    val checkout: SelfCheckout = new SelfCheckout()
    val pineapple: Item = new Item("pineapple", 99.99)
    checkout.addItemToStore("0", pineapple)
    val sale: Sale = new Sale(50.0)
//    val tax : SalesTax = new SalesTax(10.0)
    val bottle : BottleDeposit = new BottleDeposit(0.01)
//    pineapple.addModifier(tax)
    pineapple.addModifier(bottle)

    checkout.numberPressed(0)
    checkout.enterPressed()

    val cart : List[Item] = checkout.itemsInCart()
    assert(cart.size==1)
    assert(cart.head.description()=="pineapple")
    assert(Math.abs(cart.head.price()-99.99)<0.001,"price")
    assert(Math.abs(checkout.subtotal()-99.99)<0.01,"sub")
    assert(Math.abs(checkout.tax()-0.001)<0.01,"tax")
    assert(Math.abs(checkout.total()-100.00)<0.001,"total")
  }

}
