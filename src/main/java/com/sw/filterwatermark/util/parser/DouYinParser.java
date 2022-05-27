package com.sw.filterwatermark.util.parser;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONNull;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sw.filterwatermark.enmu.MediaType;
import com.sw.filterwatermark.mapper.ParserMapper;
import com.sw.filterwatermark.pojo.Result;
import com.sw.filterwatermark.util.URLUtil;
import com.sw.filterwatermark.util.UserAgentUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Suaxi
 * @date 2022/5/27 18:04
 */
@Slf4j
@Component
public class DouYinParser implements ParserMapper {

    /**
     * 视频接口地址
     */
    private static final String API = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=";


    @Override
    public Result parser(String url) {
        Result result = new Result();
        List<Result.Image> images = new ArrayList<>();
        List<Result.Video> videos = new ArrayList<>();

        //UserAgent
        String mobileUserAgent = UserAgentUtil.getMobileUserAgent();

        //获取视频资源信息
        String info = null;
        try {
            info = Jsoup.connect(API + getItemId(URLUtil.getRealUrl(mobileUserAgent, url)))
                    .userAgent(mobileUserAgent)
                    .ignoreContentType(true)
                    .execute()
                    .body();
        } catch (IOException e) {
            log.error("获取重定向地址异常");
            e.printStackTrace();
        }

        //解析无水印资源
        JSONObject itemJson = JSONUtil.parseObj(info).getJSONArray("item_list").getJSONObject(0);

        //标题、封面
        result.setTitle(itemJson.getStr("desc"))
                .setCover(new Result.Image(itemJson.getJSONObject("video")
                        .getJSONObject("cover")
                        .getJSONArray("url_list")
                        .getStr(0)));

        if (!JSONNull.NULL.equals(itemJson.get("images"))) {
            result.setMediaType(MediaType.IMAGE);
            result.setImages(images);

            JSONArray imageJsonArr = itemJson.getJSONArray("images");
            for (int i = 0; i < imageJsonArr.size(); i++) {
                images.add(new Result.Image(
                        imageJsonArr.getJSONObject(i).getJSONArray("url_list").getStr(0),
                        imageJsonArr.getJSONObject(i).getInt("width"),
                        imageJsonArr.getJSONObject(i).getInt("height")
                ));
            }
        } else {
            JSONObject videoJson = itemJson.getJSONObject("video");
            result.setMediaType(MediaType.VIDEO);
            result.setVideos(videos);

            //视频信息
            videos.add(new Result.Video(
                    videoJson.getJSONObject("play_addr").getJSONArray("url_list").getStr(0).replace("playwm", "play"),
                    videoJson.getStr("radio"),
                    videoJson.getInt("width"),
                    videoJson.getInt("height")
            ));
        }
        return result;
    }

    /**
     * 获取视频id
     *
     * @param url 视频url
     * @return 视频id
     */
    private String getItemId(String url) {
        int start = url.indexOf("/video/") + "/video/".length();
        int end = url.lastIndexOf("/");
        return url.substring(start, end);
    }
}
