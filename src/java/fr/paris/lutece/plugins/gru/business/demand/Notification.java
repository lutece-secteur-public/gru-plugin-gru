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

import fr.paris.lutece.plugins.gru.service.demand.NotificationService;


/**
 * Notification
 */
public class Notification
{
    // Variables declarations 
    private long _lTimestamp;
    private String _strTitle;
    private String _strSource;
    private Email _email;
    private Sms _sms;

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
        return NotificationService.dateFormat( _lTimestamp );
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
}
