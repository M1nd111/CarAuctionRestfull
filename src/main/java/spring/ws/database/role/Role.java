package spring.ws.database.role;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    buyer, seller;

    @Override
    public String getAuthority() {
        return name();
    }
}
