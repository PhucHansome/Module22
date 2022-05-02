package CaseMD2.services;

import CaseMD2.model.User;

import java.util.List;

public interface IUserService  {
    List<User> getUsers();
    User loginAdmin(String username, String password);
    void add(User newUser);
    void update(User newUser);
    boolean exist(int id);
    boolean checkDuplicateEmail(String Email);
    boolean checkDuplicatePhone(String phone);
    boolean checkDuplicateUserName( String userName);
    User getUserById(int id);


}
