package com.example.issue.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.example.issue.model.Reactions

@Entity
class Reactions {
    var url: String? = null
    var total_count = 0
    var laugh = 0
    var hooray = 0
    var confused = 0
    var heart = 0
    var rocket = 0
    var eyes = 0
}