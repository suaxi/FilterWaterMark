package com.sw.filterwatermark.util;

import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * @author Suaxi
 * @date 2022/5/27 18:13
 */
public class URLUtil {

    /**
     * 获取重定向地址
     * @param userAgent
     * @param url
     * @return
     * @throws IOException
     */
    public static String getRealUrl(String userAgent, String url) throws IOException {
        return Jsoup.connect(url)
                .userAgent(userAgent)
                .followRedirects(true)
                .execute()
                .url()
                .toString();
    }
}
