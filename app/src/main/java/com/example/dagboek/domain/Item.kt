package com.example.dagboek.domain

import androidx.room.Entity
import androidx.room.PrimaryKey


// DATA CLASS HEEFT ALTIJD CONSTRUCTOR
@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    // door 0 toe te wijzen wordt dit een optionele parameter
    val id: Int = 0,

    val date: String? = null,

    val title: String,

    val body: String,
) {
    override fun toString(): String {
        return "$title ($body)"
    }
}




