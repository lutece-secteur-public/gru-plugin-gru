/*
 * Copyright (c) 2002-2017, Mairie de Paris
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
package fr.paris.lutece.plugins.gru.service.feature;

import fr.paris.lutece.plugins.gru.business.feature.Feature;
import fr.paris.lutece.plugins.gru.business.feature.FeatureHome;
import fr.paris.lutece.plugins.gru.service.ActionLinkService;
import fr.paris.lutece.plugins.gru.service.encryption.CustomerEncryptionService;
import fr.paris.lutece.plugins.grubusiness.business.customer.Customer;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.util.ReferenceList;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * FeatureService
 */
public final class FeatureService
{
    private static final String PROPERTY_DISPLAY_STANDARD = "gru.features.display.standard";
    private static final String PROPERTY_DISPLAY_HOME = "gru.features.display.home";
    private static final String PROPERTY_DISPLAY_HIDDEN = "gru.features.display.hidden";

    /**
     * Private constructor
     */
    private FeatureService( )
    {

    }

    /**
     * Gives the list of display levels as a {@code ReferenceList}
     * 
     * @param locale
     *            the locale
     * @return the {@code ReferenceList}
     */
    public static ReferenceList getFeatureDisplayLevels( Locale locale )
    {
        ReferenceList list = new ReferenceList( );

        list.addItem( Feature.DISPLAY_STANDARD, I18nService.getLocalizedString( PROPERTY_DISPLAY_STANDARD, locale ) );
        list.addItem( Feature.DISPLAY_HOME, I18nService.getLocalizedString( PROPERTY_DISPLAY_HOME, locale ) );
        list.addItem( Feature.DISPLAY_HIDDEN, I18nService.getLocalizedString( PROPERTY_DISPLAY_HIDDEN, locale ) );

        return list;
    }

    /**
     * Gets the list of features that should be displayed on the home page
     * 
     * @return The list
     */
    public static List<Feature> getHomeFeatures( )
    {
        List<Feature> list = new ArrayList<Feature>( );

        for ( Feature feature : FeatureHome.getFeaturesList( ) )
        {
            if ( feature.isHome( ) )
            {
                list.add( feature );
            }
        }

        return list;
    }

    /**
     * Build a link to a feature for a given customer
     * 
     * @param feature
     *            The feature
     * @param customer
     *            The customer
     * @return The link
     */
    public static String getCustomerLink( Feature feature, Customer customer )
    {
        Customer customerEncrypted = customer;
        StringBuilder sbLinkTemplate = new StringBuilder( feature.getLink( ) );

        if ( ( customer != null ) && ( customer.getId( ) != null ) )
        {
            customerEncrypted = CustomerEncryptionService.getInstance( ).encrypt( customer, feature.getResourceTypeCode( ) + feature.getResourceId( ) );
            sbLinkTemplate.append( feature.getLinkCustomerParams( ) );
        }

        return ActionLinkService.buildLink( sbLinkTemplate.toString( ), feature.getTarget( ), customerEncrypted );
    }
}
