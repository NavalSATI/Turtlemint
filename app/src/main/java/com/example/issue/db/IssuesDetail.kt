package com.example.issue.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IssuesDetail {
    @get:Query("select * From IssuesTable")
    val all: List<IssuesTable?>?

    @Insert
    fun insertAll(vararg issueTables: IssuesTable?)

    @Delete
    fun delete(issueTables: List<IssuesTable?>?)
}