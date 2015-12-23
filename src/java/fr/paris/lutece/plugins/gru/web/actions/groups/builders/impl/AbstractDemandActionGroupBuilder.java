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
package fr.paris.lutece.plugins.gru.web.actions.groups.builders.impl;

import fr.paris.lutece.plugins.gru.web.Constants;
import fr.paris.lutece.plugins.gru.web.actions.groups.builders.AbstractActionGroupBuilder;
import java.util.List;


/**
 * AbstractDemandActionGroupBuilder
 */
public abstract class AbstractDemandActionGroupBuilder extends AbstractActionGroupBuilder
{
    private static final String BOOKMARK_ID = "{id}";
    private static final String BOOKMARK_TYPE = "{type}";
            
    private String _strDemandManagementUrl;
    private List<String> _listExcludedTypes;
    private List<String> _listIncludedTypes;

    /**
     * Define the management url of the demand
     * @param strUrl The url
     */
    public void setDemandManagementUrl( String strUrl )
    {
        _strDemandManagementUrl = strUrl;
    }

    /**
     * Gets the management url of the demand
     * @return strUrl The url
     */
    protected String getDemandManagementUrl(  )
    {
        return _strDemandManagementUrl;
    }

    /**
     * Build the management link for a given demand
     * @param strDemandId The demand
     * @param strCustomerId The customer ID
     * @return The RUL of the link
     */
    protected String buildDemandManagementLink( String strDemandId, String strDemandTypeId, String strCustomerId )
    {
        String strUrl = _strDemandManagementUrl.replace( BOOKMARK_ID , strDemandId );
        strUrl = strUrl.replace( BOOKMARK_TYPE , strDemandTypeId );
        strUrl += "&" + Constants.PARAMETER_ID_CUSTOMER + "=" + strCustomerId;
        return strUrl;
    }
 
    /**
     * Define excluded types list
     * @param listExcludedTypes The list
     */
    public void setExcludedTypesList( List<String> listExcludedTypes )
    {
        _listExcludedTypes = listExcludedTypes;
    }

    /**
     * Gets the excluded types list 
     * @return The list
     */
    protected List<String> getExcludedTypesList( )
    {
        return _listExcludedTypes;
    }
    
    /**
     * Define excluded types list
     * @param listIncludedTypes The list
     */
    public void setIncludedTypesList( List<String> listIncludedTypes )
    {
        _listIncludedTypes = listIncludedTypes;
    }

    /**
     * Gets the excluded types list 
     * @return The list
     */
    protected List<String> getIncludedTypesList( )
    {
        return _listIncludedTypes;
    }
    
}
