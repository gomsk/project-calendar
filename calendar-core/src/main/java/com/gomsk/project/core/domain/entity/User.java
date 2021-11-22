package com.gomsk.project.core.domain.entity;

import com.gomsk.project.core.util.Encrypter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Table(name ="users")
@Entity
public class User extends BaseEntity{

    private String name;
    private String email;
    private String password;
    private LocalDate birthday;

    public User( String name, String email, String password, LocalDate birthday) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    //이러한 패턴은 테스트가 편리함
    public boolean isMatch(Encrypter encrypter, String pw) {
        return encrypter.isMatch(pw, this.password);
    }
}
