package com.pdict.iplpredict.services;

import com.pdict.iplpredict.crypto.AccessTokenGenerator;
import com.pdict.iplpredict.crypto.SHA512HashGenerator;
import com.pdict.iplpredict.database.LoginSessionRepository;
import com.pdict.iplpredict.database.UserRepository;
import com.pdict.iplpredict.entities.LoginCredentials;
import com.pdict.iplpredict.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

@Path("/authentication")
public class AuthenticationService {
    Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    LoginSessionRepository loginSessionRepository = new LoginSessionRepository();
    UserRepository userRepository = new UserRepository();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(LoginCredentials loginCredentials) {
        logger.info(Instant.now()+" RECEIVED AUTHENTICATION POST: /authentication "+loginCredentials.userName);
        String accessToken=null;
        try {
            String passwordSha512 = SHA512HashGenerator.getSHA512Hash(loginCredentials.password);

            User user = userRepository.getUserByUserName(loginCredentials.userName);

            //if password shas don't match, return unauthorized
            if(!user.password.contentEquals(passwordSha512)) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
            else {
                accessToken = AccessTokenGenerator.generateAccessToken(loginCredentials.userName, loginCredentials.password);
                loginSessionRepository.createNewToken(loginCredentials.userName, accessToken, Timestamp.from(Instant.now()));
            }

        }
        catch(SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE POST: /authentication "+loginCredentials.userName, sqlException);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(accessToken).build();
    }
}
