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

import fr.paris.lutece.plugins.gru.business.demandtype.DemandTypeAction;
import fr.paris.lutece.plugins.gru.business.demandtype.DemandTypeActionHome;
import fr.paris.lutece.test.LuteceTestCase;


public class DemandTypeActionBusinessTest extends LuteceTestCase
{
    private final static String ACTIONLINK1 = "ActionLink1";
    private final static String ACTIONLINK2 = "ActionLink2";
    private final static String ACTIONLABEL1 = "ActionLabel1";
    private final static String ACTIONLABEL2 = "ActionLabel2";
    private final static int IDDEMANDTYPE1 = 1;
    private final static int IDDEMANDTYPE2 = 2;
    private final static String DEMANDTYPE1 = "DemandType1";
    private final static String DEMANDTYPE2 = "DemandType2";

    public void testBusiness(  )
    {
        // Initialize an object
        DemandTypeAction demandTypeAction = new DemandTypeAction(  );
        demandTypeAction.setLink( ACTIONLINK1 );
        demandTypeAction.setLabel( ACTIONLABEL1 );
        demandTypeAction.setIdDemandType( IDDEMANDTYPE1 );
        demandTypeAction.setDemandType( DEMANDTYPE1 );

        // Create test
        DemandTypeActionHome.create( demandTypeAction );

        DemandTypeAction demandTypeActionStored = DemandTypeActionHome.findByPrimaryKey( demandTypeAction.getId(  ) );
        assertEquals( demandTypeActionStored.getLink(  ), demandTypeAction.getLink(  ) );
        assertEquals( demandTypeActionStored.getLabel(  ), demandTypeAction.getLabel(  ) );
        assertEquals( demandTypeActionStored.getIdDemandType(  ), demandTypeAction.getIdDemandType(  ) );
        assertEquals( demandTypeActionStored.getDemandType(  ), demandTypeAction.getDemandType(  ) );

        // Update test
        demandTypeAction.setLink( ACTIONLINK2 );
        demandTypeAction.setLabel( ACTIONLABEL2 );
        demandTypeAction.setIdDemandType( IDDEMANDTYPE2 );
        demandTypeAction.setDemandType( DEMANDTYPE2 );
        DemandTypeActionHome.update( demandTypeAction );
        demandTypeActionStored = DemandTypeActionHome.findByPrimaryKey( demandTypeAction.getId(  ) );
        assertEquals( demandTypeActionStored.getLink(  ), demandTypeAction.getLink(  ) );
        assertEquals( demandTypeActionStored.getLabel(  ), demandTypeAction.getLabel(  ) );
        assertEquals( demandTypeActionStored.getIdDemandType(  ), demandTypeAction.getIdDemandType(  ) );
        assertEquals( demandTypeActionStored.getDemandType(  ), demandTypeAction.getDemandType(  ) );

        // List test
        DemandTypeActionHome.getDemandTypeActionsList(  );

        // Delete test
        DemandTypeActionHome.remove( demandTypeAction.getId(  ) );
        demandTypeActionStored = DemandTypeActionHome.findByPrimaryKey( demandTypeAction.getId(  ) );
        assertNull( demandTypeActionStored );
    }
}
