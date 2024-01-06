package ar.juarce.webapp.dtos;

import ar.juarce.models.Role;
import ar.juarce.models.User;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

public record UserDto(
        Long id,

        @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
        @Size(min = 3, max = 255)
        String username,

        String createdAt,

        Set<Role> roles) {

    public static UserDto fromUser(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getCreatedAt().toString(),
                user.getRoles()
        );
    }

    public static List<UserDto> fromUsers(List<User> users) {
        return users.stream().map(UserDto::fromUser).toList();
    }

}
