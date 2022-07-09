package com.example.issue.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.example.issue.model.Reactions

@Entity
class Label {
    var id = 0
    var node_id: String? = null
    var url: String? = null
    var name: String? = null
    var color: String? = null
    var isMydefault = false
    var description: String? = null
}