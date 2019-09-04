package com.outside.baselibrary.data.net

import com.kotlin.base.utils.AppPrefsUtils
import com.outside.baselibrary.common.BaseConstant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * className:    RetrofitFactory
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 17:18
 */

class RetrofitFactory private constructor() {

    companion object {
        val instance: RetrofitFactory by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitFactory()
        }
    }

    private val retrofit: Retrofit
    private val interceptor: Interceptor

    init {
        interceptor = Interceptor.invoke { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("charset", "UTF-8")
                .addHeader("token",AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN)!!)
                .build()
            chain.proceed(request)
        }

        retrofit = Retrofit.Builder()
            .baseUrl(BaseConstant.SERVER_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(initClient())
            .build()
    }


    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(initLogInterceptor())
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }


    // 日志拦截器 打印返回body信息
    private fun initLogInterceptor(): HttpLoggingInterceptor {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor

    }

    //构建API接口
    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}