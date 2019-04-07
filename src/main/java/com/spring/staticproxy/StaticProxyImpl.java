package com.spring.staticproxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaticProxyImpl implements UserDao {
    @Autowired
    private UserDaoImpl userDao; // 创建真正的目标对象，用于执行真正的业务代码

    @Override
    public void delete() {
        System.out.println("++++ 业务处理前操作 ++++");

        // 执行真正的业务代码
        userDao.delete();

        System.out.println("++++ 业务处理后操作 ++++");
    }
}
