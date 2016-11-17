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
package fr.paris.lutece.plugins.gru.service;

import fr.paris.lutece.plugins.gru.web.Constants;
import fr.paris.lutece.plugins.grubusiness.business.customer.Customer;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.url.UrlItem;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import java.util.Locale;


/**
 * ActionLinkService
 */
public class ActionLinkService
{
    public static final int TARGET_FRAME = 0;
    public static final int TARGET_NO_FRAME = 1;
    public static final int TARGET_NEW_WINDOW = 2;
    private static final String URL_JSP_FRAME_VIEW = "jsp/admin/plugins/gru/FrameView.jsp";
    private static final String PROPERTY_TARGET_FRAME = "gru.actions.link.target.frame";
    private static final String PROPERTY_TARGET_NO_FRAME = "gru.actions.link.target.noFrame";
    private static final String PROPERTY_TARGET_NEW_WINDOW = "gru.actions.link.target.newWindow";
    private static final String INFO_GUID = "{guid}";
    private static final String INFO_CUSTOMER_ID = "{cid}";
    private static final String INFO_USER_TITLE = "{user_title}";
    private static final String INFO_FIRSTNAME = "{firstname}";
    private static final String INFO_LASTNAME = "{lastname}";
    private static final String INFO_PHONE = "{phone}";
    private static final String INFO_EMAIL = "{email}";

    /**
     * Adapt Link for customer
     * @param strLinkTemplate The Link template
     * @param nTarget The target
     * @param customer The customer
     * @return The adapted link
     */
    public static String buildLink( String strLinkTemplate, int nTarget, Customer customer )
    {
        String strLink = setCustomerInfo( strLinkTemplate, customer );

        switch ( nTarget )
        {
            case TARGET_FRAME:
                strLink = buildFrameLink( strLink, customer );

                break;

            case TARGET_NO_FRAME:
                break;

            case TARGET_NEW_WINDOW:
                break;
        }

        return strLink;
    }

    /**
     * Replace in the linktemplate informations about the customer
     * @param strLinkTemplate The Link Template
     * @param customer The customer
     * @return  The link
     */
    public static String setCustomerInfo( String strLinkTemplate, Customer customer )
    {
        String strLink = strLinkTemplate;

        if ( customer != null )
        {
            strLink = fillLink( strLink, INFO_GUID, customer.getAccountGuid(  ) );
            strLink = fillLink( strLink, INFO_CUSTOMER_ID, customer.getId(  ) );
            strLink = fillLink( strLink, INFO_USER_TITLE, "" + customer.getIdTitle(  ) );
            strLink = fillLink( strLink, INFO_FIRSTNAME, customer.getFirstname(  ) );
            strLink = fillLink( strLink, INFO_LASTNAME, customer.getLastname(  ) );
            strLink = fillLink( strLink, INFO_PHONE, customer.getMobilePhone(  ) );
            strLink = fillLink( strLink, INFO_EMAIL, customer.getEmail(  ) );
        }

        return strLink;
    }

    private static String fillLink( String strLink, String strBookmark, String strValue )
    {
        String strNewValue = ( strValue == null ) ? StringUtils.EMPTY : strValue;

        return strLink.replace( strBookmark, strNewValue );
    }

    /**
     * Build a link to display a given URL into a frame
     * @param strUrl The input URL
     * @param customer The customer
     * @return The frame URL
     */
    private static String buildFrameLink( String strUrl, Customer customer )
    {
        String strLink;

        try
        {
            strLink = URLEncoder.encode( strUrl, "UTF-8" );

            UrlItem url = new UrlItem( URL_JSP_FRAME_VIEW );
            url.addParameter( Constants.PARAMETER_URL_FRAME, strLink );

            if ( customer != null )
            {
                url.addParameter( Constants.PARAMETER_ID_CUSTOMER, customer.getId(  ) );
            }

            return url.getUrl(  );
        }
        catch ( UnsupportedEncodingException ex )
        {
            AppLogService.error( "Error encoding url " + ex.getMessage(  ), ex );

            return "";
        }
    }

    /**
     * The list of available targets
     * @param locale The locale
     * @return The list
     */
    public static ReferenceList getTargetList( Locale locale )
    {
        ReferenceList list = new ReferenceList(  );
        list.addItem( TARGET_FRAME, I18nService.getLocalizedString( PROPERTY_TARGET_FRAME, locale ) );
        list.addItem( TARGET_NO_FRAME, I18nService.getLocalizedString( PROPERTY_TARGET_NO_FRAME, locale ) );
        list.addItem( TARGET_NEW_WINDOW, I18nService.getLocalizedString( PROPERTY_TARGET_NEW_WINDOW, locale ) );

        return list;
    }
}
