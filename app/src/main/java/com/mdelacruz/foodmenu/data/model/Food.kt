package com.mdelacruz.foodmenu.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * A data model that represents the response from the Server.
 *
 * Created on 3/22/18.
 *
 * @author Michael De La Cruz
 */
@Parcelize
data class Food(
    val title: String,
    val filter: String,
    val image: String
) : Parcelable