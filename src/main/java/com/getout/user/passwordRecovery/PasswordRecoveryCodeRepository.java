package com.getout.user.passwordRecovery;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PasswordRecoveryCodeRepository extends CrudRepository<TemporaryRecoveryCode, Long> {

    Optional<TemporaryRecoveryCode> findByCode(Integer code);
}
