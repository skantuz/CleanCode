package co.com.bancolombia.model.jwttoken.gateways;

import co.com.bancolombia.model.jwttoken.JwtToken;

public interface JwtTokenRepository {

    JwtToken getJwtToken(String claims);
    Boolean validateToken(JwtToken jwtToken);
}
