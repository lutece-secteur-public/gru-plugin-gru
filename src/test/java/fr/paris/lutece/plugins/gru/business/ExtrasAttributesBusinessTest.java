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


public class ExtrasAttributesBusinessTest extends LuteceTestCase
{
    private final static String ATTRIBUTEKEY1 = "AttributeKey1";
    private final static String ATTRIBUTEKEY2 = "AttributeKey2";
    private final static String NAME1 = "Name1";
    private final static String NAME2 = "Name2";
    private final static String DESCRIPTION1 = "Description1";
    private final static String DESCRIPTION2 = "Description2";

    public void testBusiness(  )
    {
        // Initialize an object
        ExtrasAttributes extrasAttributes = new ExtrasAttributes(  );
        extrasAttributes.setAttributeKey( ATTRIBUTEKEY1 );
        extrasAttributes.setName( NAME1 );
        extrasAttributes.setDescription( DESCRIPTION1 );

        // Create test
        ExtrasAttributesHome.create( extrasAttributes );

        ExtrasAttributes extrasAttributesStored = ExtrasAttributesHome.findByPrimaryKey( extrasAttributes.getId(  ) );
        assertEquals( extrasAttributesStored.getAttributeKey(  ), extrasAttributes.getAttributeKey(  ) );
        assertEquals( extrasAttributesStored.getName(  ), extrasAttributes.getName(  ) );
        assertEquals( extrasAttributesStored.getDescription(  ), extrasAttributes.getDescription(  ) );

        // Update test
        extrasAttributes.setAttributeKey( ATTRIBUTEKEY2 );
        extrasAttributes.setName( NAME2 );
        extrasAttributes.setDescription( DESCRIPTION2 );
        ExtrasAttributesHome.update( extrasAttributes );
        extrasAttributesStored = ExtrasAttributesHome.findByPrimaryKey( extrasAttributes.getId(  ) );
        assertEquals( extrasAttributesStored.getAttributeKey(  ), extrasAttributes.getAttributeKey(  ) );
        assertEquals( extrasAttributesStored.getName(  ), extrasAttributes.getName(  ) );
        assertEquals( extrasAttributesStored.getDescription(  ), extrasAttributes.getDescription(  ) );

        // List test
        ExtrasAttributesHome.getExtrasAttributessList(  );

        // Delete test
        ExtrasAttributesHome.remove( extrasAttributes.getId(  ) );
        extrasAttributesStored = ExtrasAttributesHome.findByPrimaryKey( extrasAttributes.getId(  ) );
        assertNull( extrasAttributesStored );
    }
}
