package ch05.studyspring;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService") // 비즈니스 로직을 담당하는 서비스 클래스
@Data
public class UserService {
    @Autowired
    private UserDAO userDAO;
    public static final int MIN_LOGOUT_FOR_SILVER = 50; //레벨 업그레이드 기준 상수값
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;

    public void upgradeLevels(){
        List<User> list = userDAO.getAll();
        boolean changed = false;

        for(User user : list) {
            if(user.getLevels() == Level.BASIC && user.getLogin() >= 50) {
                user.setLevels(Level.SILVER);
                changed = true;
            } else if(user.getLevels() == Level.SILVER && user.getRecommend() >= 30) {
                user.setLevels(Level.GOLD);
                changed = true;
            } else if(user.getLevels() == Level.GOLD) {
                changed = false;
            } else {
                changed = false;
            }

            if(changed) {
                userDAO.update(user);
            }

            changed = false;
        }
    }

    public void upgradeLevels_refactor(){
        List<User> list = userDAO.getAll();
         for(User user : list) {
            if(canUpgradeLevel(user)){
                upgradeLevel(user); // DB update
            }
         }
    }

    private void upgradeLevel(User user) {
       user.upgradeLevel(); //레벨 업그레이드가 필요하면 User 클래스에서 이를 수행함.
        userDAO.update(user);
    }

    private boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevels();
        switch (currentLevel) {
            case BASIC: return (user.getLogin() >= 50);
            case SILVER: return (user.getRecommend() >= 30);
            case GOLD: return false; // 더이상 업그레이드가 불가하니 return false;
            default: throw new IllegalArgumentException("Unknown Level: " + currentLevel);
        }
    }
}
