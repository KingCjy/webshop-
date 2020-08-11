package me.kingcjy.webshop.user.application;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.user.domain.User;
import me.kingcjy.webshop.user.domain.UserDto;
import me.kingcjy.webshop.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author KingCjy
 */
@Service
@RequiredArgsConstructor
public class UserDetailService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDto.UserDetail getDetail(Long userId) {
        User user = userRepository.findById(userId).get();
        return new UserDto.UserDetail(user.getEmail(), user.getUsername());
    }

    @Transactional
    public void changeUsername(UserDto.UsernameChange usernameChange) {
        if(isDuplicatedUsername(usernameChange.getUsername())) {
            throw new UsernameDuplicatedException(usernameChange.getUsername(), "이미 존재하는 이름입니다. 다른 이름으로 다시 시도해주세요.");
        }

        userRepository.findById(usernameChange.getUserId())
                .ifPresent(user -> user.changeUsername(usernameChange.getUsername()));
    }

    private boolean isDuplicatedUsername(String username) {
        return userRepository.findByUsername(username) != null;
    }
}
