/*
 * Copyright (c) 2002-2016, Mairie de Paris
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
package fr.paris.lutece.plugins.gru.web.utils;

import fr.paris.lutece.plugins.gru.web.Constants;
import fr.paris.lutece.plugins.grubusiness.business.notification.Notification;

import java.util.List;
import java.util.Map;

/**
 * Class providing utility methods for Model
 *
 */
public final class ModelUtils
{
    /**
     * Default constructor
     */
    private ModelUtils( )
    {
    }

    /**
     * Completes the specified model with status
     * 
     * @param model
     *            the model to complete
     * @param listNotifications
     *            the notification list used to retrieve status
     */
    public static void storeStatus( Map<String, Object> model, List<Notification> listNotifications )
    {
        boolean bIsAgentStatusFound = false;
        boolean bIsCustomerStatusFound = false;

        for ( Notification notification : listNotifications )
        {
            if ( !bIsAgentStatusFound && ( notification.getBackofficeNotification( ) != null ) )
            {
                model.put( Constants.MARK_AGENT_STATUS, notification.getBackofficeNotification( ).getStatusText( ) );
                bIsAgentStatusFound = true;
            }

            if ( !bIsCustomerStatusFound && ( notification.getMyDashboardNotification( ) != null ) )
            {
                model.put( Constants.MARK_CUSTOMER_STATUS, notification.getMyDashboardNotification( ).getStatusText( ) );
                bIsCustomerStatusFound = true;
            }

            if ( bIsAgentStatusFound && bIsCustomerStatusFound )
            {
                break;
            }
        }
    }
}
