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
package fr.paris.lutece.plugins.gru.business.feature;

import fr.paris.lutece.portal.service.rbac.RBACResource;

import org.hibernate.validator.constraints.*;

import java.io.Serializable;

import javax.validation.constraints.*;


/**
 * This is the business class for the object Feature
 */
public class Feature implements Serializable, RBACResource
{
    // RBAC management
    public static final String RESOURCE_TYPE = "GRU_FEATURE";
    public static final String PERMISSION_ACCESS = "ACCESS";
    public static final int DISPLAY_STANDARD = 0;
    public static final int DISPLAY_HOME = 1;
    public static final int DISPLAY_HIDDEN = -1;
    private static final long serialVersionUID = 1L;

    // Variables declarations 
    private int _nId;
    @NotEmpty( message = "#i18n{gru.validation.feature.Name.notEmpty}" )
    @Size( max = 50, message = "#i18n{gru.validation.feature.Name.size}" )
    private String _strName;
    @Size( max = 255, message = "#i18n{gru.validation.feature.Link.size}" )
    private String _strLink;
    private String _strLinkCustomerParams;
    private int _nTarget;
    private int _nIdCategory;
    private String _strCategory;
    private int _nIdOrder;
    private int _nDisplayLevel;

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
     * Returns the Name
     * @return The Name
     */
    public String getName(  )
    {
        return _strName;
    }

    /**
     * Sets the Name
     * @param strName The Name
     */
    public void setName( String strName )
    {
        _strName = strName;
    }

    /**
     * Returns the Link
     * @return The Link
     */
    public String getLink(  )
    {
        return _strLink;
    }

    /**
     * Sets the Link
     * @param strLink The Link
     */
    public void setLink( String strLink )
    {
        _strLink = strLink;
    }

    /**
     * Returns the LinkCustomerParams
     * @return The LinkCustomerParams
     */
    public String getLinkCustomerParams(  )
    {
        return _strLinkCustomerParams;
    }

    /**
     * Sets the LinkCustomerParams
     * @param strLinkCustomerParams The LinkCustomerParams
     */
    public void setLinkCustomerParams( String strLinkCustomerParams )
    {
        _strLinkCustomerParams = strLinkCustomerParams;
    }

    /**
     * Returns the IdCategory
     * @return The IdCategory
     */
    public int getIdCategory(  )
    {
        return _nIdCategory;
    }

    /**
     * Sets the IdCategory
     * @param nIdCategory The IdCategory
     */
    public void setIdCategory( int nIdCategory )
    {
        _nIdCategory = nIdCategory;
    }

    /**
     * Returns the Category
     * @return The Category
     */
    public String getCategory(  )
    {
        return _strCategory;
    }

    /**
     * Sets the Category
     * @param strCategory The Category
     */
    public void setCategory( String strCategory )
    {
        _strCategory = strCategory;
    }

    /**
     * Returns the IdOrder
     * @return The IdOrder
     */
    public int getIdOrder(  )
    {
        return _nIdOrder;
    }

    /**
     * Sets the IdOrder
     * @param nIdOrder The IdOrder
     */
    public void setIdOrder( int nIdOrder )
    {
        _nIdOrder = nIdOrder;
    }

    /**
     * Returns the Target
     * @return The Target
     */
    public int getTarget(  )
    {
        return _nTarget;
    }

    /**
     * Sets the Target
     * @param nTarget The Target
     */
    public void setTarget( int nTarget )
    {
        _nTarget = nTarget;
    }

    /**
     * Returns the DisplayLevel
     * @return The DisplayLevel
     */
    public int getDisplayLevel(  )
    {
        return _nDisplayLevel;
    }

    /**
     * Sets the DisplayLevel
     * @param nDisplayLevel The DisplayLevel
     */
    public void setDisplayLevel( int nDisplayLevel )
    {
        _nDisplayLevel = nDisplayLevel;
    }

    /**
     * Is feature disabled
     * @return true if disabled otherwise false
     */
    public boolean isHidden(  )
    {
        return _nDisplayLevel == DISPLAY_HIDDEN;
    }

    /**
     * Is feature on Home page
     * @return true if on Home page otherwise false
     */
    public boolean isHome(  )
    {
        return _nDisplayLevel == DISPLAY_HOME;
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
