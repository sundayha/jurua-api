package com.jurua.api;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class JuruaApiApplicationTests {

	@Test
	public void contextLoads() {
	}

    //@Test
    //public void t() {
		////Objects.equals("", "");
    //    BASE64Encoder base64Encoder = new BASE64Encoder();
    //    System.out.println(base64Encoder.encode("{\"typ\": \"JWT\",\"alg\": \"HS256\"}".getBytes()));
    //
    //    BASE64Decoder base64Decoder = new BASE64Decoder();
    //    try {
    //        System.out.println(new String(base64Decoder.decodeBuffer("rSWamyAYwuHCo7IFAgd1oRpSP7nzL7BF5t7ItqpKViM")));
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //}

    @Test
    public void createJWT() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        //ECPrivateKey ecPrivateKey;
        //try {
        //     ecPrivateKey = new ECPrivateKeyImpl("woshinibabanishiwoerzi".getBytes());
        //} catch (InvalidKeyException e) {
        //    e.printStackTrace();
        //}
        Date now = new Date();
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("woshinibabanishiwoerzi");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setId("id")
                .setIssuedAt(now)
                .setSubject("subject")
                .setIssuer("issuer").setAudience("lalala")
                .signWith(signatureAlgorithm, signingKey);
        //if (20000 >= 0) {
        //    long expMillis = nowMillis;
        //    Date exp = new Date(expMillis);
        //    builder.setExpiration(exp);
        //}

        System.out.println(builder.compact());
    }

    @Test
    public void parseJWT() {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("woshinibabanishiwoerzi"))
                .parseClaimsJws("eyJhbGciOiJIUzU阿打算xMiJ9.eyJqdGkiOiJpZCIsImlhdCI6MTUwOTAwODk5Niwic3ViIjoic3ViamVjdCIsImlzcyI6Imlzc3VlciIsImF1ZCI6ImxhbGFsYSJ9.6M3yNqVOrg5ZMOThvNnRlStaWqxD2N4u-Pea-xRT6_m-sRRpGSq1Fj4XdE8FdO9Zqqi5qv9CDbLAvpUd4T6qRQ")
                .getBody();
        //Jwts.parser().
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuedAt());
        System.out.println("Expiration: " + claims.getExpiration());
        System.out.println("Aud: " + claims.getAudience());
    }

    @Test
    public void getUid() {
        //System.out.println(UUID.randomUUID());
        System.out.println("\"/v2/api-docs\", \"/configuration/ui\", \"/swagger-resources/**\", \"/configuration/**\", \"/swagger-ui.html\", \"/webjars/**\"".replace("\"", ""));
    }

    @Test
    public void getDate() {
	    Date date = new Date(77652349);
	    Date dateNow = new Date();
	    Date datea = new Date(System.currentTimeMillis() - 300 * 1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(date));
        System.out.println(format.format(dateNow));
        System.out.println(format.format(datea));
        System.out.println(date.before(datea));
    }
}
