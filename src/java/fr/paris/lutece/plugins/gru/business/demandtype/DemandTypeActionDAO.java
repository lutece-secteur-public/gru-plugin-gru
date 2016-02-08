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
package fr.paris.lutece.plugins.gru.business.demandtype;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * This class provides Data Access methods for DemandTypeAction objects
 */
public final class DemandTypeActionDAO implements IDemandTypeActionDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_demand_type_action ) FROM gru_demand_type_action";
    private static final String SQL_QUERY_SELECT = "SELECT a.id_demand_type_action, a.action_link, a.action_label, a.id_demand_type, b.title " +
        " FROM gru_demand_type_action a, gru_demand_type b WHERE a.id_demand_type = b.id_demand_type AND id_demand_type_action = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO gru_demand_type_action ( id_demand_type_action, action_link, action_label, id_demand_type ) VALUES ( ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM gru_demand_type_action WHERE id_demand_type_action = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE gru_demand_type_action SET id_demand_type_action = ?, action_link = ?, action_label = ?, id_demand_type = ? WHERE id_demand_type_action = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT a.id_demand_type_action, a.action_link, a.action_label, a.id_demand_type, b.title " +
        " FROM gru_demand_type_action a, gru_demand_type b WHERE a.id_demand_type = b.id_demand_type ";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_demand_type_action FROM gru_demand_type_action";
    private static final String SQL_QUERY_SELECT_BY_TYPE = "SELECT a.id_demand_type_action, a.action_link, a.action_label, a.id_demand_type, b.title " +
        " FROM gru_demand_type_action a, gru_demand_type b WHERE a.id_demand_type = b.id_demand_type AND a.id_demand_type = ?";

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
    public void insert( DemandTypeAction demandTypeAction, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        demandTypeAction.setId( newPrimaryKey( plugin ) );

        daoUtil.setInt( 1, demandTypeAction.getId(  ) );
        daoUtil.setString( 2, demandTypeAction.getLink(  ) );
        daoUtil.setString( 3, demandTypeAction.getLabel(  ) );
        daoUtil.setInt( 4, demandTypeAction.getIdDemandType(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public DemandTypeAction load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeQuery(  );

        DemandTypeAction demandTypeAction = null;

        if ( daoUtil.next(  ) )
        {
            demandTypeAction = new DemandTypeAction(  );
            demandTypeAction.setId( daoUtil.getInt( 1 ) );
            demandTypeAction.setLink( daoUtil.getString( 2 ) );
            demandTypeAction.setLabel( daoUtil.getString( 3 ) );
            demandTypeAction.setIdDemandType( daoUtil.getInt( 4 ) );
            demandTypeAction.setDemandType( daoUtil.getString( 5 ) );
        }

        daoUtil.free(  );

        return demandTypeAction;
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
    public void store( DemandTypeAction demandTypeAction, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        daoUtil.setInt( 1, demandTypeAction.getId(  ) );
        daoUtil.setString( 2, demandTypeAction.getLink(  ) );
        daoUtil.setString( 3, demandTypeAction.getLabel(  ) );
        daoUtil.setInt( 4, demandTypeAction.getIdDemandType(  ) );
        daoUtil.setInt( 5, demandTypeAction.getId(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<DemandTypeAction> selectDemandTypeActionsList( Plugin plugin )
    {
        List<DemandTypeAction> demandTypeActionList = new ArrayList<DemandTypeAction>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            DemandTypeAction demandTypeAction = new DemandTypeAction(  );

            demandTypeAction.setId( daoUtil.getInt( 1 ) );
            demandTypeAction.setLink( daoUtil.getString( 2 ) );
            demandTypeAction.setLabel( daoUtil.getString( 3 ) );
            demandTypeAction.setIdDemandType( daoUtil.getInt( 4 ) );
            demandTypeAction.setDemandType( daoUtil.getString( 5 ) );

            demandTypeActionList.add( demandTypeAction );
        }

        daoUtil.free(  );

        return demandTypeActionList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdDemandTypeActionsList( Plugin plugin )
    {
        List<Integer> demandTypeActionList = new ArrayList<Integer>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            demandTypeActionList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free(  );

        return demandTypeActionList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<DemandTypeAction> selectActionsByType( int nDemandTypeId, Plugin plugin )
    {
        List<DemandTypeAction> demandTypeActionList = new ArrayList<DemandTypeAction>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_TYPE, plugin );
        daoUtil.setInt( 1, nDemandTypeId );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            DemandTypeAction demandTypeAction = new DemandTypeAction(  );

            demandTypeAction.setId( daoUtil.getInt( 1 ) );
            demandTypeAction.setLink( daoUtil.getString( 2 ) );
            demandTypeAction.setLabel( daoUtil.getString( 3 ) );
            demandTypeAction.setIdDemandType( daoUtil.getInt( 4 ) );
            demandTypeAction.setDemandType( daoUtil.getString( 5 ) );

            demandTypeActionList.add( demandTypeAction );
        }

        daoUtil.free(  );

        return demandTypeActionList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ReferenceList selectActionsReferenceList( Plugin plugin )
    {
        ReferenceList list = new ReferenceList(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            list.addItem( daoUtil.getInt( 1 ), daoUtil.getString( 5 ) + " - " + daoUtil.getString( 3 ) );
        }

        daoUtil.free(  );

        return list;
    }
}
