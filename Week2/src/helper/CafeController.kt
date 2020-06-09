package helper

import model.DaysOfTheWeek
import model.animals.Cat
import model.caffe.Cafe
import model.caffe.Product
import model.caffe.Receipt
import model.people.Employee
import model.people.Patron
import model.people.Person
import model.shelter.Shelter

class CafeController {

    // cafe related things
    private val cafe = Cafe()

    // shelter related things
    private val shelters = Utilities.getAllSheltersAvailable
    private val shelterToCat = mutableMapOf<Shelter, MutableSet<Cat>>()

    // users
    private val employees: MutableSet<Employee>
    private val customers: MutableSet<Patron>


    init {
        // Populate shelters, employees and patrons with random data
        shelterToCat[shelters.first()] = Utilities.initShelterWith(6, forShelterId = shelters.first().id)
        shelterToCat[shelters.last()] = Utilities.initShelterWith(8, forShelterId = shelters.last().id)
        employees = Utilities.initEmployeesWith(numberOfEmployees = 3)
        customers = Utilities.initPatronWith(numberOfPatrons = 5)
    }

    fun adoptCat(catId: String) {
        // get random person
        val person = (employees + customers).random()

        // check if cats exist, and retrieve its entry!
        val catInShelter = shelterToCat.entries.firstOrNull { (_, catsInShelter) ->
            catsInShelter.any { it.id == catId }
        }

        // you can adopt that cat!
        if (catInShelter != null) {
            val cat = catInShelter.value.first { cat -> cat.id == catId } // find the cat for that ID again

            // remove the cat from the shelter
            catInShelter.value.remove(cat)

            // add the cat to the person
            person.cats.add(cat)

            println("\n${person.firstName} has adopted ${cat.name} :) !!!!!")
        }
    }

    fun sellItems(items: List<Product>, forEmployee: Boolean) {
        val receipt = cafe.getReceipt(items, if (forEmployee) employees.random() else customers.random())
        println(receipt)
    }

    /**
     * First gets a list of all adopters, then queries shelters.
     *
     * */
    fun getNumberOfAdoptionsPerShelter(): Map<String, Int> {
        val allAdopters = getAdopters()

        return emptyMap() // TODO find which cats belong to which shelter, and create a map of Shelter name to number of adoptions
    }


    fun getUnadoptedCats(): Set<Cat> {
        val tempCats = shelterToCat.flatMap { it.value }
        return tempCats.toSet()
    }

    fun getShelterBy(id: String): Shelter? {
        var tempShelter: Shelter? = null
        shelters.forEach {
            if (it.id == id) tempShelter = it
        }
        return tempShelter
    }

    /**
     * Show receipts info
     */
    fun showReceiptsInfo() = cafe.showNumberOfReceiptsForDay()

    /**
     * Show top selling info
     */
    fun showTopTenInfo() = cafe.getTopSellingItems()

    fun getAdopters(): List<Person> {
        return (employees + customers).filter { it.cats.isNotEmpty() }
    }

    fun getAdoptedCats(): Set<Cat> {
        val allAdopters = getAdopters()
        var catsAdopted = mutableSetOf<Cat>()
        allAdopters.forEach {
            it.cats.forEach {cat ->
                catsAdopted.add(cat)
            }
        }

        return catsAdopted
    }

    fun getWorkingEmployees(): Set<Employee> = employees
}