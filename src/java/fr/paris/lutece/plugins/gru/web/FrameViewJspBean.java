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
package fr.paris.lutece.plugins.gru.web;

import fr.paris.lutece.plugins.gru.service.CustomerActionsService;
import fr.paris.lutece.plugins.gru.utils.CustomerUtils;
import fr.paris.lutece.plugins.gru.utils.UrlUtils;
import fr.paris.lutece.plugins.gru.web.actions.model.ActionPanel;
import fr.paris.lutece.plugins.grubusiness.business.customer.Customer;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.util.mvc.admin.MVCAdminJspBean;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

/**
 * FrameViewJspBean
 */
@Controller( controllerJsp = "FrameView.jsp", controllerPath = "jsp/admin/plugins/gru/", right = "GRU_MANAGEMENT" )
public class FrameViewJspBean extends MVCAdminJspBean
{
    private static final String TEMPLATE_FRAME = "/admin/plugins/gru/frame_view.html";
    private static final String VIEW_FRAME = "viewFrame";
    private static final long serialVersionUID = 1L;

    /**
     * Builds the view for a frame
     * 
     * @param request
     *            The HTTP request
     * @return the view
     */
    @View( value = VIEW_FRAME, defaultView = true )
    public String viewFrame( HttpServletRequest request )
    {
        String strUrl = request.getParameter( Constants.PARAMETER_URL_FRAME );

        Customer customer = CustomerUtils.getCustomer( request );
        Map<String, String> mapParameters = new HashMap<String, String>( );
        mapParameters.put( Constants.PARAMETER_URL_FRAME, UrlUtils.encodeUrl( strUrl ) );

        if ( customer != null )
        {
            mapParameters.put( Constants.PARAMETER_ID_CUSTOMER, customer.getId( ) );
        }

        List<ActionPanel> listPanels = CustomerActionsService.getPanels( customer, getUser( ) );

        Map<String, Object> model = getModel( );
        model.put( Constants.MARK_ACTION_PANELS, listPanels );
        model.put( Constants.MARK_CUSTOMER, ( customer != null ) ? customer : new Customer( ) );
        model.put( Constants.MARK_URL_FRAME, strUrl );
        model.put( Constants.MARK_RETURN_URL,
                UrlUtils.buildReturnUrl( AppPathService.getBaseUrl( request ) + getControllerPath( ) + getControllerJsp( ), VIEW_FRAME, mapParameters ) );

        return getPage( "", TEMPLATE_FRAME, model );
    }
}
