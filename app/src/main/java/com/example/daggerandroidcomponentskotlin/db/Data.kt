package com.example.daggerandroidcomponentskotlin.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
public class Data(
    var last_name: String?,
    @PrimaryKey(autoGenerate = false)
    var id: String,
    @SerializedName("avatar")
    @Expose
    var avatar: String?,
    @SerializedName("first_name")
    @Expose
    var first_name: String?
) {

    override fun toString(): String {
        return "ClassPojo [last_name = $last_name, id = $id, avatar = $avatar, first_name = $first_name]"
    }
}
