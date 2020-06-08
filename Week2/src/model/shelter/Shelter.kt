package model.shelter

import java.util.*

data class Shelter(
        val id: String = UUID.randomUUID().toString(),
        val name: String,
        val address: String,
        val phoneNumber: Long
)