package com.example.issue

import android.content.Context
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.issue.db.AppDatabase
import androidx.recyclerview.widget.RecyclerView
import com.example.issue.adapter.CommentAdapter
import com.example.issue.util.TransparentProgressDialog
import android.os.Bundle
import com.example.issue.R
import android.text.Html
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issue.network.API
import com.example.issue.network.RetrofitClient
import com.example.issue.model.Comments
import android.widget.Toast
import com.google.gson.Gson
import com.example.issue.db.CommentsTable
import com.example.issue.IssuesInfoActivity
import com.example.issue.util.DateTimeUtil
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IssuesInfoActivity : AppCompatActivity() {
    private var tvIssueInfo: TextView? = null
    private var db: AppDatabase? = null
    private var rvComments: RecyclerView? = null
    private var commentAdapter: CommentAdapter? = null
    private var url: String? = null
    private var imageBack: ImageView? = null
    private var pd: TransparentProgressDialog? = null
    private var recordId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issues_info)
        db = AppDatabase.getInstance(this)
        val b = intent.extras
        url = b!!.getString("comment_url")
        recordId = b.getInt("record_id")
        val issue_info = b.getString("issue_info")
        //        Log.e("url====>",url);
//        Log.e("record_id====>",recordId+"");
        imageBack = findViewById(R.id.imageBack)
        rvComments = findViewById(R.id.rvComments)
        tvIssueInfo = findViewById(R.id.tvIssueInfo)
        tvIssueInfo?.setText(Html.fromHtml(issue_info, Html.FROM_HTML_MODE_COMPACT))
        rvComments?.setLayoutManager(
            LinearLayoutManager(
                this@IssuesInfoActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        rvComments?.addItemDecoration(SpacingItemDecoration(this@IssuesInfoActivity, 5))
        if (db?.commentDao()?.getCommentsByUrl(recordId)?.size!! < 1) {
            mGetCommentData()
        } else {
            commentAdapter =
                CommentAdapter(this@IssuesInfoActivity,
                    db?.commentDao()!!.getCommentsByUrl(recordId) as List<CommentsTable>
                )
            rvComments?.setAdapter(commentAdapter)
        }
        imageBack?.setOnClickListener(View.OnClickListener { onBackPressed() })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun mGetCommentData() {
        showProgressDialog()
        val api = RetrofitClient.instance?.myApi
        val detailCall = api?.getAllComments(url)
        detailCall.enqueue(object : Callback<List<Comments>> {
            override fun onResponse(
                call: Call<List<Comments>>,
                response: Response<List<Comments>>
            ) {
                hideProgressDialog()
                if (response.code() == 404) {
                    Toast.makeText(
                        this@IssuesInfoActivity,
                        "Please Check once ",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (!response.isSuccessful) {
                    Toast.makeText(
                        this@IssuesInfoActivity,
                        "Please Check once111 ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                val comments = response.body()!!
                Log.e("comments resp===", Gson().toJson(response.body()))
                if (comments.size > 0) {
                    for (comment in comments) {
                        val date = comment.updated_at
                        val newDate = date?.split("T")?.toTypedArray()
                        db!!.commentDao()?.insertAllComments(
                            CommentsTable(
                                if (comment.node_id == null) "NA" else html2text(comment.node_id),
                                if (comment.body == null) "NA" else html2text(comment.body),
                                DateTimeUtil.convertDateToString(
                                    newDate?.get(0)
                                ),
                                if (comment.user?.login == null) "NA" else html2text(
                                    comment.user?.login
                                ),
                                if (comment.user?.avatar_url == null
                                ) "NA" else comment.user?.avatar_url,
                                recordId
                            )
                        )
                    }
                }
                commentAdapter = CommentAdapter(
                    this@IssuesInfoActivity,
                    db!!.commentDao()?.getCommentsByUrl(recordId) as List<CommentsTable>
                ) //db.commentDao().getAllComments()
                rvComments!!.adapter = commentAdapter
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                hideProgressDialog()
            }
        })
    }

    fun showProgressDialog() {
        if (pd == null) {
            pd = TransparentProgressDialog(this)
            // pd.setMsg("Loading...");
            pd!!.setCancelable(false)
        }
        if (!this.isDestroyed) {
            if (pd!!.isShowing) {
                pd!!.dismiss()
            } else {
                pd!!.show()
            }
        }
    }

    fun hideProgressDialog() {
        if (pd != null && !this.isDestroyed) {
            if (pd!!.isShowing) {
                pd!!.dismiss()
            }
        }
    }

    inner class SpacingItemDecoration(context: Context, padding: Int) : ItemDecoration() {
        private val spacing: Int
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            if (position != state.itemCount - 1) {
                outRect.right = spacing
                outRect.left = spacing
            }
        }

        init {
            val metrics = context.resources.displayMetrics
            spacing = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                padding.toFloat(),
                metrics
            ).toInt()
        }
    }

    companion object {
        fun html2text(html: String?): String {
            return Jsoup.parse(html).text()
        }
    }
}

private fun <T> Call<T>?.enqueue(callback: Callback<List<Comments>>) {

}
