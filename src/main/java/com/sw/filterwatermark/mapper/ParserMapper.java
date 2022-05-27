package com.sw.filterwatermark.mapper;

import com.sw.filterwatermark.pojo.Result;

/**
 * @author Suaxi
 * @date 2022/5/27 17:52
 */
public interface ParserMapper {

    /**
     * 根据url解析无水印视频信息
     * @param url 视频url
     * @return 无水印视频信息
     */
    Result parser(String url);

}
