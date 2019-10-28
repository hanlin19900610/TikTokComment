package tracyeminem.com.tiktokcomment

import android.content.Context
import com.lxj.xpopup.core.BottomPopupView
import kotlinx.android.synthetic.main.custom_edittext_bottom_popup.view.*


/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/28 16:43
 * @描述
 */
class CustomEditTextBottomPopup(context: Context): BottomPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.custom_edittext_bottom_popup
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onShow() {
        super.onShow()
        btnSubmit.setOnClickListener {

        }
    }

    override fun onDismiss() {
        super.onDismiss()
    }

    fun getComment(): String {
        return et_comment.text.toString()
    }


}