package org.sit.controller;

import org.sit.service.ConstantRemoteService;
import org.sit.vo.ConstantRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author WangZhen
 * @Date 2023/12/13 23:54
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ConstantRemoteService constantRemoteService;

    @GetMapping("/constantTest1")
    public ConstantRespVo test1(){
        ConstantRespVo cmy = constantRemoteService.getConstantListByType("cmy");
        return cmy;
    }
}
