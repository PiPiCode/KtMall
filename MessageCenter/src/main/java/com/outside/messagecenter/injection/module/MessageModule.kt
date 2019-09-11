package com.outside.messagecenter.injection.module

import com.outside.messagecenter.service.MessageService
import com.outside.messagecenter.service.impl.MessageServiceImpl
import dagger.Module
import dagger.Provides
/**
 * className:    MessageModule
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 17:42
 */
@Module
class MessageModule {

    @Provides
    fun providesMessageService(service: MessageServiceImpl):MessageService{
        return service
    }
}

