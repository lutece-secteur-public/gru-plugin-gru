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

import fr.paris.lutece.plugins.gru.business.customer.CustomerHome;
import fr.paris.lutece.plugins.grubusiness.business.customer.Customer;
import fr.paris.lutece.test.LuteceTestCase;

public class CustomerBusinessTest extends LuteceTestCase
{
    private final static int IDTITLE1 = 1;
    private final static int IDTITLE2 = 2;
    private final static String FIRSTNAME1 = "Firstname1";
    private final static String FIRSTNAME2 = "Firstname2";
    private final static String LASTNAME1 = "Lastname1";
    private final static String LASTNAME2 = "Lastname2";
    private final static boolean HASACCOUNT1 = true;
    private final static boolean HASACCOUNT2 = false;
    private final static String ACCOUNTLOGIN1 = "AccountLogin1";
    private final static String ACCOUNTLOGIN2 = "AccountLogin2";
    private final static String ACCOUNTGUID1 = "AccountGuid1";
    private final static String ACCOUNTGUID2 = "AccountGuid2";
    private final static String EMAIL1 = "Email1";
    private final static String EMAIL2 = "Email2";
    private final static boolean ISEMAILVERIFIED1 = true;
    private final static boolean ISEMAILVERIFIED2 = false;
    private final static String MOBILEPHONE1 = "MobilePhone1";
    private final static String MOBILEPHONE2 = "MobilePhone2";
    private final static boolean ISMOBILEPHONEVERIFIED1 = true;
    private final static boolean ISMOBILEPHONEVERIFIED2 = false;
    private final static String EXTRASATTRIBUTES1 = "ExtrasAttributes1";
    private final static String EXTRASATTRIBUTES2 = "ExtrasAttributes2";

    public void testBusiness( )
    {
        // Initialize an object
        Customer customer = new Customer( );
        customer.setIdTitle( IDTITLE1 );
        customer.setFirstname( FIRSTNAME1 );
        customer.setLastname( LASTNAME1 );
        customer.setHasAccount( HASACCOUNT1 );
        customer.setAccountLogin( ACCOUNTLOGIN1 );
        customer.setAccountGuid( ACCOUNTGUID1 );
        customer.setEmail( EMAIL1 );
        customer.setIsEmailVerified( ISEMAILVERIFIED1 );
        customer.setMobilePhone( MOBILEPHONE1 );
        customer.setIsMobilePhoneVerified( ISMOBILEPHONEVERIFIED1 );
        customer.setExtrasAttributes( EXTRASATTRIBUTES1 );

        // Create test
        CustomerHome.create( customer );

        Customer customerStored = CustomerHome.findByPrimaryKey( customer.getId( ) );
        assertEquals( customerStored.getIdTitle( ), customer.getIdTitle( ) );
        assertEquals( customerStored.getFirstname( ), customer.getFirstname( ) );
        assertEquals( customerStored.getLastname( ), customer.getLastname( ) );
        assertEquals( customerStored.getHasAccount( ), customer.getHasAccount( ) );
        assertEquals( customerStored.getAccountLogin( ), customer.getAccountLogin( ) );
        assertEquals( customerStored.getAccountGuid( ), customer.getAccountGuid( ) );
        assertEquals( customerStored.getEmail( ), customer.getEmail( ) );
        assertEquals( customerStored.getIsEmailVerified( ), customer.getIsEmailVerified( ) );
        assertEquals( customerStored.getMobilePhone( ), customer.getMobilePhone( ) );
        assertEquals( customerStored.getIsMobilePhoneVerified( ), customer.getIsMobilePhoneVerified( ) );
        assertEquals( customerStored.getExtrasAttributes( ), customer.getExtrasAttributes( ) );

        // Update test
        customer.setIdTitle( IDTITLE2 );
        customer.setFirstname( FIRSTNAME2 );
        customer.setLastname( LASTNAME2 );
        customer.setHasAccount( HASACCOUNT2 );
        customer.setAccountLogin( ACCOUNTLOGIN2 );
        customer.setAccountGuid( ACCOUNTGUID2 );
        customer.setEmail( EMAIL2 );
        customer.setIsEmailVerified( ISEMAILVERIFIED2 );
        customer.setMobilePhone( MOBILEPHONE2 );
        customer.setIsMobilePhoneVerified( ISMOBILEPHONEVERIFIED2 );
        customer.setExtrasAttributes( EXTRASATTRIBUTES2 );
        CustomerHome.update( customer );
        customerStored = CustomerHome.findByPrimaryKey( customer.getId( ) );
        assertEquals( customerStored.getIdTitle( ), customer.getIdTitle( ) );
        assertEquals( customerStored.getFirstname( ), customer.getFirstname( ) );
        assertEquals( customerStored.getLastname( ), customer.getLastname( ) );
        assertEquals( customerStored.getHasAccount( ), customer.getHasAccount( ) );
        assertEquals( customerStored.getAccountLogin( ), customer.getAccountLogin( ) );
        assertEquals( customerStored.getAccountGuid( ), customer.getAccountGuid( ) );
        assertEquals( customerStored.getEmail( ), customer.getEmail( ) );
        assertEquals( customerStored.getIsEmailVerified( ), customer.getIsEmailVerified( ) );
        assertEquals( customerStored.getMobilePhone( ), customer.getMobilePhone( ) );
        assertEquals( customerStored.getIsMobilePhoneVerified( ), customer.getIsMobilePhoneVerified( ) );
        assertEquals( customerStored.getExtrasAttributes( ), customer.getExtrasAttributes( ) );

        // List test
        CustomerHome.getCustomersList( );

        // Delete test
        CustomerHome.remove( customer.getId( ) );
        customerStored = CustomerHome.findByPrimaryKey( customer.getId( ) );
        assertNull( customerStored );
    }
}
