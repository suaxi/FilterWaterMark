package com.sw.filterwatermark.controller;

import com.sw.filterwatermark.pojo.Result;
import com.sw.filterwatermark.service.FilterWaterMarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author Suaxi
 * @date 2022/5/27 17:25
 */
@Api(tags = "去水印接口")
@RestController
public class FilterWaterMarkController {

    @Autowired
    private FilterWaterMarkService filterWaterMarkService;

    @ApiOperation("视频去水印")
    @PostMapping("/filterWaterMark")
    public ResponseEntity<Result> download(@NotBlank(message = "请输入链接地址")
                                           @ApiParam("视频链接")
                                           @RequestParam("url") String url) {
        return new ResponseEntity<>(filterWaterMarkService.filterWaterMark(url), HttpStatus.OK);
    }
}
