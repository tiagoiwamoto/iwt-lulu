package br.com.tiagoiwamoto.iwtlulu.business.object;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 05/05/2021 | 20:45
 */

import br.com.tiagoiwamoto.iwtlulu.business.service.UserService;
import br.com.tiagoiwamoto.iwtlulu.entity.User;
import br.com.tiagoiwamoto.iwtlulu.model.ApiDTO;
import br.com.tiagoiwamoto.iwtlulu.model.UserStatusEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserBO {

    private final UserService userService;

    public UserBO(UserService userService) {
        this.userService = userService;
    }

    public ApiDTO<User> performUserCreation(User user){
        user.setCreatedAt(LocalDateTime.now());
        user.setStatus(UserStatusEnum.ACTIVE);
        User userCreated = this.userService.saveUser(user);
        userCreated.setPassword("******");
        return new ApiDTO<>("0", "Operação realizada com sucesso", userCreated);
    }

    public ApiDTO<User> performLogin(String email, String password){
        User userFound = this.userService.recoverUserWithEmailAndPassword(email, password);
        userFound.setPassword("******");
        return new ApiDTO<>("0",
                "Usuário recuperado com sucesso",
                userFound);
    }

    public ApiDTO<User> performFindUserByEmail(String email){
        User user = this.userService.recoverUserWithEmail(email);
        user.setPassword("******");
        user.setId(777777777777777777L);
        return new ApiDTO<>("0",
                "Usuário recuperado com sucesso",
                user);
    }

}
