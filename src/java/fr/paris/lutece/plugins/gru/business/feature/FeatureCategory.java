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


// import org.hibernate.validator.constraints.*;
import java.io.Serializable;

import java.util.List;

import javax.validation.constraints.*;


/**
 * This is the business class for the object FeatureCategory
 */
public class FeatureCategory implements Serializable
{
    private static final long serialVersionUID = 1L;

    // Variables declarations 
    private int _nId;

    //    @NotEmpty( message = "#i18n{gru.validation.featurecategory.Name.notEmpty}" )
    @Size( max = 50, message = "#i18n{gru.validation.featurecategory.Name.size}" )
    private String _strName;

    //    @NotEmpty( message = "#i18n{gru.validation.featurecategory.Description.notEmpty}" )
    @Size( max = 255, message = "#i18n{gru.validation.featurecategory.Description.size}" )
    private String _strDescription;
    private int _nIdOrder;
    @Size( max = 255, message = "#i18n{gru.validation.featurecategory.CategoryIcon.size}" )
    private String _strCategoryIcon;
    private int _nIndicatorStyle;
    private String _strIndicatorText;

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
     * Returns the Description
     * @return The Description
     */
    public String getDescription(  )
    {
        return _strDescription;
    }

    /**
     * Sets the Description
     * @param strDescription The Description
     */
    public void setDescription( String strDescription )
    {
        _strDescription = strDescription;
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
     * Returns the Category Icon
     * @return The Category Icon
     */
    public String getIcon(  )
    {
        return _strCategoryIcon;
    }

    /**
     * Sets the CategoryIcon
     * @param strCategoryIcon The CategoryIcon
     */
    public void setIcon( String strCategoryIcon )
    {
        _strCategoryIcon = strCategoryIcon;
    }

    /**
    * Returns the IndicatorStyle
    * @return The IndicatorStyle
    */
    public int getIndicatorStyle(  )
    {
        return _nIndicatorStyle;
    }

    /**
     * Sets the IndicatorStyle
     * @param nIndicatorStyle The IndicatorStyle
     */
    public void setIndicatorStyle( int nIndicatorStyle )
    {
        _nIndicatorStyle = nIndicatorStyle;
    }

    /**
     * Returns the IndicatorText
     * @return The IndicatorText
     */
    public String getIndicatorText(  )
    {
        return _strIndicatorText;
    }

    /**
     * Sets the IndicatorText
     * @param strIndicatorText The IndicatorText
     */
    public void setIndicatorText( String strIndicatorText )
    {
        _strIndicatorText = strIndicatorText;
    }

    public List<Feature> getFeatures(  )
    {
        return FeatureHome.getFeaturesListByCategory( _nId );
    }
}
