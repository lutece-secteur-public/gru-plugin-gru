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
package fr.paris.lutece.plugins.gru.web;

import fr.paris.lutece.plugins.gru.business.ExtrasAttributes;
import fr.paris.lutece.plugins.gru.business.ExtrasAttributesHome;
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
 * This class provides the user interface to manage ExtrasAttributes features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageExtrasAttributess.jsp", controllerPath = "jsp/admin/plugins/gru/", right = "GRU_ADMIN_MANAGEMENT" )
public class ExtrasAttributesJspBean extends ManageAdminGRUJspBean
{
    // //////////////////////////////////////////////////////////////////////////
    // Constants

    // templates
    private static final String TEMPLATE_MANAGE_EXTRASATTRIBUTESS = "/admin/plugins/gru/manage_extrasattributess.html";
    private static final String TEMPLATE_CREATE_EXTRASATTRIBUTES = "/admin/plugins/gru/create_extrasattributes.html";
    private static final String TEMPLATE_MODIFY_EXTRASATTRIBUTES = "/admin/plugins/gru/modify_extrasattributes.html";

    // Parameters
    private static final String PARAMETER_ID_EXTRASATTRIBUTES = "id";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_EXTRASATTRIBUTESS = "gru.manage_extrasattributess.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_EXTRASATTRIBUTES = "gru.modify_extrasattributes.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_EXTRASATTRIBUTES = "gru.create_extrasattributes.pageTitle";

    // Markers
    private static final String MARK_EXTRASATTRIBUTES_LIST = "extrasattributes_list";
    private static final String MARK_EXTRASATTRIBUTES = "extrasattributes";
    private static final String JSP_MANAGE_EXTRASATTRIBUTESS = "jsp/admin/plugins/gru/ManageExtrasAttributess.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_EXTRASATTRIBUTES = "gru.message.confirmRemoveExtrasAttributes";
    private static final String PROPERTY_DEFAULT_LIST_EXTRASATTRIBUTES_PER_PAGE = "gru.listExtrasAttributess.itemsPerPage";
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "gru.model.entity.extrasattributes.attribute.";

    // Views
    private static final String VIEW_MANAGE_EXTRASATTRIBUTESS = "manageExtrasAttributess";
    private static final String VIEW_CREATE_EXTRASATTRIBUTES = "createExtrasAttributes";
    private static final String VIEW_MODIFY_EXTRASATTRIBUTES = "modifyExtrasAttributes";

    // Actions
    private static final String ACTION_CREATE_EXTRASATTRIBUTES = "createExtrasAttributes";
    private static final String ACTION_MODIFY_EXTRASATTRIBUTES = "modifyExtrasAttributes";
    private static final String ACTION_REMOVE_EXTRASATTRIBUTES = "removeExtrasAttributes";
    private static final String ACTION_CONFIRM_REMOVE_EXTRASATTRIBUTES = "confirmRemoveExtrasAttributes";

    // Infos
    private static final String INFO_EXTRASATTRIBUTES_CREATED = "gru.info.extrasattributes.created";
    private static final String INFO_EXTRASATTRIBUTES_UPDATED = "gru.info.extrasattributes.updated";
    private static final String INFO_EXTRASATTRIBUTES_REMOVED = "gru.info.extrasattributes.removed";

    // Session variable to store working values
    private ExtrasAttributes _extrasattributes;

    /**
     * Build the Manage View
     * 
     * @param request
     *            The HTTP request
     * @return The page
     */
    @View( value = VIEW_MANAGE_EXTRASATTRIBUTESS, defaultView = true )
    public String getManageExtrasAttributess( HttpServletRequest request )
    {
        _extrasattributes = null;

        List<ExtrasAttributes> listExtrasAttributess = (List<ExtrasAttributes>) ExtrasAttributesHome.getExtrasAttributessList( );
        Map<String, Object> model = getPaginatedListModel( request, MARK_EXTRASATTRIBUTES_LIST, listExtrasAttributess, JSP_MANAGE_EXTRASATTRIBUTESS );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_EXTRASATTRIBUTESS, TEMPLATE_MANAGE_EXTRASATTRIBUTESS, model );
    }

    /**
     * Returns the form to create a extrasattributes
     *
     * @param request
     *            The Http request
     * @return the html code of the extrasattributes form
     */
    @View( VIEW_CREATE_EXTRASATTRIBUTES )
    public String getCreateExtrasAttributes( HttpServletRequest request )
    {
        _extrasattributes = ( _extrasattributes != null ) ? _extrasattributes : new ExtrasAttributes( );

        Map<String, Object> model = getModel( );
        model.put( MARK_EXTRASATTRIBUTES, _extrasattributes );

        return getPage( PROPERTY_PAGE_TITLE_CREATE_EXTRASATTRIBUTES, TEMPLATE_CREATE_EXTRASATTRIBUTES, model );
    }

    /**
     * Process the data capture form of a new extrasattributes
     *
     * @param request
     *            The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_EXTRASATTRIBUTES )
    public String doCreateExtrasAttributes( HttpServletRequest request )
    {
        populate( _extrasattributes, request );

        // Check constraints
        if ( !validateBean( _extrasattributes, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_EXTRASATTRIBUTES );
        }

        ExtrasAttributesHome.create( _extrasattributes );
        addInfo( INFO_EXTRASATTRIBUTES_CREATED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_EXTRASATTRIBUTESS );
    }

    /**
     * Manages the removal form of a extrasattributes whose identifier is in the http request
     *
     * @param request
     *            The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_EXTRASATTRIBUTES )
    public String getConfirmRemoveExtrasAttributes( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_EXTRASATTRIBUTES ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_EXTRASATTRIBUTES ) );
        url.addParameter( PARAMETER_ID_EXTRASATTRIBUTES, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_EXTRASATTRIBUTES, url.getUrl( ),
                AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a extrasattributes
     *
     * @param request
     *            The Http request
     * @return the jsp URL to display the form to manage extrasattributess
     */
    @Action( ACTION_REMOVE_EXTRASATTRIBUTES )
    public String doRemoveExtrasAttributes( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_EXTRASATTRIBUTES ) );
        ExtrasAttributesHome.remove( nId );
        addInfo( INFO_EXTRASATTRIBUTES_REMOVED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_EXTRASATTRIBUTESS );
    }

    /**
     * Returns the form to update info about a extrasattributes
     *
     * @param request
     *            The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_EXTRASATTRIBUTES )
    public String getModifyExtrasAttributes( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_EXTRASATTRIBUTES ) );

        if ( ( _extrasattributes == null ) || ( _extrasattributes.getId( ) != nId ) )
        {
            _extrasattributes = ExtrasAttributesHome.findByPrimaryKey( nId );
        }

        Map<String, Object> model = getModel( );
        model.put( MARK_EXTRASATTRIBUTES, _extrasattributes );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_EXTRASATTRIBUTES, TEMPLATE_MODIFY_EXTRASATTRIBUTES, model );
    }

    /**
     * Process the change form of a extrasattributes
     *
     * @param request
     *            The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_EXTRASATTRIBUTES )
    public String doModifyExtrasAttributes( HttpServletRequest request )
    {
        populate( _extrasattributes, request );

        // Check constraints
        if ( !validateBean( _extrasattributes, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_EXTRASATTRIBUTES, PARAMETER_ID_EXTRASATTRIBUTES, _extrasattributes.getId( ) );
        }

        ExtrasAttributesHome.update( _extrasattributes );
        addInfo( INFO_EXTRASATTRIBUTES_UPDATED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_EXTRASATTRIBUTESS );
    }
}
