package cn.zt0.shortlink.dao;

import cn.zt0.shortlink.config.MongoAnimal;
import cn.zt0.shortlink.table.User;
import cn.zt0.shortlink.util.ToolsUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao extends MongoAnimal<User> {
    public UserDao() {
        super(User.class);
    }
    public boolean isAdmin(String id) {
        return super.aggregate(super.getMatch(super.and(super.or(super.where("admin",true),super.where("superAdmin",true)),super.where("_id",id)))).size() > 0;
    }
    public boolean isSuperAdmin(String id) {
        return super.aggregate(super.getMatch(super.and(super.where("superAdmin",true),super.where("_id",id)))).size() > 0;
    }

    public User findByUsername(String username) {
        List<User> list = super.aggregate(super.getMatch(super.where("username",username)));
        if (list.size() == 0) return null;
        return list.get(0);
    }

    public Page<User> findAllByTitle(String title, Pageable pageable) {
        AggregationOperation mach = super.getMatch(
                super.where("username").regex("^.*" + ToolsUtil.escapeExprSpecialWord(title) + ".*$")
        );
        List<User> list = super.aggregate(mach,super.getSkip(pageable.getOffset()),super.getLimit(pageable.getPageSize()),super.getSort(pageable));
        long total = super.count(mach,super.getGroup());
        return super.newPage(pageable, list, total);
    }

    public Page<User> findAdmin(String id,Pageable pageable) {
        AggregationOperation mach = super.getMatch(
                super.and(
                        super.where("superior").is(id),
                        super.where("superAdmin").is(false)
                )
        );
        List<User> list = super.aggregate(mach,super.getSkip(pageable.getOffset()),super.getLimit(pageable.getPageSize()),super.getSort(pageable));
        long total = super.count(mach,super.getGroup());
        return super.newPage(pageable, list, total);
    }

    public Page<User> findAdminByTitle(String id,String title, Pageable pageable) {
        AggregationOperation mach = super.getMatch(
                super.and(
                        super.where("username").regex("^.*" + ToolsUtil.escapeExprSpecialWord(title) + ".*$"),
                        super.where("superior").is(id),
                        super.where("superAdmin").is(false)
                )
        );
        List<User> list = super.aggregate(mach,super.getSkip(pageable.getOffset()),super.getLimit(pageable.getPageSize()),super.getSort(pageable));
        long total = super.count(mach,super.getGroup());
        return super.newPage(pageable, list, total);
    }
}
