package ch01.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.management.QueryExp;
import java.util.HashSet;
import java.util.Set;

import static javax.management.Query.not;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/junit.xml")
public class JUnitTest {
    @Autowired
    ApplicationContext context;

    static Set<JUnitTest> testObjects = new HashSet<JUnitTest>();

    static ApplicationContext contextObject = null;
//    static JUnitTest testObject;
    //JUnit은 태스트 메소드를 수행할 때마다 새로운 오브젝트를 만든다

    @Test
    public void test1() {
        //assertThat(this, CoreMatchers.<JUnitTest>is((JUnitTest) not((QueryExp) testObject)));
        //testObject = this;

        assertThat(testObjects, (Matcher<? super Set<JUnitTest>>) not((QueryExp) hasItem(this)));
        testObjects.add(this);

        assertThat(contextObject == null || contextObject == this.context, is(true));
        contextObject = this.context;
    }

    @Test
    public void test2() {
        //assertThat(this, CoreMatchers.<JUnitTest>is((JUnitTest) not((QueryExp) testObject)));
        //testObject = this;
        //assertThat(this, is(not(sameInstance(testObject))));
        assertThat(testObjects, (Matcher<? super Set<JUnitTest>>) not((QueryExp) hasItem(this)));
        testObjects.add(this);

        assertTrue(contextObject == null || context == this.context);
        contextObject = this.context;

    }

    @Test
    public void test3() {
        //assertThat(this, CoreMatchers.<JUnitTest>is((JUnitTest) not((QueryExp) testObject)));
        //testObject = this;
        //assertThat(this, is(not(sameInstance(testObject))));

        assertThat(testObjects, (Matcher<? super Set<JUnitTest>>) not((QueryExp) hasItem(this)));
        testObjects.add(this);

        assertThat(contextObject, either(is(nullValue())).or(CoreMatchers.<Object>is(this.context)));
        contextObject = this.context;
    }
}