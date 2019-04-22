package de.struktuhr.ownsecurityfilter.security;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * UserAuthenticationReader
 */
public class UserAuthenticationReader {
    
    private Map<String, Authentication> authentications;

    public UserAuthenticationReader() {
        initAuthentications();
    }
    
    public Authentication getAuthentication(String userId) {
        return authentications.get(userId);
    }
    
    private void initAuthentications() {
        authentications = new LinkedHashMap<>();
        authentications.put("user", buildAuthentication("user", "ROLE_USER"));
        authentications.put("admin", buildAuthentication("admin", "ROLE_USER, ROLE_ADMIN"));
    }

    private Authentication buildAuthentication(String userId, String commaSeparatedRoles) {
        return new UsernamePasswordAuthenticationToken(userId, "N/A", AuthorityUtils.commaSeparatedStringToAuthorityList(commaSeparatedRoles));
    }

}