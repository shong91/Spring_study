package ch05.studyspring;

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
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserDAOJdbcTest {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private UserDAO userDAO;

    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() throws Exception {
        //this.userDAO = context.getBean("userDAO", UserDAO.class);

        user1 = new User("hong", "홍길동", "123", Level.BASIC, 1, 0);
        user2 = new User("kim", "김시내", "123", Level.SILVER, 55, 10);
        user3 = new User("park", "박정석", "123", Level.GOLD, 100, 40);

    }


    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId(), is(user2.getId()));
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassword(), is(user2.getPassword()));
        assertThat(user1.getLevels(), is(user2.getLevels()));
        assertThat(user1.getLogin(), is(user2.getLogin()));
        assertThat(user1.getRecommend(), is(user2.getRecommend()));
    }

    @Test
    public void setJdbcTemplate() {
    }

    @Test
    public void add() {
        // 등록 전 cnt 확인
        userDAO.deleteAll();
        assertThat(userDAO.getCount(), is(0));

        // 등록
        userDAO.add(user1);
        userDAO.add(user2);
        userDAO.add(user3);
        assertThat(userDAO.getCount(), is(3));

        User userget1 = userDAO.get(user1.getId());
        checkSameUser(userget1, user1);

        User userget2 = userDAO.get(user2.getId());
        checkSameUser(userget2, user2);
    }

    @Test
    public void get() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void deleteAll() {
    }

    @Test
    public void getCount() {
    }

    // 메인 ======================================================================
    public static void main(String[] args) {
        JUnitCore.main("ch05.studyspring.UserDAOJdbcTest");
    }

}
