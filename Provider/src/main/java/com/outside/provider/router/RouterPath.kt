package com.outside.provider.router

/**
 * className:    RouterPath
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/6 16:57
 */

object RouterPath {
    class UserCenter {
        companion object {
            const val PATH_LOGIN = "/userCenter/login"
        }
    }

    class OrderCenter {
        companion object {
            const val PATH_ORDER_CONFIRM = "/orderCenter/confirm"
        }
    }
    
    class PaySDK {
        companion object {
            const val PATH_SDK = "/paySDK/pay"
        }


    }

    class MessageCenter {
        companion object {
            const val PATH_MESSAGE_PUSH = "/messageCenter/push"
            const val PATH_MESSAGE_ORDER = "/messageCenter_2/order"
        }
    }

}