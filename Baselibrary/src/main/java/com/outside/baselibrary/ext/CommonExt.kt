package com.outside.baselibrary.ext

import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.kennyc.view.MultiStateView
import com.outside.baselibrary.R
import com.outside.baselibrary.data.protocol.BaseResp
import com.outside.baselibrary.rx.BaseFunc
import com.outside.baselibrary.rx.BaseFuncBoolean
import com.outside.baselibrary.rx.BaseObserver
import com.outside.baselibrary.utils.GlideUtils
import com.outside.baselibrary.widgets.DefaultTextWatcher
import com.trello.rxlifecycle3.LifecycleProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.find

//Kotlin通用扩展

fun <T> Observable<T>.excute(observer: BaseObserver<T>, lifecycleProvider: LifecycleProvider<*>) {

    this.observeOn(AndroidSchedulers.mainThread())
        .compose(lifecycleProvider.bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .subscribe(observer)
}

fun <T> Observable<BaseResp<T>>.convert():Observable<T> {
    return this.flatMap(BaseFunc())
}

fun <T> Observable<BaseResp<T>>.convertBoolean():Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean())
}
/*
    ImageView加载网络图片
 */
fun ImageView.loadUrl(url: String) {
    GlideUtils.loadUrlImage(context, url, this)
}


/*
    扩展点击事件
 */
fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}

fun View.onClick(block: () -> Unit) {
    this.setOnClickListener { block() }
}

fun Button.enable(editText: EditText,method:()->Boolean){
    val btn = this
    editText.addTextChangedListener(object: DefaultTextWatcher(){
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}

/*
    多状态视图开始加载
 */
fun MultiStateView.startLoading(){
    viewState = MultiStateView.VIEW_STATE_LOADING
    val loadingView = getView(MultiStateView.VIEW_STATE_LOADING)
    val animBackground = loadingView!!.find<View>(R.id.loading_anim_view).background
    (animBackground as AnimationDrawable).start()
}

/*
    扩展视图可见性
 */
fun View.setVisible(visible:Boolean){
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

