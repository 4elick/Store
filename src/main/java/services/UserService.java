package services;

import entities.User;
import lombok.SneakyThrows;
import repositories.UserRepository;
import utility.Encrypter;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SneakyThrows
    public void add(User user)  {
        user.setPassword(Encrypter.toHexString(Encrypter.getSHA(user.getPassword())));
        userRepository.add(user);
    }

    public User getById(long id) {
        return userRepository.getById(id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User getByPassword(String encPassword) throws NoSuchAlgorithmException {
        return userRepository.getByPassword(Encrypter.toHexString(Encrypter.getSHA(encPassword)));
    }

    public User getByPasswordAndEmail(String email,String encPassword) throws NoSuchAlgorithmException {
        return userRepository.getByPasswordAndEmail(email,Encrypter.toHexString(Encrypter.getSHA(encPassword)));
    }
    public void removeById(long id){
        userRepository.removeById(id);
    }

}
