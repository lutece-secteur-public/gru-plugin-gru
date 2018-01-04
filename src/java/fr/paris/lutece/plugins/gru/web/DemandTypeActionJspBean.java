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

import fr.paris.lutece.plugins.gru.business.demandtype.DemandTypeAction;
import fr.paris.lutece.plugins.gru.business.demandtype.DemandTypeActionHome;
import fr.paris.lutece.plugins.gru.business.demandtype.DemandTypeHome;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.util.url.UrlItem;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * This class provides the user interface to manage DemandTypeAction features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageDemandTypeActions.jsp", controllerPath = "jsp/admin/plugins/gru/", right = "GRU_DEMAND_MANAGEMENT" )
public class DemandTypeActionJspBean extends AbstractManageDemandJspBean
{
    // //////////////////////////////////////////////////////////////////////////
    // Constants

    /**
     * Generated serial id
     */
    private static final long serialVersionUID = 6752764568707843457L;

    // templates
    private static final String TEMPLATE_MANAGE_DEMANDTYPEACTIONS = "/admin/plugins/gru/demandtype/manage_demandtypeactions.html";
    private static final String TEMPLATE_CREATE_DEMANDTYPEACTION = "/admin/plugins/gru/demandtype/create_demandtypeaction.html";
    private static final String TEMPLATE_MODIFY_DEMANDTYPEACTION = "/admin/plugins/gru/demandtype/modify_demandtypeaction.html";

    // Parameters
    private static final String PARAMETER_ID_DEMANDTYPEACTION = "id";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_DEMANDTYPEACTIONS = "gru.manage_demandtypeactions.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_DEMANDTYPEACTION = "gru.modify_demandtypeaction.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_DEMANDTYPEACTION = "gru.create_demandtypeaction.pageTitle";

    // Markers
    private static final String MARK_DEMANDTYPEACTION_LIST = "demandtypeaction_list";
    private static final String MARK_DEMANDTYPEACTION = "demandtypeaction";
    private static final String MARK_DEMAND_TYPES_LIST = "demand_types_list";
    private static final String JSP_MANAGE_DEMANDTYPEACTIONS = "jsp/admin/plugins/gru/ManageDemandTypeActions.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_DEMANDTYPEACTION = "gru.message.confirmRemoveDemandTypeAction";
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "gru.model.entity.demandtypeaction.attribute.";

    // Views
    private static final String VIEW_MANAGE_DEMANDTYPEACTIONS = "manageDemandTypeActions";
    private static final String VIEW_CREATE_DEMANDTYPEACTION = "createDemandTypeAction";
    private static final String VIEW_MODIFY_DEMANDTYPEACTION = "modifyDemandTypeAction";

    // Actions
    private static final String ACTION_CREATE_DEMANDTYPEACTION = "createDemandTypeAction";
    private static final String ACTION_MODIFY_DEMANDTYPEACTION = "modifyDemandTypeAction";
    private static final String ACTION_REMOVE_DEMANDTYPEACTION = "removeDemandTypeAction";
    private static final String ACTION_CONFIRM_REMOVE_DEMANDTYPEACTION = "confirmRemoveDemandTypeAction";

    // Infos
    private static final String INFO_DEMANDTYPEACTION_CREATED = "gru.info.demandtypeaction.created";
    private static final String INFO_DEMANDTYPEACTION_UPDATED = "gru.info.demandtypeaction.updated";
    private static final String INFO_DEMANDTYPEACTION_REMOVED = "gru.info.demandtypeaction.removed";

    // Session variable to store working values
    private DemandTypeAction _demandtypeaction;

    /**
     * Build the Manage View
     * 
     * @param request
     *            The HTTP request
     * @return The page
     */
    @View( value = VIEW_MANAGE_DEMANDTYPEACTIONS, defaultView = true )
    public String getManageDemandTypeActions( HttpServletRequest request )
    {
        _demandtypeaction = null;

        List<DemandTypeAction> listDemandTypeActions = (List<DemandTypeAction>) DemandTypeActionHome.getDemandTypeActionsList( );
        Map<String, Object> model = getPaginatedListModel( request, MARK_DEMANDTYPEACTION_LIST, listDemandTypeActions, JSP_MANAGE_DEMANDTYPEACTIONS );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_DEMANDTYPEACTIONS, TEMPLATE_MANAGE_DEMANDTYPEACTIONS, model );
    }

    /**
     * Returns the form to create a demandtypeaction
     *
     * @param request
     *            The Http request
     * @return the html code of the demandtypeaction form
     */
    @View( VIEW_CREATE_DEMANDTYPEACTION )
    public String getCreateDemandTypeAction( HttpServletRequest request )
    {
        _demandtypeaction = ( _demandtypeaction != null ) ? _demandtypeaction : new DemandTypeAction( );

        Map<String, Object> model = getModel( );
        model.put( MARK_DEMANDTYPEACTION, _demandtypeaction );
        model.put( MARK_DEMAND_TYPES_LIST, DemandTypeHome.getDemandTypes( ) );

        return getPage( PROPERTY_PAGE_TITLE_CREATE_DEMANDTYPEACTION, TEMPLATE_CREATE_DEMANDTYPEACTION, model );
    }

    /**
     * Process the data capture form of a new demandtypeaction
     *
     * @param request
     *            The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_DEMANDTYPEACTION )
    public String doCreateDemandTypeAction( HttpServletRequest request )
    {
        populate( _demandtypeaction, request );

        // Check constraints
        if ( !validateBean( _demandtypeaction, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_DEMANDTYPEACTION );
        }

        DemandTypeActionHome.create( _demandtypeaction );
        addInfo( INFO_DEMANDTYPEACTION_CREATED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_DEMANDTYPEACTIONS );
    }

    /**
     * Manages the removal form of a demandtypeaction whose identifier is in the http request
     *
     * @param request
     *            The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_DEMANDTYPEACTION )
    public String getConfirmRemoveDemandTypeAction( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_DEMANDTYPEACTION ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_DEMANDTYPEACTION ) );
        url.addParameter( PARAMETER_ID_DEMANDTYPEACTION, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_DEMANDTYPEACTION, url.getUrl( ),
                AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a demandtypeaction
     *
     * @param request
     *            The Http request
     * @return the jsp URL to display the form to manage demandtypeactions
     */
    @Action( ACTION_REMOVE_DEMANDTYPEACTION )
    public String doRemoveDemandTypeAction( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_DEMANDTYPEACTION ) );
        DemandTypeActionHome.remove( nId );
        addInfo( INFO_DEMANDTYPEACTION_REMOVED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_DEMANDTYPEACTIONS );
    }

    /**
     * Returns the form to update info about a demandtypeaction
     *
     * @param request
     *            The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_DEMANDTYPEACTION )
    public String getModifyDemandTypeAction( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_DEMANDTYPEACTION ) );

        if ( ( _demandtypeaction == null ) || ( _demandtypeaction.getId( ) != nId ) )
        {
            _demandtypeaction = DemandTypeActionHome.findByPrimaryKey( nId );
        }

        Map<String, Object> model = getModel( );
        model.put( MARK_DEMANDTYPEACTION, _demandtypeaction );
        model.put( MARK_DEMAND_TYPES_LIST, DemandTypeHome.getDemandTypes( ) );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_DEMANDTYPEACTION, TEMPLATE_MODIFY_DEMANDTYPEACTION, model );
    }

    /**
     * Process the change form of a demandtypeaction
     *
     * @param request
     *            The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_DEMANDTYPEACTION )
    public String doModifyDemandTypeAction( HttpServletRequest request )
    {
        populate( _demandtypeaction, request );

        // Check constraints
        if ( !validateBean( _demandtypeaction, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_DEMANDTYPEACTION, PARAMETER_ID_DEMANDTYPEACTION, _demandtypeaction.getId( ) );
        }

        DemandTypeActionHome.update( _demandtypeaction );
        addInfo( INFO_DEMANDTYPEACTION_UPDATED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_DEMANDTYPEACTIONS );
    }
}
