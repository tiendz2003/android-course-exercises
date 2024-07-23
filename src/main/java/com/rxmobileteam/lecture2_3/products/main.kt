package com.rxmobileteam.lecture2_3.products

import java.util.*

private val product = Product(
  id = UUID.randomUUID().toString(),
  name = "Sandy Short Special Edition",
  price = 2.3,
  category = ProductCategory.LAPTOP,
  favoriteCount = 1,
)

private val productList = listOf(
  product,
  Product(
    id = UUID.randomUUID().toString(),
    name = "Stacie Riddle",
    price = 6.7,
    category = ProductCategory.PHONE,
    favoriteCount = 2,
  ),
  Product(
    id = UUID.randomUUID().toString(),
    name = "Stacie Riddle",
    price = 6.7,
    category = ProductCategory.LAPTOP,
    favoriteCount = 3,
  ),
  Product(
    id = UUID.randomUUID().toString(),
    name = "Stacie Riddle",
    price = 6.7,
    category = ProductCategory.SMART_WATCH,
    favoriteCount = 4,
  ),
  Product(
    id = UUID.randomUUID().toString(),
    name = "Stacie Riddle",
    price = 1.0,
    category = ProductCategory.HEADPHONES,
    favoriteCount = 5,
  ),
  Product(
    id = UUID.randomUUID().toString(),
    name = "Stacie Riddle",
    price = 10.0,
    category = ProductCategory.CAMERA,
    favoriteCount = 0,
  ),
)

private val orderList = listOf(
  Order(
    id = UUID.randomUUID().toString(),
    products = listOf(
      product,
      Product(
        id = UUID.randomUUID().toString(),
        name = "Stacie Riddle",
        price = 6.7,
        category = ProductCategory.PHONE,
        favoriteCount = 2,
      ),
    ),
    isDelivered = true,
  ),
  Order(
    id = UUID.randomUUID().toString(),
    products = listOf(
      product,
      Product(
        id = UUID.randomUUID().toString(),
        name = "Stacie Riddle",
        price = 100.0,
        category = ProductCategory.SMART_WATCH,
        favoriteCount = 3,
      ),
    ),
    isDelivered = false,
  ),
  Order(
    id = UUID.randomUUID().toString(),
    products = listOf(
      product,
      Product(
        id = UUID.randomUUID().toString(),
        name = "Stacie Riddle",
        price = 6.7,
        category = ProductCategory.PHONE,
        favoriteCount = 2,
      ),
      Product(
        id = UUID.randomUUID().toString(),
        name = "Efrain Hawkins",
        price = 100.0,
        category = ProductCategory.CAMERA,
        favoriteCount = 5235,
      ),
    ),
    isDelivered = true,
  ),
)

fun main() {
  //region sortedByPriceAscendingThenByFavoriteCountDescending
  println("sortedByPriceAscendingThenByFavoriteCountDescending")
  val sortedProducts = productList.sortedByPriceAscendingThenByFavoriteCountDescending()
  sortedProducts.forEach { println(it) }
  println("-".repeat(80))
  //endregion
  //region getProductsSet
  println("getProductsSet")
  println(orderList.getProductsSet())
  println("-".repeat(80))
  //endregion
  //region getProductsList
  println("getProductsList")
  println(orderList.getProductsList())
  println("-".repeat(80))
  //endregion
  //region getDeliveredOrders
  println("getDeliveredOrders")
  val deliveredOrders = orderList.getDeliveredOrders()
  deliveredOrders.forEach { println(it) }
  println("-".repeat(80))
  //region getDeliveredProductsList
  println("getDeliveredProductsList")
  println(orderList.getDeliveredProductsList())
  println("-".repeat(80))
  //endregion
  //region partitionDeliveredAndNotDelivered
  println("partitionDeliveredAndNotDelivered")
  val (delivered, notDelivered) = orderList.partitionDeliveredAndNotDelivered()
  println("Delivered orders: $delivered")
  println("Not delivered orders: $notDelivered")
  println("-".repeat(80))
  //endregion
  //region countOfEachProduct
  println("countOfEachProduct")
  println(orderList.countOfEachProduct())
  println("-".repeat(80))
  //endregion
  //region sumProductPrice
  println("sumProductPrice")
  println(orderList[0].sumProductPrice())
  println("-".repeat(80))
  //endregion
  //region getMaxPriceProduct, getMinPriceProduct
  println("getMaxPriceProduct, getMinPriceProduct")
  println(orderList[0].getMaxPriceProduct())
  println(orderList[0].getMinPriceProduct())
  println("-".repeat(80))
  //endregion
  val totalCount = orderList.getSumUniqueProductinOrder()
  println(totalCount) // Expected output: 3
  val sortedOrders = orderList.sortByTotalPriceDescending()
  sortedOrders.forEach { println("Order(id=${it.id}, total=${it.products.sumOf { product -> product.price }})") }
}
