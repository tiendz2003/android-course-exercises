package com.rxmobileteam.lecture2_3.products

import com.rxmobileteam.utils.ExerciseNotCompletedException

enum class ProductCategory {
  LAPTOP,
  PHONE,
  HEADPHONES,
  SMART_WATCH,
  CAMERA,
}

data class Product(
  val id: String,
  val name: String,
  val price: Double,
  val category: ProductCategory,
  val favoriteCount: Int,
)

data class Order(
  val id: String,
  val products: List<Product>,
  val isDelivered: Boolean,
)

// TODO: Return a list of Product, sorted in the ascending by price. if prices are equal, sorted by favoriteCount descending
fun List<Product>.sortedByPriceAscendingThenByFavoriteCountDescending(): List<Product> {

  return this.sortedBy { it.price }.sortedByDescending { it.favoriteCount }
}



// TODO: Return a set of Products in the orders (The order doesn't matter).
fun List<Order>.getProductsSet(): Set<Product> {
  return this.flatMap { it.products }.toSet()
}

// TODO: Return a list of Products in the orders, duplicates are allowed.
fun List<Order>.getProductsList(): List<Product> = this.flatMap { it.products }

// TODO: Return a list of delivered orders
fun List<Order>.getDeliveredOrders(): List<Order> =this.filter { it.isDelivered }

// TODO: Return a list of products in the delivered orders
fun List<Order>.getDeliveredProductsList(): List<Product> = this.filter { it.isDelivered }.flatMap { it.products }

// TODO: Partition the orders into two lists: "delivered" and "not delivered"
fun List<Order>.partitionDeliveredAndNotDelivered(): Pair<List<Order>, List<Order>> {
  return this.partition { it.isDelivered }
}


// TODO: Return a map of product to count of this product in the orders
// eg. [Product1 -> 2, Product2 -> 1, Product3 -> 3]
fun List<Order>.countOfEachProduct(): Map<Product, Int> {
  return this.flatMap { it.products }.groupingBy { it }.eachCount()
}

// TODO: Return the sum of product prices in the order
fun Order.sumProductPrice(): Double = this.products.sumOf { it.price }

// TODO: Return the product with the maximum price in the order
fun Order.getMaxPriceProduct(): Product = this.products.maxBy { it.price }

// TODO: Return the product with the min price in the order
fun Order.getMinPriceProduct(): Product =  this.products.minBy { it.price }
//Bonus:Practice makes perfect
//Tính tổng số lượng các sản phẩm độc nhất
fun List<Order>.getSumUniqueProductinOrder():Int = this.flatMap { it.products }.distinct().count()
//Sắp xếp đơn hàng theo tổng giá sản phẩm giảm dần
fun List<Order>.sortByTotalPriceDescending(): List<Order> {
  return this.sortedByDescending { it.products.sumOf { it.price } }
}
