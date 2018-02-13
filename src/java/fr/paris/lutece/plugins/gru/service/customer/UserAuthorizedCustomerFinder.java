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

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.paris.lutece.plugins.grubusiness.business.customer.Customer;
import fr.paris.lutece.plugins.grubusiness.business.demand.Demand;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.admin.AccessDeniedException;
import fr.paris.lutece.portal.service.util.AppLogService;

/**
 * This class finds customers depending on the authorizations of the user
 *
 */
public class UserAuthorizedCustomerFinder
{
    private final AdminUser _user;

    /**
     * Constructor
     * 
     * @param user
     *            the user used to find the customers
     */
    public UserAuthorizedCustomerFinder( AdminUser user )
    {
        _user = user;
    }

    /**
     * Finds a customer by its id
     * 
     * @param strCustomerId
     *            the customer id
     * @return the customer
     * @throws AccessDeniedException
     *             if the user is not authorized to access the customer
     */
    public Customer findById( String strCustomerId ) throws AccessDeniedException
    {
        Customer customer = CustomerService.instance( ).findById( strCustomerId );

        CustomerAuthorizationFilterChain chain = CustomerAuthorizationFilterChainFactory.getInstance( ).createFilterChain( );
        chain.doFilter( _user, customer );

        return customer;
    }

    /**
     * Finds the customer linked to the specified demand
     * 
     * @param demand
     *            the demand
     * @return the customer, or {@code null} if the demand has no customer
     * @throws AccessDeniedException
     *             if the user is not authorized to access the customer
     */
    public Customer findByDemand( Demand demand ) throws AccessDeniedException
    {
        Customer customer = demand.getCustomer( );

        if ( customer != null )
        {
            customer = findById( customer.getId( ) );
        }

        return customer;
    }

    /**
     * Finds customers depending on the specified query.
     * 
     * @param strJsonQuery
     *            the query in a JSON format
     * @return the list of customers the user is authorized to access
     */
    public List<Customer> findByQuery( String strJsonQuery )
    {
        Map<String, String> mapFilter = convertToSearchFilter( strJsonQuery );

        List<Customer> listCustomer = CustomerService.instance( ).findByFilter( mapFilter );

        CustomerAuthorizationFilterChain chain = CustomerAuthorizationFilterChainFactory.getInstance( ).createFilterChain( );
        chain.doFilter( _user, listCustomer );

        return listCustomer;
    }

    /**
     * Converts the specified query into a search filter
     * 
     * @param strJsonQuery
     *            the query in JSON format to convert
     * @return the search filter
     */
    private Map<String, String> convertToSearchFilter( String strJsonQuery )
    {
        Map<String, String> mapFilter = new HashMap<>( );

        try
        {
            ObjectMapper mapper = new ObjectMapper( );
            JsonNode nodeQuery = mapper.readTree( strJsonQuery );

            Iterator<String> iterator = nodeQuery.fieldNames( );

            while ( iterator.hasNext( ) )
            {
                String strSearchField = iterator.next( );
                mapFilter.put( strSearchField, nodeQuery.get( strSearchField ).asText( ) );
            }

        }
        catch( IOException e )
        {
            AppLogService.error( "cannot convert the search query to JSON object :" + strJsonQuery );
        }

        return mapFilter;
    }
}
