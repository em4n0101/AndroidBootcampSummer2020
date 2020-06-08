package model.people

import helper.formatTimeToReadableText
import java.util.*

class Employee(
    firstName: String,
    lastName: String,
    email: String,
    phoneNumber: String,
    val salary: Double,
    val socialSecurityNumber: String,
    val hireDate: String
) : Person(firstName = firstName, lastName = lastName, email = email, phoneNumber = phoneNumber) {

    override fun toString(): String {
        return "Employee: " +
                "ID: $id" +
                "First name: $firstName \n" +
                "Last name: $lastName " +
                "Email: $email " +
                "Phone number: $phoneNumber" +
                "Salary: $salary" +
                "Social number: $socialSecurityNumber" +
                "Hire date: $hireDate"
    }

    /**
     * Prints a time of clocking in, in a nice format.
     * */
    fun clockIn() {
        println("Clock in time: ${Date().formatTimeToReadableText()}")
    }

    /**
     * Prints a time of clocking out, in a nice format.
     * */
    fun clockOut() {
        println("Clock out time: ${Date().formatTimeToReadableText()}")
    }
}