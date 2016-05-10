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

import java.util.ArrayList;
import java.util.List;


/**
 * Demand Class
 */
public class Demand extends BaseDemand
{
    public static final int STATUS_INPROGRESS = 0;
    public static final int STATUS_CLOSED = 1;
    private static final String NO_DATE = "";

    // Variables declarations 
    private String _strTitle;
    private List<Notification> _listNotifications = new ArrayList<Notification>(  );
    private List<Action> _listActions = new ArrayList<Action>(  );
    private String _strCustomerStatus;
    private String _strAgentStatus;
    private boolean _bShowDetails;

    /** Constructor */
    public Demand(  )
    {
    }

    /**
     * Constructor
     * @param base Base demand
     */
    public Demand( BaseDemand base )
    {
        setId( base.getId(  ) );
        setReference( base.getReference(  ) );
        setDemandTypeId( base.getDemandTypeId(  ) );
        setStatus( base.getStatus(  ) );
        setTimeOpenedInMs( base.getTimeOpenedInMs(  ) );
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
     * Returns the Notifications
     *
     * @return The Notifications
     */
    public List<Notification> getNotifications(  )
    {
        return _listNotifications;
    }

    /**
     * Sets the Notifications
     *
     * @param Notifications The Notifications
     */
    public void setNotifications( List<Notification> Notifications )
    {
        _listNotifications = Notifications;
    }

    /**
     * Add a notification
     * @param notification
     */
    public void addNotification( Notification notification )
    {
        _listNotifications.add( notification );
    }

    /**
     * Returns the Actions
     *
     * @return The Actions
     */
    public List<Action> getActions(  )
    {
        return _listActions;
    }

    /**
     * Sets the Actions
     *
     * @param Actions The Actions
     */
    public void setActions( List<Action> Actions )
    {
        _listActions = Actions;
    }

    /**
     * Add a notification
     * @param notification
     */
    public void addAction( Action notification )
    {
        _listActions.add( notification );
    }

    /**
     * Returns the customer status
     *
     * @return The customer status
     */
    public String getCustomerStatus(  )
    {
        return _strCustomerStatus;
    }

    /**
     * Sets the customer status
     *
     * @param strCustomerStatus The customer status
     */
    public void setCustomerStatus( String strCustomerStatus )
    {
        _strCustomerStatus = strCustomerStatus;
    }

    /**
     * Returns the agent status
     *
     * @return The agent status
     */
    public String getAgentStatus(  )
    {
        return _strAgentStatus;
    }

    /**
     * Sets the agent status
     *
     * @param strAgentStatus The agent status
     */
    public void setAgentStatus( String strAgentStatus )
    {
        _strAgentStatus = strAgentStatus;
    }

    /**
     * The first notification date
     * @return The first notification date
     */
    public String getFirstNotificationDate(  )
    {
        if ( ( _listNotifications != null ) && !_listNotifications.isEmpty(  ) )
        {
            return _listNotifications.get( 0 ).getDate(  );
        }

        return NO_DATE;
    }

    /**
     * The last notification date
     * @return The last notification date
     */
    public String getLastNotificationDate(  )
    {
        if ( ( _listNotifications != null ) && !_listNotifications.isEmpty(  ) )
        {
            return _listNotifications.get( _listNotifications.size(  ) - 1 ).getDate(  );
        }

        return NO_DATE;
    }

    /**
     * Set the flag to show or not details
     * @param bShowDetails the flag value
     */
    public void setShowDetails( boolean bShowDetails )
    {
        _bShowDetails = bShowDetails;
    }

    /**
     * Return true if details can be shown otherwise false.
     * @return The display details flag
     */
    public boolean getShowDetails(  )
    {
        return _bShowDetails;
    }
}
