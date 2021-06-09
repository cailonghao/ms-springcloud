package rpg.cloud.gateway.dto;

import lombok.Data;

@Data
public class JwtDto {

    private Long exp;
    private String user_name;
    private String[] authorities;
    private String jti;
    private String client_id;
    private String[] scope;
}
