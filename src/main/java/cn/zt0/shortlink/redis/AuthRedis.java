package cn.zt0.shortlink.redis;

import cn.zt0.shortlink.dao.UserDao;
import cn.zt0.shortlink.table.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.time.temporal.ChronoUnit.SECONDS;

@Slf4j
@Component
public class AuthRedis {
    @Resource
    private RedisTemplate<String, User> userRedisTemplate;
    @Autowired
    private UserDao userDao;
    public void put(User user){
        userRedisTemplate.opsForValue().set(user.getToken(),user, Duration.of(60*15,SECONDS));
    }
    public void deleteByToken(String token){
        userRedisTemplate.delete(token);
    }
    public User findByToken(String token) {
        return userRedisTemplate.opsForValue().get(token);
    }
    private List<User> getAllUsers() {
        Set<String> keys = userRedisTemplate.keys("*");
        if (keys == null) return new ArrayList<>();
        return userRedisTemplate.opsForValue().multiGet(keys);
    }
    public User findByUserId(String userId) {
        List<User> users = getAllUsers();
        for (User user: users) {
            if (user.getId().equals(userId)) return user;
        }
        return null;
    }
    public void deleteByUserId(String userId){
        User profile = userDao.findById(userId);
        if (profile == null) return;
        List<User> users = getAllUsers();
        for (User user: users) {
            if (user.getId().equals(userId)) {
                deleteByToken(user.getToken());
            }
        }
    }
    public void updateByUserId(String userId){
        User profile = userDao.findById(userId);
        if (profile == null) return;
        List<User> users = getAllUsers();
        for (User user: users) {
            if (user.getId().equals(userId)) {
                profile.setToken(user.getToken());
                put(profile);
            }
        }
    }
}
