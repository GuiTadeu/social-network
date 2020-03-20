package com.getout.user.passwordRecovery;

import com.getout.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PasswordRecoveryCodeRepository extends CrudRepository<PasswordRecoveryCode, Long> {

    Optional<PasswordRecoveryCode> findByCodeAndUser(Integer code, User user);
}
