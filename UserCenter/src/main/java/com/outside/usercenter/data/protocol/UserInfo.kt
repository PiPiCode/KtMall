package com.outside.usercenter.data.protocol

/**
 * className:    UserInfo
 * description:  用户实体类
 * author:       CLW2018
 * creatTime:    2019/9/2 17:47
 */

data class UserInfo(val id:String,
                    val userIcon:String,
                    val userName:String,
                    val userGender:String,
                    val userMobile:String,
                    val userSign:String)