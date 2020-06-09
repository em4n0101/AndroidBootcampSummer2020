package model.animals

import model.caffe.Sponsorship
import java.util.*

data class Cat(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val gender: String,
    val breed: String,
    val shelterId: String? = null,
    val sponsorships: MutableSet<Sponsorship>? = null
)