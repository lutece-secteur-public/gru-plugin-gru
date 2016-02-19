/*
 * Copyright (c) 2002-2015, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.gru.business.demand;

import fr.paris.lutece.plugins.gru.utils.DateUtils;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;


/**
 * Notification
 */
@JsonRootName( value = "notification" )
@JsonIgnoreProperties( ignoreUnknown = true )
public class Notification
{
    // Variables declarations 
    private long _lTimestamp;
    private String _strTitle;
    private String _strSource;
    private Email _email;
    private Sms _sms;
    private UserDashboard _userDashboard;
    private BackOfficeLogging _backOfficeLogging;

    /**
     * Returns the Timestamp
     * @return The Timestamp
     */
    public long getTimestamp(  )
    {
        return _lTimestamp;
    }

    /**
     * Sets the Timestamp
     * @param Timestamp The Timestamp
     */
    public void setTimestamp( long Timestamp )
    {
        _lTimestamp = Timestamp;
    }

    /**
     * Returns the Date
     * @return The Date
     */
    public String getDate(  )
    {
        return DateUtils.dateFormat( _lTimestamp );
    }

    /**
     * Returns the Source
     * @return The Source
     */
    public String getSource(  )
    {
        return _strSource;
    }

    /**
     * Sets the Source
     * @param strSource The Source
     */
    public void setSource( String strSource )
    {
        _strSource = strSource;
    }

    /**
    * Returns the Title
    * @return The Title
    */
    public String getTitle(  )
    {
        return _strTitle;
    }

    /**
     * Sets the Title
     * @param strTitle The Title
     */
    public void setTitle( String strTitle )
    {
        _strTitle = strTitle;
    }

    /**
     * Set the Email object
     * @param email The Email Object
     */
    @JsonProperty( "user_email" )
    public void setEmail( Email email )
    {
        _email = email;
    }

    /**
    * Returns the Email object
    * @return The Email object
    */
    public Email getEmail(  )
    {
        return _email;
    }

    /**
     * Sets the SMS object
     * @param sms The SMS object
     */
    @JsonProperty( "user_sms" )
    public void setSms( Sms sms )
    {
        _sms = sms;
    }

    /**
    * Returns the SMS Object
    * @return The SMS Object
    */
    public Sms getSms(  )
    {
        return _sms;
    }

    /**
     * Set the UserDashboard object
     * @param userDashboard The UserDashboard Object
     */
    @JsonProperty( "user_dashboard" )
    public void setUserDashboard( UserDashboard userDashboard )
    {
        _userDashboard = userDashboard;
    }

    /**
    * Returns the UserDashboard object
    * @return The UserDashboard object
    */
    public UserDashboard getUserDashboard(  )
    {
        return _userDashboard;
    }
    
    /**
     * Set the BackOfficeLogging object
     * @param backOfficeLogging The BackOfficeLogging Object
     */
    @JsonProperty( "backoffice_logging" )
    public void setBackOfficeLogging( BackOfficeLogging backOfficeLogging )
    {
        _backOfficeLogging = backOfficeLogging;
    }

    /**
    * Returns the BackOfficeLogging object
    * @return The BackOfficeLogging object
    */
    public BackOfficeLogging getBackOfficeLogging(  )
    {
        return _backOfficeLogging;
    }
    
}
