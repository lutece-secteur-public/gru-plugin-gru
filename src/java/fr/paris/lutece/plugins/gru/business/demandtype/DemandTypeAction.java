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
package fr.paris.lutece.plugins.gru.business.demandtype;

import fr.paris.lutece.portal.service.rbac.RBACResource;

import org.hibernate.validator.constraints.*;

import java.io.Serializable;

import javax.validation.constraints.*;


/**
 * This is the business class for the object DemandTypeAction
 */
public class DemandTypeAction implements Serializable, RBACResource
{
    public static final String RESOURCE_TYPE = "GRU_DEMAND_ACTION";
    public static final String PERMISSION_ACCESS = "ACCESS";
    private static final long serialVersionUID = 1L;

    // Variables declarations 
    private int _nId;
    @NotEmpty( message = "#i18n{gru.validation.demandtypeaction.ActionLink.notEmpty}" )
    @Size( max = 255, message = "#i18n{gru.validation.demandtypeaction.ActionLink.size}" )
    private String _strActionLink;
    @NotEmpty( message = "#i18n{gru.validation.demandtypeaction.ActionLabel.notEmpty}" )
    @Size( max = 50, message = "#i18n{gru.validation.demandtypeaction.ActionLabel.size}" )
    private String _strActionLabel;
    private int _nIdDemandType;
    @Size( max = 50, message = "#i18n{gru.validation.demandtypeaction.DemandType.size}" )
    private String _strDemandType;

    /**
     * Returns the Id
     * @return The Id
     */
    public int getId(  )
    {
        return _nId;
    }

    /**
     * Sets the Id
     * @param nId The Id
     */
    public void setId( int nId )
    {
        _nId = nId;
    }

    /**
     * Returns the ActionLink
     * @return The ActionLink
     */
    public String getLink(  )
    {
        return _strActionLink;
    }

    /**
     * Sets the ActionLink
     * @param strActionLink The ActionLink
     */
    public void setLink( String strActionLink )
    {
        _strActionLink = strActionLink;
    }

    /**
     * Returns the ActionLabel
     * @return The ActionLabel
     */
    public String getLabel(  )
    {
        return _strActionLabel;
    }

    /**
     * Sets the ActionLabel
     * @param strActionLabel The ActionLabel
     */
    public void setLabel( String strActionLabel )
    {
        _strActionLabel = strActionLabel;
    }

    /**
     * Returns the IdDemandType
     * @return The IdDemandType
     */
    public int getIdDemandType(  )
    {
        return _nIdDemandType;
    }

    /**
     * Sets the IdDemandType
     * @param nIdDemandType The IdDemandType
     */
    public void setIdDemandType( int nIdDemandType )
    {
        _nIdDemandType = nIdDemandType;
    }

    /**
     * Returns the DemandType
     * @return The DemandType
     */
    public String getDemandType(  )
    {
        return _strDemandType;
    }

    /**
     * Sets the DemandType
     * @param strDemandType The DemandType
     */
    public void setDemandType( String strDemandType )
    {
        _strDemandType = strDemandType;
    }

    ////////////////////////////////////////////////////////////////////////////
    // RBAC Resource implementation

    /**
     * {@inheritDoc }
     */
    @Override
    public String getResourceTypeCode(  )
    {
        return RESOURCE_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getResourceId(  )
    {
        return "" + _nId;
    }
}
