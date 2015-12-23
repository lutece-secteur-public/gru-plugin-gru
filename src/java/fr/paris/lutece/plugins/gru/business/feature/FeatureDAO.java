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
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * This class provides Data Access methods for Feature objects
 */
public final class FeatureDAO implements IFeatureDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_feature ) FROM gru_feature";
    private static final String SQL_QUERY_SELECT = "SELECT id_feature, name, link, link_customer_params, target, id_category, id_order, display_level FROM gru_feature WHERE id_feature = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO gru_feature ( id_feature, name, link, link_customer_params, target, id_category, id_order, display_level ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM gru_feature WHERE id_feature = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE gru_feature SET id_feature = ?, name = ?, link = ?, link_customer_params = ?, target = ?, id_category = ?, id_order = ?, display_level = ? WHERE id_feature = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT a.id_feature, a.name, a.link, a.link_customer_params, a.target, a.id_category, b.name, a.id_order, a.display_level " +
        " FROM gru_feature a, gru_feature_category b WHERE a.id_category = b.id_feature_category ORDER BY a.id_order";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_feature FROM gru_feature";
    private static final String SQL_QUERY_SELECT_BY_CATEGORY = "SELECT id_feature, name, link, link_customer_params, target, id_category, id_order, display_level FROM gru_feature WHERE id_category = ? ORDER BY id_order";

    /**
     * Generates a new primary key
     * @param plugin The Plugin
     * @return The new primary key
     */
    public int newPrimaryKey( Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK, plugin );
        daoUtil.executeQuery(  );

        int nKey = 1;

        if ( daoUtil.next(  ) )
        {
            nKey = daoUtil.getInt( 1 ) + 1;
        }

        daoUtil.free(  );

        return nKey;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( Feature feature, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        feature.setId( newPrimaryKey( plugin ) );

        int nIndex = 1;
        daoUtil.setInt( nIndex++, feature.getId(  ) );
        daoUtil.setString( nIndex++, feature.getName(  ) );
        daoUtil.setString( nIndex++, feature.getLink(  ) );
        daoUtil.setString( nIndex++, feature.getLinkCustomerParams(  ) );
        daoUtil.setInt( nIndex++, feature.getTarget(  ) );
        daoUtil.setInt( nIndex++, feature.getIdCategory(  ) );
        daoUtil.setInt( nIndex++, feature.getIdOrder(  ) );
        daoUtil.setInt( nIndex++, feature.getDisplayLevel());

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Feature load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeQuery(  );

        Feature feature = null;

        if ( daoUtil.next(  ) )
        {
            feature = new Feature(  );

            int nIndex = 1;
            feature.setId( daoUtil.getInt( nIndex++ ) );
            feature.setName( daoUtil.getString( nIndex++ ) );
            feature.setLink( daoUtil.getString( nIndex++ ) );
            feature.setLinkCustomerParams( daoUtil.getString( nIndex++ ) );
            feature.setTarget( daoUtil.getInt( nIndex++ ) );
            feature.setIdCategory( daoUtil.getInt( nIndex++ ) );
            feature.setIdOrder( daoUtil.getInt( nIndex++ ) );
            feature.setDisplayLevel(daoUtil.getInt( nIndex++ ) );
        }

        daoUtil.free(  );

        return feature;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( Feature feature, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        int nIndex = 1;
        daoUtil.setInt( nIndex++, feature.getId(  ) );
        daoUtil.setString( nIndex++, feature.getName(  ) );
        daoUtil.setString( nIndex++, feature.getLink(  ) );
        daoUtil.setString( nIndex++, feature.getLinkCustomerParams(  ) );
        daoUtil.setInt( nIndex++, feature.getTarget(  ) );
        daoUtil.setInt( nIndex++, feature.getIdCategory(  ) );
        daoUtil.setInt( nIndex++, feature.getIdOrder(  ) );
        daoUtil.setInt( nIndex++, feature.getDisplayLevel());
        daoUtil.setInt( nIndex++, feature.getId(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Feature> selectFeaturesList( Plugin plugin )
    {
        List<Feature> featureList = new ArrayList<Feature>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            Feature feature = new Feature(  );

            int nIndex = 1;
            feature.setId( daoUtil.getInt( nIndex++ ) );
            feature.setName( daoUtil.getString( nIndex++ ) );
            feature.setLink( daoUtil.getString( nIndex++ ) );
            feature.setLinkCustomerParams( daoUtil.getString( nIndex++ ) );
            feature.setTarget( daoUtil.getInt( nIndex++ ) );
            feature.setIdCategory( daoUtil.getInt( nIndex++ ) );
            feature.setCategory( daoUtil.getString( nIndex++ ) );
            feature.setIdOrder( daoUtil.getInt( nIndex++ ) );
            feature.setDisplayLevel(daoUtil.getInt( nIndex++ ) );

            featureList.add( feature );
        }

        daoUtil.free(  );

        return featureList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Feature> selectFeaturesListByCategory( int nCategory, Plugin plugin )
    {
        List<Feature> featureList = new ArrayList<Feature>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_CATEGORY, plugin );
        daoUtil.setInt( 1, nCategory );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            Feature feature = new Feature(  );

            int nIndex = 1;
            feature.setId( daoUtil.getInt( nIndex++ ) );
            feature.setName( daoUtil.getString( nIndex++ ) );
            feature.setLink( daoUtil.getString( nIndex++ ) );
            feature.setLinkCustomerParams( daoUtil.getString( nIndex++ ) );
            feature.setTarget( daoUtil.getInt( nIndex++ ) );
            feature.setIdCategory( daoUtil.getInt( nIndex++ ) );
            feature.setIdOrder( daoUtil.getInt( nIndex++ ) );
            feature.setDisplayLevel(daoUtil.getInt( nIndex++ ) );

            featureList.add( feature );
        }

        daoUtil.free(  );

        return featureList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdFeaturesList( Plugin plugin )
    {
        List<Integer> featureList = new ArrayList<Integer>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            featureList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free(  );

        return featureList;
    }
}
