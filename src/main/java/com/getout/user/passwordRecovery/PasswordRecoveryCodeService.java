package com.getout.user.passwordRecovery;

import com.getout.user.User;
import com.getout.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.concurrent.ThreadLocalRandom.current;

@Service
public class PasswordRecoveryCodeService {

    @Autowired
    private PasswordRecoveryCodeRepository passwordRecoveryCodeRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Code format is 6 digits, expiration in 5 minutes
     */
    public TemporaryRecoveryCode generateAndSaveTemporaryCodeByUser(Long userId) {
        Integer code = current().nextInt(000000, 999999);
        var passwordRecoveryCode = new TemporaryRecoveryCode(code, userId);
        return passwordRecoveryCodeRepository.save(passwordRecoveryCode);
    }

    public Optional<User> loadUserByRecoveryCode(Integer code) {
        Optional<TemporaryRecoveryCode> recoveryCode = passwordRecoveryCodeRepository.findByCode(code);

        if (recoveryCode.isEmpty()) return Optional.empty();

        User user = userRepository.getById(recoveryCode.get().getUserId());
        return Optional.of(user);
    }

    public boolean isValid(Integer code) {
        Optional<TemporaryRecoveryCode> recoveryCode = passwordRecoveryCodeRepository.findByCode(code);
        return recoveryCode.get().isValid();
    }
}
