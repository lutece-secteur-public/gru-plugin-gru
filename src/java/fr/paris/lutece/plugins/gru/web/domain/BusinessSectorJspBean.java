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
package fr.paris.lutece.plugins.gru.web.domain;

import fr.paris.lutece.plugins.gru.business.domain.BusinessSector;
import fr.paris.lutece.plugins.gru.business.domain.BusinessSectorHome;
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
 * This class provides the user interface to manage BusinessSector features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageBusinessSectors.jsp", controllerPath = "jsp/admin/plugins/gru/", right = "GRU_DOMAIN_MANAGEMENT" )
public class BusinessSectorJspBean extends AbstractManageDomainGruJspBean
{
    // //////////////////////////////////////////////////////////////////////////
    // Constants

    /**
     * Generated serial id
     */
    private static final long serialVersionUID = -3901716328444597760L;

    // templates
    private static final String TEMPLATE_MANAGE_BUSINESSSECTORS = "/admin/plugins/gru/domain/manage_business_sectors.html";
    private static final String TEMPLATE_CREATE_BUSINESSSECTOR = "/admin/plugins/gru/domain/create_business_sector.html";
    private static final String TEMPLATE_MODIFY_BUSINESSSECTOR = "/admin/plugins/gru/domain/modify_business_sector.html";

    // Parameters
    private static final String PARAMETER_ID_BUSINESSSECTOR = "id";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_BUSINESSSECTORS = "gru.manage_businesssectors.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_BUSINESSSECTOR = "gru.modify_businesssector.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_BUSINESSSECTOR = "gru.create_businesssector.pageTitle";

    // Markers
    private static final String MARK_BUSINESSSECTOR_LIST = "businesssector_list";
    private static final String MARK_BUSINESSSECTOR = "businesssector";
    private static final String JSP_MANAGE_BUSINESSSECTORS = "jsp/admin/plugins/gru/ManageBusinessSectors.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_BUSINESSSECTOR = "gru.message.confirmRemoveBusinessSector";
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "gru.model.entity.businesssector.attribute.";

    // Views
    private static final String VIEW_MANAGE_BUSINESSSECTORS = "manageBusinessSectors";
    private static final String VIEW_CREATE_BUSINESSSECTOR = "createBusinessSector";
    private static final String VIEW_MODIFY_BUSINESSSECTOR = "modifyBusinessSector";

    // Actions
    private static final String ACTION_CREATE_BUSINESSSECTOR = "createBusinessSector";
    private static final String ACTION_MODIFY_BUSINESSSECTOR = "modifyBusinessSector";
    private static final String ACTION_REMOVE_BUSINESSSECTOR = "removeBusinessSector";
    private static final String ACTION_CONFIRM_REMOVE_BUSINESSSECTOR = "confirmRemoveBusinessSector";

    // Infos
    private static final String INFO_BUSINESSSECTOR_CREATED = "gru.info.businesssector.created";
    private static final String INFO_BUSINESSSECTOR_UPDATED = "gru.info.businesssector.updated";
    private static final String INFO_BUSINESSSECTOR_REMOVED = "gru.info.businesssector.removed";

    // Session variable to store working values
    private BusinessSector _businesssector;

    /**
     * Build the Manage View
     * 
     * @param request
     *            The HTTP request
     * @return The page
     */
    @View( value = VIEW_MANAGE_BUSINESSSECTORS, defaultView = true )
    public String getManageBusinessSectors( HttpServletRequest request )
    {
        _businesssector = null;

        List<BusinessSector> listBusinessSectors = (List<BusinessSector>) BusinessSectorHome.getBusinessSectorsList( );
        Map<String, Object> model = getPaginatedListModel( request, MARK_BUSINESSSECTOR_LIST, listBusinessSectors, JSP_MANAGE_BUSINESSSECTORS );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_BUSINESSSECTORS, TEMPLATE_MANAGE_BUSINESSSECTORS, model );
    }

    /**
     * Returns the form to create a businesssector
     *
     * @param request
     *            The Http request
     * @return the html code of the businesssector form
     */
    @View( VIEW_CREATE_BUSINESSSECTOR )
    public String getCreateBusinessSector( HttpServletRequest request )
    {
        _businesssector = ( _businesssector != null ) ? _businesssector : new BusinessSector( );

        Map<String, Object> model = getModel( );
        model.put( MARK_BUSINESSSECTOR, _businesssector );

        return getPage( PROPERTY_PAGE_TITLE_CREATE_BUSINESSSECTOR, TEMPLATE_CREATE_BUSINESSSECTOR, model );
    }

    /**
     * Process the data capture form of a new businesssector
     *
     * @param request
     *            The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_BUSINESSSECTOR )
    public String doCreateBusinessSector( HttpServletRequest request )
    {
        populate( _businesssector, request );

        // Check constraints
        if ( !validateBean( _businesssector, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_BUSINESSSECTOR );
        }

        BusinessSectorHome.create( _businesssector );
        addInfo( INFO_BUSINESSSECTOR_CREATED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_BUSINESSSECTORS );
    }

    /**
     * Manages the removal form of a businesssector whose identifier is in the http request
     *
     * @param request
     *            The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_BUSINESSSECTOR )
    public String getConfirmRemoveBusinessSector( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_BUSINESSSECTOR ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_BUSINESSSECTOR ) );
        url.addParameter( PARAMETER_ID_BUSINESSSECTOR, nId );

        String strMessageUrl = AdminMessageService
                .getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_BUSINESSSECTOR, url.getUrl( ), AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a businesssector
     *
     * @param request
     *            The Http request
     * @return the jsp URL to display the form to manage businesssectors
     */
    @Action( ACTION_REMOVE_BUSINESSSECTOR )
    public String doRemoveBusinessSector( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_BUSINESSSECTOR ) );
        BusinessSectorHome.remove( nId );
        addInfo( INFO_BUSINESSSECTOR_REMOVED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_BUSINESSSECTORS );
    }

    /**
     * Returns the form to update info about a businesssector
     *
     * @param request
     *            The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_BUSINESSSECTOR )
    public String getModifyBusinessSector( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_BUSINESSSECTOR ) );

        if ( ( _businesssector == null ) || ( _businesssector.getId( ) != nId ) )
        {
            _businesssector = BusinessSectorHome.findByPrimaryKey( nId );
        }

        Map<String, Object> model = getModel( );
        model.put( MARK_BUSINESSSECTOR, _businesssector );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_BUSINESSSECTOR, TEMPLATE_MODIFY_BUSINESSSECTOR, model );
    }

    /**
     * Process the change form of a businesssector
     *
     * @param request
     *            The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_BUSINESSSECTOR )
    public String doModifyBusinessSector( HttpServletRequest request )
    {
        populate( _businesssector, request );

        // Check constraints
        if ( !validateBean( _businesssector, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_BUSINESSSECTOR, PARAMETER_ID_BUSINESSSECTOR, _businesssector.getId( ) );
        }

        BusinessSectorHome.update( _businesssector );
        addInfo( INFO_BUSINESSSECTOR_UPDATED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_BUSINESSSECTORS );
    }
}
