package com.example.issue.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CommentsDetail {
    @get:Query("select * From CommentsTable")
    val allComments: List<CommentsTable?>?

    @Query("select * From CommentsTable WHERE record_id = :recordId ")
    fun getCommentsByUrl(recordId: Int?): List<CommentsTable?>?

    @Insert
    fun insertAllComments(vararg commentsTables: CommentsTable?)

    @Delete
    fun deleteComments(commentsTables: List<CommentsTable?>?)
}