package com.traditionalcake

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TraditionalCake(
    val name: String,
    val description: String,
    val daerah: String,
    val photo: Int
) : Parcelable