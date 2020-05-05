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
}

334page

