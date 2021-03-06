/**
 * Title: AsyncHttpConst.java
 * Description: 
 * Copyright: Copyright (c) 2013-2015 luoxudong.com
 * Company: 个人
 * Author: 罗旭东 (hi@luoxudong.com)
 * Date: 2015年7月13日 下午3:49:49
 * Version: 1.0
 */
package com.luoxudong.app.asynchttp;

import android.os.Build;

/** 
 * ClassName: AsyncHttpConst
 * Description:http常量信息
 * Create by: 罗旭东
 * Date: 2015年7月13日 下午3:49:49
 */
public class AsyncHttpConst {
	public static final String TAG_LOG = "AsyncHttpHelp";
	public static final int MAX_CONNECTIONS = 50;//创建socket上线
	public static final int MAX_PER_ROUTE = 30;//最大并发连接数
	public static final int DEFAULT_SO_TIMEOUT = 30 * 1000;//读数据超时时间
	public static final int DEFAULT_CONNECT_TIMEOUT = 30 * 1000;//连接超时时间
	public static final int DEFAULT_MAX_RETRIES = 0;//请求失败时重复请求次数
	public static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192;
	public static final String HEADER_CONTENT_TYPE_JSON = "application/json";
	public static final String HEADER_CONTENT_TYPE_TEXT = "text/plain";
	public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
	public static final String ENCODING_GZIP = "gzip";
	public static final String HEADER_RANGE = "range";
	public static final String HTTP_ENCODING = "UTF-8";
	public static final String HEADER_COOKIE = "Cookie";
	public static final String HEADER_USER_AGENT = "User-Agent";
	public static final long TRANSFER_MAX_SIZE = 1024 * 1024 * 1024;
	public static long TRANSFER_REFRESH_TIME_INTERVAL = 1000;//传输刷新时间间隔(毫秒)
	public static String sUserAgent = "Mozilla/5.0 (Linux; Android " + Build.VERSION.RELEASE + "; " + Build.MODEL + " Build/KTU84P) AppleWebKit/537.36 hi@luoxudong.com";
}
