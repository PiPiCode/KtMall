package com.outside.messagecenter.injection.component

import com.outside.baselibrary.injection.PerComponentScope
import com.outside.baselibrary.injection.component.ActivityComponent
import com.outside.messagecenter.injection.module.MessageModule
import com.outside.messagecenter.ui.fragment.MessageFragment
import dagger.Component

/**
 * className:    MessageComponent
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 17:47
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(MessageModule::class))
interface MessageComponent {
    fun inject(messageFragment: MessageFragment)

}