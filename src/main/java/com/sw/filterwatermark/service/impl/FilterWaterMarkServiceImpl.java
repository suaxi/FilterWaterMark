package com.sw.filterwatermark.service.impl;

import com.sw.filterwatermark.mapper.ParserMapper;
import com.sw.filterwatermark.pojo.Result;
import com.sw.filterwatermark.service.FilterWaterMarkService;
import com.sw.filterwatermark.util.StringUtil;
import com.sw.filterwatermark.util.parser.ParserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Suaxi
 * @date 2022/5/27 18:55
 */
@Service
public class FilterWaterMarkServiceImpl implements FilterWaterMarkService {

    @Autowired
    private ParserFactory parserFactory;

    @Override
    public Result filterWaterMark(String url) {
        ParserMapper parser = parserFactory.getParser(url);
        if (parser == null) {
            throw new IllegalArgumentException("解析器异常");
        }
        return parser.parser(StringUtil.filterUrl(url));
    }
}
