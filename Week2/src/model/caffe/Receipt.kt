package model.caffe

import model.people.Person

// TODO add data, such as ID, list of products, and maybe an optional set of cats adopted/sponsored!
class Receipt(val menuItems: List<Product>, val customer: Person) {
    val receiptTotal: Double
        get() {
            return 0.0
        }
}