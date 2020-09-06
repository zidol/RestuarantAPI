package kr.co.fastcampus.eatgo.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTests {

    @Test
    public void creation() {
        User user = User.builder()
                .email("test@exmaple.com")
                .name("tester")
                .level(100L)
                .build();
        assertThat(user.getName(), is("tester"));
        assertThat(user.isAdmin(), is(true));
        assertThat(user.isActive(), is(true));

        user.deactivate();

        assertThat(user.isActive(), is(false));
    }


    //사용안함
//    @Test
//    public void accessTokenWithPassword() {
//        User user = User.builder().password("ACCESSTOKEN").build();
//
//        assertThat(user.getAccessToken(), is("ACCESSTOKE"));
//
//    }

//    @Test
//    public void accessTokenWithoutPassword() {
//        User user = new User();
//
//        assertThat(user.getAccessToken(), is(""));
//    }
}