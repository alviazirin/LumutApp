package com.example.alvimaghfirantoazirin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Entity(
    var userId : Int,
    var id: Int,
    var title: String,
    var status: String
) : Parcelable
