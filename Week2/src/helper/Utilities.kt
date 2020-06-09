package helper

import model.animals.Cat
import model.caffe.Product
import model.people.Employee
import model.people.Patron
import model.shelter.Shelter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Method extension for format the current date
 */
fun Date.formatTimeToReadableText(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return formatter.format(this)
}

class Utilities {
    companion object {
        /**
         * Init a shelter with a specific number of cats, get and remove a random [Cat] from [getAllCatsAvailable]
         * @param numberOfCats the number of cats to generate
         * @return a set with the cats generated
         */
        fun initShelterWith(numberOfCats: Int, forShelterId: String): MutableSet<Cat> {
            var tempCats = mutableSetOf<Cat>()

            for (i in 1..numberOfCats) {
                val randomCat = getAllCatsAvailable.random()
                // update now is in the shelter, update it's value
                val copyCat = randomCat.copy(shelterId = forShelterId)
                tempCats.add(copyCat)
                getAllCatsAvailable.remove(randomCat)
            }

            return tempCats
        }

        fun initEmployeesWith(numberOfEmployees: Int): MutableSet<Employee> {
            var tempEmployees = mutableSetOf<Employee>()

            for (i in 1..numberOfEmployees) {
                val randomEmployee = getAllEmployeesAvailable.random()
                tempEmployees.add(randomEmployee)
                getAllEmployeesAvailable.remove(randomEmployee)
            }

            return tempEmployees
        }

        fun initPatronWith(numberOfPatrons: Int): MutableSet<Patron> {
            var tempPatrons = mutableSetOf<Patron>()

            for (i in 1..numberOfPatrons) {
                val randomPatron = getAllPatronAvailable.random()
                tempPatrons.add(randomPatron)
                getAllPatronAvailable.remove(randomPatron)
            }

            return tempPatrons
        }

        var getAllSheltersAvailable = mutableSetOf(
                Shelter(name = "Happy Shelter Cat", address = "803 11th Avenue\n" +
                        "Sunnyvale, CA 94089", phoneNumber = "5123435283"),
                Shelter(name = "Rescue Cats", address = "12422 W. Bluff Creek Drive\n" +
                        "Playa Vista, CA 90094", phoneNumber = "7343326500")
        )

        private var getAllCatsAvailable = mutableSetOf(
                Cat(name = "Tiger", gender = "Male", breed = "Persian"),
                Cat(name = "Simba", gender = "Female", breed = "Himalayan"),
                Cat(name = "Chloe", gender = "Female", breed = "Maine Coon"),
                Cat(name = "Sam", gender = "Male", breed = "Cornish Rex"),
                Cat(name = "Missy", gender = "Female", breed = "Persian"),
                Cat(name = "Cleo", gender = "Female", breed = "Abyssinian"),
                Cat(name = "Puss", gender = "Female", breed = "Cornish Rex"),
                Cat(name = "Kitty", gender = "Female", breed = "Himalayan"),
                Cat(name = "Felix", gender = "Male", breed = "Persian"),
                Cat(name = "Fluffy", gender = "Male", breed = "Maine Coon"),
                Cat(name = "Daisy", gender = "Female", breed = "Persian"),
                Cat(name = "Oscar", gender = "Male", breed = "Abyssinian"),
                Cat(name = "Charlie", gender = "Male", breed = "Himalayan"),
                Cat(name = "Minnie", gender = "Female", breed = "Maine Coon"),
                Cat(name = "Kimba", gender = "Male", breed = "Persian"),
                Cat(name = "Sylvester", gender = "Male", breed = "Himalayan"),
                Cat(name = "Lilly", gender = "Female", breed = "Persian")
        )

        var getAllEmployeesAvailable = mutableListOf(
                Employee(firstName = "Mary" ,lastName = "Sanchez", phoneNumber = "5522113351", email = "Mary@gmail.com", salary = 1000.00, socialSecurityNumber = "SSN001", hireDate = Date().formatTimeToReadableText()),
                Employee(firstName = "Helen" ,lastName = "Bennett", phoneNumber = "5522113352", email = "Helen@gmail.com", salary = 1000.00, socialSecurityNumber = "SSN002", hireDate = Date().formatTimeToReadableText()),
                Employee(firstName = "Elizabeth" ,lastName = "Williams", phoneNumber = "5522113353", email = "Elizabeth@gmail.com", salary = 1000.00, socialSecurityNumber = "SSN003", hireDate = Date().formatTimeToReadableText()),
                Employee(firstName = "Anna" ,lastName = "Jones", phoneNumber = "5522113354", email = "Anna@gmail.com", salary = 1000.00, socialSecurityNumber = "SSN004", hireDate = Date().formatTimeToReadableText()),
                Employee(firstName = "John" ,lastName = "Miller", phoneNumber = "5522113355", email = "John@gmail.com", salary = 1000.00, socialSecurityNumber = "SSN005", hireDate = Date().formatTimeToReadableText())
        )

        var getAllPatronAvailable = mutableListOf(
                Patron(firstName = "Daniel" ,lastName = "Smith", phoneNumber = "55221133557", email = "Daniel@gmail.com"),
                Patron(firstName = "William" ,lastName = "Hall", phoneNumber = "55221133558", email = "William@gmail.com"),
                Patron(firstName = "Robert" ,lastName = "Stewart", phoneNumber = "55221133559", email = "Robert@gmail.com"),
                Patron(firstName = "James" ,lastName = "Price", phoneNumber = "552211335510", email = "James@gmail.com"),
                Patron(firstName = "Joseph" ,lastName = "Johnson", phoneNumber = "552211335511", email = "Joseph@gmail.com"),
                Patron(firstName = "Charles" ,lastName = "Allen", phoneNumber = "552211335522", email = "Charles@gmail.com")
        )

        var getAllProductsAvailable = setOf(
                Product(name = "Cappuccino", price = 3.00),
                Product(name = "Americano", price = 2.00),
                Product(name = "Cold Brew", price = 2.50),
                Product(name = "Mocha", price = 3.50),
                Product(name = "Latte", price = 3.50),
                Product(name = "Cookies", price = 1.00),
                Product(name = "Fresh Fruit", price = 4.00),
                Product(name = "French Macaron", price = 4.50),
                Product(name = "Gluten Free Donuts", price = 2.50)
        )
    }
}