package com.outside.messagecenter.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.outside.baselibrary.rx.RxBus
import com.outside.baselibrary.ui.fragment.BaseMvpFragment
import com.outside.messagecenter.R
import com.outside.messagecenter.data.protocol.Message
import com.outside.messagecenter.injection.component.DaggerMessageComponent
import com.outside.messagecenter.injection.module.MessageModule
import com.outside.messagecenter.presenter.MessagePresenter
import com.outside.messagecenter.presenter.view.MessageView
import com.outside.messagecenter.ui.adapter.MessageAdapter
import com.outside.provider.event.MessageBadgeEvent
import kotlinx.android.synthetic.main.fragment_message.*

/**
 * className:    MessageFragment
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 17:31
 */

class MessageFragment: BaseMvpFragment<MessagePresenter>(),MessageView {


    private lateinit var mAdapter: MessageAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_message
    }

    override fun injectComponent() {
        DaggerMessageComponent.builder().activityComponent(activityComponent).messageModule(
            MessageModule()
        ).build().inject(this)

    }


    override fun initView() {

        mMessageRv.layoutManager = LinearLayoutManager(activity)
        mAdapter = MessageAdapter(activity!!)
        mMessageRv.adapter = mAdapter

        mPresenter.getMessageList()

    }


    override fun onGetMessageListResult(t: MutableList<Message>?) {
        if (t!=null && t.size>0){
            mAdapter.setData(t)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }else{
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden){
            RxBus.send(MessageBadgeEvent(false))
        }
    }
}