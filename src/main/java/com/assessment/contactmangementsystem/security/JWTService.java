package com.assessment.contactmangementsystem.security;

//This is just an example of JWT token service, We can create our custom service for OAuth
// authentication and can generate JWT token for each user.
public class JWTService {
    private final String jwtToken = "e.xyz.1204788732532698:1760d3a296ab08fc1d8ebe76b2aa6274";

    public boolean verify(String token) {
        if(jwtToken.equals(token))
            return true;
        return false;
    }
}
