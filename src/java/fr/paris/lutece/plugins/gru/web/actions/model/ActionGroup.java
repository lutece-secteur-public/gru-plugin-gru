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
package fr.paris.lutece.plugins.gru.web.actions.model;

import java.util.ArrayList;
import java.util.List;


/**
 * ActionGroup
 */
public class ActionGroup
{
    // Variables declarations 
    private String _strTitle;
    private String _strIcon;
    private String _strBadgeText;
    private String _strBadgeColor;
    private List<ActionItem> _listActions = new ArrayList<ActionItem>(  );

    /**
     * Returns the Title
     *
     * @return The Title
     */
    public String getTitle(  )
    {
        return _strTitle;
    }

    /**
     * Sets the Title
     *
     * @param strTitle The Title
     */
    public void setTitle( String strTitle )
    {
        _strTitle = strTitle;
    }

    /**
     * Returns the Icon
     *
     * @return The Icon
     */
    public String getIcon(  )
    {
        return _strIcon;
    }

    /**
     * Sets the Icon
     *
     * @param strIcon The Icon
     */
    public void setIcon( String strIcon )
    {
        _strIcon = strIcon;
    }

    /**
     * Returns the BadgeText
     *
     * @return The BadgeText
     */
    public String getBadgeText(  )
    {
        return _strBadgeText;
    }

    /**
     * Sets the BadgeText
     *
     * @param strBadgeText The BadgeText
     */
    public void setBadgeText( String strBadgeText )
    {
        _strBadgeText = strBadgeText;
    }

    /**
     * Returns the BadgeColor
     *
     * @return The BadgeColor
     */
    public String getBadgeColor(  )
    {
        return _strBadgeColor;
    }

    /**
     * Sets the BadgeColor
     *
     * @param strBadgeColor The BadgeColor
     */
    public void setBadgeColor( String strBadgeColor )
    {
        _strBadgeColor = strBadgeColor;
    }

    /**
     * Returns the Actions
     *
     * @return The Actions
     */
    public List<ActionItem> getActions(  )
    {
        return _listActions;
    }

    /**
     * Sets the Actions
     *
     * @param listActions The Actions
     */
    public void setActions( List<ActionItem> listActions )
    {
        _listActions = listActions;
    }

    public void addActionItem( ActionItem item )
    {
        _listActions.add( item );
    }
}
