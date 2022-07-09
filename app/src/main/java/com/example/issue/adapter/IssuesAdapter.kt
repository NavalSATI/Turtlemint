package com.example.issue.adapter

import android.content.Context
import com.example.issue.db.IssuesTable
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.issue.R
import android.text.Html
import com.bumptech.glide.Glide
import android.content.Intent
import android.view.View
import android.widget.ImageView
import com.example.issue.IssuesInfoActivity
import android.widget.TextView
import android.widget.LinearLayout

class IssuesAdapter(var context: Context, var mList: List<IssuesTable>) :
    RecyclerView.Adapter<IssuesAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvUserName.text = "Name : " + mList[position].userName?.substring(0, 1)
            ?.toUpperCase() + mList[position].userName?.substring(1)
        holder.tvTitle.text =
            Html.fromHtml("<b>" + "Title : " + "</b>" + mList[position].title)
        holder.tvUpdateDate.text = "Date : " + mList[position].updatedAt
        holder.tvDescription.text = Html.fromHtml(
            "<b>" + "Description : " + "</b>" + mList[position].body + "",
            Html.FROM_HTML_MODE_COMPACT
        )
        Glide.with(context)
            .load(mList[position].userImage)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.ivUserImg)
        holder.llParent.setOnClickListener {
            val intent = Intent(context, IssuesInfoActivity::class.java)
            intent.putExtra("comment_url", mList[position].commentsUrl)
            intent.putExtra("issue_info", mList[position].body)
            intent.putExtra("record_id", mList[position].id)
            context.startActivity(intent)
        }
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