package lk.ijse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class UserDTO {
    private String id;
    private String username;
    private String phoneno;
    private String email;
    private String password;
}
