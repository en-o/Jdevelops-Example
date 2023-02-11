package com.example.webssprings.controller;

import cn.jdevelops.springs.context.ContextHolder;
import cn.jdevelops.springs.context.entity.JdevelopsRequest;
import cn.jdevelops.springs.context.entity.JdevelopsResponse;
import cn.jdevelops.springs.context.service.JdevelopsContext;
import cn.jdevelops.springs.service.url.UrlService;
import cn.jdevelops.springs.service.url.entity.Urls;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tnnn
 * @version V1.0
 * @date 2022-07-24 00:21
 */
@RequestMapping("url")
@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/getAll")
    public Map<String, List<Urls>> getAllUrl(){
        List<Urls> localUrl = urlService.getLocalUrl();
        List<Urls> resultUrl = localUrl.stream().distinct().collect(Collectors.toList());
        Map<String, List<Urls>> resultUrlMap = resultUrl.stream().collect(Collectors.groupingBy(Urls::getGrouping));
        return resultUrlMap;
    }


    @GetMapping("/jdevelopsContext")
    public void testJdevelopsContext(){
        JdevelopsContext jdevelopsContext = ContextHolder.getContext();
        JdevelopsRequest request = ContextHolder.getRequest();
        JdevelopsRequest requestw = jdevelopsContext.getRequest();
        System.out.println(request.getParam("name"));
        System.out.println(requestw.getParam("name"));
        JdevelopsResponse response = ContextHolder.getResponse();
        response.addHeader("name","asda");
    }
}
