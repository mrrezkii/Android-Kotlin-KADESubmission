package com.dicoding.submission2.presenter

import com.dicoding.submission2.repository.MemberListRepo

class MemberListPresenter(val endpoint: String, val repo: MemberListRepo) {
    fun getData() {
        repo.getData("lookup_all_players.php?id=" + endpoint)
    }
}