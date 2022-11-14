package cn.zt0.shortlink.service;

import cn.zt0.shortlink.config.BusinessException;
import cn.zt0.shortlink.dao.UserDao;
import cn.zt0.shortlink.data.RequestHeader;
import cn.zt0.shortlink.data.pData;
import cn.zt0.shortlink.redis.AuthRedis;
import cn.zt0.shortlink.table.User;
import cn.zt0.shortlink.util.ToolsUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ApiService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthRedis authRedis;

    public void test(pData data, HttpServletRequest request, HttpServletResponse response) {
        RequestHeader header = ToolsUtil.getRequestHeaders(request);
        if (header.getUser() == null) {
            User user = new User().setAdmin(true).setEnabled(true).setAvatar("test").setUsername("test").setToken("test");
            userDao.save(user);
            authRedis.put(user);
            throw new BusinessException( "用户不存在！");
        }
        System.out.println(header.getUser());
    }
}
