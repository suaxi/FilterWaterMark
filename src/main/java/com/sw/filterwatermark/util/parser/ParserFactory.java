package com.sw.filterwatermark.util.parser;

import com.sw.filterwatermark.constant.PlatformConfig;
import com.sw.filterwatermark.enmu.Platform;
import com.sw.filterwatermark.mapper.ParserMapper;
import com.sw.filterwatermark.util.ApplicationUtil;
import org.springframework.stereotype.Component;

/**
 * @author Suaxi
 * @date 2022/5/27 18:36
 */
@Component
public class ParserFactory {

    /**
     * 根据url获取对应平台解析器
     *
     * @param url 视频url
     * @return 对应解析器
     */
    public ParserMapper getParser(String url) {
        for (Platform platform : PlatformConfig.PLATFORMS) {
            if (!this.isSupportPlatform(url, platform)) {
                throw new IllegalArgumentException("去水印功能暂不支持该短视频平台");
            }
            return (ParserMapper) ApplicationUtil.getBean(platform.getParserClass().getSimpleName());
        }
        return null;
    }

    /**
     * 根据url判断是否支持该平台
     *
     * @param url 视频url
     * @param platform 平台信息
     * @return
     */
    private boolean isSupportPlatform(String url, Platform platform) {
        return url.contains(platform.getDomain());
    }
}
