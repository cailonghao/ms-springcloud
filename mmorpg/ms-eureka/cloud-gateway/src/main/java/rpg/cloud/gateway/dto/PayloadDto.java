package rpg.cloud.gateway.dto;

import lombok.Data;

import java.util.List;

@Data
public class PayloadDto {


    /**
     * 过期时间
     */
    private Long exp;
    /**
     * 用户名
     */
    private String user_name;
    /**
     * 客户端
     */
    private String client_id;
    /**
     * jwt的标识
     */
    private String jti;
    /**
     * 权限
     */
    private List<String> authorities;
}
