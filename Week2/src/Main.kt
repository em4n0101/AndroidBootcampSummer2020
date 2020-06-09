import helper.CafeController
import helper.Utilities

fun main() {
    val cafeController = CafeController() // print out the data here using CafeController functions
    var option: String?


    while (true) {
        showOptionForInput()
        option = readLine()
        when (option) {
            "1" -> cafeController.sellItems(listOf(
                    Utilities.getAllProductsAvailable.random(),
                    Utilities.getAllProductsAvailable.random(),
                    Utilities.getAllProductsAvailable.random(),
                    Utilities.getAllProductsAvailable.random(),
                    Utilities.getAllProductsAvailable.random(),
                    Utilities.getAllProductsAvailable.random(),
                    Utilities.getAllProductsAvailable.random()
            ), forEmployee = true)
            "2" -> cafeController.sellItems(listOf(
                    Utilities.getAllProductsAvailable.random(),
                    Utilities.getAllProductsAvailable.random(),
                    Utilities.getAllProductsAvailable.random(),
                    Utilities.getAllProductsAvailable.random(),
                    Utilities.getAllProductsAvailable.random(),
                    Utilities.getAllProductsAvailable.random(),
                    Utilities.getAllProductsAvailable.random()
            ), forEmployee = false)
            "3" -> cafeController.showReceiptsInfo()
            "4" -> cafeController.showTopTenInfo()
            "5" -> showInfoAboutUnadoptedCats(cafeController)
            "6" -> cafeController.adoptCat(cafeController.getUnadoptedCats().random().id)
            "7" -> cafeController.getAdoptedCats().forEach (::println)
            else -> return
        }
    }
}

private fun showOptionForInput() {
    println("Select an option\n\n" +
            "1 : Sell items for employee\n" +
            "2 : Sell items for patron\n" +
            "3 : Show receipts for week\n" +
            "4 : Show top ten selling menu items\n" +
            "5 : Show cats in shelters\n" +
            "6 : Adopt a cat\n" +
            "7 : Get adopted cat\n")
}

private fun showInfoAboutUnadoptedCats(cafeController: CafeController) {
    cafeController.getUnadoptedCats().groupBy { it.shelterId }.forEach {
        println("\nFor: ${cafeController.getShelterBy(it.key ?: "")?.name}")
        println("Number of current cats: ${it.value.size}")
        print("Cats: ")
        it.value.forEach { cat -> print("${cat.name}, ") }
        println()
    }
}