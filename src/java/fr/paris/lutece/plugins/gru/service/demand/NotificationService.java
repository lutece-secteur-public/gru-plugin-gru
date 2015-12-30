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
package fr.paris.lutece.plugins.gru.service.demand;

import fr.paris.lutece.plugins.gru.business.demand.Email;
import fr.paris.lutece.plugins.gru.business.demand.Notification;
import fr.paris.lutece.plugins.gru.business.demand.Sms;
import fr.paris.lutece.portal.service.util.AppPropertiesService;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * NotificationService
 */
public class NotificationService
{
    private static final String PROPERTY_DATE_FORMAT = "gru.notifications.date_format";
    private static final String _strPattern = AppPropertiesService.getProperty( PROPERTY_DATE_FORMAT );
    private static final SimpleDateFormat _formater = new SimpleDateFormat( _strPattern );

    /**
     * Add info to a given notification from a JSON data
     * @param notification The notification
     * @param strJSON The JSON data
     * @return The filled notification
     */
    public static Notification parseJSON( Notification notification, String strJSON )
    {
        try
        {
            JsonFactory factory = new JsonFactory(  );
            JsonParser parser = factory.createJsonParser( strJSON );
            parser.setCodec( new ObjectMapper(  ) );

            JsonNode jsonNode = parser.readValueAsTree(  );
            readJsonData( jsonNode, notification );
        }
        catch ( IOException ex )
        {
            Logger.getLogger( NotificationService.class.getName(  ) ).log( Level.SEVERE, null, ex );
        }

        return notification;
    }

    /**
     * Read recursively the JSON flow
     * @param jsonNode A Json node
     * @param notification A notification to fill
     */
    private static void readJsonData( JsonNode jsonNode, Notification notification )
    {
        Iterator<Map.Entry<String, JsonNode>> ite = jsonNode.getFields(  );

        while ( ite.hasNext(  ) )
        {
            Map.Entry<String, JsonNode> entry = ite.next(  );

            if ( entry.getValue(  ).isObject(  ) )
            {
                if ( entry.getKey(  ).equals( "user_email" ) )
                {
                    notification.setEmail( readEmail( entry.getValue(  ) ) );
                }
                else if ( entry.getKey(  ).equals( "user_sms" ) )
                {
                    notification.setSms( readSms( entry.getValue(  ) ) );
                }
                else
                {
                    readJsonData( entry.getValue(  ), notification );
                }
            }
            else
            {
                if ( entry.getKey(  ).equals( "notification_source" ) )
                {
                    notification.setSource( entry.getValue(  ).getTextValue(  ) );
                }
            }
        }
    }

    /**
     * Read Email data
     * @param jsonNode The Email node
     * @return An Email object
     */
    private static Email readEmail( JsonNode jsonNode )
    {
        Email email = new Email(  );
        email.setRecipient( jsonNode.get( "recipient" ).asText(  ) );
        email.setSubject( jsonNode.get( "subject" ).asText(  ) );
        email.setMessage( jsonNode.get( "message" ).asText(  ) );

        return email;
    }

    /**
     * Read SMS data
     * @param jsonNode The SMS node
     * @return An SMS object
     */
    private static Sms readSms( JsonNode jsonNode )
    {
        Sms sms = new Sms(  );
        sms.setPhoneNumber( jsonNode.get( "phone_number" ).asText(  ) );
        sms.setMessage( jsonNode.get( "message" ).asText(  ) );

        return sms;
    }

    /**
     * Date formatter
     * @param lTime Time
     * @return The date
     */
    public static String dateFormat( long lTime )
    {
        return _formater.format( ( new Date( lTime ) ) );
    }
}
