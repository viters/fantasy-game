package com.ls.soa.game.fantasy.server.utils;

import com.ls.soa.game.fantasy.server.models.TokenMetadata;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@ApplicationScoped
public class TokenUtil {
    private final Key signingKey = new SecretKeySpec(
            DatatypeConverter.parseBase64Binary("EFEE3E98B1FEE3C75C471C66D899A"),
            SignatureAlgorithm.HS512.getJcaName()
    );

    public String createToken(String id, String role, long expired) {
        return Jwts.builder()
                .setSubject(id)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .setExpiration(new Date(expired))
                .compact();
    }

    public TokenMetadata validateToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
        String id = claims.getSubject();
        String role = claims.get("role").toString();

        Jwts.parser()
                .requireSubject(claims.getSubject())
                .setSigningKey(signingKey)
                .parseClaimsJws(token);

        return new TokenMetadata(id, role);
    }
}
