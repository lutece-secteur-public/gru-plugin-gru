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
package fr.paris.lutece.plugins.gru.service.customer;

import java.util.ArrayList;
import java.util.List;

import fr.paris.lutece.plugins.grubusiness.business.customer.Customer;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.admin.AccessDeniedException;

/**
 * This class represents a chain to filter the customer depending on the user authorizations
 *
 */
public class CustomerAuthorizationFilterChain
{
    private final List<ICustomerAuthorizationFilter> _listCustomerAuthorizationFilter;
    private int _nIndex;

    /**
     * Constructor
     */
    public CustomerAuthorizationFilterChain( )
    {
        _listCustomerAuthorizationFilter = new ArrayList<>( );
    }

    /**
     * Launches the chain to filter the specified customer depending on the user authorizations
     * 
     * @param user
     *            the user
     * @param customer
     *            the customer to filter
     * @throws AccessDeniedException
     *             if the user is not authorized to access the customer
     */
    public void doFilter( AdminUser user, Customer customer ) throws AccessDeniedException
    {
        if ( user == null )
        {
            throw new AccessDeniedException( "User is null!" );
        }

        if ( customer == null )
        {
            throw new AccessDeniedException( "Customer is null!" );
        }

        if ( _nIndex < _listCustomerAuthorizationFilter.size( ) )
        {
            ICustomerAuthorizationFilter customerAuthorizationFilter = _listCustomerAuthorizationFilter.get( _nIndex );
            _nIndex++;

            customerAuthorizationFilter.doFilter( user, customer, this );
        }
    }

    /**
     * Launches the chain to filter the specified list of customers depending on the user authorizations
     * 
     * @param user
     *            the user
     * @param listCustomer
     *            the list of customers to filter
     */
    public void doFilter( AdminUser user, List<Customer> listCustomer )
    {
        if ( listCustomer != null )
        {
            if ( user == null )
            {
                listCustomer.clear( );
            }
            else
            {
                if ( _nIndex < _listCustomerAuthorizationFilter.size( ) )
                {
                    ICustomerAuthorizationFilter customerAuthorizationFilter = _listCustomerAuthorizationFilter.get( _nIndex );
                    _nIndex++;

                    customerAuthorizationFilter.doFilter( user, listCustomer, this );
                }
            }
        }
    }

    /**
     * Adds a filter in the chain
     * 
     * @param customerAuthorizationFilter
     *            the filter to add
     */
    public void addFilter( ICustomerAuthorizationFilter customerAuthorizationFilter )
    {
        _listCustomerAuthorizationFilter.add( customerAuthorizationFilter );
    }
}
