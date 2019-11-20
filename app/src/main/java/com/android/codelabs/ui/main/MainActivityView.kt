package com.android.codelabs.ui.main

import com.android.codelabs.data.remote.model.League
import com.android.codelabs.data.remote.model.Team

interface MainActivityView{
    fun showAllLeagues(leagues: List<League>)
    fun showAllTeams(teams: List<Team>)
    fun showLoading()
    fun hideLoading()
    fun showToast(message: String)
}