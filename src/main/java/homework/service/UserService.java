package homework.service;

import homework.dto.UserDto;
import homework.entity.User;
import homework.repository.UserDaoImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserDaoImpl userDao;

    public User saveUser(UserDto dto) {
        User newUser = new User(dto.username(), dto.email(), dto.age());
        return userDao.save(newUser)
                .orElseThrow(() -> new RuntimeException("Error while new user saving"));
    }

    public User updateUser(User userToUpdate) {
        return userDao.update(userToUpdate)
                .orElseThrow(() -> new RuntimeException("Error while user updating"));
    }

    public void deleteUser(int id) {
        User userToDelete = getUserById(id);
        if (userDao.delete(userToDelete)) {
            log.info("User with id: {} successfully deleted", id);
        } else {
            throw new RuntimeException("Error while user deleting");
        }
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUserById(int id) {
        return userDao.findById((long) id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User with id: %d not found", id)));
    }

}