package com.cse.medscape.helper

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationHelper : Interceptor {

    /**
     * helps set headers
     */
    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()
        return chain.proceed(request)

    }


}