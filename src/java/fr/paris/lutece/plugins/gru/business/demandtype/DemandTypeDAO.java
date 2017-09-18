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
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides Data Access methods for DemandType objects
 */
public final class DemandTypeDAO implements IDemandTypeDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_demand_type ) FROM gru_demand_type";
    private static final String SQL_QUERY_SELECT = "SELECT a.id_demand_type, a.demand_type_id, a.title, b.name, c.name "
            + " FROM gru_demand_type a, gru_business_domain b, gru_business_sector c "
            + " WHERE id_demand_type = ? AND a.id_business_domain = b.id_business_domain AND b.id_business_sector = c.id_business_sector ";
    private static final String SQL_QUERY_INSERT = "INSERT INTO gru_demand_type ( id_demand_type, demand_type_id, title , id_business_domain ) VALUES ( ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM gru_demand_type WHERE id_demand_type = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE gru_demand_type SET id_demand_type = ?, demand_type_id = ?, title = ?, id_business_domain = ? WHERE id_demand_type = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT a.id_demand_type, a.demand_type_id, a.title, b.name, c.name  FROM gru_demand_type a "
            + "LEFT JOIN gru_business_domain b ON a.id_business_domain = b.id_business_domain "
            + "LEFT JOIN gru_business_sector c ON b.id_business_sector = c.id_business_sector;";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_demand_type FROM gru_demand_type";
    private static final String SQL_QUERY_SELECT_BY_TYPE_ID = "SELECT a.id_demand_type, a.demand_type_id, a.title, b.name, c.name  FROM gru_demand_type a "
            + "LEFT JOIN gru_business_domain b ON a.id_business_domain = b.id_business_domain "
            + "LEFT JOIN gru_business_sector c ON b.id_business_sector = c.id_business_sector WHERE a.demand_type_id = ?";

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
    public void insert( DemandType demandType, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        demandType.setId( newPrimaryKey( plugin ) );

        int nIndex = 1;
        daoUtil.setInt( nIndex++, demandType.getId( ) );
        daoUtil.setInt( nIndex++, demandType.getDemandTypeId( ) );
        daoUtil.setString( nIndex++, demandType.getTitle( ) );
        daoUtil.setInt( nIndex++, demandType.getBusinessDomainId( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public DemandType load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeQuery( );

        DemandType demandType = null;

        if ( daoUtil.next( ) )
        {
            int nIndex = 1;
            demandType = new DemandType( );
            demandType.setId( daoUtil.getInt( nIndex++ ) );
            demandType.setDemandTypeId( daoUtil.getInt( nIndex++ ) );
            demandType.setTitle( daoUtil.getString( nIndex++ ) );
            demandType.setBusinessDomain( daoUtil.getString( nIndex++ ) );
            demandType.setBusinessSector( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.free( );

        return demandType;
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
    public void store( DemandType demandType, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        int nIndex = 1;
        daoUtil.setInt( nIndex++, demandType.getId( ) );
        daoUtil.setInt( nIndex++, demandType.getDemandTypeId( ) );
        daoUtil.setString( nIndex++, demandType.getTitle( ) );
        daoUtil.setInt( nIndex++, demandType.getBusinessDomainId( ) );
        daoUtil.setInt( nIndex++, demandType.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<DemandType> selectDemandTypesList( Plugin plugin )
    {
        List<DemandType> demandTypeList = new ArrayList<DemandType>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            int nIndex = 1;
            DemandType demandType = new DemandType( );
            demandType.setId( daoUtil.getInt( nIndex++ ) );
            demandType.setDemandTypeId( daoUtil.getInt( nIndex++ ) );
            demandType.setTitle( daoUtil.getString( nIndex++ ) );
            demandType.setBusinessDomain( daoUtil.getString( nIndex++ ) );
            demandType.setBusinessSector( daoUtil.getString( nIndex++ ) );

            demandTypeList.add( demandType );
        }

        daoUtil.free( );

        return demandTypeList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdDemandTypesList( Plugin plugin )
    {
        List<Integer> demandTypeList = new ArrayList<Integer>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            demandTypeList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free( );

        return demandTypeList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ReferenceList selectDemandTypes( Plugin plugin )
    {
        ReferenceList list = new ReferenceList( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            list.addItem( daoUtil.getInt( 1 ), daoUtil.getString( 3 ) );
        }

        daoUtil.free( );

        return list;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public DemandType selectByTypeId( String strDemandTypeId, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_TYPE_ID, plugin );
        daoUtil.setString( 1, strDemandTypeId );
        daoUtil.executeQuery( );

        DemandType demandType = null;

        if ( daoUtil.next( ) )
        {
            int nIndex = 1;
            demandType = new DemandType( );
            demandType.setId( daoUtil.getInt( nIndex++ ) );
            demandType.setDemandTypeId( daoUtil.getInt( nIndex++ ) );
            demandType.setTitle( daoUtil.getString( nIndex++ ) );
            demandType.setBusinessDomain( daoUtil.getString( nIndex++ ) );
            demandType.setBusinessSector( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.free( );

        return demandType;
    }
}
