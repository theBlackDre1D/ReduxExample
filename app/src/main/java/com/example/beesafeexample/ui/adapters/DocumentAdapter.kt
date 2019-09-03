package com.example.beesafeexample.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beesafeexample.AppController
import com.example.beesafeexample.R
import com.example.beesafeexample.models.User
import kotlinx.android.synthetic.main.item_document.view.*

class DocumentAdapter(
    private val documents: List<User>
): RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        val v = LayoutInflater.from(AppController.currentActivity.get()!!).inflate(R.layout.item_document, parent, false)
        return DocumentViewHolder(v)
    }

    override fun getItemCount() = documents.size

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) { holder.bind(documents[position]) }

    inner class DocumentViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(user: User) {
            itemView.tvName.text = user.name
            itemView.tvSurname.text = user.surname
            itemView.tvAge.text = user.age.toString()
        }
    }
}