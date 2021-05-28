package co.com.bancolombia.jwt;

import co.com.bancolombia.model.config.ExceptionClient;
import co.com.bancolombia.model.jwttoken.JwtToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {
    private ObjectMapper mapper;

    private JwtService jwtService;
    private JwtService jwtService2;


    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        jwtService = new JwtService("miSecreto", "test", mapper);
        jwtService2 = new JwtService("miSecreto", "test1", mapper);
    }

    @Test
    void getJwtToken() {
        assertEquals(168,
                jwtService.getJwtToken("{\"test\": \"prueba1\"}").getJwt().length());
    }

    @Test
    void validateToken() {
        System.out.println(jwtService.getJwtToken("{\"test\": \"prueba1\"}").getJwt());
        assertTrue(jwtService.validateToken(jwtService.getJwtToken("{\"test\": \"prueba1\"}")));
    }

    @Test
    void validateTokenError() {
        assertFalse(jwtService.validateToken(jwtService2.getJwtToken("{\"test\": \"prueba1\"}")));
    }
    @Test
    void validateTokenException(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0ZXN0IjoicHJ1ZWJhMSIsImlzcyI6InRlc3QiLCJleHAiOjE2MjIyM" +
                "DE4NzUsImlhdCI6MTYyMjIwMDM3NX0.lT0mEGr-XCw_Th5lFFUzsxKlJHmBOUYOrY8zo9GURTY";
        String[] tokenSplit = token.split("\\.");

        ExceptionClient exceptionClient = assertThrows(ExceptionClient.class, () -> jwtService.validateToken(JwtToken.builder()
                .header(tokenSplit[0])
                .payload(tokenSplit[1])
                .sing(tokenSplit[2])
                .build()));
        assertEquals("The Token has expired on Fri May 28 06:37:55 COT 2021.", exceptionClient.getLog());
    }


    @Test
    void getJwtTokenExceptionClient() {
                ExceptionClient exceptionClient = assertThrows(ExceptionClient.class,
                        () -> jwtService.getJwtToken("{\"test\": prueba1\"}").getJwt());
        assertEquals("Error generando los Claims", exceptionClient.getLog());
    }
}