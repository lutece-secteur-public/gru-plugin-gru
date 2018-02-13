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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import fr.paris.lutece.plugins.gru.util.IntGenerator;
import fr.paris.lutece.plugins.grubusiness.business.customer.Customer;
import fr.paris.lutece.plugins.grubusiness.business.customer.MockCustomer;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.business.user.MockAdminUser;
import fr.paris.lutece.portal.service.admin.AccessDeniedException;
import fr.paris.lutece.test.LuteceTestCase;

public class CustomerAuthorizationFilterChainTest extends LuteceTestCase
{
    private AdminUser _user;
    private Customer _customer;
    private CustomerAuthorizationFilterChain _chain;

    public void testFilterCustomerWitNoFilter( ) throws AccessDeniedException
    {
        init( );

        _chain.doFilter( _user, _customer );
        ;
    }

    public void testFilterCustomerWithOneFilter( ) throws AccessDeniedException
    {
        init( );
        addOneFilterInChain( );

        _chain.doFilter( _user, _customer );
    }

    private void addOneFilterInChain( )
    {
        _chain.addFilter( new MockCustomerAuthorizationFilter( ) );
    }

    public void testFilterCustomerWithTwoFilter( ) throws AccessDeniedException
    {
        init( );
        addOneFilterInChain( );
        addOneFilterInChain( );

        _chain.doFilter( _user, _customer );
    }

    public void testFilterCustomerWhenUserIsNull( )
    {
        init( );

        try
        {
            _chain.doFilter( null, _customer );
            fail( "Expected an AccessDeniedException to be thrown" );
        }
        catch( AccessDeniedException e )
        {
            // Correct behavior
        }
    }

    private void init( )
    {
        _user = MockAdminUser.create( );
        _customer = MockCustomer.create( );
        _chain = new CustomerAuthorizationFilterChain( );
    }

    public void testFilterCustomerWhenCustomerIsNull( )
    {
        init( );
        Customer customer = null;

        try
        {
            _chain.doFilter( _user, customer );
            fail( "Expected an AccessDeniedException to be thrown" );
        }
        catch( AccessDeniedException e )
        {
            // Correct behavior
        }
    }

    public void testFilterListCustomerWithNoFilter( ) throws AccessDeniedException
    {
        init( );
        List<Customer> listCustomer = createListCustomer( );

        _chain.doFilter( _user, listCustomer );
    }

    public void testFilterListCustomerWithOneFilter( ) throws AccessDeniedException
    {
        init( );
        addOneFilterInChain( );
        List<Customer> listCustomer = createListCustomer( );
        int nListSize = listCustomer.size( );

        _chain.doFilter( _user, listCustomer );

        assertThat( listCustomer.size( ), is( nListSize ) );
    }

    public void testFilterListCustomerWithTwoFilter( ) throws AccessDeniedException
    {
        init( );
        addOneFilterInChain( );
        addOneFilterInChain( );
        List<Customer> listCustomer = createListCustomer( );
        int nListSize = listCustomer.size( );

        _chain.doFilter( _user, listCustomer );

        assertThat( listCustomer.size( ), is( nListSize ) );
    }

    public void testFilterListCustomerWhenUserIsNull( )
    {
        init( );
        List<Customer> listCustomer = createListCustomer( );

        _chain.doFilter( null, listCustomer );

        assertThat( listCustomer.size( ), is( 0 ) );
    }

    private List<Customer> createListCustomer( )
    {
        int nNumberOfCustomers = IntGenerator.generateIntDifferentThanZero( );
        List<Customer> listCustomer = new ArrayList<>( nNumberOfCustomers );

        for ( int i = 0; i < nNumberOfCustomers; i++ )
        {
            Customer customer = MockCustomer.create( );
            listCustomer.add( customer );
        }

        return listCustomer;
    }

    public void testFilterListCustomerWhenCustomerIsNull( )
    {
        init( );
        List<Customer> listCustomer = null;

        _chain.doFilter( _user, listCustomer );

        assertThat( listCustomer, is( nullValue( ) ) );
    }

    private static class MockCustomerAuthorizationFilter implements ICustomerAuthorizationFilter
    {

        @Override
        public void doFilter( AdminUser user, Customer customer, CustomerAuthorizationFilterChain chain ) throws AccessDeniedException
        {
            chain.doFilter( user, customer );
        }

        @Override
        public void doFilter( AdminUser user, List<Customer> listCustomer, CustomerAuthorizationFilterChain chain )
        {
            chain.doFilter( user, listCustomer );
        }

    }
}
