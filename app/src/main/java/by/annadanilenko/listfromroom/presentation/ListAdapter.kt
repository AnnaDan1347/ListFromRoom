package by.annadanilenko.listfromroom.presentation

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.annadanilenko.listfromroom.R
import by.annadanilenko.listfromroom.data.model.dbroom.ItemUser
import com.bumptech.glide.Glide


class ListAdapter(
    val data: List<ItemUser>
) : RecyclerView.Adapter<ListAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MessageViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_user, viewGroup, false)
        return MessageViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: MessageViewHolder, i: Int) {

        viewHolder.userName.text = data[i]?.userName
        viewHolder.originalUrl.text = data[i]?.originalApi

        viewHolder.avatar.clipToOutline = true

        try {
            Glide.with(viewHolder.avatar)
                .load(Uri.parse(data[i].imageUrl))
                .into(viewHolder.avatar)
        } catch (e: Exception) {
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MessageViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        internal var userName =
            mView.findViewById(R.id.userName) as TextView
        internal var originalUrl =
            mView.findViewById(R.id.originalUrl) as TextView
        internal var avatar = mView.findViewById(R.id.userImage) as ImageView
    }
}