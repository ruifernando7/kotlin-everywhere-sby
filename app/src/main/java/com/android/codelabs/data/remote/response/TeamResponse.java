package com.android.codelabs.data.remote.response;


import com.android.codelabs.data.remote.model.Team;

import java.util.List;

public class TeamResponse {
    private List<Team> teams;

    public TeamResponse(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
