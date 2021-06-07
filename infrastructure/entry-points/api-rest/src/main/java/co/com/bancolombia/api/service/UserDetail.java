package co.com.bancolombia.api.service;

import co.com.bancolombia.api.model.Login;
import co.com.bancolombia.model.config.ExceptionClient;
import co.com.bancolombia.model.jwttoken.JwtToken;
import co.com.bancolombia.usecase.jwttoken.JwtTokenUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserDetail {

    private static final String REGEX_TOKEN = "^Bearer [A-Za-z0-9-/_=]+\\.[A-Za-z0-9-/_=]+\\.[A-Za-z0-9-/_]*$";
    private static final Pattern BEARER_TOKEN = Pattern.compile(REGEX_TOKEN);
    private final JwtTokenUseCase jwtTokenUseCase;
    private final ObjectMapper mapper;

    public String auth(Login login) {
        if ("user".equals(login.getUser()) && "12345".equals(login.getPass())) {
           return jwtTokenUseCase.getToken("{\"user\": \"" + login.getUser() + "\"," +
                   "\"role\": \"ADMIN\"}").getJwt();
        }
        throw new ExceptionClient(login.getUser() + " No valido", "Unauthorize", 401, "Token Invalido o mal formado", "EC-401");
    }

    public void checkAuth(Map<String, Object> headers,String role) {
        var opt = Optional.ofNullable(headers.get("Authorization"));
        if (opt.isEmpty()) {
            throw new ExceptionClient(" Permiso denegado", "Forbiden", 403, "Requiere Auth", "EC-403");
        }
        boolean check = BEARER_TOKEN.matcher(opt.get().toString()).find();
        if (Boolean.FALSE.equals(check)) {
            throw new ExceptionClient(opt.get() + " No valido", "Unauthorize", 401, "Token Invalido o mal formado", "EC-401");
        }
        String[] tokenR = opt.get().toString().replace("Bearer ", "").split("\\.");
        JsonNode node;
        try {
            node=mapper.readValue(jwtTokenUseCase.getClaims(JwtToken.builder()
                    .header(tokenR[0]).payload(tokenR[1]).sing(tokenR[2]).build()), JsonNode.class);
        } catch (JsonProcessingException e) {
            throw new ExceptionClient(e.getMessage(),
                    "Error mapeando Claims",401,"Error mapeando Claims","EC-401");
        }
        if(role.equals(node.get("role").asText())){
        throw new ExceptionClient(opt.get() + " No valido", "Unauthorize", 401, "Token Invalido o mal formado", "EC-401");
        }
    }


}
