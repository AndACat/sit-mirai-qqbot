package org.sit.controller;

import org.sit.constant.Constant;
import org.sit.vo.ResponseData;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author WangZhen
 * @Date 2023/11/6 20:04
 */
@RestController
@Controller("/filter")
public class FilterController {
    @Resource
    private Constant constant;

    @GetMapping("/list")
    public ResponseData list(){
        return ResponseData.success(constant.getFilterList());
    }

    @GetMapping("/add")
    public ResponseData add(@RequestParam("filter") String filter){
        constant.getFilterList().add(filter);
        return ResponseData.success(constant.getFilterList());
    }
    @GetMapping("/remove")
    public ResponseData remove(@RequestParam("filter") String filter){
        constant.getFilterList().remove(filter);
        return ResponseData.success(constant.getFilterList());
    }

}
