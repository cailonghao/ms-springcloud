package rpg.mem.auth.provider.dto;

import lombok.Data;

@Data
public class UserDto {

    private String user_name;
    private String[] authority;
}
