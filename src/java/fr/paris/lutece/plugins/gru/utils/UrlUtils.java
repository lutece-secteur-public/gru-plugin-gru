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
package fr.paris.lutece.plugins.gru.utils;

import fr.paris.lutece.plugins.gru.web.Constants;
import fr.paris.lutece.plugins.grubusiness.business.customer.Customer;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.util.mvc.utils.MVCUtils;
import fr.paris.lutece.util.url.UrlItem;

import java.io.UnsupportedEncodingException;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provides utility methods for URLs
 *
 */
public final class UrlUtils
{
    private static final String URL_ENCODING = "UTF-8";

    /**
     * Default constructor
     */
    private UrlUtils( )
    {
    }

    /**
     * Encodes a URL
     * 
     * @param strUrl
     *            the URL
     * @return the encoded URL or the original URL if there is an exception during the encoding
     */
    public static String encodeUrl( String strUrl )
    {
        String strUrlEncoded = strUrl;

        try
        {
            strUrlEncoded = java.net.URLEncoder.encode( strUrl, URL_ENCODING );
        }
        catch( UnsupportedEncodingException e1 )
        {
            AppLogService.error( e1 );
        }

        return strUrlEncoded;
    }

    /**
     * Builds the URL to return to plugin gru after action on another plugin
     * 
     * @param strControllerUrl
     *            the controller URL
     * @param strView
     *            the view to display
     * @param mapParameters
     *            the map of parameters
     * @return the return URL
     */
    public static String buildReturnUrl( String strControllerUrl, String strView, Map<String, String> mapParameters )
    {
        UrlItem url = new UrlItem( strControllerUrl );
        url.addParameter( MVCUtils.PARAMETER_VIEW, strView );

        for ( Map.Entry<String, String> entry : mapParameters.entrySet( ) )
        {
            url.addParameter( entry.getKey( ), entry.getValue( ) );
        }

        return url.getUrl( );
    }

    /**
     * Builds the URL to return to plugin gru after action on another plugin
     * 
     * @param strControllerUrl
     *            the controller URL
     * @param strView
     *            the view to display
     * @param customer
     *            the customer
     * @return the return URL
     */
    public static String buildReturnUrl( String strControllerUrl, String strView, Customer customer )
    {
        Map<String, String> mapParameters = new HashMap<String, String>( );

        if ( customer != null )
        {
            mapParameters.put( Constants.PARAMETER_ID_CUSTOMER, customer.getId( ) );
        }

        return buildReturnUrl( strControllerUrl, strView, mapParameters );
    }
}
