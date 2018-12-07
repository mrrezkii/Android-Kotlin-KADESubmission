package com.dicoding.submission2.view

import com.dicoding.submission2.model.MemberModel


interface ViewMemberList {
    fun onDataLoaded(mutableList: MutableList<MemberModel>)
}