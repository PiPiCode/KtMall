package com.outside.messagecenter.service.impl

import com.outside.baselibrary.ext.convert
import com.outside.messagecenter.data.protocol.Message
import com.outside.messagecenter.data.repository.MessageRepository
import com.outside.messagecenter.service.MessageService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * className:    MessageServiceImpl
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 17:34
 */

class MessageServiceImpl @Inject constructor() :MessageService {

    @Inject
    lateinit var repository: MessageRepository

    override fun getMessageList(): Observable<MutableList<Message>?> {
        return repository.getMessageList().convert()
    }

}