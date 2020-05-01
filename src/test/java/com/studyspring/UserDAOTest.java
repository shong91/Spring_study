package com.studyspring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserDAOTest {
    @Autowired
    private ApplicationContext context;
    private UserDAO userDAO;
    private User user1, user2, user3;

    @Before
    public void setUp(){
        this.userDAO = context.getBean("userDAO", UserDAO.class);

        User user1 = new User("hong", "홍길동", "123");
        User user2 = new User("kim", "김시내", "123");
        User user3 = new User("park", "박정석", "123");

//        User user = new User();
//        user.setId("hong");
//        user.setName("홍길동");
//        user.setPassword("123");

    }

    @Test
    public void addAndGet() throws ClassNotFoundException, SQLException {
        // 등록 전 cnt 확인
        userDAO.deleteAll();
        assertThat(userDAO.getCount(), is(0));

        // 등록 & 등록 후 cnt 확인
        userDAO.add(user1);
        assertThat(userDAO.getCount(), is(1));

        // 조회
        User user2 = userDAO.get(user1.getId());
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassword(), is(user2.getPassword()));

    }

    @Test//(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws ClassNotFoundException, SQLException {
        userDAO.deleteAll();
        assertThat(userDAO.getCount(), is(0));

        userDAO.get("unknown");

    }

// 메인 ======================================================================
    public static void main(String[] args) {
        JUnitCore.main("com.studyspring.UserDAOTest");
    }
}