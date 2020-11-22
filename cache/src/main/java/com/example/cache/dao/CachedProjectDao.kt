package com.example.cache.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.db.DELETE_PROJECTS
import com.example.cache.db.QUERY_BOOKMARKED_PROJECTS
import com.example.cache.db.QUERY_PROJECTS
import com.example.cache.db.QUERY_UPDATE_BOOKMARK_STATUS
import com.example.cache.model.CachedProjectEntity
import io.reactivex.Flowable

interface CachedProjectDao {
    @Query(QUERY_PROJECTS)
    @JvmSuppressWildcards
    abstract fun getProjects(): Flowable<List<CachedProjectEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertProjects(projects: List<CachedProjectEntity>)

    @Query(DELETE_PROJECTS)
    abstract fun deleteProjects()

    @Query(QUERY_BOOKMARKED_PROJECTS)
    abstract fun getBookmarkedProjects(): Flowable<List<CachedProjectEntity>>

    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookmarkStatus(
        isBookmarked: Boolean,
        projectId: String
    )
}