package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public Collection<User> findAll() {
        return users.values();
    }

//<<<<<<< HEAD
//    public User create(User user) {
//        if(user.getEmail() == null || user.getEmail().isBlank()) {
//            throw new InvalidEmailException("Адрес электронной почты не может быть пустым.");
//        }
//        if(users.containsKey(user.getEmail())) {
//            throw new UserAlreadyExistException("Пользователь с электронной почтой " +
//                    user.getEmail() + " уже зарегистрирован.");
//=======
    public User createUser(User user) {
        checkEmail(user);
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException(String.format(
                    "Пользователь с электронной почтой %s уже зарегистрирован.",
                    user.getEmail()
            ));
//>>>>>>> origin/error-handler_add-handling
        }
        users.put(user.getEmail(), user);
        return user;
    }

//<<<<<<< HEAD
//    public User put(User user) {
//        if(user.getEmail() == null || user.getEmail().isBlank()) {
//            throw new InvalidEmailException("Адрес электронной почты не может быть пустым.");
//        }
//=======
    public User updateUser(User user) {
        checkEmail(user);
//>>>>>>> origin/error-handler_add-handling
        users.put(user.getEmail(), user);

        return user;
    }
//<<<<<<< HEAD
//}
//=======

    public User findUserByEmail(String email) {
        if (email == null) {
            return null;
        }
        return users.get(email);
    }

    private void checkEmail(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Адрес электронной почты не может быть пустым.");
        }
    }
}
//>>>>>>> origin/error-handler_add-handling
