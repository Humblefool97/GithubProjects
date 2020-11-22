package com.example.presentation.mapper

import com.example.domain.model.Project
import com.example.presentation.model.ProjectView

class ProjectViewMapper : Mapper<ProjectView, Project> {
    override fun fromMapToView(type: Project): ProjectView {
        return ProjectView(
            type.id, type.name, type.fullName,
            type.starCount, type.dateCreated, type.ownerName,
            type.ownerAvatar, type.isBookmarked
        )
    }
}