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

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.List;


/**
 * This class provides instances management methods (create, find, ...) for Feature objects
 */
public final class FeatureHome
{
    // Static variable pointed at the DAO instance
    private static IFeatureDAO _dao = SpringContextService.getBean( "gru.featureDAO" );
    private static Plugin _plugin = PluginService.getPlugin( "gru" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private FeatureHome(  )
    {
    }

    /**
     * Create an instance of the feature class
     * @param feature The instance of the Feature which contains the informations to store
     * @return The  instance of feature which has been created with its primary key.
     */
    public static Feature create( Feature feature )
    {
        _dao.insert( feature, _plugin );

        return feature;
    }

    /**
     * Update of the feature which is specified in parameter
     * @param feature The instance of the Feature which contains the data to store
     * @return The instance of the  feature which has been updated
     */
    public static Feature update( Feature feature )
    {
        _dao.store( feature, _plugin );

        return feature;
    }

    /**
     * Remove the feature whose identifier is specified in parameter
     * @param nKey The feature Id
     */
    public static void remove( int nKey )
    {
        _dao.delete( nKey, _plugin );
    }

    ///////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Returns an instance of a feature whose identifier is specified in parameter
     * @param nKey The feature primary key
     * @return an instance of Feature
     */
    public static Feature findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin );
    }

    /**
     * Load the data of all the feature objects and returns them in form of a collection
     * @return the collection which contains the data of all the feature objects
     */
    public static List<Feature> getFeaturesList(  )
    {
        return _dao.selectFeaturesList( _plugin );
    }

    /**
     * Gets all features for a given category
     * @param nCategory The category
     * @return The list
     */
    public static List<Feature> getFeaturesListByCategory( int nCategory )
    {
        return _dao.selectFeaturesListByCategory( nCategory, _plugin );
    }

    /**
     * Load the id of all the feature objects and returns them in form of a collection
     * @return the collection which contains the id of all the feature objects
     */
    public static List<Integer> getIdFeaturesList(  )
    {
        return _dao.selectIdFeaturesList( _plugin );
    }
}
