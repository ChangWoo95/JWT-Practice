package me.changwoo.practice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @JsonIgnore
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", length = 50, unique=true)
    private String username;

    @JsonIgnore
    @Column(name="password", length=100)
    private String password;

    @Column(name="nickname", length=50)
    private String nickname;

    @JsonIgnore
    @Column(name="activated")
    private boolean activated;

    @ManyToMany
    @JoinTable( // ManyToMany 어노테이션으로 자동생성해주는 table 대신 지정해서 사용하게 도와주는 방식
            name="user_authority", // jpa가 만들어주는 table 명
            joinColumns = {@JoinColumn(name="user_id", referencedColumnName = "user_id")}, // user table에서 매핑할 정보(컬럼) 지정
            inverseJoinColumns = {@JoinColumn(name="authority_name", referencedColumnName = "authority_name")}) // authority table에서 매핑할 정보(컬럼) 지정
    private Set<Authority> authorities;

}
