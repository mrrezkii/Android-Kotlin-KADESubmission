package com.dicoding.submission2.view

import com.dicoding.submission2.model.MemberModel


interface ViewMemberDetail {
    fun onDataLoaded(member: MemberModel)
}