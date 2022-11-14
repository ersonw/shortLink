package cn.zt0.shortlink.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.TimeZone;

@WebFilter(filterName = "adminFilter", urlPatterns = {"/admin/api/*"})
public class AdminFilter implements Filter {
//    private AuthDao authDao;
    @Override
    public void init(FilterConfig filterConfig){
//        authDao = Utils.getAuthDao();
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest) {
            CustomHttpServletRequest request = new CustomHttpServletRequest((HttpServletRequest) req);
            String contentType = request.getContentType();
            String token = request.getHeader("Token");
//            if (StringUtils.isNotEmpty(token)){
//                User user = authDao.findUserByToken(token);
//                if (user != null)request.addHeader("user",JSONObject.toJSONString(user));
//            }
            chain.doFilter(request, response);
        } else {
            chain.doFilter(req, response);
        }
    }
    @Override
    public void destroy() {
    }
}
