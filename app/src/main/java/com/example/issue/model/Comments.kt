package com.example.issue.model

import com.google.gson.annotations.SerializedName
import com.example.issue.model.Reactions
import java.io.Serializable

class Comments : Serializable {
    @SerializedName("url")
    var url: String? = null

    @SerializedName("html_url")
    var html_url: String? = null

    @SerializedName("issue_url")
    var issue_url: String? = null

    @SerializedName("id")
    var id = 0

    @SerializedName("node_id")
    var node_id: String? = null

    @SerializedName("user")
    var user: User? = null

    @SerializedName("author_association")
    var author_association: String? = null

    @SerializedName("body")
    var body: String? = null

    @SerializedName("created_at")
    var created_at: String? = null

    @SerializedName("updated_at")
    var updated_at: String? = null
}