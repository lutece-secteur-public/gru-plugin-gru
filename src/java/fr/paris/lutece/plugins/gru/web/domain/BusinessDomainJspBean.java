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

 
package fr.paris.lutece.plugins.gru.web.domain;

import fr.paris.lutece.plugins.gru.business.domain.BusinessDomain;
import fr.paris.lutece.plugins.gru.business.domain.BusinessDomainHome;
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
 * This class provides the user interface to manage BusinessDomain features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageBusinessDomains.jsp", controllerPath = "jsp/admin/plugins/gru/", right = "GRU_DOMAIN_MANAGEMENT" )
public class BusinessDomainJspBean extends ManageDomainGruJspBean
{

    ////////////////////////////////////////////////////////////////////////////
    // Constants

    // templates
    private static final String TEMPLATE_MANAGE_BUSINESSDOMAINS = "/admin/plugins/gru/domain/manage_business_domains.html";
    private static final String TEMPLATE_CREATE_BUSINESSDOMAIN = "/admin/plugins/gru/domain/create_business_domain.html";
    private static final String TEMPLATE_MODIFY_BUSINESSDOMAIN = "/admin/plugins/gru/domain/modify_business_domain.html";


    // Parameters
    private static final String PARAMETER_ID_BUSINESSDOMAIN = "id";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_BUSINESSDOMAINS = "gru.manage_businessdomains.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_BUSINESSDOMAIN = "gru.modify_businessdomain.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_BUSINESSDOMAIN = "gru.create_businessdomain.pageTitle";

    // Markers
    private static final String MARK_BUSINESS_DOMAIN_LIST = "businessdomain_list";
    private static final String MARK_BUSINESS_DOMAIN = "businessdomain";
    private static final String MARK_BUSINESS_SECTORS_LIST = "business_sectors_list";

    private static final String JSP_MANAGE_BUSINESSDOMAINS = "jsp/admin/plugins/gru/ManageBusinessDomains.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_BUSINESSDOMAIN = "gru.message.confirmRemoveBusinessDomain";
    private static final String PROPERTY_DEFAULT_LIST_BUSINESSDOMAIN_PER_PAGE = "gru.listBusinessDomains.itemsPerPage";
 
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "gru.model.entity.businessdomain.attribute.";

    // Views
    private static final String VIEW_MANAGE_BUSINESSDOMAINS = "manageBusinessDomains";
    private static final String VIEW_CREATE_BUSINESSDOMAIN = "createBusinessDomain";
    private static final String VIEW_MODIFY_BUSINESSDOMAIN = "modifyBusinessDomain";

    // Actions
    private static final String ACTION_CREATE_BUSINESSDOMAIN = "createBusinessDomain";
    private static final String ACTION_MODIFY_BUSINESSDOMAIN = "modifyBusinessDomain";
    private static final String ACTION_REMOVE_BUSINESSDOMAIN = "removeBusinessDomain";
    private static final String ACTION_CONFIRM_REMOVE_BUSINESSDOMAIN = "confirmRemoveBusinessDomain";

    // Infos
    private static final String INFO_BUSINESSDOMAIN_CREATED = "gru.info.businessdomain.created";
    private static final String INFO_BUSINESSDOMAIN_UPDATED = "gru.info.businessdomain.updated";
    private static final String INFO_BUSINESSDOMAIN_REMOVED = "gru.info.businessdomain.removed";
    
    // Session variable to store working values
    private BusinessDomain _businessdomain;
    
    
    /**
     * Build the Manage View
     * @param request The HTTP request
     * @return The page
     */
    @View( value = VIEW_MANAGE_BUSINESSDOMAINS, defaultView = true )
    public String getManageBusinessDomains( HttpServletRequest request )
    {
        _businessdomain = null;
        List<BusinessDomain> listBusinessDomains = (List<BusinessDomain>) BusinessDomainHome.getBusinessDomainsList(  );
        Map<String, Object> model = getPaginatedListModel( request, MARK_BUSINESS_DOMAIN_LIST, listBusinessDomains, JSP_MANAGE_BUSINESSDOMAINS );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_BUSINESSDOMAINS, TEMPLATE_MANAGE_BUSINESSDOMAINS, model );
    }

    /**
     * Returns the form to create a businessdomain
     *
     * @param request The Http request
     * @return the html code of the businessdomain form
     */
    @View( VIEW_CREATE_BUSINESSDOMAIN )
    public String getCreateBusinessDomain( HttpServletRequest request )
    {
        _businessdomain = ( _businessdomain != null ) ? _businessdomain : new BusinessDomain(  );

        Map<String, Object> model = getModel(  );
        model.put( MARK_BUSINESS_DOMAIN, _businessdomain );
        model.put( MARK_BUSINESS_SECTORS_LIST, BusinessSectorHome.getBusinessSectors() );

        return getPage( PROPERTY_PAGE_TITLE_CREATE_BUSINESSDOMAIN, TEMPLATE_CREATE_BUSINESSDOMAIN, model );
    }

    /**
     * Process the data capture form of a new businessdomain
     *
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_BUSINESSDOMAIN )
    public String doCreateBusinessDomain( HttpServletRequest request )
    {
        populate( _businessdomain, request );

        // Check constraints
        if ( !validateBean( _businessdomain, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_BUSINESSDOMAIN );
        }

        BusinessDomainHome.create( _businessdomain );
        addInfo( INFO_BUSINESSDOMAIN_CREATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_BUSINESSDOMAINS );
    }

    /**
     * Manages the removal form of a businessdomain whose identifier is in the http
     * request
     *
     * @param request The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_BUSINESSDOMAIN )
    public String getConfirmRemoveBusinessDomain( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_BUSINESSDOMAIN ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_BUSINESSDOMAIN ) );
        url.addParameter( PARAMETER_ID_BUSINESSDOMAIN, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_BUSINESSDOMAIN,
                url.getUrl(  ), AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a businessdomain
     *
     * @param request The Http request
     * @return the jsp URL to display the form to manage businessdomains
     */
    @Action( ACTION_REMOVE_BUSINESSDOMAIN )
    public String doRemoveBusinessDomain( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_BUSINESSDOMAIN ) );
        BusinessDomainHome.remove( nId );
        addInfo( INFO_BUSINESSDOMAIN_REMOVED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_BUSINESSDOMAINS );
    }

    /**
     * Returns the form to update info about a businessdomain
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_BUSINESSDOMAIN )
    public String getModifyBusinessDomain( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_BUSINESSDOMAIN ) );

        if ( _businessdomain == null || ( _businessdomain.getId(  ) != nId ))
        {
            _businessdomain = BusinessDomainHome.findByPrimaryKey( nId );
        }

        Map<String, Object> model = getModel(  );
        model.put( MARK_BUSINESS_DOMAIN, _businessdomain );
        model.put( MARK_BUSINESS_SECTORS_LIST, BusinessSectorHome.getBusinessSectors() );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_BUSINESSDOMAIN, TEMPLATE_MODIFY_BUSINESSDOMAIN, model );
    }

    /**
     * Process the change form of a businessdomain
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_BUSINESSDOMAIN )
    public String doModifyBusinessDomain( HttpServletRequest request )
    {
        populate( _businessdomain, request );

        // Check constraints
        if ( !validateBean( _businessdomain, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_BUSINESSDOMAIN, PARAMETER_ID_BUSINESSDOMAIN, _businessdomain.getId( ) );
        }

        BusinessDomainHome.update( _businessdomain );
        addInfo( INFO_BUSINESSDOMAIN_UPDATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_BUSINESSDOMAINS );
    }
}
