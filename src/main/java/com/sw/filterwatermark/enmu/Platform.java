package com.sw.filterwatermark.enmu;

import com.sw.filterwatermark.util.parser.DouYinParser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Suaxi
 * @date 2022/5/27 18:39
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Platform {

    /**
     * 抖音
     */
    DOU_YIN("抖音", "v.douyin.com", DouYinParser.class);

    private String name;
    private String domain;
    private Class<?> parserClass;
}
