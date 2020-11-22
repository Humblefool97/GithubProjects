package com.example.cache

import com.example.cache.db.AppDatabase
import com.example.cache.mapper.CachedProjectMapper
import com.example.cache.model.Config
import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class CacheProjectsImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val mapper: CachedProjectMapper
) : ProjectsCache {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return appDatabase.provideProjectDao()
            .getProjects()
            .toObservable()
            .map {
                it.map { cachedProjectEntity -> mapper.mapFromCache(cachedProjectEntity) }
            }
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return appDatabase.provideProjectDao()
            .getBookmarkedProjects()
            .toObservable()
            .map {
                it.map { mapper.mapFromCache(it) }
            }
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return Completable.defer {
            appDatabase.provideProjectDao().setBookmarkStatus(true, projectId)
            Completable.complete()
        }
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return Completable.defer {
            appDatabase.provideProjectDao().setBookmarkStatus(false, projectId)
            Completable.complete()
        }
    }

    override fun clearProjects(): Completable {
        return Completable.defer {
            appDatabase.provideProjectDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun saveProjectsToCache(project: List<ProjectEntity>): Completable {
        return Completable.defer {
            appDatabase.provideProjectDao().insertProjects(
                project.map {
                    mapper.mapFromEntity(it)
                }
            )
            Completable.complete()
        }
    }

    override fun areProjectsCached(): Single<Boolean> {
        return appDatabase.provideProjectDao().getProjects().isEmpty.map {
            !it
        }
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            appDatabase.provideConfigDao().insertConfig(Config(lastCachedTime = lastCache))
            Completable.complete()
        }
    }

    override fun isProjectsCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val timeLimit = 10 * 60 * 1000
        return appDatabase.provideConfigDao().getConfig()
            .single(Config(lastCachedTime = 0))
            .map {
                currentTime - it.lastCachedTime > timeLimit
            }
    }
}