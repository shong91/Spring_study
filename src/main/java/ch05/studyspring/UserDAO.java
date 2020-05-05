package ch05.studyspring;

import java.util.List;

//JDBC에서 만든 DAO 에서는 SQLException 이,
//JPA 에서는 PersistenceException 이,
//Hibernate 에서는 HibernateException 이 던져진다.
//따라서 DAO를 사용하는 클라이언트의 입장에서는 DAO의 사용 기술에 따라 예외처리 방법이 달라져야 한다.
//=> 인터페이스를 추상화하고, 그에 의미있는 Exception 이 적용될 수 있도록 하여야 함

public interface UserDAO {
    public void add(User user);
    public ch05.studyspring.User get(String id);
    public List<User> getAll();
    public void deleteAll();
    public int getCount();

    public void update(User user1);
}
