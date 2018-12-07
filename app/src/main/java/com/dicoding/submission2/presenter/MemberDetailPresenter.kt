package com.dicoding.submission2.presenter

import com.dicoding.submission2.repository.MemberDetailRepo

class MemberDetailPresenter(val endpoint: String, val repository: MemberDetailRepo) {
    fun getData() {
        repository.getData("lookupplayer.php?id=" + endpoint)
    }
}