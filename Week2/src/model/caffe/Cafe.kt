package model.caffe

import helper.formatTimeToReadableText
import model.DaysOfTheWeek
import model.animals.Cat
import model.people.Employee
import model.people.Person
import java.util.*

class Cafe {


    // cafe related stuff

    /**
     * To simplify it, let's imitate a week-long cafe turnaround.
     *
     * Make sure to add your receipts to each set, with your data.
     * */
    private val receiptsByDay = mutableMapOf(
            DaysOfTheWeek.MONDAY to mutableSetOf<Receipt>(),
            DaysOfTheWeek.TUESDAY to mutableSetOf<Receipt>(),
            DaysOfTheWeek.WEDNESDAY to mutableSetOf<Receipt>(),
            DaysOfTheWeek.THURSDAY to mutableSetOf<Receipt>(),
            DaysOfTheWeek.FRIDAY to mutableSetOf<Receipt>(),
            DaysOfTheWeek.SATURDAY to mutableSetOf<Receipt>(),
            DaysOfTheWeek.SUNDAY to mutableSetOf<Receipt>()
    )

    // make sure to add sponsorships and tie them to people!
    private val sponsorships = mutableSetOf<Sponsorship>()

    fun checkInEmployee(employee: Employee) {
        println("Employee ${employee.firstName} check in: ${Date().formatTimeToReadableText()}")
    }

    fun checkOutEmployee(employee: Employee) {
        println("Employee ${employee.firstName} check out: ${Date().formatTimeToReadableText()}")
    }

    fun showNumberOfReceiptsForDay() {
        receiptsByDay.entries.forEach {
            println("\nFor day: ${it.key} you made ${it.value.size} transactions\n" +
                    "${it.value}")
        }
    }

    /**
     * Create a receipt, generate a random day and update de receipts by day
     */
    fun getReceipt(items: List<Product>, customer: Person): Receipt {
        val receipt = Receipt(menuItems = items, customer = customer)
        val randomDay = DaysOfTheWeek.values().random()
        receiptsByDay.entries.forEach {
            if (it.key == randomDay) {
                it.value.add(receipt)
            }
        }
        return receipt
    }

    fun addSponsorship(catId: String, personId: String) {
        // TODO add the sponsorship
    }

    fun getSponsoredCats(): Set<Cat> {
        return emptySet()
    }

    fun getMostPopularCats(): Set<Cat> {
        return emptySet()
    }

    fun getTopSellingItems() {
        val menuItemsGrouped = receiptsByDay.values.flatten()
                .flatMap { it.menuItems }
                .groupBy { it.name }
                .toList()
                .sortedBy { (key, value) -> -value.size }
                .toMap()
        menuItemsGrouped.forEach {
            println("${it.key} has ${it.value.size} gross sales ")
        }
        println()
    }
}