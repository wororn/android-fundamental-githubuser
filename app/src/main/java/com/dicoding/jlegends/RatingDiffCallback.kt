package com.dicoding.jlegends

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.jlegends.user.User

class RatingDiffCallback(private val mOldNoteList: List<User>, private val mNewNoteList: List<User>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldNoteList.size
    }
    override fun getNewListSize(): Int {
        return mNewNoteList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition].id == mNewNoteList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldNoteList[oldItemPosition]
        val newEmployee = mNewNoteList[newItemPosition]
        return oldEmployee.avatar_url == newEmployee.avatar_url && oldEmployee.login == newEmployee.login
    }
}