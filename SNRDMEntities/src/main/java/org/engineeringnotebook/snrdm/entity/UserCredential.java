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

package org.engineeringnotebook.snrdm.entity;

import javax.persistence.Entity;
import com.spaceprogram.simplejpa.model.IdedTimestampedBase;

/**
 * An entity bean representing a user's credentials
 * 
 * @todo This class should be broken apart into a User class and a credentials
 * class
 * 
 * @author Jeff Coble <jeffrey.a.coble@gmail.com> http://engineeringnotebook.org
 */
@Entity
public class UserCredential extends IdedTimestampedBase{
    
    private String twitterScreenName = null;
    private String twitterRequestSecret = null;
    private String twitterRequestToken = null;
    private String twitterAccessSecret = null;  
    private String twitterAccessToken = null;

    /**
     * 
     */
    public UserCredential(){}
      
    /**
     * 
     * @param screenName user's twitter screen name
     */
    public void setTwitterScreenName(String screenName) {        
        this.twitterScreenName = screenName;
    }
    
    /**
     * 
     * @return user's twitter screen name as a string
     */
    public String getTwitterScreenName() {
        return this.twitterScreenName;
    }
    
    /**
     * 
     * @param token access token
     */
    public void setTwitterAccessToken(String token) {
        this.twitterAccessToken = token;
    }
    
    /**
     * 
     * @return access token
     */
    public String getTwitterAccessToken() {
        return twitterAccessToken;
    }
    
    /**
     * 
     * @param secret access token secret
     */
    public void setTwitterAccessSecret(String secret) {
        this.twitterAccessSecret = secret;
    }
    
    /**
     * 
     * @return access token secret
     */
    public String getTwitterAccessSecret() {
        return twitterAccessSecret;
    }
    
     /**
     * 
     * @param token request token
     */
    public void setTwitterRequestToken(String token) {
        this.twitterRequestToken = token;
    }
    
    /**
     * 
     * @return request token
     */
    public String getTwitterRequestToken() {
        return twitterRequestToken;
    }
    
    /**
     * 
     * @param secret request token secret
     */
    public void setTwitterRequestSecret(String secret) {
        this.twitterRequestSecret = secret;
    }
    
    /**
     * 
     * @return request token secret
     */
    public String getTwitterRequestSecret() {
        return twitterRequestSecret;
    }
    
         
    /**
     * The rest is auto-generated entity bean stuff
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * 
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserCredential)) {
            return false;
        }
        UserCredential other = (UserCredential) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return "org.engineeringnotebook.registrationservice.entities.UserCredential[ id=" + id + " ]";
    }

}
