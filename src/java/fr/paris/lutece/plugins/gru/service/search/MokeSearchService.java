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
package fr.paris.lutece.plugins.gru.service.search;

import java.util.ArrayList;
import java.util.List;


/**
 * MokeSearchService
 */
public class MokeSearchService implements ISearchService
{
    /**
     * {@inheritDoc }
     */
    @Override
    public List<CustomerResult> searchCustomer( String strQuery )
    {
        List<CustomerResult> list = new ArrayList<CustomerResult>(  );
        CustomerResult customer1 = new CustomerResult(  );
        customer1.setId( 1 );
        customer1.setFirstname( "Maurice" );
        customer1.setLastname( "Dupont" );
        customer1.setMobilePhone( "0612459812" );
        customer1.setEmail( "maurice.dupont@domain.com" );
        list.add( customer1 );

        if ( strQuery.equals( "multiple" ) )
        {
            CustomerResult customer2 = new CustomerResult(  );
            customer2.setId( 1 );
            customer2.setFirstname( "Maurice" );
            customer2.setLastname( "Dupont" );
            customer2.setMobilePhone( "0612684112" );
            customer2.setEmail( "maurice.dupont@somewhere.com" );
            list.add( customer2 );
        }

        return list;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isAutoComplete(  )
    {
        return false;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getAutoCompleteUrl(  )
    {
        return "";
    }
}
