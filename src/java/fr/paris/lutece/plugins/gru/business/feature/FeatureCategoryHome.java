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
package fr.paris.lutece.plugins.gru.business.feature;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

import java.util.List;

/**
 * This class provides instances management methods (create, find, ...) for FeatureCategory objects
 */
public final class FeatureCategoryHome
{
    // Static variable pointed at the DAO instance
    private static IFeatureCategoryDAO _dao = SpringContextService.getBean( "gru.featureCategoryDAO" );
    private static Plugin _plugin = PluginService.getPlugin( "gru" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private FeatureCategoryHome( )
    {
    }

    /**
     * Create an instance of the featureCategory class
     * 
     * @param featureCategory
     *            The instance of the FeatureCategory which contains the informations to store
     * @return The instance of featureCategory which has been created with its primary key.
     */
    public static FeatureCategory create( FeatureCategory featureCategory )
    {
        _dao.insert( featureCategory, _plugin );

        return featureCategory;
    }

    /**
     * Update of the featureCategory which is specified in parameter
     * 
     * @param featureCategory
     *            The instance of the FeatureCategory which contains the data to store
     * @return The instance of the featureCategory which has been updated
     */
    public static FeatureCategory update( FeatureCategory featureCategory )
    {
        _dao.store( featureCategory, _plugin );

        return featureCategory;
    }

    /**
     * Remove the featureCategory whose identifier is specified in parameter
     * 
     * @param nKey
     *            The featureCategory Id
     */
    public static void remove( int nKey )
    {
        _dao.delete( nKey, _plugin );
    }

    // /////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Returns an instance of a featureCategory whose identifier is specified in parameter
     * 
     * @param nKey
     *            The featureCategory primary key
     * @return an instance of FeatureCategory
     */
    public static FeatureCategory findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin );
    }

    /**
     * Load the data of all the featureCategory objects and returns them in form of a collection
     * 
     * @return the collection which contains the data of all the featureCategory objects
     */
    public static List<FeatureCategory> getFeatureCategorysList( )
    {
        return _dao.selectFeatureCategorysList( _plugin );
    }

    /**
     * Load the id of all the featureCategory objects and returns them in form of a collection
     * 
     * @return the collection which contains the id of all the featureCategory objects
     */
    public static List<Integer> getIdFeatureCategorysList( )
    {
        return _dao.selectIdFeatureCategorysList( _plugin );
    }

    /**
     * Gives the list of categories as a {@code ReferenceList}
     * 
     * @return the {@code ReferenceList}
     */
    public static ReferenceList getCategoriesList( )
    {
        return _dao.selectCategoriesList( _plugin );
    }
}
