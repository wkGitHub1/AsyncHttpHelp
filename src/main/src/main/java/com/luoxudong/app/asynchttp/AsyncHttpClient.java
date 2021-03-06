/**
 * Title: AsyncHttpClient.java
 * Description: AsyncHttpClient类
 * Copyright: Copyright (c) 2013-2015 luoxudong.com
 * Company: 个人
 * Author: 罗旭东 (hi@luoxudong.com)
 * Date: 2015年7月13日 下午3:49:02
 * Version: 1.0
 */
package com.luoxudong.app.asynchttp;

import android.text.TextUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;

import com.luoxudong.app.asynchttp.cookie.PersistentCookieJar;
import com.luoxudong.app.asynchttp.cookie.cache.SetCookieCache;
import com.luoxudong.app.asynchttp.https.MySslSocketFactory;
import com.luoxudong.app.asynchttp.https.SSLParams;
import com.luoxudong.app.asynchttp.interceptor.UserAgentInterceptor;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/** 
 * ClassName: AsyncHttpClient
 * Description:AsyncHttpClient类
 * Create by: 罗旭东
 * Date: 2015年7月13日 下午3:49:02
 */
public class AsyncHttpClient {
	private static final String TAG = AsyncHttpClient.class.getSimpleName();
	/** mOkHttpClient对象 */
	private OkHttpClient mOkHttpClient = null;
	/** 全局mSslSocketFactory */
	private SSLSocketFactory mSslSocketFactory = null;
	/** 全局mTrustManager */
	private X509TrustManager mTrustManager = null;
	/** 全局cookie */
	private CookieJar mCookieJar = null;
	/** 全局userAgent */
	private String mUserAgent = null;
	
	public AsyncHttpClient() {
		SSLParams sslParams = new MySslSocketFactory().getSslSocketFactory(null, null, null);
		mOkHttpClient = new OkHttpClient.Builder()
		.connectTimeout(AsyncHttpConst.DEFAULT_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
		.readTimeout(AsyncHttpConst.DEFAULT_SO_TIMEOUT, TimeUnit.MILLISECONDS)
		.writeTimeout(AsyncHttpConst.DEFAULT_SO_TIMEOUT, TimeUnit.MILLISECONDS)
		.addInterceptor(new UserAgentInterceptor())
		.cookieJar(new PersistentCookieJar(new SetCookieCache(), null))
		.sslSocketFactory(sslParams.getSSLSocketFactory(), sslParams.getTrustManager())
		.build();
	}

	public AsyncHttpClient sslSocketFactory(SSLSocketFactory sslSocketFactory, X509TrustManager trustManager) {
		mSslSocketFactory = sslSocketFactory;
		mTrustManager = trustManager;
		return this;
	}

	public AsyncHttpClient cookieJar(CookieJar cookieJar) {
		mCookieJar = cookieJar;
		return this;
	}

	public AsyncHttpClient userAgent(String userAgent) {
		mUserAgent = userAgent;
		return this;
	}

	public void build() {
		OkHttpClient.Builder builder  = getOkHttpClient().newBuilder();

		if (mSslSocketFactory != null && mTrustManager != null) {
			builder.sslSocketFactory(mSslSocketFactory, mTrustManager);
		}

		if (mCookieJar != null) {
			builder.cookieJar(mCookieJar);
		}

		if (!TextUtils.isEmpty(mUserAgent)) {
			builder.addInterceptor(new UserAgentInterceptor(mUserAgent));
		}
		mOkHttpClient = builder.build();
	}

	public OkHttpClient getOkHttpClient() {
		return mOkHttpClient;
	}
}
