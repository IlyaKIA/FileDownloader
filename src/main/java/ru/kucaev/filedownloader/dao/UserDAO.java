package ru.kucaev.filedownloader.dao;

import ru.kucaev.filedownloader.model.User;
import ru.kucaev.filedownloader.model.ROLE;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final List<User> store = new ArrayList<>();

    public User getById(int id) {
        User result = new User();
        result.setId(-1);
        for (User user : store) {
            if (user.getId() == id) {
                result = user;
            }
        }
        return result;
    }

    public List<User> getAllUsers (){
        return store;
    }

    public boolean add(final User user) {
        for (User u : store) {
            if (u.getLogin().equals(user.getLogin()) && u.getPassword().equals(user.getPassword())) {
                return false;
            }
        }
        return store.add(user);
    }

    public ROLE getRoleByLoginPassword(final String login, final String password) {
        ROLE result = ROLE.ANONYMOUS;
        for (User user : store) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user.getRole();
            }
        }
        return result;
    }

    public boolean userIsExist(final String login, final String password) {
        boolean result = false;
        for (User user : store) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
