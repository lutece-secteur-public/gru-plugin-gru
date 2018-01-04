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
package fr.paris.lutece.plugins.gru.business.demandtype;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

import java.util.List;

/**
 * This class provides instances management methods (create, find, ...) for DemandTypeAction objects
 */
public final class DemandTypeActionHome
{
    // Static variable pointed at the DAO instance
    private static IDemandTypeActionDAO _dao = SpringContextService.getBean( "gru.demandTypeActionDAO" );
    private static Plugin _plugin = PluginService.getPlugin( "gru" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private DemandTypeActionHome( )
    {
    }

    /**
     * Create an instance of the demandTypeAction class
     * 
     * @param demandTypeAction
     *            The instance of the DemandTypeAction which contains the informations to store
     * @return The instance of demandTypeAction which has been created with its primary key.
     */
    public static DemandTypeAction create( DemandTypeAction demandTypeAction )
    {
        _dao.insert( demandTypeAction, _plugin );

        return demandTypeAction;
    }

    /**
     * Update of the demandTypeAction which is specified in parameter
     * 
     * @param demandTypeAction
     *            The instance of the DemandTypeAction which contains the data to store
     * @return The instance of the demandTypeAction which has been updated
     */
    public static DemandTypeAction update( DemandTypeAction demandTypeAction )
    {
        _dao.store( demandTypeAction, _plugin );

        return demandTypeAction;
    }

    /**
     * Remove the demandTypeAction whose identifier is specified in parameter
     * 
     * @param nKey
     *            The demandTypeAction Id
     */
    public static void remove( int nKey )
    {
        _dao.delete( nKey, _plugin );
    }

    // /////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Returns an instance of a demandTypeAction whose identifier is specified in parameter
     * 
     * @param nKey
     *            The demandTypeAction primary key
     * @return an instance of DemandTypeAction
     */
    public static DemandTypeAction findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin );
    }

    /**
     * Load the data of all the demandTypeAction objects and returns them in form of a collection
     * 
     * @return the collection which contains the data of all the demandTypeAction objects
     */
    public static List<DemandTypeAction> getDemandTypeActionsList( )
    {
        return _dao.selectDemandTypeActionsList( _plugin );
    }

    /**
     * Load the id of all the demandTypeAction objects and returns them in form of a collection
     * 
     * @return the collection which contains the id of all the demandTypeAction objects
     */
    public static List<Integer> getIdDemandTypeActionsList( )
    {
        return _dao.selectIdDemandTypeActionsList( _plugin );
    }

    /**
     * Gets a list of action by demand type
     * 
     * @param nDemandTypeId
     *            The demand type id
     * @return The list
     */
    public static List<DemandTypeAction> getActionsByType( int nDemandTypeId )
    {
        return _dao.selectActionsByType( nDemandTypeId, _plugin );
    }

    /**
     * Gets all actions as a ReferenceList
     * 
     * @return The ReferenceList
     */
    public static ReferenceList getActions( )
    {
        return _dao.selectActionsReferenceList( _plugin );
    }
}
