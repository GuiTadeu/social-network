package com.getout.user.passwordRecovery;

import com.getout.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
public class PasswordRecoveryCode {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Getter
    @NotNull
    private Integer code;

    @Getter
    @NotNull
    @ManyToOne
    private User user;

    private LocalDateTime expiredAt = now().plusMinutes(5);

    public PasswordRecoveryCode(Integer code, User user) {
        this.code = code;
        this.user = user;
    }

    public boolean isNotExpired() {
        return now().isBefore(expiredAt);
    }
}
