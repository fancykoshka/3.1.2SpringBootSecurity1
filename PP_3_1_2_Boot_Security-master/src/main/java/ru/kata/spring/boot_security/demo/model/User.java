package ru.kata.spring.boot_security.demo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
/*
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import jakarta.persistence.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
*/

/*@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}*/
//implements UserDetails означает, что класс User, который мы рассматриваем, реализует интерфейс UserDetails.
// Это интерфейс из пакета Spring Security, который определяет набор методов для представления информации о пользователе в контексте безопасности.
//
//Основные моменты:
//
//UserDetails - это интерфейс, который определяет стандартный набор атрибутов пользователя для Spring Security.
//Реализация этого интерфейса позволяет нашему классу User работать с системой безопасности Spring Security.
//Основные методы, которые нужно реализовать в UserDetails, включают:
//getUsername(): возвращает имя пользователя
//getPassword(): возвращает пароль пользователя
//getAuthorities(): возвращает права доступа (роли) пользователя
//isAccountNonExpired(), isAccountNonLocked(), isCredentialsNonExpired(), isEnabled(): проверяют различные состояния учетной записи
//В нашем классе User мы реализуем все эти методы, что позволяет Spring Security использовать информацию о пользователе из нашего класса.
//Это позволяет нам легко интегрировать наш пользовательский класс в систему безопасности Spring, не создавая собственный UserDetailsService.
//Для новичка важно понять:
//
//Реализация UserDetails делает наш класс совместимым с системой безопасности Spring Security.
//Это упрощает интеграцию нашего пользовательского класса с другими компонентами Spring Security.
//Реализация всех методов UserDetails обеспечивает полную информацию о пользователе для системы безопасности.
//Таким образом, implements UserDetails - это способ сделать наш пользовательский класс "поддерживаемым" системой безопасности Spring Security,
// что очень важно для разработки безопасных приложений с использованием Spring Boot.