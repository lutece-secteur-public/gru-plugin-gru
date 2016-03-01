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
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides Data Access methods for BusinessSector objects
 */

public final class BusinessSectorDAO implements IBusinessSectorDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_business_sector ) FROM gru_business_sector";
    private static final String SQL_QUERY_SELECT = "SELECT id_business_sector, name, description FROM gru_business_sector WHERE id_business_sector = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO gru_business_sector ( id_business_sector, name, description ) VALUES ( ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM gru_business_sector WHERE id_business_sector = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE gru_business_sector SET id_business_sector = ?, name = ?, description = ? WHERE id_business_sector = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_business_sector, name, description FROM gru_business_sector";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_business_sector FROM gru_business_sector";

    /**
     * Generates a new primary key
     * @param plugin The Plugin
     * @return The new primary key
     */
    public int newPrimaryKey( Plugin plugin)
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK , plugin  );
        daoUtil.executeQuery( );

        int nKey = 1;

        if( daoUtil.next( ) )
        {
                nKey = daoUtil.getInt( 1 ) + 1;
        }

        daoUtil.free();

        return nKey;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( BusinessSector businessSector, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        businessSector.setId( newPrimaryKey( plugin ) );

        daoUtil.setInt( 1, businessSector.getId( ) );
        daoUtil.setString( 2, businessSector.getName( ) );
        daoUtil.setString( 3, businessSector.getDescription( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public BusinessSector load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1 , nKey );
        daoUtil.executeQuery( );

        BusinessSector businessSector = null;

        if ( daoUtil.next( ) )
        {
            businessSector = new BusinessSector();
            businessSector.setId( daoUtil.getInt( 1 ) );
            businessSector.setName( daoUtil.getString( 2 ) );
            businessSector.setDescription( daoUtil.getString( 3 ) );
        }

        daoUtil.free( );
        return businessSector;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1 , nKey );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( BusinessSector businessSector, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );
        
        daoUtil.setInt( 1, businessSector.getId( ) );
        daoUtil.setString( 2, businessSector.getName( ) );
        daoUtil.setString( 3, businessSector.getDescription( ) );
        daoUtil.setInt( 4, businessSector.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<BusinessSector> selectBusinessSectorsList( Plugin plugin )
    {
        List<BusinessSector> businessSectorList = new ArrayList<BusinessSector>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            BusinessSector businessSector = new BusinessSector(  );
            
            businessSector.setId( daoUtil.getInt( 1 ) );
                businessSector.setName( daoUtil.getString( 2 ) );
                businessSector.setDescription( daoUtil.getString( 3 ) );

            businessSectorList.add( businessSector );
        }

        daoUtil.free( );
        return businessSectorList;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdBusinessSectorsList( Plugin plugin )
    {
            List<Integer> businessSectorList = new ArrayList<Integer>( );
            DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
            daoUtil.executeQuery(  );

            while ( daoUtil.next(  ) )
            {
                businessSectorList.add( daoUtil.getInt( 1 ) );
            }

            daoUtil.free( );
            return businessSectorList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ReferenceList selectBusinessSectors( Plugin plugin )
    {
        ReferenceList listSectors = new ReferenceList();
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            listSectors.addItem( daoUtil.getInt( 1 ) , daoUtil.getString( 2 ) );
        }

        daoUtil.free( );
        return listSectors;
    }
}