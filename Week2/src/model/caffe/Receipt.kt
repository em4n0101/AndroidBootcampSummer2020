package model.caffe

import model.people.Employee
import model.people.Person
import java.util.*

class Receipt(val id: String = UUID.randomUUID().toString(),
              val menuItems: List<Product>,
              val customer: Person,
              val sponsorships: Set<Sponsorship>? = null) {
    val receiptTotal: Double
        get() {
            var res = 0.0
            menuItems.forEach {
                res += it.price
            }

            return if (customer is Employee) {
                res * .85
            } else {
                res
            }
        }

    override fun toString(): String {
        val productsByName = menuItems.groupBy { it.name }

        var productsString = "\n"
        productsByName.forEach {
            productsString += "\t\t\t${it.key} \t\t\t${it.value.size} \t\t\t\$${it.value.first().price * it.value.size}\n"
        }

        return "\nReceipt ID: $id\n" +
                "Customer: ${customer.firstName} ${customer.lastName}\n" +
                "Products: " +
                "$productsString \n" +
                "Total: ${String.format("%.2f", receiptTotal)} ${if (customer is Employee) " (15% employee discount)\n" else "\n"}"
    }
}