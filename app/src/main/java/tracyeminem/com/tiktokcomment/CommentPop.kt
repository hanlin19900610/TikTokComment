package tracyeminem.com.tiktokcomment

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.lxj.xpopup.core.BottomPopupView
import kotlinx.android.synthetic.main.custom_bottom_popup.view.*
import me.drakeet.multitype.MultiTypeAdapter
import tracyeminem.com.peipei.adapter.ChildCommentAdapter
import tracyeminem.com.peipei.adapter.LoadMoreAdapter
import tracyeminem.com.peipei.adapter.ParentCommentAdapter
import tracyeminem.com.peipei.model.ChildComment
import tracyeminem.com.peipei.model.LoadMoreBean
import tracyeminem.com.peipei.model.ParentComment
import com.lxj.xpopup.util.XPopupUtils
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.SimpleCallback


/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/28 16:32
 * @描述
 */
class CommentPop(context: Context) : BottomPopupView(context){

    internal var multiTypeAdapter: MultiTypeAdapter? = null
    lateinit var items:MutableList<Any>

    override fun getImplLayoutId(): Int {
        return R.layout.custom_bottom_popup
    }

    override fun onCreate() {
        super.onCreate()
        val loadMoreAdapter = LoadMoreAdapter()
        val parentCommentAdapter = ParentCommentAdapter()
        val childCommentAdapter = ChildCommentAdapter()

        items = ArrayList()
        multiTypeAdapter = MultiTypeAdapter()
        multiTypeAdapter!!.register(loadMoreAdapter)
        multiTypeAdapter!!.register(parentCommentAdapter)
        multiTypeAdapter!!.register(childCommentAdapter)
        multiTypeAdapter!!.items = items
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = multiTypeAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        for (i in 0..20){
            if(i % 2 == 0){
                items.add(ParentComment("parent"))
            }else{
                items.add(ParentComment("parent"))
                for (i in 0..3){
                    items.add(ChildComment("child"))
                }
                items.add(LoadMoreBean("加载更多"))
            }
        }

        multiTypeAdapter!!.notifyDataSetChanged()

        loadMoreAdapter.onLoadMoreInterface = object : LoadMoreAdapter.onLoadMore {
            override fun onLoadMore(position: Int) {
                for(i in 0..5) {
                    items.add(position, ChildComment("child"))
                }
                multiTypeAdapter!!.items = items
                multiTypeAdapter!!.notifyItemRangeInserted(position,6)
            }
        }

        tv_temp.setOnClickListener {
            //弹出新的弹窗用来输入
            val textBottomPopup = CustomEditTextBottomPopup(context)
            XPopup.Builder(context)
                .autoOpenSoftInput(true)
                //                        .hasShadowBg(false)
                .setPopupCallback(object : SimpleCallback() {
                    override fun onShow() {}

                    override fun onDismiss() {
                        val comment = textBottomPopup.getComment()
                        if (!comment.isEmpty()) {
                            //                                data.add(0, comment)
                            //                                commonAdapter.notifyDataSetChanged()
                        }
                    }
                })
                .asCustom(textBottomPopup)
                .show()
        }
    }

    //完全可见执行
    override fun onShow() {
        super.onShow()
    }

    //完全消失执行
    override fun onDismiss() {

    }

    override fun getMaxHeight(): Int {
        return (XPopupUtils.getWindowHeight(context) * .80f).toInt()
    }

}