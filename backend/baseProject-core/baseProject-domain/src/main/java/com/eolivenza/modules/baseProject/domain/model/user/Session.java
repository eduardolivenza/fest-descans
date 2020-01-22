package com.eolivenza.modules.baseProject.domain.model.user;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.function.Function;

public class Session {

    private User user;
    private String token;
    private static String SECRET_KEY = "eduard";
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 60*30;

    public Session(User currentUser) {
        this.user = currentUser;
        //generate token and store in sessions table
        this.token = createJWT(currentUser );
    }

    private String createJWT(User currentUser) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Long now = System.currentTimeMillis();

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(currentUser.toString())
                .setIssuedAt(new Date(now))
                .setSubject(currentUser.getName())
                .setIssuer(currentUser.getEmail())
                .signWith(signatureAlgorithm, signingKey)
                .claim("userRights", currentUser.getRights())
                .setExpiration(new Date(now + ACCESS_TOKEN_VALIDITY_SECONDS * 1000));

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public String getToken(){
        return this.token;
    }

    public static String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuer);
    }

    public static String getUserRightsFromToken(String token){
        final Claims claims = getAllClaimsFromToken(token);
        return  (String) claims.get("userRights");
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private static Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


}
