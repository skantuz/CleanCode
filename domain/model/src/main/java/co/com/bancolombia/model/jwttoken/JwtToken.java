package co.com.bancolombia.model.jwttoken;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class JwtToken {
    String header;
    String payload;
    String sing;

    public String getJwt(){
        StringBuilder token = new StringBuilder();
        token.append(header).append(".");
        token.append(payload).append(".");
        token.append(sing);
        return token.toString();
    }
}
