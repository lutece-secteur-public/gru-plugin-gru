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
package fr.paris.lutece.plugins.gru.business.customer;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * This class provides Data Access methods for Customer objects
 */
public final class CustomerDAO implements ICustomerDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_customer ) FROM gru_customer";
    private static final String SQL_QUERY_SELECT = "SELECT id_customer, id_title, firstname, lastname, has_account, account_login, account_guid, email, is_email_verified, mobile_phone, is_mobile_phone_verified, extras_attributes FROM gru_customer WHERE id_customer = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO gru_customer ( id_customer, id_title, firstname, lastname, has_account, account_login, account_guid, email, is_email_verified, mobile_phone, is_mobile_phone_verified, extras_attributes ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM gru_customer WHERE id_customer = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE gru_customer SET id_customer = ?, id_title = ?, firstname = ?, lastname = ?, has_account = ?, account_login = ?, account_guid = ?, email = ?, is_email_verified = ?, mobile_phone = ?, is_mobile_phone_verified = ?, extras_attributes = ? WHERE id_customer = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_customer, id_title, firstname, lastname, has_account, account_login, account_guid, email, is_email_verified, mobile_phone, is_mobile_phone_verified, extras_attributes FROM gru_customer";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_customer FROM gru_customer";
    private static final String SQL_QUERY_SELECT_BY_GUID = "SELECT id_customer, id_title, firstname, lastname, has_account, account_login, account_guid, email, is_email_verified, mobile_phone, is_mobile_phone_verified, extras_attributes FROM gru_customer WHERE account_guid = ?";

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
    public int insert( Customer customer, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        customer.setId( newPrimaryKey( plugin ) );

        daoUtil.setInt( 1, customer.getId(  ) );
        daoUtil.setInt( 2, customer.getIdTitle(  ) );
        daoUtil.setString( 3, customer.getFirstname(  ) );
        daoUtil.setString( 4, customer.getLastname(  ) );
        daoUtil.setBoolean( 5, customer.getHasAccount(  ) );
        daoUtil.setString( 6, customer.getAccountLogin(  ) );
        daoUtil.setString( 7, customer.getAccountGuid(  ) );
        daoUtil.setString( 8, customer.getEmail(  ) );
        daoUtil.setBoolean( 9, customer.getIsEmailVerified(  ) );
        daoUtil.setString( 10, customer.getMobilePhone(  ) );
        daoUtil.setBoolean( 11, customer.getIsMobilePhoneVerified(  ) );
        daoUtil.setString( 12, customer.getExtrasAttributes(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );

        return customer.getId(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Customer load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeQuery(  );

        Customer customer = null;

        if ( daoUtil.next(  ) )
        {
            customer = new Customer(  );
            customer.setId( daoUtil.getInt( 1 ) );
            customer.setIdTitle( daoUtil.getInt( 2 ) );
            customer.setFirstname( daoUtil.getString( 3 ) );
            customer.setLastname( daoUtil.getString( 4 ) );
            customer.setHasAccount( daoUtil.getBoolean( 5 ) );
            customer.setAccountLogin( daoUtil.getString( 6 ) );
            customer.setAccountGuid( daoUtil.getString( 7 ) );
            customer.setEmail( daoUtil.getString( 8 ) );
            customer.setIsEmailVerified( daoUtil.getBoolean( 9 ) );
            customer.setMobilePhone( daoUtil.getString( 10 ) );
            customer.setIsMobilePhoneVerified( daoUtil.getBoolean( 11 ) );
            customer.setExtrasAttributes( daoUtil.getString( 12 ) );
        }

        daoUtil.free(  );

        return customer;
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
    public void store( Customer customer, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        daoUtil.setInt( 1, customer.getId(  ) );
        daoUtil.setInt( 2, customer.getIdTitle(  ) );
        daoUtil.setString( 3, customer.getFirstname(  ) );
        daoUtil.setString( 4, customer.getLastname(  ) );
        daoUtil.setBoolean( 5, customer.getHasAccount(  ) );
        daoUtil.setString( 6, customer.getAccountLogin(  ) );
        daoUtil.setString( 7, customer.getAccountGuid(  ) );
        daoUtil.setString( 8, customer.getEmail(  ) );
        daoUtil.setBoolean( 9, customer.getIsEmailVerified(  ) );
        daoUtil.setString( 10, customer.getMobilePhone(  ) );
        daoUtil.setBoolean( 11, customer.getIsMobilePhoneVerified(  ) );
        daoUtil.setString( 12, customer.getExtrasAttributes(  ) );
        daoUtil.setInt( 13, customer.getId(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Customer> selectCustomersList( Plugin plugin )
    {
        List<Customer> customerList = new ArrayList<Customer>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            Customer customer = new Customer(  );

            customer.setId( daoUtil.getInt( 1 ) );
            customer.setIdTitle( daoUtil.getInt( 2 ) );
            customer.setFirstname( daoUtil.getString( 3 ) );
            customer.setLastname( daoUtil.getString( 4 ) );
            customer.setHasAccount( daoUtil.getBoolean( 5 ) );
            customer.setAccountLogin( daoUtil.getString( 6 ) );
            customer.setAccountGuid( daoUtil.getString( 7 ) );
            customer.setEmail( daoUtil.getString( 8 ) );
            customer.setIsEmailVerified( daoUtil.getBoolean( 9 ) );
            customer.setMobilePhone( daoUtil.getString( 10 ) );
            customer.setIsMobilePhoneVerified( daoUtil.getBoolean( 11 ) );
            customer.setExtrasAttributes( daoUtil.getString( 12 ) );

            customerList.add( customer );
        }

        daoUtil.free(  );

        return customerList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdCustomersList( Plugin plugin )
    {
        List<Integer> customerList = new ArrayList<Integer>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            customerList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free(  );

        return customerList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Customer selectByGuid( String strGuid, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_GUID, plugin );
        daoUtil.setString( 1, strGuid );
        daoUtil.executeQuery(  );

        Customer customer = null;

        if ( daoUtil.next(  ) )
        {
            customer = new Customer(  );
            customer.setId( daoUtil.getInt( 1 ) );
            customer.setIdTitle( daoUtil.getInt( 2 ) );
            customer.setFirstname( daoUtil.getString( 3 ) );
            customer.setLastname( daoUtil.getString( 4 ) );
            customer.setHasAccount( daoUtil.getBoolean( 5 ) );
            customer.setAccountLogin( daoUtil.getString( 6 ) );
            customer.setAccountGuid( daoUtil.getString( 7 ) );
            customer.setEmail( daoUtil.getString( 8 ) );
            customer.setIsEmailVerified( daoUtil.getBoolean( 9 ) );
            customer.setMobilePhone( daoUtil.getString( 10 ) );
            customer.setIsMobilePhoneVerified( daoUtil.getBoolean( 11 ) );
            customer.setExtrasAttributes( daoUtil.getString( 12 ) );
        }

        daoUtil.free(  );

        return customer;
    }
}
