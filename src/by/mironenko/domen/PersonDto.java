package by.mironenko.domen;

import java.util.Arrays;
import java.util.List;

public class PersonDto {
    private final String name;
    private final String surname;
    private final String email;
    private final List<Roles> roles;
    private final List<String> numbers;

    public PersonDto(final String name,
                     final String surname,
                     final String email,
                     final List<Roles> roles,
                     final List<String> numbers) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roles = roles;
        this.numbers = numbers;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public List<String> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + Arrays.toString(roles.toArray()) +
                ", numbers=" + Arrays.toString(numbers.toArray());
    }
}
