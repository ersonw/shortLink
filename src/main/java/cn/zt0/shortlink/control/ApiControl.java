package cn.zt0.shortlink.control;

import cn.zt0.shortlink.data.ResponseData;
import cn.zt0.shortlink.data.pData;
import cn.zt0.shortlink.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class ApiControl {
    @Autowired
    private ApiService service;

    @GetMapping("/test")
    public ResponseData test(@ModelAttribute pData data, HttpServletRequest request, HttpServletResponse response){
        service.test(data,request,response);
        return ResponseData.success();
    }
}
