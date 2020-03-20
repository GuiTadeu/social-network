package com.getout.user.passwordRecovery;

import com.getout.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.concurrent.ThreadLocalRandom.current;

@Service
public class PasswordRecoveryCodeService {

    @Autowired
    private PasswordRecoveryCodeRepository passwordRecoveryCodeRepository;

    /**
     * Code format is 6 digits, expiration in 5 minutes
     */
    public PasswordRecoveryCode generateAndSaveTemporaryCodeByUser(User user) {
        Integer code = current().nextInt(000000, 999999);
        var passwordRecoveryCode = new PasswordRecoveryCode(code, user);
        return passwordRecoveryCodeRepository.save(passwordRecoveryCode);
    }

    public boolean isValidCodeForUser(Integer code, User user) {
        var passwordRecoveryCode = passwordRecoveryCodeRepository.findByCodeAndUser(code, user);
        return passwordRecoveryCode.isPresent() ? passwordRecoveryCode.get().isNotExpired() : false;
    }
}
