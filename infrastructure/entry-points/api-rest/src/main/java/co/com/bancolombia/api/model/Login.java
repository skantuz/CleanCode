package co.com.bancolombia.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {

    @NotNull
    @Pattern(regexp = "^[a-z0-9@]{4,10}$")
    private String user;
    @NotNull
    private String pass;
}
