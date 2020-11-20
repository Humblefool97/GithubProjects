package com.example.data.mapper

import com.example.data.model.ProjectEntity
import com.example.domain.model.Project
import javax.inject.Inject

class ProjectMapper @Inject constructor() : EntityMapper<ProjectEntity, Project> {
    override fun mapFromEntity(entity: ProjectEntity): Project {
        return Project(
            entity.id,
            entity.name,
            entity.fullName,
            entity.starCount,
            entity.dateCreated,
            entity.ownerName,
            entity.ownerAvatar
        )
    }

    override fun mapToEntity(data: Project): ProjectEntity {
        return ProjectEntity(
            data.id,
            data.name,
            data.fullName,
            data.starCount,
            data.dateCreated,
            data.ownerName,
            data.ownerAvatar
        )
    }

}