package ch05.studyspring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static ch05.studyspring.UserService.MIN_LOGOUT_FOR_SILVER;
import static ch05.studyspring.UserService.MIN_RECOMMEND_FOR_GOLD;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDAO userDAO;

    List<User> users;

    @Test
    public void bean(){
        assertThat(this.userService, is(notNullValue()));
    }

    @Before
    public void setUp(){
        users = Arrays.asList(
                new User("johhan", "김요한", "p1", Level.BASIC, MIN_LOGOUT_FOR_SILVER -1, 0),
                new User("pram", "박프람", "p2", Level.BASIC, MIN_LOGOUT_FOR_SILVER, 0),
                new User("russil", "루실리카", "p3", Level.SILVER, MIN_RECOMMEND_FOR_GOLD -1, 29),
                new User("frau", "프라우", "p4", Level.SILVER, MIN_RECOMMEND_FOR_GOLD, 30),
                new User("helga", "헬가", "p5", Level.GOLD, 100, 130)
        );
    }

    @Test
    public void upgradeLevels(){
        userDAO.deleteAll();
        for(User user : users) {
            userDAO.add(user);
        }

        userService.upgradeLevels();

        checkLevel(users.get(0), false);
        checkLevel(users.get(1), true);
        checkLevel(users.get(2), false);
        checkLevel(users.get(3), true);
        checkLevel(users.get(4), false);
    }

    private void checkLevel(User user, boolean upgraded) {
        User userUpdate = userDAO.get(user.getId());
        if(upgraded) {
            assertThat(userUpdate.getLevels(), is(user.getLevels().nextLevel()));
        } else {
            assertThat(userUpdate.getLevels(), is(user.getLevels()));
        }

    }
}