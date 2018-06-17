package io.github.cd871127.hodgepodge.quartz.controller;

import io.github.cd871127.hodgepodge.quartz.service.QuartzService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/quartz")
@RestController
public class QuartzController {

    @Resource
    private QuartzService quartzService;

    @RequestMapping("test")
    public String test()
    {
        System.out.println(quartzService.toString());

        return "quratz";
    }
}
