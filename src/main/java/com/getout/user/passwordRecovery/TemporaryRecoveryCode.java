package com.getout.user.passwordRecovery;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
public class TemporaryRecoveryCode {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Getter
    @NotNull
    private Integer code;

    @Getter
    @NotNull
    private Long userId;

    private LocalDateTime expiredAt = now().plusMinutes(5);

    public TemporaryRecoveryCode(Integer code, Long userId) {
        this.code = code;
        this.userId = userId;
    }

    public boolean isValid() {
        return now().isBefore(expiredAt) ? true : false;
    }
}
