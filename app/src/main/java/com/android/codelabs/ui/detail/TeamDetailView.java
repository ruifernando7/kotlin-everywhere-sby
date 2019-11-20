package com.android.codelabs.ui.detail;

import com.android.codelabs.data.remote.model.Team;

public interface TeamDetailView {
    void showLoading();
    void hideLoading();
    void populateData(Team team);
}
