package store.model.items

class Item (itemDescription : String, var basePrice: Double) {

  // TODO: Complete this class according to the features listed in the HW document
  var modlist: List[Modifier] = List()
  var totaltax: Double = 0.0
  var baseprice : Double = basePrice
  var afterS : Double = 0.0
  var afterLSale : Double = 0.0

  def description(): String = {
    itemDescription.toString
  }

  def setBasePrice(input: Double): Unit = {
    this.basePrice = input
  }

  def addModifier(input: Modifier): Unit = {
    this.modlist :+= input
  }

  def price(): Double = {
    var updatedPrice = baseprice
    for (modifier <- modlist) {
      updatedPrice = modifier.updatePrice(updatedPrice)
    }
    afterS = updatedPrice
    afterS
  }

  def tax(): Double = {
    var taxPrice : Double = 0.0
    var salePrice : Double = price()
    for (mod <- modlist){
      taxPrice += mod.computeTax(salePrice)
    }
    taxPrice
  }

  def addLoyaltySale() : Double = {
    var updatedPrice = price()
    for (modifier <- modlist) {
      updatedPrice = modifier.loyaltyPrice(updatedPrice)
    }
    updatedPrice
  }

  def loyaltyTax() : Double = {
    var taxPrice : Double = 0.0
    var loyaltyPrice : Double  = addLoyaltySale()
    for (modifier <- modlist){
      taxPrice += modifier.computeTax(loyaltyPrice)
    }
    taxPrice
  }

  def removeSalePrice() : Double = {
    var updatedPrice = basePrice
    for (modifier <- modlist) {
      updatedPrice = modifier.removeSale(updatedPrice)
    }
    baseprice = updatedPrice
    baseprice
  }


}

