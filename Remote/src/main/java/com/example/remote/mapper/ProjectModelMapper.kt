package com.example.remote.mapper

import com.example.data.model.ProjectEntity
import com.example.remote.model.ProjectModel

class ProjectModelMapper : ModelMapper<ProjectModel, ProjectEntity> {
    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(
            model.id, model.name, model.fullName,
            model.starCount.toString(), model.dateCreated, model.owner.ownerName,
            model.owner.ownerAvatar
        )
    }
}