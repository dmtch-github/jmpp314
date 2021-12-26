package jm.spring.rest.client.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

import static jm.spring.rest.client.entities.Roles.USER;

@NoArgsConstructor
@Setter
@Getter
public class UserRole {

    private int id;
    private String email;   //уникальное значение
    private String name;
    private String lastName;
    private int age;
    private String password;
    private Roles[] roles;

    public UserRole(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        email = "email@yandex.ru";
        password = "123";
        roles = new Roles[]{USER};
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", roles=" + Arrays.toString(roles) +
                '}';
    }
}
