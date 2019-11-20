package com.android.codelabs.ui.detail

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import com.android.codelabs.R
import com.android.codelabs.data.remote.model.Team
import com.android.codelabs.ui.main.MainActivity
import com.android.codelabs.utils.hide
import com.android.codelabs.utils.show
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private var imgLogoTeam: ImageView? = null
    private var tvTeamName: TextView? = null
    private var tvTeamYear: TextView? = null
    private var tvStadium: TextView? = null
    private var tvDesc: TextView? = null
    private var pbLoading: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        if (supportActionBar != null) supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        imgLogoTeam = findViewById(R.id.iv_teamactivity_logo)
        tvTeamName = findViewById(R.id.tv_teamactivity_name)
        tvTeamYear = findViewById(R.id.tv_teamactivity_year)
        tvStadium = findViewById(R.id.tv_teamactivity_stadium)
        tvDesc = findViewById(R.id.tv_teamactivity_desc)
        pbLoading = findViewById(R.id.pb_teamactivity)

        val data = intent.getParcelableExtra<Team>(MainActivity.FLAG_EXTRAS_TEAM)
        if (data != null) populateData(data)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        pbLoading?.show()
    }

    override fun hideLoading() {
        pbLoading?.hide()
    }

    override fun populateData(team: Team) {
        with(team){
            Picasso.get().load(strTeamBadge).into(iv_teamactivity_logo)
            tvTeamName?.text = strTeam
            tvTeamYear?.text = intFormedYear
            tvStadium?.text = strStadium
            tvDesc?.text = strDescriptionEN?.substring(0, 300)
        }
    }
}
