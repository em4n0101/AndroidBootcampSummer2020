package helper

import model.animals.Cat
import model.caffe.Sponsorship
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
        fun fillShelterWith(numberOfCats: Int): MutableSet<Cat> {
            var tempCats = mutableSetOf<Cat>()

            for (i in 0..numberOfCats) {
                val randomCat = getAllCatsAvailable()[i]
                tempCats.add(randomCat)
                println(getAllCatsAvailable().removeAt(i))
            }

            return tempCats
        }

        fun getAllShelter() = mutableSetOf(
                Shelter(name = "Happy Shelter Cat", address = "803 11th Avenue\n" +
                        "Sunnyvale, CA 94089", phoneNumber = 5123435283),
                Shelter(name = "Rescue Cats", address = "12422 W. Bluff Creek Drive\n" +
                        "Playa Vista, CA 90094", phoneNumber = 7343326500)
        )

        private fun getAllCatsAvailable() = mutableListOf(
                Cat(name = "Tiger", gender = "Male", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>()),
                Cat(name = "Simba", gender = "Female", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>()),
                Cat(name = "Chloe", gender = "Female", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>()),
                Cat(name = "Sam", gender = "Male", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>()),
                Cat(name = "Missy", gender = "Female", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>()),
                Cat(name = "Cleo", gender = "Female", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>()),
                Cat(name = "Puss", gender = "Female", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>()),
                Cat(name = "Kitty", gender = "Female", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>()),
                Cat(name = "Felix", gender = "Male", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>()),
                Cat(name = "Fluffy", gender = "Male", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>()),
                Cat(name = "Daisy", gender = "Female", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>()),
                Cat(name = "Oscar", gender = "Male", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>()),
                Cat(name = "Charlie", gender = "Male", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>()),
                Cat(name = "Minnie", gender = "Female", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>()),
                Cat(name = "Kimba", gender = "Male", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>()),
                Cat(name = "Sylvester", gender = "Male", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>()),
                Cat(name = "Lilly", gender = "Female", shelterId = "0", sponsorships = mutableSetOf<Sponsorship>())
        )
    }
}