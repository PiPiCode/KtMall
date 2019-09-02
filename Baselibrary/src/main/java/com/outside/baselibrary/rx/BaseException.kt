package com.outside.baselibrary.rx

/**
 * className:    BaseException
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 17:58
 */

class BaseException(val status:Int,val msg:String): Throwable() {
}