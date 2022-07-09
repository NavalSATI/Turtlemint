package com.example.issue.db

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
class IssuesTable {
    @JvmField
    @PrimaryKey(autoGenerate = true)
    var auId = 0

    @JvmField
    @ColumnInfo(name = "id")
    var id: Int? = null

    @JvmField
    @ColumnInfo(name = "title")
    var title: String? = null

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

    constructor() {}
    constructor(
        id: Int?,
        title: String?,
        des: String?,
        updateDate: String?,
        userName: String?,
        userImg: String?,
        commentsUrl: String?
    ) {
        this.id = id
        this.title = title
        this.body = des
        this.updatedAt = updateDate
        this.userName = userName
        this.userImage = userImg
        this.commentsUrl = commentsUrl
    }
}