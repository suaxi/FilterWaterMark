package com.sw.filterwatermark.enmu;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Suaxi
 * @date 2022/5/27 17:49
 */
@Getter
@AllArgsConstructor
public enum MediaType {

    /**
     * 视频
     */
    VIDEO("视频"),

    /**
     * 图片
     */
    IMAGE("图片");

    private String name;

}
