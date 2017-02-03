/*
 * Copyright (c) 2002-2016, Mairie de Paris
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
package fr.paris.lutece.plugins.gru.service.search;

import fr.paris.lutece.plugins.grubusiness.business.customer.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * MockSearchService
 */
public class MockCustomerService
{
    /**
     * Find all customer with a specified name and lastname
     * 
     * @param strFirstName
     * @param strLastName
     * @return the list of all customer with the same name
     */
    public List<Customer> findbyName( String strFirstName, String strLastName )
    {
        List<Customer> list = new ArrayList<Customer>( );
        Customer customer1 = new Customer( );
        customer1.setId( "48376eb6-b6c9-4247-931c-351a8182d297" );
        customer1.setIdTitle( 1 );
        customer1.setFirstname( "Maurice" );
        customer1.setLastname( "Dupont" );
        customer1.setFixedPhoneNumber( "0199842317" );
        customer1.setMobilePhone( "0612459812" );
        customer1.setEmail( "maurice.dupont@domain.com" );
        customer1.setBirthDate( "01/01/1970" );
        list.add( customer1 );

        Customer customer2 = new Customer( );
        customer2.setId( "48376eb6-b6c9-4247-931c-351a8182d297" );
        customer2.setIdTitle( 2 );
        customer2.setFirstname( "Maurice" );
        customer2.setLastname( "Dupont" );
        customer2.setFixedPhoneNumber( "0199842319" );
        customer2.setMobilePhone( "0712684112" );
        customer2.setEmail( "maurice.dupont@somewhere.com" );
        customer2.setBirthDate( "11/11/1918" );
        list.add( customer2 );

        return list;
    }

    /**
     * Find a customer by its id
     * 
     * @param strCustomerId
     * @return the customer with the specified id
     */
    public Customer findById( String strCustomerId )
    {
        Customer customer = new Customer( );
        customer.setId( "48376eb6-b6c9-4247-931c-351a8182d297" );
        customer.setFirstname( "Maurice" );
        customer.setLastname( "Dupont" );
        customer.setMobilePhone( "0612459812" );
        customer.setEmail( "maurice.dupont@domain.com" );

        return customer;
    }
}
