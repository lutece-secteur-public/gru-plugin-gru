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
package fr.paris.lutece.plugins.gru.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * This class provides Data Access methods for ExtrasAttributes objects
 */
public final class ExtrasAttributesDAO implements IExtrasAttributesDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_extras_attributes ) FROM gru_customer_extras_attributes";
    private static final String SQL_QUERY_SELECT = "SELECT id_extras_attributes, attribute_key, name, description FROM gru_customer_extras_attributes WHERE id_extras_attributes = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO gru_customer_extras_attributes ( id_extras_attributes, attribute_key, name, description ) VALUES ( ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM gru_customer_extras_attributes WHERE id_extras_attributes = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE gru_customer_extras_attributes SET id_extras_attributes = ?, attribute_key = ?, name = ?, description = ? WHERE id_extras_attributes = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_extras_attributes, attribute_key, name, description FROM gru_customer_extras_attributes";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_extras_attributes FROM gru_customer_extras_attributes";

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
    public void insert( ExtrasAttributes extrasAttributes, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        extrasAttributes.setId( newPrimaryKey( plugin ) );

        daoUtil.setInt( 1, extrasAttributes.getId(  ) );
        daoUtil.setString( 2, extrasAttributes.getAttributeKey(  ) );
        daoUtil.setString( 3, extrasAttributes.getName(  ) );
        daoUtil.setString( 4, extrasAttributes.getDescription(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ExtrasAttributes load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeQuery(  );

        ExtrasAttributes extrasAttributes = null;

        if ( daoUtil.next(  ) )
        {
            extrasAttributes = new ExtrasAttributes(  );
            extrasAttributes.setId( daoUtil.getInt( 1 ) );
            extrasAttributes.setAttributeKey( daoUtil.getString( 2 ) );
            extrasAttributes.setName( daoUtil.getString( 3 ) );
            extrasAttributes.setDescription( daoUtil.getString( 4 ) );
        }

        daoUtil.free(  );

        return extrasAttributes;
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
    public void store( ExtrasAttributes extrasAttributes, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        daoUtil.setInt( 1, extrasAttributes.getId(  ) );
        daoUtil.setString( 2, extrasAttributes.getAttributeKey(  ) );
        daoUtil.setString( 3, extrasAttributes.getName(  ) );
        daoUtil.setString( 4, extrasAttributes.getDescription(  ) );
        daoUtil.setInt( 5, extrasAttributes.getId(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<ExtrasAttributes> selectExtrasAttributessList( Plugin plugin )
    {
        List<ExtrasAttributes> extrasAttributesList = new ArrayList<ExtrasAttributes>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            ExtrasAttributes extrasAttributes = new ExtrasAttributes(  );

            extrasAttributes.setId( daoUtil.getInt( 1 ) );
            extrasAttributes.setAttributeKey( daoUtil.getString( 2 ) );
            extrasAttributes.setName( daoUtil.getString( 3 ) );
            extrasAttributes.setDescription( daoUtil.getString( 4 ) );

            extrasAttributesList.add( extrasAttributes );
        }

        daoUtil.free(  );

        return extrasAttributesList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdExtrasAttributessList( Plugin plugin )
    {
        List<Integer> extrasAttributesList = new ArrayList<Integer>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            extrasAttributesList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free(  );

        return extrasAttributesList;
    }
}
