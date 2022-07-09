package com.example.issue

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.issue.adapter.IssuesAdapter
import com.example.issue.db.AppDatabase
import com.example.issue.db.AppDatabase.Companion.getInstance
import com.example.issue.db.IssuesTable
import com.example.issue.model.Issues
import com.example.issue.network.RetrofitClient
import com.example.issue.util.DateTimeUtil
import com.example.issue.util.TransparentProgressDialog
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IssuesListActivity : AppCompatActivity() {
    private var db: AppDatabase? = null
    private var recycler: RecyclerView? = null
    private var issuesAdapter: IssuesAdapter? = null
    private var noData: TextView? = null
    private var pd: TransparentProgressDialog? = null
    private var imageBack: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issues_list)
        db = getInstance(this@IssuesListActivity)
        noData = findViewById(R.id.noData)
        recycler = findViewById(R.id.recycler)
        imageBack = findViewById(R.id.imageBack)
        recycler?.setLayoutManager(
            LinearLayoutManager(
                this@IssuesListActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        )
        recycler?.addItemDecoration(SpacingItemDecoration(this@IssuesListActivity, 5))
        if (db!!.issueDao()!!.all!!.size < 1) {
            mGetIssueData()
            recycler?.setVisibility(View.VISIBLE)
        } else {
            noData?.setVisibility(View.GONE)
            issuesAdapter = IssuesAdapter(
                this@IssuesListActivity,
                db!!.issueDao()!!.all as List<IssuesTable>
            )
            recycler?.setAdapter(issuesAdapter)
        }
        imageBack?.setOnClickListener(View.OnClickListener { onBackPressed() })
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun mGetIssueData() {
        showProgressDialog()
        val api = RetrofitClient.instance?.myApi
        val detailCall = api?.issueDetails
        detailCall?.enqueue(object : Callback<List<Issues>> {
            override fun onResponse(call: Call<List<Issues>>, response: Response<List<Issues>>) {
                hideProgressDialog()
                if (response.code() == 404) {
                    Toast.makeText(
                        this@IssuesListActivity,
                        "Please Check once ",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (!response.isSuccessful) {
                    Toast.makeText(
                        this@IssuesListActivity,
                        "Please Check once111 ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                val issues = response.body()!!
                //Log.e("response===",new Gson().toJson(response.body()));
                if (issues.size > 0) {
                    for (issue in issues) {
                        val date = issue.updated_at
                        val newDate = date!!.split("T").toTypedArray()
                        db!!.issueDao()!!.insertAll(
                            IssuesTable(
                                issue.id,
                                if (issue.title == null) "NA" else html2text(issue.title),
                                if (issue.body == null) "NA" else html2text(issue.body),
                                DateTimeUtil.convertDateToString(
                                    newDate[0]
                                ),
                                if (issue.user!!.login == null) "NA" else html2text(
                                    issue.user!!.login
                                ),
                                if (issue.user!!.avatar_url == null) "NA" else issue.user!!.avatar_url,
                                if (issue.comments_url == null) "NA" else issue.comments_url
                            )
                        )
                    }
                }
                issuesAdapter = IssuesAdapter(
                    this@IssuesListActivity,
                    db!!.issueDao()!!.all as List<IssuesTable>
                )
                recycler!!.adapter = issuesAdapter
            }

            override fun onFailure(call: Call<List<Issues>>, t: Throwable) {
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

private fun <T> Call<T>?.enqueue(callback: Callback<List<Issues>>) {

}
