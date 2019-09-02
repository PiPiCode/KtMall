package com.outside.baselibrary.data.protocol

/**
 * className:    BaseResp
 * description:  out 相当于 只读取，相当于Java中? extends T
 * author:       CLW2018
 * creatTime:    2019/9/2 17:39
 */

class BaseResp<out T>(val status: Int, val message: String, val data: T) {

}