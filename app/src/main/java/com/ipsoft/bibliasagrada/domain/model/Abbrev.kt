package com.ipsoft.bibliasagrada.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Abbrev(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("pt")
    val pt: String = ""
)
