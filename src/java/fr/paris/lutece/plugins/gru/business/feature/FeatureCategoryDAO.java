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
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides Data Access methods for FeatureCategory objects
 */
public final class FeatureCategoryDAO implements IFeatureCategoryDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_feature_category ) FROM gru_feature_category";
    private static final String SQL_QUERY_SELECT = "SELECT id_feature_category, name, description, id_order, category_icon, color FROM gru_feature_category WHERE id_feature_category = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO gru_feature_category ( id_feature_category, name, description, id_order, category_icon, color ) VALUES ( ?, ?, ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM gru_feature_category WHERE id_feature_category = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE gru_feature_category SET id_feature_category = ?, name = ?, description = ?, id_order = ?, category_icon = ?, color = ? WHERE id_feature_category = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_feature_category, name, description, id_order, category_icon, color FROM gru_feature_category";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_feature_category FROM gru_feature_category";

    /**
     * Generates a new primary key
     * 
     * @param plugin
     *            The Plugin
     * @return The new primary key
     */
    public int newPrimaryKey( Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK, plugin );
        daoUtil.executeQuery( );

        int nKey = 1;

        if ( daoUtil.next( ) )
        {
            nKey = daoUtil.getInt( 1 ) + 1;
        }

        daoUtil.free( );

        return nKey;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( FeatureCategory category, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        category.setId( newPrimaryKey( plugin ) );

        daoUtil.setInt( 1, category.getId( ) );
        daoUtil.setString( 2, category.getName( ) );
        daoUtil.setString( 3, category.getDescription( ) );
        daoUtil.setInt( 4, category.getIdOrder( ) );
        daoUtil.setString( 5, category.getIcon( ) );
        daoUtil.setString( 6, category.getColor( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public FeatureCategory load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeQuery( );

        FeatureCategory category = null;

        if ( daoUtil.next( ) )
        {
            category = new FeatureCategory( );
            category.setId( daoUtil.getInt( 1 ) );
            category.setName( daoUtil.getString( 2 ) );
            category.setDescription( daoUtil.getString( 3 ) );
            category.setIdOrder( daoUtil.getInt( 4 ) );
            category.setIcon( daoUtil.getString( 5 ) );
            category.setColor( daoUtil.getString( 6 ) );
        }

        daoUtil.free( );

        return category;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( FeatureCategory category, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        daoUtil.setInt( 1, category.getId( ) );
        daoUtil.setString( 2, category.getName( ) );
        daoUtil.setString( 3, category.getDescription( ) );
        daoUtil.setInt( 4, category.getIdOrder( ) );
        daoUtil.setString( 5, category.getIcon( ) );
        daoUtil.setString( 6, category.getColor( ) );
        daoUtil.setInt( 7, category.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<FeatureCategory> selectFeatureCategorysList( Plugin plugin )
    {
        List<FeatureCategory> listCategories = new ArrayList<FeatureCategory>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            FeatureCategory category = new FeatureCategory( );

            category.setId( daoUtil.getInt( 1 ) );
            category.setName( daoUtil.getString( 2 ) );
            category.setDescription( daoUtil.getString( 3 ) );
            category.setIdOrder( daoUtil.getInt( 4 ) );
            category.setIcon( daoUtil.getString( 5 ) );
            category.setColor( daoUtil.getString( 6 ) );

            listCategories.add( category );
        }

        daoUtil.free( );

        return listCategories;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdFeatureCategorysList( Plugin plugin )
    {
        List<Integer> listCategories = new ArrayList<Integer>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            listCategories.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free( );

        return listCategories;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ReferenceList selectCategoriesList( Plugin plugin )
    {
        ReferenceList listCategories = new ReferenceList( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            listCategories.addItem( daoUtil.getInt( 1 ), daoUtil.getString( 2 ) );
        }

        daoUtil.free( );

        return listCategories;
    }
}
