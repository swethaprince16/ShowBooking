package com.showbooking.dto;

import com.showbooking.enums.Role;
import com.showbooking.models.Ticket;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {
    private Integer id;
    private String username;
    private String email;
    private String mobile;
    private List<Ticket> tickets;
    private Role role;
}
