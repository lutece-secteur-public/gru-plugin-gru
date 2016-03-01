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
package fr.paris.lutece.plugins.gru.business.domain;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

import java.util.List;


/**
 * This class provides instances management methods (create, find, ...) for BusinessSector objects
 */
public final class BusinessSectorHome
{
    // Static variable pointed at the DAO instance
    private static IBusinessSectorDAO _dao = SpringContextService.getBean( "gru.businessSectorDAO" );
    private static Plugin _plugin = PluginService.getPlugin( "gru" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private BusinessSectorHome(  )
    {
    }

    /**
     * Create an instance of the businessSector class
     * @param businessSector The instance of the BusinessSector which contains the informations to store
     * @return The  instance of businessSector which has been created with its primary key.
     */
    public static BusinessSector create( BusinessSector businessSector )
    {
        _dao.insert( businessSector, _plugin );

        return businessSector;
    }

    /**
     * Update of the businessSector which is specified in parameter
     * @param businessSector The instance of the BusinessSector which contains the data to store
     * @return The instance of the  businessSector which has been updated
     */
    public static BusinessSector update( BusinessSector businessSector )
    {
        _dao.store( businessSector, _plugin );

        return businessSector;
    }

    /**
     * Remove the businessSector whose identifier is specified in parameter
     * @param nKey The businessSector Id
     */
    public static void remove( int nKey )
    {
        _dao.delete( nKey, _plugin );
    }

    ///////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Returns an instance of a businessSector whose identifier is specified in parameter
     * @param nKey The businessSector primary key
     * @return an instance of BusinessSector
     */
    public static BusinessSector findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin );
    }

    /**
     * Load the data of all the businessSector objects and returns them in form of a collection
     * @return the collection which contains the data of all the businessSector objects
     */
    public static List<BusinessSector> getBusinessSectorsList(  )
    {
        return _dao.selectBusinessSectorsList( _plugin );
    }

    /**
     * Load the id of all the businessSector objects and returns them in form of a collection
     * @return the collection which contains the id of all the businessSector objects
     */
    public static List<Integer> getIdBusinessSectorsList(  )
    {
        return _dao.selectIdBusinessSectorsList( _plugin );
    }

    /**
     * Gets all business sectors as a reference list
     * @return all business sectors as a reference list
     */
    public static ReferenceList getBusinessSectors(  )
    {
        return _dao.selectBusinessSectors( _plugin );
    }
}
