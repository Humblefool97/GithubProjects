package com.example.mobile_ui.bookmark

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_ui.R
import com.example.mobile_ui.ViewModelFactory
import com.example.mobile_ui.mapper.ProjectViewMapper
import com.example.presentation.model.ProjectView
import com.example.presentation.state.Resource
import com.example.presentation.state.ResourceState
import com.example.presentation.viewmodel.BookmarkProjectsViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_bookmarked.*
import javax.inject.Inject

class BookmarkedActivity:AppCompatActivity() {
    @Inject
    lateinit var mapper:ProjectViewMapper

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var browseViewModel:BookmarkProjectsViewModel

    companion object{

        fun getStartIntent(context: Context):Intent{
            return Intent(context,BookmarkedActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmarked)
        AndroidInjection.inject(this)

        browseViewModel = ViewModelProviders.of(this,viewModelFactory).get(BookmarkProjectsViewModel::class.java)
    setUpBrowserRecycler()
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getProjects().observe(this, Observer {

        })
        browseViewModel.fetchProjects()
    }

    private fun setUpBrowserRecycler(){
        recycler_projects.layoutManager = LinearLayoutManager(this)
    }

    private fun handleDataState(resource:Resource<List<ProjectView>>){
        when(resource.status){
            ResourceState.SUCCESS -> {
                progress.visibility = View.GONE
                recycler_projects.visibility = View.VISIBLE
            }
            ResourceState.LOADING -> {
                progress.visibility = View.VISIBLE
                recycler_projects.visibility = View.GONE
            }
        }
    }
}