package by.boris.service;

import by.boris.entity.Role;
import by.boris.entity.User;
import by.boris.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SmtpMailSender smtpMailSender;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepo.findByUsername(userName);
    }

    public boolean addUser(User user){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setCodeActivation(UUID.randomUUID().toString());
        userRepo.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Hello, %s \n" +
                    "Welcome to Travel stories. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(), user.getCodeActivation());

            smtpMailSender.send(user.getEmail(), "Travel project activation code", message);
        }
        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByCodeActivation(code);

        if (user == null) {
            return false;
        }
        user.setCodeActivation(null);
        userRepo.save(user);
        return true;
    }
}
