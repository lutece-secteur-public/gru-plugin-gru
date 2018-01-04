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
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides Data Access methods for BusinessDomain objects
 */
public final class BusinessDomainDAO implements IBusinessDomainDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_business_domain ) FROM gru_business_domain";
    private static final String SQL_QUERY_SELECT = "SELECT a.id_business_domain, a.name, a.description, a.id_business_sector, b.name "
            + " FROM gru_business_domain a, gru_business_sector b " + " WHERE a.id_business_domain = ? AND a.id_business_sector = b.id_business_sector";
    private static final String SQL_QUERY_INSERT = "INSERT INTO gru_business_domain ( id_business_domain, name, description, id_business_sector ) VALUES ( ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM gru_business_domain WHERE id_business_domain = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE gru_business_domain SET id_business_domain = ?, name = ?, description = ?, id_business_sector = ? WHERE id_business_domain = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT a.id_business_domain, a.name, a.description, a.id_business_sector, b.name "
            + " FROM gru_business_domain a, gru_business_sector b " + " WHERE a.id_business_sector = b.id_business_sector";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_business_domain FROM gru_business_domain";

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
    public void insert( BusinessDomain businessDomain, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        businessDomain.setId( newPrimaryKey( plugin ) );

        int nIndex = 1;
        daoUtil.setInt( nIndex++, businessDomain.getId( ) );
        daoUtil.setString( nIndex++, businessDomain.getName( ) );
        daoUtil.setString( nIndex++, businessDomain.getDescription( ) );
        daoUtil.setInt( nIndex++, businessDomain.getIdBusinessSector( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public BusinessDomain load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeQuery( );

        BusinessDomain businessDomain = null;

        if ( daoUtil.next( ) )
        {
            int nIndex = 1;
            businessDomain = new BusinessDomain( );
            businessDomain.setId( daoUtil.getInt( nIndex++ ) );
            businessDomain.setName( daoUtil.getString( nIndex++ ) );
            businessDomain.setDescription( daoUtil.getString( nIndex++ ) );
            businessDomain.setIdBusinessSector( daoUtil.getInt( nIndex++ ) );
            businessDomain.setBusinessSector( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.free( );

        return businessDomain;
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
    public void store( BusinessDomain businessDomain, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        int nIndex = 1;
        daoUtil.setInt( nIndex++, businessDomain.getId( ) );
        daoUtil.setString( nIndex++, businessDomain.getName( ) );
        daoUtil.setString( nIndex++, businessDomain.getDescription( ) );
        daoUtil.setInt( nIndex++, businessDomain.getIdBusinessSector( ) );
        daoUtil.setInt( nIndex++, businessDomain.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<BusinessDomain> selectBusinessDomainsList( Plugin plugin )
    {
        List<BusinessDomain> businessDomainList = new ArrayList<BusinessDomain>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            int nIndex = 1;
            BusinessDomain businessDomain = new BusinessDomain( );
            businessDomain.setId( daoUtil.getInt( nIndex++ ) );
            businessDomain.setName( daoUtil.getString( nIndex++ ) );
            businessDomain.setDescription( daoUtil.getString( nIndex++ ) );
            businessDomain.setIdBusinessSector( daoUtil.getInt( nIndex++ ) );
            businessDomain.setBusinessSector( daoUtil.getString( nIndex++ ) );

            businessDomainList.add( businessDomain );
        }

        daoUtil.free( );

        return businessDomainList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdBusinessDomainsList( Plugin plugin )
    {
        List<Integer> businessDomainList = new ArrayList<Integer>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            businessDomainList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free( );

        return businessDomainList;
    }
}
