package com.spring.staticproxy;

import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao {
    @Override
    public void delete() {
        System.out.println("==== 进行删除业务处理 ====");
    }
}
