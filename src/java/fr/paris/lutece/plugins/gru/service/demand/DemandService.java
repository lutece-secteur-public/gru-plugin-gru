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
import fr.paris.lutece.plugins.gru.business.demand.Notification;
import fr.paris.lutece.plugins.gru.business.demandtype.DemandType;
import fr.paris.lutece.plugins.gru.business.demandtype.DemandTypeHome;
import fr.paris.lutece.plugins.gru.business.domain.BusinessDomain;
import fr.paris.lutece.plugins.gru.service.demandtype.DemandTypeService;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.rbac.RBACService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.util.AppException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Demande Service
 */
public class DemandService
{
    private static final String BEAN_DEMAND_SERVICE = "gru.demandService";
    private static IDemandService _service;
    private static Comparator<Demand> _comparatorDemands = new Comparator<Demand>(  )
        {
            @Override
            public int compare( Demand demand1, Demand demand2 )
            {
                return ( Long.valueOf( demand1.getTimeOpenedInMs(  ) )
                             .compareTo( Long.valueOf( demand2.getTimeOpenedInMs(  ) ) ) );
            }
        };

    private static Comparator<Notification> _comparatorNotifications = new Comparator<Notification>(  )
        {
            @Override
            public int compare( Notification notification1, Notification notification2 )
            {
                return ( Long.valueOf( notification2.getTimestamp(  ) )
                             .compareTo( Long.valueOf( notification1.getTimestamp(  ) ) ) );
            }
        };

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
     * @param strDemandTypeId
     * @param user The Admin User
     * @return The demand
     */
    public static Demand getDemand( String strDemandId, String strDemandTypeId, AdminUser user )
    {
        Demand demand = getService(  ).getDemand( strDemandId, strDemandTypeId, user );
        Collections.sort( demand.getNotifications(  ), _comparatorNotifications );

        demand.setTitle( DemandTypeService.getTypeLabel( strDemandTypeId ) );
        demand.setShowDetails( isDetailsAuthorized( strDemandTypeId, user ) );

        return demand;
    }

    /**
     * Gets a list of demand for a given Customer
     * @param customer The customer
     * @param user The admin user
     * @param nStatus The status
     * @return The list
     */
    public static List<Demand> getDemands( Customer customer, AdminUser user, int nStatus )
    {
        List<BaseDemand> listBase = getService(  ).getDemands( "" + customer.getId(  ), user );
        List<Demand> listDemand = new ArrayList<Demand>(  );

        for ( BaseDemand base : listBase )
        {
            if ( base.getStatus(  ) == nStatus )
            {
                if ( isAuthorized( base, user ) )
                {
                    listDemand.add( DemandTypeService.setDemandActions( base, customer, user ) );
                }
            }
        }

        Collections.sort( listDemand, _comparatorDemands );

        return listDemand;
    }

    /**
     * Gets a list of demand for a given Customer filtered by types
     * @param customer The customer
     * @param listExcludedTypes excluded types
     * @param user The admin user
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
                if ( isAuthorized( base, user ) )
                {
                    listDemand.add( DemandTypeService.setDemandActions( base, customer, user ) );
                }
            }
        }

        Collections.sort( listDemand, _comparatorDemands );

        return listDemand;
    }

    /**
     * Gets a list of demand for a given Customer filtered by types
     * @param customer The customer
     * @param listIncludedTypes included types
     * @param user The admin user
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
                if ( isAuthorized( base, user ) )
                {
                    listDemand.add( DemandTypeService.setDemandActions( base, customer, user ) );
                }
            }
        }

        Collections.sort( listDemand, _comparatorDemands );

        return listDemand;
    }

    /**
     * Check if the user can view the demand
     * @param base The base demand
     * @param user The Admin User
     * @return true if authorized
     */
    private static boolean isAuthorized( BaseDemand base, AdminUser user )
    {
        DemandType type = DemandTypeHome.findByTypeId( base.getDemandTypeId(  ) );

        if ( type == null )
        {
            throw new AppException( "Demand Type missing for ID : " + base.getDemandTypeId(  ) );
        }

        String strBusinessDomainId = String.valueOf( type.getBusinessDomainId(  ) );

        return RBACService.isAuthorized( BusinessDomain.RESOURCE_TYPE, strBusinessDomainId,
            BusinessDomain.PERMISSION_VIEW_SUMMARY, user ) ||
        RBACService.isAuthorized( BusinessDomain.RESOURCE_TYPE, strBusinessDomainId,
            BusinessDomain.PERMISSION_VIEW_DETAILS, user );
    }

    /**
     * Check if the user can view details
     * @param strDemandTypeId The demand Id type
     * @param user The admin user
     * @return true if authorized
     */
    private static boolean isDetailsAuthorized( String strDemandTypeId, AdminUser user )
    {
        DemandType type = DemandTypeHome.findByTypeId( strDemandTypeId );

        if ( type == null )
        {
            throw new AppException( "Demand Type missing for ID : " + strDemandTypeId );
        }

        String strBusinessDomainId = String.valueOf( type.getBusinessDomainId(  ) );

        return RBACService.isAuthorized( BusinessDomain.RESOURCE_TYPE, strBusinessDomainId,
            BusinessDomain.PERMISSION_VIEW_DETAILS, user );
    }
}
