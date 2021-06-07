package co.com.bancolombia.jwt;

import co.com.bancolombia.model.config.ExceptionClient;
import co.com.bancolombia.model.jwttoken.JwtToken;
import co.com.bancolombia.model.jwttoken.gateways.JwtTokenRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class JwtService implements JwtTokenRepository {

    private final String secret;
    private final String issuer;
    private final ObjectMapper mapper;

    @Override
    public JwtToken getJwtToken(String claims) {
        String token = "";
        token = JWT.create()
                .withIssuedAt(new Date())
                .withIssuer(issuer)
                .withExpiresAt(Date.from(Instant.now().plusSeconds(3600)))
                .withPayload(getClaims(claims))
                .sign(Algorithm.HMAC256(secret));

        String[] tokenSplit = token.split("\\.");
        return JwtToken.builder()
                .header(tokenSplit[0])
                .payload(tokenSplit[1])
                .sing(tokenSplit[2])
                .build();

    }

    private Map<String, Object> getClaims(String claims) {
        try {
            return mapper.readValue(claims, HashMap.class);
        } catch (JsonProcessingException e) {
            throw new ExceptionClient("Error generando los Claims",
                    "Error Interno de Api",
                    500,
                    "Comuniquese con soporte",
                    "CE-401");
        }

    }

    @Override
    public Boolean validateToken(JwtToken jwtToken) {
        JWTVerifier verification = JWT.require(Algorithm.HMAC256(secret)).build();
        try {
            return verification.verify(jwtToken.getJwt()).getIssuer().equals(issuer);
        }catch (TokenExpiredException e){
            throw new ExceptionClient(e.getMessage(),"Unauthorized",401,"Token Expirado","EC-401");
        }
    }

    @Override
    public String getClaims(JwtToken jwtToken) {
        JWTVerifier verification = JWT.require(Algorithm.HMAC256(secret)).build();
        try {
            return verification.verify(jwtToken.getJwt()).getClaims().toString();
        }catch (TokenExpiredException e){
            throw new ExceptionClient(e.getMessage(),"Unauthorized",401,"Token Expirado","EC-401");
        }
    }
}

