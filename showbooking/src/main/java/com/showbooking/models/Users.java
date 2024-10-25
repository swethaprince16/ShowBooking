package com.showbooking.models;

import com.showbooking.dto.UserDto;
import com.showbooking.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String mobile;
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
    @Enumerated(EnumType.STRING)
    private Role role;

    public static UserDto toDto(Users user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .tickets(user.getTickets())
                .role(user.getRole())
                .build();

    }
}
