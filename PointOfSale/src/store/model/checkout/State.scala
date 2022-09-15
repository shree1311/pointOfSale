package store.model.checkout

abstract class State {

  def numberPressed(number: Int): Unit

  def clearPressed(): Unit

  def enterPressed(): Unit

  def checkoutPressed(): Unit

  def cashPressed(): Unit

  def creditPressed(): Unit

  def loyaltyCardPressed(): Unit

  def displayString(): String

}
