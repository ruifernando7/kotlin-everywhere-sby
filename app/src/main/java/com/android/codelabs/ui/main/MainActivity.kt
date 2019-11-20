package com.android.codelabs.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.codelabs.R
import com.android.codelabs.data.remote.RetrofitClient
import com.android.codelabs.data.remote.model.League
import com.android.codelabs.data.remote.model.Team
import com.android.codelabs.ui.detail.TeamDetailActivity
import com.android.codelabs.ui.main.adapter.TeamRvAdapter
import com.android.codelabs.utils.hide
import com.android.codelabs.utils.show
import com.android.codelabs.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityView {

    private lateinit var presenter: MainActivityPresenter
    private lateinit var spinnerAdapter : ArrayAdapter<String>
    private var listLeagueName : MutableList<String> = mutableListOf()
    private var listLeague : MutableList<League> = mutableListOf()
    private var listTeam : MutableList<Team> = mutableListOf()
    private lateinit var rvAdapter: TeamRvAdapter

    companion object{
        const val FLAG_EXTRAS_TEAM = "TEAM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listLeagueName)
        sp_mainactivity.adapter = spinnerAdapter
        sp_mainactivity.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, selectedIndex: Int, p3: Long) {
                presenter.getAllTeamInLeague(listLeague[selectedIndex].idLeague)
            }
        }

        rvAdapter = TeamRvAdapter(listTeam){ team ->
            val intent = Intent(this@MainActivity, TeamDetailActivity::class.java)
            intent.putExtra(FLAG_EXTRAS_TEAM, team)
            startActivity(intent)
        }
        rv_mainactivity_team?.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_mainactivity_team?.adapter= rvAdapter

        presenter = MainActivityPresenter(this, RetrofitClient.getInstance())
        presenter.getAllLeagues()
    }

    override fun showAllLeagues(leagues: List<League>) {
        val filtered = leagues.filter { it.strSport == "Soccer" }
        toast("Total Leagues: ${filtered.size}")
        listLeague.clear()
        listLeague.addAll(filtered)
        for(element in filtered){
            listLeagueName.add(element.strLeague)
        }
        spinnerAdapter.notifyDataSetChanged()
    }

    override fun showAllTeams(teams: List<Team>) {
        listTeam.clear()
        listTeam.addAll(teams)
        rvAdapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        pb_mainactivity.show()
        rv_mainactivity_team.hide()
    }

    override fun hideLoading() {
        pb_mainactivity.hide()
        rv_mainactivity_team.show()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
