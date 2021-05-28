package co.com.bancolombia.usecase.jwttoken;

import co.com.bancolombia.model.jwttoken.JwtToken;
import co.com.bancolombia.model.jwttoken.gateways.JwtTokenRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtTokenUseCase {

    private final JwtTokenRepository jwtTokenRepository;

    public JwtToken getToken(String claims) {
        return jwtTokenRepository.getJwtToken(claims);
    }

    public Boolean validateToken(JwtToken jwtToken) {
        return jwtTokenRepository.validateToken(jwtToken);
    }
}
