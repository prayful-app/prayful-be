package ar.juarce.webapp.auth;

import ar.juarce.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    private final JwtEncoder jwtEncoder;

    @Autowired
    public JwtUtils(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public Jwt createJwt(User user) {
        final JwsHeader header = JwsHeader.with(SignatureAlgorithm.ES256).build();
        final JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(user.getId().toString())
                .build();

        final JwtEncoderParameters parameters = JwtEncoderParameters.from(header, claims);
        return jwtEncoder.encode(parameters);
    }
}
