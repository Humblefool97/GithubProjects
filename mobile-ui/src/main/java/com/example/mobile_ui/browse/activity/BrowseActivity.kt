package com.example.mobile_ui.browse.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_ui.R
import com.example.mobile_ui.ViewModelFactory
import com.example.mobile_ui.bookmark.BookmarkedActivity
import com.example.mobile_ui.browse.ProjectListener
import com.example.mobile_ui.browse.adapters.BrowseAdapter
import com.example.mobile_ui.mapper.ProjectViewMapper
import com.example.mobile_ui.model.Project
import com.example.presentation.model.ProjectView
import com.example.presentation.state.Resource
import com.example.presentation.state.ResourceState
import com.example.presentation.viewmodel.ProjectsViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_browse.*
import javax.inject.Inject

class BrowseActivity:AppCompatActivity() {
    @Inject lateinit var browseAdapter: BrowseAdapter
    @Inject lateinit var mapper:ProjectViewMapper
    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var browsViewModel:ProjectsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        AndroidInjection.inject(this)

        browsViewModel = ViewModelProviders.of(this,viewModelFactory).get(ProjectsViewModel::class.java)
        setupBrowserRecycler()
    }

    override fun onStart() {
        super.onStart()
        browsViewModel.getProjects().observe(this, Observer {
                it?.let {
                    handleDataState(it)
                }
        })
        browsViewModel.fetchProjects()
    }
    private fun setupBrowserRecycler(){
        browseAdapter.projectListener = projectListener
        recycler_projects.layoutManager = LinearLayoutManager(this)
        recycler_projects.adapter = browseAdapter
    }

    private fun handleDataState(resource: Resource<List<ProjectView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                setupScreenForSuccess(resource.data?.map {
                    mapper.mapFromView(it)
                })
            }
            ResourceState.LOADING -> {
                progress.visibility = View.VISIBLE
                recycler_projects.visibility = View.GONE
            }
        }
    }

    private fun setupScreenForSuccess(projects: List<Project>?) {
        progress.visibility = View.GONE
        projects?.let {
            browseAdapter.projects = it
            browseAdapter.notifyDataSetChanged()
            recycler_projects.visibility = View.VISIBLE
        } ?: run {

        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_bookmarked -> {
                startActivity(BookmarkedActivity.getStartIntent(this))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private val projectListener = object :ProjectListener{
        override fun onBookmarkedProjectClicked(projectId: String) {

        }

        override fun onProjectClicked(projectId: String) {

        }

    }
}