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
package fr.paris.lutece.plugins.gru.business;

import fr.paris.lutece.plugins.gru.business.demandtype.DemandType;
import fr.paris.lutece.plugins.gru.business.demandtype.DemandTypeHome;
import fr.paris.lutece.test.LuteceTestCase;

public class DemandTypeBusinessTest extends LuteceTestCase
{
    private final static int DEMANDTYPEID1 = 1;
    private final static int DEMANDTYPEID2 = 2;
    private final static String TITLE1 = "Title1";
    private final static String TITLE2 = "Title2";

    public void testBusiness( )
    {
        // Initialize an object
        DemandType demandType = new DemandType( );
        demandType.setDemandTypeId( DEMANDTYPEID1 );
        demandType.setTitle( TITLE1 );

        // Create test
        DemandTypeHome.create( demandType );

        DemandType demandTypeStored = DemandTypeHome.findByPrimaryKey( demandType.getId( ) );
        assertEquals( demandTypeStored.getDemandTypeId( ), demandType.getDemandTypeId( ) );
        assertEquals( demandTypeStored.getTitle( ), demandType.getTitle( ) );

        // Update test
        demandType.setDemandTypeId( DEMANDTYPEID2 );
        demandType.setTitle( TITLE2 );
        DemandTypeHome.update( demandType );
        demandTypeStored = DemandTypeHome.findByPrimaryKey( demandType.getId( ) );
        assertEquals( demandTypeStored.getDemandTypeId( ), demandType.getDemandTypeId( ) );
        assertEquals( demandTypeStored.getTitle( ), demandType.getTitle( ) );

        // List test
        DemandTypeHome.getDemandTypesList( );

        // Delete test
        DemandTypeHome.remove( demandType.getId( ) );
        demandTypeStored = DemandTypeHome.findByPrimaryKey( demandType.getId( ) );
        assertNull( demandTypeStored );
    }
}
