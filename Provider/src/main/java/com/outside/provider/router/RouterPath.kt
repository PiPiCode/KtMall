package com.outside.provider.router

/**
 * className:    RouterPath
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/6 16:57
 */
 
object RouterPath {
    class UserCenter{
        companion object{
            const val PATH_LOGIN = "/userCenter/login"
        }
    }
    class OrderCenter{
        companion object{
            const val PATH_ORDER_CONFIRM = "/orderCenter/confirm"
        }
    }

}