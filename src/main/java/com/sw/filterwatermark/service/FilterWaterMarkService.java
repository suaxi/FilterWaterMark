package com.sw.filterwatermark.service;

import com.sw.filterwatermark.pojo.Result;

/**
 * @author Suaxi
 * @date 2022/5/27 20:54
 */
public interface FilterWaterMarkService {

    /**
     * 获取无水印视频信息
     * @param url 视频url
     * @return 无水印视频信息
     */
    Result filterWaterMark(String url);
}
