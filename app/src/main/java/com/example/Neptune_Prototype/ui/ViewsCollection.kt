package com.example.Neptune_Prototype.ui

import com.example.Neptune_Prototype.ui.views.controlView.ControlViewBody
import com.example.Neptune_Prototype.ui.views.controlView.controlViewOnBack
import com.example.Neptune_Prototype.ui.views.infoView.InfoViewBody
import com.example.Neptune_Prototype.ui.views.infoView.infoViewOnBack
import com.example.Neptune_Prototype.ui.views.joinView.JoinViewBody
import com.example.Neptune_Prototype.ui.views.joinView.joinViewOnBack
import com.example.Neptune_Prototype.ui.views.modeSelectView.ModeSelectViewBody
import com.example.Neptune_Prototype.ui.views.modeSelectView.modeSelectViewOnBack
import com.example.Neptune_Prototype.ui.views.modeSettingsView.ModeSettingsViewBody
import com.example.Neptune_Prototype.ui.views.modeSettingsView.modeSettingsViewOnBack
import com.example.Neptune_Prototype.ui.views.searchView.SearchViewBody
import com.example.Neptune_Prototype.ui.views.searchView.searchViewOnBack
import com.example.Neptune_Prototype.ui.views.startView.StartViewBody
import com.example.Neptune_Prototype.ui.views.startView.startViewOnBack
import com.example.Neptune_Prototype.ui.views.statsView.StatsViewBody
import com.example.Neptune_Prototype.ui.views.statsView.statsViewOnBack
import com.example.Neptune_Prototype.ui.views.voteView.VoteViewBody
import com.example.Neptune_Prototype.ui.views.voteView.voteViewOnBack

object ViewsCollection {
    val START_VIEW =
        View(
            name = "START_VIEW",
            composableViewBody = { navController -> StartViewBody(navController) },
            onBack = { navController -> startViewOnBack(navController) },
            topBarDescription = "Start"
        )
    val JOIN_VIEW =
        View(
            name = "JOIN_VIEW",
            composableViewBody = { navController -> JoinViewBody(navController) },
            onBack = { navController -> joinViewOnBack(navController) },
            topBarDescription = "Session beitreten"
        )
    val VOTE_VIEW =
        View(
            name = "VOTE_VIEW",
            composableViewBody = { navController -> VoteViewBody(navController) },
            onBack = { navController -> voteViewOnBack(navController) },
            topBarDescription = "[Modus]"
        )
    val SEARCH_VIEW =
        View(
            name = "SEARCH_VIEW",
            composableViewBody = { navController -> SearchViewBody(navController) },
            onBack = { navController -> searchViewOnBack(navController) },
            topBarDescription = "[Modus]"
        )
    val MODE_SELECT_VIEW =
        View(
            name = "MODE_SELECT_VIEW",
            composableViewBody = { navController -> ModeSelectViewBody(navController) },
            onBack = { navController -> modeSelectViewOnBack(navController) },
            topBarDescription = "Modusauswahl"
        )
    val MODE_SETTINGS_VIEW =
        View(
            name = "MODE_SETTINGS_VIEW",
            composableViewBody = { navController -> ModeSettingsViewBody(navController) },
            onBack = { navController -> modeSettingsViewOnBack(navController) },
            topBarDescription = "Einstellungen"
        )
    val CONTROL_VIEW =
        View(
            name = "CONTROL_VIEW",
            composableViewBody = { navController -> ControlViewBody(navController) },
            onBack = { navController -> controlViewOnBack(navController) },
            topBarDescription = "[Modus]"
        )
    val INFO_VIEW =
        View(
            name = "INFO_VIEW",
            composableViewBody = { navController -> InfoViewBody(navController) },
            onBack = { navController -> infoViewOnBack(navController) },
            topBarDescription = "Session-Infos"
        )
    val STATS_VIEW =
        View(
            name = "STATS_VIEW",
            composableViewBody = { navController -> StatsViewBody(navController) },
            onBack = { navController -> statsViewOnBack(navController) },
            topBarDescription = "Session-Statistiken"
        )
}