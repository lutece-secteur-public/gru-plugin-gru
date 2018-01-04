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

import fr.paris.lutece.plugins.gru.business.demandtype.DemandType;
import fr.paris.lutece.plugins.gru.business.demandtype.DemandTypeHome;
import fr.paris.lutece.plugins.gru.service.demandtype.DemandTypeService;
import fr.paris.lutece.plugins.gru.service.domain.BusinessDomainService;
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
 * This class provides the user interface to manage DemandType features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageDemandTypes.jsp", controllerPath = "jsp/admin/plugins/gru/", right = "GRU_DEMAND_MANAGEMENT" )
public class DemandTypeJspBean extends ManageDemandJspBean
{
    // //////////////////////////////////////////////////////////////////////////
    // Constants

    // templates
    private static final String TEMPLATE_MANAGE_DEMANDTYPES = "/admin/plugins/gru/demandtype/manage_demandtypes.html";
    private static final String TEMPLATE_CREATE_DEMANDTYPE = "/admin/plugins/gru/demandtype/create_demandtype.html";
    private static final String TEMPLATE_MODIFY_DEMANDTYPE = "/admin/plugins/gru/demandtype/modify_demandtype.html";

    // Parameters
    private static final String PARAMETER_ID_DEMANDTYPE = "id";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_DEMANDTYPES = "gru.manage_demandtypes.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_DEMANDTYPE = "gru.modify_demandtype.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_DEMANDTYPE = "gru.create_demandtype.pageTitle";

    // Markers
    private static final String MARK_DEMANDTYPE_LIST = "demandtype_list";
    private static final String MARK_DEMANDTYPE = "demandtype";
    private static final String MARK_BUSINESS_DOMAINS_LIST = "domains_list";
    private static final String JSP_MANAGE_DEMANDTYPES = "jsp/admin/plugins/gru/ManageDemandTypes.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_DEMANDTYPE = "gru.message.confirmRemoveDemandType";
    private static final String PROPERTY_DEFAULT_LIST_DEMANDTYPE_PER_PAGE = "gru.listDemandTypes.itemsPerPage";
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "gru.model.entity.demandtype.attribute.";

    // Views
    private static final String VIEW_MANAGE_DEMANDTYPES = "manageDemandTypes";
    private static final String VIEW_CREATE_DEMANDTYPE = "createDemandType";
    private static final String VIEW_MODIFY_DEMANDTYPE = "modifyDemandType";

    // Actions
    private static final String ACTION_CREATE_DEMANDTYPE = "createDemandType";
    private static final String ACTION_MODIFY_DEMANDTYPE = "modifyDemandType";
    private static final String ACTION_REMOVE_DEMANDTYPE = "removeDemandType";
    private static final String ACTION_CONFIRM_REMOVE_DEMANDTYPE = "confirmRemoveDemandType";

    // Infos
    private static final String INFO_DEMANDTYPE_CREATED = "gru.info.demandtype.created";
    private static final String INFO_DEMANDTYPE_UPDATED = "gru.info.demandtype.updated";
    private static final String INFO_DEMANDTYPE_REMOVED = "gru.info.demandtype.removed";

    // Session variable to store working values
    private DemandType _demandtype;

    /**
     * Build the Manage View
     * 
     * @param request
     *            The HTTP request
     * @return The page
     */
    @View( value = VIEW_MANAGE_DEMANDTYPES, defaultView = true )
    public String getManageDemandTypes( HttpServletRequest request )
    {
        _demandtype = null;

        List<DemandType> listDemandTypes = (List<DemandType>) DemandTypeHome.getDemandTypesList( );
        Map<String, Object> model = getPaginatedListModel( request, MARK_DEMANDTYPE_LIST, listDemandTypes, JSP_MANAGE_DEMANDTYPES );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_DEMANDTYPES, TEMPLATE_MANAGE_DEMANDTYPES, model );
    }

    /**
     * Returns the form to create a demandtype
     *
     * @param request
     *            The Http request
     * @return the html code of the demandtype form
     */
    @View( VIEW_CREATE_DEMANDTYPE )
    public String getCreateDemandType( HttpServletRequest request )
    {
        _demandtype = ( _demandtype != null ) ? _demandtype : new DemandType( );

        Map<String, Object> model = getModel( );
        model.put( MARK_DEMANDTYPE, _demandtype );
        model.put( MARK_BUSINESS_DOMAINS_LIST, BusinessDomainService.getDomains( getLocale( ) ) );

        return getPage( PROPERTY_PAGE_TITLE_CREATE_DEMANDTYPE, TEMPLATE_CREATE_DEMANDTYPE, model );
    }

    /**
     * Process the data capture form of a new demandtype
     *
     * @param request
     *            The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_DEMANDTYPE )
    public String doCreateDemandType( HttpServletRequest request )
    {
        populate( _demandtype, request );

        // Check constraints
        if ( !validateBean( _demandtype, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_DEMANDTYPE );
        }

        DemandTypeHome.create( _demandtype );
        addInfo( INFO_DEMANDTYPE_CREATED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_DEMANDTYPES );
    }

    /**
     * Manages the removal form of a demandtype whose identifier is in the http request
     *
     * @param request
     *            The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_DEMANDTYPE )
    public String getConfirmRemoveDemandType( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_DEMANDTYPE ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_DEMANDTYPE ) );
        url.addParameter( PARAMETER_ID_DEMANDTYPE, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_DEMANDTYPE, url.getUrl( ), AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a demandtype
     *
     * @param request
     *            The Http request
     * @return the jsp URL to display the form to manage demandtypes
     */
    @Action( ACTION_REMOVE_DEMANDTYPE )
    public String doRemoveDemandType( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_DEMANDTYPE ) );
        DemandTypeHome.remove( nId );
        addInfo( INFO_DEMANDTYPE_REMOVED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_DEMANDTYPES );
    }

    /**
     * Returns the form to update info about a demandtype
     *
     * @param request
     *            The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_DEMANDTYPE )
    public String getModifyDemandType( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_DEMANDTYPE ) );

        if ( ( _demandtype == null ) || ( _demandtype.getId( ) != nId ) )
        {
            _demandtype = DemandTypeHome.findByPrimaryKey( nId );
        }

        Map<String, Object> model = getModel( );
        model.put( MARK_DEMANDTYPE, _demandtype );
        model.put( MARK_BUSINESS_DOMAINS_LIST, BusinessDomainService.getDomains( getLocale( ) ) );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_DEMANDTYPE, TEMPLATE_MODIFY_DEMANDTYPE, model );
    }

    /**
     * Process the change form of a demandtype
     *
     * @param request
     *            The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_DEMANDTYPE )
    public String doModifyDemandType( HttpServletRequest request )
    {
        populate( _demandtype, request );

        // Check constraints
        if ( !validateBean( _demandtype, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_DEMANDTYPE, PARAMETER_ID_DEMANDTYPE, _demandtype.getId( ) );
        }

        DemandTypeHome.update( _demandtype );
        DemandTypeService.updateDemandType( _demandtype );
        addInfo( INFO_DEMANDTYPE_UPDATED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_DEMANDTYPES );
    }
}
