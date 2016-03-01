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

import fr.paris.lutece.test.LuteceTestCase;


public class BusinessDomainBusinessTest extends LuteceTestCase
{
    private final static int IDBUSINESSSECTOR1 = 1;
    private final static int IDBUSINESSSECTOR2 = 2;
    private final static String BUSINESSSECTOR1 = "BusinessSector1";
    private final static String BUSINESSSECTOR2 = "BusinessSector2";
    private final static String NAME1 = "Name1";
    private final static String NAME2 = "Name2";
    private final static String DESCRIPTION1 = "Description1";
    private final static String DESCRIPTION2 = "Description2";

    public void testBusiness(  )
    {
        // Initialize an object
        BusinessDomain businessDomain = new BusinessDomain(  );
        businessDomain.setIdBusinessSector( IDBUSINESSSECTOR1 );
        businessDomain.setBusinessSector( BUSINESSSECTOR1 );
        businessDomain.setName( NAME1 );
        businessDomain.setDescription( DESCRIPTION1 );

        // Create test
        BusinessDomainHome.create( businessDomain );

        BusinessDomain businessDomainStored = BusinessDomainHome.findByPrimaryKey( businessDomain.getId(  ) );
        assertEquals( businessDomainStored.getIdBusinessSector(  ), businessDomain.getIdBusinessSector(  ) );
        assertEquals( businessDomainStored.getBusinessSector(  ), businessDomain.getBusinessSector(  ) );
        assertEquals( businessDomainStored.getName(  ), businessDomain.getName(  ) );
        assertEquals( businessDomainStored.getDescription(  ), businessDomain.getDescription(  ) );

        // Update test
        businessDomain.setIdBusinessSector( IDBUSINESSSECTOR2 );
        businessDomain.setBusinessSector( BUSINESSSECTOR2 );
        businessDomain.setName( NAME2 );
        businessDomain.setDescription( DESCRIPTION2 );
        BusinessDomainHome.update( businessDomain );
        businessDomainStored = BusinessDomainHome.findByPrimaryKey( businessDomain.getId(  ) );
        assertEquals( businessDomainStored.getIdBusinessSector(  ), businessDomain.getIdBusinessSector(  ) );
        assertEquals( businessDomainStored.getBusinessSector(  ), businessDomain.getBusinessSector(  ) );
        assertEquals( businessDomainStored.getName(  ), businessDomain.getName(  ) );
        assertEquals( businessDomainStored.getDescription(  ), businessDomain.getDescription(  ) );

        // List test
        BusinessDomainHome.getBusinessDomainsList(  );

        // Delete test
        BusinessDomainHome.remove( businessDomain.getId(  ) );
        businessDomainStored = BusinessDomainHome.findByPrimaryKey( businessDomain.getId(  ) );
        assertNull( businessDomainStored );
    }
}
