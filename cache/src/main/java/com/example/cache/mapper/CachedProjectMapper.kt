package com.example.cache.mapper

import com.example.cache.model.CachedProjectEntity
import com.example.data.model.ProjectEntity
import javax.inject.Inject

class CachedProjectMapper @Inject constructor() : CacheMapper<CachedProjectEntity, ProjectEntity> {
    override fun mapFromCache(type: CachedProjectEntity): ProjectEntity {
        return ProjectEntity(
            type.id,
            type.name,
            type.fullName,
            type.starCount,
            type.dateCreated,
            type.ownerName,
            type.ownerAvatar,
            type.isBookmarked
        )
    }

    override fun mapFromEntity(type: ProjectEntity): CachedProjectEntity {
        return CachedProjectEntity(
            type.id,
            type.name,
            type.fullName,
            type.starCount,
            type.dateCreated,
            type.ownerName,
            type.ownerAvatar,
            type.isBookmarked
        )
    }
}