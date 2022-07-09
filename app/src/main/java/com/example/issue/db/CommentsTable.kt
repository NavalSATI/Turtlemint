package com.example.issue.db

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
class CommentsTable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @JvmField
    @ColumnInfo(name = "node_id")
    var nodeId: String? = null

    @JvmField
    @ColumnInfo(name = "user_image")
    var userImage: String? = null

    @JvmField
    @ColumnInfo(name = "user_name")
    var userName: String? = null

    @JvmField
    @ColumnInfo(name = "updated_at")
    var updatedAt: String? = null

    @JvmField
    @ColumnInfo(name = "body")
    var body: String? = null

    @JvmField
    @ColumnInfo(name = "comments_url")
    var commentsUrl: String? = null

    @JvmField
    @ColumnInfo(name = "issue_url")
    var issueUrl: String? = null

    @JvmField
    @ColumnInfo(name = "record_id")
    var recordId: Int? = null

    constructor() {}
    constructor(
        nodeId: String?,
        body: String?,
        updateDate: String?,
        userName: String?,
        userImg: String?,
        recordId: Int?
    ) {
        this.nodeId = nodeId
        this.body = body
        updatedAt = updateDate
        this.userName = userName
        userImage = userImg
        this.recordId = recordId
    }
}