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

import fr.paris.lutece.plugins.gru.business.feature.Feature;
import fr.paris.lutece.plugins.gru.business.feature.FeatureHome;
import fr.paris.lutece.test.LuteceTestCase;


public class FeatureBusinessTest extends LuteceTestCase
{
    private final static String NAME1 = "Name1";
    private final static String NAME2 = "Name2";
    private final static String LINK1 = "Link1";
    private final static String LINK2 = "Link2";
    private final static int IDCATEGORY1 = 1;
    private final static int IDCATEGORY2 = 2;
    private final static int IDORDER1 = 1;
    private final static int IDORDER2 = 2;

    public void testBusiness(  )
    {
        // Initialize an object
        Feature feature = new Feature(  );
        feature.setName( NAME1 );
        feature.setLink( LINK1 );
        feature.setIdCategory( IDCATEGORY1 );
        feature.setIdOrder( IDORDER1 );

        // Create test
        FeatureHome.create( feature );

        Feature featureStored = FeatureHome.findByPrimaryKey( feature.getId(  ) );
        assertEquals( featureStored.getName(  ), feature.getName(  ) );
        assertEquals( featureStored.getLink(  ), feature.getLink(  ) );
        assertEquals( featureStored.getIdCategory(  ), feature.getIdCategory(  ) );
        assertEquals( featureStored.getIdOrder(  ), feature.getIdOrder(  ) );

        // Update test
        feature.setName( NAME2 );
        feature.setLink( LINK2 );
        feature.setIdCategory( IDCATEGORY2 );
        feature.setIdOrder( IDORDER2 );
        FeatureHome.update( feature );
        featureStored = FeatureHome.findByPrimaryKey( feature.getId(  ) );
        assertEquals( featureStored.getName(  ), feature.getName(  ) );
        assertEquals( featureStored.getLink(  ), feature.getLink(  ) );
        assertEquals( featureStored.getIdCategory(  ), feature.getIdCategory(  ) );
        assertEquals( featureStored.getIdOrder(  ), feature.getIdOrder(  ) );

        // List test
        FeatureHome.getFeaturesList(  );

        // Delete test
        FeatureHome.remove( feature.getId(  ) );
        featureStored = FeatureHome.findByPrimaryKey( feature.getId(  ) );
        assertNull( featureStored );
    }
}
