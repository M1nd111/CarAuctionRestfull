package spring.ws.database.dto.read;

import lombok.ToString;
import lombok.Value;


@ToString
@Value
public class LoginDto
{
    String username;
    String password;
}
