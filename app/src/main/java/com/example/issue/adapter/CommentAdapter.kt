package com.example.issue.adapter

import android.content.Context
import com.example.issue.db.CommentsTable
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.issue.R
import android.text.Html
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.widget.TextView
import android.widget.LinearLayout

class CommentAdapter(var context: Context, var mList: List<CommentsTable>) :
    RecyclerView.Adapter<CommentAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, poss: Int) {
        holder.tvUserName.text =
            "Name : " + mList[poss].userName?.substring(0, 1)?.toUpperCase() + mList[poss]
                .userName?.substring(1)
        holder.tvTitle.text = Html.fromHtml("<b>" + "Node Id : " + "</b>" + mList[poss].nodeId)
        val date = mList[poss].updatedAt
        val newDate = date?.split("T")?.toTypedArray()
        holder.tvUpdateDate.text = "Date : " + (newDate?.get(0) ?: "NA")
        holder.tvDescription.text = Html.fromHtml(
            "<b>" + "Description : " + "</b>" + mList[poss].body + "",
            Html.FROM_HTML_MODE_COMPACT
        )
        Glide.with(context)
            .load(mList[poss].userImage)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.ivUserImg)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class MyViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var tvUserName: TextView
        var tvTitle: TextView
        var tvUpdateDate: TextView
        var tvDescription: TextView
        var ivUserImg: ImageView
        var llParent: LinearLayout

        init {
            tvUserName = row.findViewById(R.id.tvUserName)
            tvTitle = row.findViewById(R.id.tvTitle)
            tvUpdateDate = row.findViewById(R.id.tvUpdateDate)
            tvDescription = row.findViewById(R.id.tvDescription)
            ivUserImg = row.findViewById(R.id.ivUserImage)
            llParent = row.findViewById(R.id.llParent)
        }
    }
}