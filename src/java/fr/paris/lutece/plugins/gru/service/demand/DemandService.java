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
package fr.paris.lutece.plugins.gru.service.demand;

import fr.paris.lutece.plugins.gru.business.customer.Customer;
import fr.paris.lutece.plugins.gru.business.demand.BaseDemand;
import fr.paris.lutece.plugins.gru.business.demand.Demand;
import fr.paris.lutece.plugins.gru.service.demandtype.DemandTypeService;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.ArrayList;
import java.util.List;


/**
 * DemandeService
 */
public class DemandService
{
    private static final String BEAN_DEMAND_SERVICE = "gru.demandService";
    private static IDemandService _service;

    /**
     * Get External implementation
     * @return The service
     */
    private static IDemandService getService(  )
    {
        if ( _service == null )
        {
            _service = SpringContextService.getBean( BEAN_DEMAND_SERVICE );
        }

        return _service;
    }

    /**
     * Return a Demand object from an Id
     * @param strDemandId The Demand Id
     * @return The demand
     */
    public static Demand getDemand( String strDemandId, String strDemandTypeId, AdminUser user )
    {
        return getService(  ).getDemand( strDemandId, strDemandTypeId, user );
    }

    /**
     * Gets a list of demand for a given Customer
     * @param customer The customer
     * @return The list
     */
    public static List<Demand> getDemands( Customer customer, AdminUser user )
    {
        List<BaseDemand> listBase = getService(  ).getDemands( "" + customer.getId(  ), user );
        List<Demand> listDemand = new ArrayList<Demand>(  );

        for ( BaseDemand base : listBase )
        {
            listDemand.add( DemandTypeService.buildDemand( base, customer, user ) );
        }

        return listDemand;
    }

    /**
     * Gets a list of demand for a given Customer filtered by types
     * @param customer The customer
     * @param listExcludedTypes excluded types
     * @return The list
     */
    public static List<Demand> getDemandsExcludingTypes( Customer customer, List<String> listExcludedTypes,
        AdminUser user )
    {
        List<BaseDemand> listBase = getService(  ).getDemands( "" + customer.getId(  ), user );
        List<Demand> listDemand = new ArrayList<Demand>(  );

        for ( BaseDemand base : listBase )
        {
            if ( !listExcludedTypes.contains( base.getDemandTypeId(  ) ) )
            {
                listDemand.add( DemandTypeService.buildDemand( base, customer, user ) );
            }
        }

        return listDemand;
    }

    /**
     * Gets a list of demand for a given Customer filtered by types
     * @param customer The customer
     * @param listIncludedTypes included types
     * @return The list
     */
    public static List<Demand> getDemandsIncludingTypes( Customer customer, List<String> listIncludedTypes,
        AdminUser user )
    {
        List<BaseDemand> listBase = getService(  ).getDemands( "" + customer.getId(  ), user );
        List<Demand> listDemand = new ArrayList<Demand>(  );

        for ( BaseDemand base : listBase )
        {
            if ( listIncludedTypes.contains( base.getDemandTypeId(  ) ) )
            {
                listDemand.add( DemandTypeService.buildDemand( base, customer, user ) );
            }
        }

        return listDemand;
    }
}
