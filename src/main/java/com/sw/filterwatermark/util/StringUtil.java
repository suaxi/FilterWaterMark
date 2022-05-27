package com.sw.filterwatermark.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Suaxi
 * @date 2022/5/27 19:01
 */
public class StringUtil {

    /**
     * 过滤链接中的汉字（url中包含的 %、# 等特殊符号需在前端处理）
     *
     * @param originOrl 原始url
     * @return 过滤后的url
     */
    public static String filterUrl(String originOrl) {
        String regex = "https?://(\\w|-)+(\\.(\\w|-)+)+(/((\\w|-|.)+(\\?(\\w+=(\\w|%|-)*(\\&\\w+=(\\w|%|-)*)*)?)?)?)+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(originOrl);
        if (matcher.find()) {
            return originOrl.substring(matcher.start(), matcher.end());
        }
        return null;
    }

    /**
     * 获取查询参数
     *
     * @param url url
     * @return 查询参数map
     */
    public static Map<String, List<String>> getQueryParams(String url) {
        try {
            Map<String, List<String>> map = new HashMap<>();
            String[] urlParts = url.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }
                    List<String> values = map.computeIfAbsent(key, k -> new ArrayList<>());
                    values.add(value);
                }
            }
            return map;
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
