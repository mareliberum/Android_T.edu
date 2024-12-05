package com.example.myapplication.recycler.util

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.data.db.Joke

class DiffUtilCallback(
    private val oldList: List<Joke>,
    private val newList: List<Joke>

): DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
