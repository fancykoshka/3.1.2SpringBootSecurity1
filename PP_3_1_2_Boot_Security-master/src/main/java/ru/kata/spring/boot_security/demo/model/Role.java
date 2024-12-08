package ru.kata.spring.boot_security.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    public Role() {
    }
    @Override
    public String getAuthority() {
        return role;
    }
}
//implements GrantedAuthority означает, что класс реализует интерфейс GrantedAuthority.
// Этот интерфейс используется в Spring Security для представления разрешений или прав доступа в приложении.
//
//Основные моменты:
//
//GrantedAuthority - это интерфейс, который определяет способность пользователя выполнять определенные действия или иметь определенные права.
//Реализация этого интерфейса позволяет классу представлять разрешения или роли пользователя в системе безопасности Spring Security.
//Основной метод, который нужно реализовать в GrantedAuthority, это:
//String getAuthority();
//Этот метод должен возвращать строковое представление разрешения или роли.
//
//В контексте Spring Security, GrantedAuthority часто используется для представления ролей пользователей или более конкретных разрешений.
//Для новичка важно понять:
//
//GrantedAuthority - это абстракция, которая позволяет гибко определять и управлять разрешениями пользователей.
//Реализация этого интерфейса позволяет создавать собственные типы разрешений, которые могут быть более специфичными для вашего приложения.
//В Spring Security часто используются стандартные реализации GrantedAuthority, такие как SimpleGrantedAuthority или RoleVoter.