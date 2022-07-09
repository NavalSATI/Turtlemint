package com.example.issue.model

import com.google.gson.annotations.SerializedName
import com.example.issue.model.Reactions
import java.io.Serializable
import java.util.ArrayList

class Issues : Serializable {
    @SerializedName("url")
    var url: String? = null

    @SerializedName("repository_url")
    var repository_url: String? = null

    @SerializedName("labels_url")
    var labels_url: String? = null

    @SerializedName("comments_url")
    var comments_url: String? = null

    @SerializedName("events_url")
    var events_url: String? = null

    @SerializedName("html_url")
    var html_url: String? = null

    @SerializedName("id")
    var id = 0

    @SerializedName("node_id")
    var node_id: String? = null

    @SerializedName("number")
    var number = 0

    @SerializedName("title")
    var title: String? = null

    @SerializedName("user")
    var user: User? = null

    @SerializedName("labels")
    var labels: ArrayList<Label>? = null

    @SerializedName("state")
    var state: String? = null

    @SerializedName("locked")
    var isLocked = false

    @SerializedName("assignee")
    var assignee: Any? = null

    @SerializedName("assignees")
    var assignees: ArrayList<Any>? = null

    @SerializedName("milestone")
    var milestone: Any? = null

    @SerializedName("comments")
    var comments = 0

    @SerializedName("created_at")
    var created_at: String? = null

    @SerializedName("updated_at")
    var updated_at: String? = null

    @SerializedName("closed_at")
    var closed_at: Any? = null

    @SerializedName("author_association")
    var author_association: String? = null

    @SerializedName("active_lock_reason")
    var active_lock_reason: Any? = null

    @SerializedName("body")
    var body: String? = null

    @SerializedName("reactions")
    var reactions: Reactions? = null

    @SerializedName("timeline_url")
    var timeline_url: String? = null

    @SerializedName("performed_via_github_app")
    var performed_via_github_app: Any? = null

    @SerializedName("state_reason")
    var state_reason: Any? = null
}