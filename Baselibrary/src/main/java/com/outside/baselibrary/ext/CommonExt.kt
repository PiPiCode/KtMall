package com.kotlin.base.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.arch.core.executor.DefaultTaskExecutor
import com.kotlin.base.widgets.DefaultTextWatcher
import com.outside.baselibrary.data.protocol.BaseResp
import com.outside.baselibrary.rx.BaseFunc
import com.outside.baselibrary.rx.BaseFuncBoolean
import com.outside.baselibrary.rx.BaseObserver
import com.trello.rxlifecycle3.LifecycleProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
    editText.addTextChangedListener(object:DefaultTextWatcher(){
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}
