package ch05.studyspring;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

@Data
@ComponentScan
public class User {
//    클래스에서 enum 정의
//    private static final int BASIC = 1;
//    private static final int SILVER = 2;
//    private static final int GOLD = 3;
    private String id;
    private String name;
    private String password;
    private Level levels;
    private int login;
    private int recommend;

    public User(){

    }

    public User(String id, String name, String password, Level levels, int login, int recommend) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.levels = levels;
        this.login = login;
        this.recommend = recommend;
    }

    //레벨 업그레이드가 필요하면 User 클래스에서 이를 수행함.
    public void upgradeLevel(){
        Level nextLevel = this.levels.nextLevel();
        if(nextLevel == null) {
            throw new IllegalArgumentException(this.levels + "은 업그레이드가 불가합니다. ");

        } else {
            this.levels = nextLevel;
        }
    }
}
