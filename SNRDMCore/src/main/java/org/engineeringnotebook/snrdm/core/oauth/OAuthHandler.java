/*
 * Copyright 2011 Jeff Coble <jeffrey.a.coble@gmail.com> http://engineeringnotebook.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.engineeringnotebook.snrdm.core.oauth;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Verb;
import org.scribe.model.Response;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Jeff Coble <jeffrey.a.coble@gmail.com> http://engineeringnotebook.org
 */
public class OAuthHandler {

    private static String consumerKey;
    private static String consumerSecretKey;
    private OAuthService service = null;
    private static final Logger logger = Logger.getLogger(OAuthHandler.class.getName());    
    
    /**
     * 
     */
    public OAuthHandler(String cKey, String cSKey) {
        consumerKey = cKey;
        consumerSecretKey = cSKey;
        service = new ServiceBuilder()
                                .provider(TwitterApi.class)
                                .apiKey(consumerKey)
                                .apiSecret(consumerSecretKey)
                                .build();
    }
    
    /**
     * 
     * @return
     */
    public Token getRequestToken() {
       logger.log(Level.FINE, "OAuth getRequestToken");
       
       // Obtain the Request Token from Twitter
       Token requestToken = service.getRequestToken(); 
       
       return(requestToken);
    }
    
    /**
     * 
     * @param requestToken
     * @return
     */
    public String getAuthorizationURL(Token requestToken) {
        logger.log(Level.FINE, "OAuth getAuthorizationURL");
        
        String authURL = service.getAuthorizationUrl(requestToken);
        
        return(authURL); 
        
    }
    
    /**
     * 
     * @param requestToken 
     * @param verificationToken
     * @return
     */
    public Token getAccessToken(Token requestToken, String verificationToken) {
        logger.log(Level.FINE, "OAuth getResource");
        
        Verifier verifier = new Verifier(verificationToken);
        
        // Use the request token and verification token to retrieve the access token
        Token accessToken = service.getAccessToken(requestToken, verifier);
        
        return accessToken;
    }

    public String getResource(Token accessToken, String protectedResourceURL) {
        logger.log(Level.FINE, "OAuth getResource");
        
        // Request protected resource
        OAuthRequest request = new OAuthRequest(Verb.GET, protectedResourceURL);
        service.signRequest(accessToken, request);
        Response response = request.send();
                
        return response.getBody();
    }

}
