package com.sw.filterwatermark.pojo;

import com.sw.filterwatermark.enmu.MediaType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Suaxi
 * @date 2022/5/27 17:54
 */
@Data
@Accessors(chain = true)
@ApiModel("返回结果")
public class Result {

    @ApiModelProperty("媒体类型")
    private MediaType mediaType;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("视频封面")
    private Image cover;

    @ApiModelProperty("图片集合")
    private List<Video> videos;

    @ApiModelProperty("视频集合")
    private List<Image> images;

    @Data
    @AllArgsConstructor
    public static class Video {

        @ApiModelProperty("地址")
        private String url;

        @ApiModelProperty("清晰度")
        private String quality;

        @ApiModelProperty("宽度")
        private Integer width;

        @ApiModelProperty("高度")
        private Integer height;
    }

    @Data
    @AllArgsConstructor
    public static class Image {

        @ApiModelProperty("地址")
        private String url;

        @ApiModelProperty("宽度")
        private Integer width;

        @ApiModelProperty("高度")
        private Integer height;

        public Image(String url) {
            this.url = url;
        }
    }
}
