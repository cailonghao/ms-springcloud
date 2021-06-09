package rpg.cloud.gateway.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import lombok.extern.slf4j.Slf4j;
import rpg.cloud.gateway.dto.JwtDto;
import rpg.cloud.gateway.dto.PayloadDto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Slf4j
public class JwtUtil {
    /**
     * 1.创建一个32-byte的密匙JWT生成TOKEN
     */
    //private static final String JWT_SECRET = "geiwodiangasfdjsikolkjikolkijswe";
    private static final String JWT_SECRET = "secret";


//    public static String getJwt(Long uid, String username, List<String> list) throws JOSEException, JsonProcessingException {
//        //加密算法
//        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
//        //封装数据
//        String json = getPayLoad(uid, username, list);
//        Payload payload = new Payload(json);
//        //创建JWS对象
//        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
//        //创建HMAC签名器
//        JWSSigner jwsSigner = new MACSigner(JWT_SECRET);
//        //签名
//        jwsObject.sign(jwsSigner);
//        //生成token
//        return jwsObject.serialize();
//    }

//    public static String getPayLoad(Long uid, String username, List<String> list) throws JsonProcessingException {
//        long now = System.currentTimeMillis();
//        PayloadDto payloadDto = new PayloadDto();
//        payloadDto.setUser_name(jwtDto.getUser_name());
//        payloadDto.setJti(jwtDto.getJti());
//        payloadDto.setExp(jwtDto.getExp());
//        payloadDto.setClient_id(jwtDto.getClient_id());
//        payloadDto.setAuthorities(Arrays.asList(jwtDto.getAuthorities()));
//        payloadDto.setAuthorities(list);
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(payloadDto);
//    }

    /**
     * 校验token
     */
    public static PayloadDto verifyJwt(String token) {
        long now = System.currentTimeMillis();
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            String secret =  Arrays.toString(
                    MessageDigest.getInstance("SHA-256").digest(JWT_SECRET.getBytes(StandardCharsets.UTF_8)));
            JWSVerifier jwsVerifier = new MACVerifier(secret);
            if (jwsObject.verify(jwsVerifier)) {
                String payload = jwsObject.getPayload().toString();
                ObjectMapper mapper = new ObjectMapper();
                JwtDto jwtDto = mapper.readValue(payload, JwtDto.class);
                PayloadDto dto = new PayloadDto();
                dto.setUser_name(jwtDto.getUser_name());
                dto.setJti(jwtDto.getJti());
                dto.setExp(jwtDto.getExp());
                dto.setClient_id(jwtDto.getClient_id());
                dto.setAuthorities(Arrays.asList(jwtDto.getAuthorities()));
                return dto;
            }
        } catch (Exception e) {
            log.error("解析token失败 {}", e.getMessage());
        }
        return null;
    }


}
