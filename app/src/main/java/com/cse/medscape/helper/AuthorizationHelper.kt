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
            .addHeader("App-Id", "4ad985fc")
            .addHeader("App-Key", "db6aa1b5f942f03379396eaeb8b6b591")
            .build()
        return chain.proceed(request)

    }


}