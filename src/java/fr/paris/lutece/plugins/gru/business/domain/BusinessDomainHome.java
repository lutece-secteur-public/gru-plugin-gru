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
package fr.paris.lutece.plugins.gru.business.domain;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

import java.util.List;

/**
 * This class provides instances management methods (create, find, ...) for BusinessDomain objects
 */
public final class BusinessDomainHome
{
    // Static variable pointed at the DAO instance
    private static IBusinessDomainDAO _dao = SpringContextService.getBean( "gru.businessDomainDAO" );
    private static Plugin _plugin = PluginService.getPlugin( "gru" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private BusinessDomainHome( )
    {
    }

    /**
     * Create an instance of the businessDomain class
     * 
     * @param businessDomain
     *            The instance of the BusinessDomain which contains the informations to store
     * @return The instance of businessDomain which has been created with its primary key.
     */
    public static BusinessDomain create( BusinessDomain businessDomain )
    {
        _dao.insert( businessDomain, _plugin );

        return businessDomain;
    }

    /**
     * Update of the businessDomain which is specified in parameter
     * 
     * @param businessDomain
     *            The instance of the BusinessDomain which contains the data to store
     * @return The instance of the businessDomain which has been updated
     */
    public static BusinessDomain update( BusinessDomain businessDomain )
    {
        _dao.store( businessDomain, _plugin );

        return businessDomain;
    }

    /**
     * Remove the businessDomain whose identifier is specified in parameter
     * 
     * @param nKey
     *            The businessDomain Id
     */
    public static void remove( int nKey )
    {
        _dao.delete( nKey, _plugin );
    }

    // /////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Returns an instance of a businessDomain whose identifier is specified in parameter
     * 
     * @param nKey
     *            The businessDomain primary key
     * @return an instance of BusinessDomain
     */
    public static BusinessDomain findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin );
    }

    /**
     * Load the data of all the businessDomain objects and returns them in form of a collection
     * 
     * @return the collection which contains the data of all the businessDomain objects
     */
    public static List<BusinessDomain> getBusinessDomainsList( )
    {
        return _dao.selectBusinessDomainsList( _plugin );
    }

    /**
     * Load the id of all the businessDomain objects and returns them in form of a collection
     * 
     * @return the collection which contains the id of all the businessDomain objects
     */
    public static List<Integer> getIdBusinessDomainsList( )
    {
        return _dao.selectIdBusinessDomainsList( _plugin );
    }
}
