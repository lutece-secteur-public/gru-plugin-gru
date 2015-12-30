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
package fr.paris.lutece.plugins.gru.service.demandtype;

import fr.paris.lutece.plugins.gru.business.customer.Customer;
import fr.paris.lutece.plugins.gru.business.demand.Action;
import fr.paris.lutece.plugins.gru.business.demand.BaseDemand;
import fr.paris.lutece.plugins.gru.business.demand.Demand;
import fr.paris.lutece.plugins.gru.business.demandtype.DemandType;
import fr.paris.lutece.plugins.gru.business.demandtype.DemandTypeAction;
import fr.paris.lutece.plugins.gru.business.demandtype.DemandTypeActionHome;
import fr.paris.lutece.plugins.gru.business.demandtype.DemandTypeHome;
import fr.paris.lutece.plugins.gru.service.ActionLinkService;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.rbac.RBACService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Demande Type Service
 */
public class DemandTypeService
{
    private static final String BOOKMARK_ID = "{id}";
    private static Map<String, DemandType> _mapDemandTypes = new HashMap<String, DemandType>(  );

    public static Demand buildDemand( BaseDemand base, Customer customer, AdminUser user )
    {
        Demand demand = new Demand( base );
        DemandType type = _mapDemandTypes.get( demand.getDemandTypeId(  ) );

        if ( type == null )
        {
            type = DemandTypeHome.findByTypeId( demand.getDemandTypeId(  ) );
            _mapDemandTypes.put( demand.getDemandTypeId(  ), type );
        }

        demand.setTitle( type.getTitle(  ) );

        List<DemandTypeAction> listActions = DemandTypeActionHome.getActionsByType( type.getId(  ) );

        for ( DemandTypeAction dta : listActions )
        {
            if ( RBACService.isAuthorized( dta, DemandTypeAction.PERMISSION_ACCESS, user ) )
            {
                Action action = new Action(  );
                action.setName( dta.getLabel(  ) );

                String strUrl = dta.getLink(  );
                strUrl = strUrl.replace( BOOKMARK_ID, demand.getId(  ) );
                // TODO manage target
                action.setUrl( ActionLinkService.buildLink( strUrl, ActionLinkService.TARGET_NO_FRAME, customer ) );
                demand.addAction( action );
            }
        }

        return demand;
    }
}
