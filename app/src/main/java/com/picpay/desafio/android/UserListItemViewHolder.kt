package com.picpay.desafio.android

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val username = itemView.findViewById<TextView>(R.id.username)
        val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
        val picture = itemView.findViewById<ImageView>(R.id.picture)

        name.text = user.name
        username.text = user.username
        progressBar.visibility = View.VISIBLE

        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(picture, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    progressBar.visibility = View.GONE
                }
            })

    }
}