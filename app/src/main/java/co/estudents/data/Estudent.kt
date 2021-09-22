package co.estudents.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Estudent(
    @SerializedName("nombres")
    val name: String = "",
    @SerializedName("apellidos")
    val lastName: String = "",
    @SerializedName("institucionEducativa")
    val university: String
): Parcelable

@Parcelize
data class Estudents(val list: List<Estudent>): Parcelable
