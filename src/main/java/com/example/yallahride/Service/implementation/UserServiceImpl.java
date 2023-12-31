package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.EntityListener.UserEventListener;
import com.example.yallahride.Entity.Role;
import com.example.yallahride.Entity.TravelPreference;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.TravelPreferenceRepository;
import com.example.yallahride.Repository.UserRepository;
import com.example.yallahride.Service.Interface.FileService;
import com.example.yallahride.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TravelPreferenceRepository travelPreferenceRepository;
    @Autowired
    FileService fileService;

    @Autowired
    UserEventListener userEventListener;


    static User unwrapUser(Optional<User> user, Long id) {
        if (user.isPresent()) return user.get();
        else throw new EntityNotFoundException(id, User.class);
    }

    @Override
    public User saveUser(User user) {
        if (user.getMultipartFile() != null) {
            if (user.getImagePath() != null) {
//                fileService.d
            }
            String key = fileService.uploadFile(user.getMultipartFile());
            user.setImagePath(fileService.getObjectUrl(key));
        }
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return unwrapUser(userRepository.findById(id), id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public void deleteUserById(Long id) {
        User user = findUserById(id);
        userEventListener.removeUserImage(user);
        userRepository.deleteById(id);
    }

    @Override
    public long getNumberOfUser() {
        return userRepository.count();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User activateUserById(Long userId) {
        User user = findUserById(userId);
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public User deactivateUserById(Long userId) {
        User user = findUserById(userId);
        user.setActive(false);
        return userRepository.save(user);
    }

    @Override
    public User addTravelPreference(Long userId, TravelPreference travelPreference) {
//        User user = findUserById(userId);
//        user.addTravelPreference(travelPreference);
//        return saveUser(user);
        return null;
    }

    @Override
    public Collection<TravelPreference> getUserTravelPreferences(Long userId) {
        return findUserById(userId).getTravelPreferences();
    }

    @Override
    public User deleteTravelPreference(Long userId, TravelPreference travelPreference) {
        User user = findUserById(userId);
        user.deleteTravelPreference(travelPreference);
        return saveUser(user);
    }

    @Override
    public User addRole(Long userId, Role role) {
        User user = findUserById(userId);
        user.addRole(role);
        return saveUser(user);
    }

    @Override
    public Collection<Role> getUserRoles(Long userId) {
        return findUserById(userId).getRoles();

    }

    @Override
    public User deleteRole(Long userId, Role role) {
        User user = findUserById(userId);
        user.deleteRole(role);
        return saveUser(user);
    }


    @Override
    public void verifiedAccountById(long id) {
        userRepository.verifiedAccountById(id);
    }

    @Override
    public User saveUserTravelPrefernces(TravelPreference[] travelPreferences, Long id) {
        User user = findUserById(id);
        user.deleteAllTravelPreference();
        user.addTravelPreferences(travelPreferences);
        return userRepository.save(user);
    }

}
