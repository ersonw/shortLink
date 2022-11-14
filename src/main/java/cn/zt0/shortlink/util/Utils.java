package cn.zt0.shortlink.util;

import cn.zt0.shortlink.dao.UserDao;
import cn.zt0.shortlink.redis.AuthRedis;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Getter
@Component
public class Utils {
    private static Utils self;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthRedis authRedis;
    @PostConstruct
    public void initPost(){
        self = this;
        System.out.printf("加载器成功！\n");
    }
    public static UserDao getUserDao() {
        return self.userDao;
    }
    public static AuthRedis getAuthRedis() {
        return self.authRedis;
    }
//    public static void checkClient(HttpServletRequest request, HttpServletResponse response){
//        RequestHeader headers = ToolsUtil.getRequestHeaders(request);
//        Client client = headers.getClient();
//        if (client == null){
//            client = new Client(JSONObject.toJSONString(headers));
//            response.addCookie(new Cookie("clientId", client.getId()));
//        }
//        client.setUpdateTime(System.currentTimeMillis());
//        client.setUpdateHeader(JSONObject.toJSONString(headers));
//        self.clientDao.save(client);
//    }
//    public static void clearClient(Cookie[] cookies,String token, HttpServletResponse response){
//       if (StringUtils.isNotEmpty(token)){
//           for (Cookie cookie : cookies){
//               if (cookie.getName().equals("clientId")){
//                   cookie.setValue("");
//                   response.addCookie(cookie);
//               }
//           }
//           Cookie cookie = new Cookie("clientId", token);
//           cookie.setPath("/");
//           response.addCookie(cookie);
//       }
//    }
//    public static Client addClient(HttpServletRequest request, HttpServletResponse response){
//        RequestHeader headers = ToolsUtil.getRequestHeaders(request);
//        Client client = new Client(JSONObject.toJSONString(headers));
//        Cookie cookie = new Cookie("clientId", client.getId());
//        cookie.setPath("/");
////            cookie.setDomain("");
//        response.addCookie(cookie);
//        self.clientDao.save(client);
//        return client;
//    }
}
