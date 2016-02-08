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

import fr.paris.lutece.plugins.gru.business.feature.FeatureCategory;
import fr.paris.lutece.plugins.gru.business.feature.FeatureCategoryHome;
import fr.paris.lutece.plugins.gru.utils.ColorService;
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
 * This class provides the user interface to manage FeatureCategory features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageFeatureCategorys.jsp", controllerPath = "jsp/admin/plugins/gru/", right = "GRU_ADMIN_MANAGEMENT" )
public class FeatureCategoryJspBean extends ManageAdminGRUJspBean
{
    ////////////////////////////////////////////////////////////////////////////
    // Constants

    // templates
    private static final String TEMPLATE_MANAGE_FEATURECATEGORYS = "/admin/plugins/gru/manage_featurecategories.html";
    private static final String TEMPLATE_CREATE_FEATURECATEGORY = "/admin/plugins/gru/create_featurecategory.html";
    private static final String TEMPLATE_MODIFY_FEATURECATEGORY = "/admin/plugins/gru/modify_featurecategory.html";

    // Parameters
    private static final String PARAMETER_ID_FEATURECATEGORY = "id";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_FEATURECATEGORYS = "gru.manage_featurecategories.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_FEATURECATEGORY = "gru.modify_featurecategory.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_FEATURECATEGORY = "gru.create_featurecategory.pageTitle";

    // Markers
    private static final String MARK_FEATURECATEGORY_LIST = "featurecategory_list";
    private static final String MARK_FEATURECATEGORY = "featurecategory";
    private static final String JSP_MANAGE_FEATURECATEGORYS = "jsp/admin/plugins/gru/ManageFeatureCategorys.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_FEATURECATEGORY = "gru.message.confirmRemoveFeatureCategory";
    private static final String PROPERTY_DEFAULT_LIST_FEATURECATEGORY_PER_PAGE = "gru.listFeatureCategorys.itemsPerPage";
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "gru.model.entity.featurecategory.attribute.";

    // Views
    private static final String VIEW_MANAGE_FEATURECATEGORYS = "manageFeatureCategorys";
    private static final String VIEW_CREATE_FEATURECATEGORY = "createFeatureCategory";
    private static final String VIEW_MODIFY_FEATURECATEGORY = "modifyFeatureCategory";

    // Actions
    private static final String ACTION_CREATE_FEATURECATEGORY = "createFeatureCategory";
    private static final String ACTION_MODIFY_FEATURECATEGORY = "modifyFeatureCategory";
    private static final String ACTION_REMOVE_FEATURECATEGORY = "removeFeatureCategory";
    private static final String ACTION_CONFIRM_REMOVE_FEATURECATEGORY = "confirmRemoveFeatureCategory";

    // Infos
    private static final String INFO_FEATURECATEGORY_CREATED = "gru.info.featurecategory.created";
    private static final String INFO_FEATURECATEGORY_UPDATED = "gru.info.featurecategory.updated";
    private static final String INFO_FEATURECATEGORY_REMOVED = "gru.info.featurecategory.removed";

    // Session variable to store working values
    private FeatureCategory _featurecategory;

    /**
     * Build the Manage View
     * @param request The HTTP request
     * @return The page
     */
    @View( value = VIEW_MANAGE_FEATURECATEGORYS, defaultView = true )
    public String getManageFeatureCategorys( HttpServletRequest request )
    {
        _featurecategory = null;

        List<FeatureCategory> listFeatureCategorys = (List<FeatureCategory>) FeatureCategoryHome.getFeatureCategorysList(  );
        Map<String, Object> model = getPaginatedListModel( request, MARK_FEATURECATEGORY_LIST, listFeatureCategorys,
                JSP_MANAGE_FEATURECATEGORYS );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_FEATURECATEGORYS, TEMPLATE_MANAGE_FEATURECATEGORYS, model );
    }

    /**
     * Returns the form to create a featurecategory
     *
     * @param request The Http request
     * @return the html code of the featurecategory form
     */
    @View( VIEW_CREATE_FEATURECATEGORY )
    public String getCreateFeatureCategory( HttpServletRequest request )
    {
        _featurecategory = ( _featurecategory != null ) ? _featurecategory : new FeatureCategory(  );

        Map<String, Object> model = getModel(  );
        model.put( MARK_FEATURECATEGORY, _featurecategory );
        model.put( Constants.MARK_COLORS_LIST, ColorService.getColorList(  ) );

        return getPage( PROPERTY_PAGE_TITLE_CREATE_FEATURECATEGORY, TEMPLATE_CREATE_FEATURECATEGORY, model );
    }

    /**
     * Process the data capture form of a new featurecategory
     *
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_FEATURECATEGORY )
    public String doCreateFeatureCategory( HttpServletRequest request )
    {
        populate( _featurecategory, request );

        // Check constraints
        if ( !validateBean( _featurecategory, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_FEATURECATEGORY );
        }

        FeatureCategoryHome.create( _featurecategory );
        addInfo( INFO_FEATURECATEGORY_CREATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_FEATURECATEGORYS );
    }

    /**
     * Manages the removal form of a featurecategory whose identifier is in the http
     * request
     *
     * @param request The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_FEATURECATEGORY )
    public String getConfirmRemoveFeatureCategory( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_FEATURECATEGORY ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_FEATURECATEGORY ) );
        url.addParameter( PARAMETER_ID_FEATURECATEGORY, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_FEATURECATEGORY,
                url.getUrl(  ), AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a featurecategory
     *
     * @param request The Http request
     * @return the jsp URL to display the form to manage featurecategorys
     */
    @Action( ACTION_REMOVE_FEATURECATEGORY )
    public String doRemoveFeatureCategory( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_FEATURECATEGORY ) );
        FeatureCategoryHome.remove( nId );
        addInfo( INFO_FEATURECATEGORY_REMOVED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_FEATURECATEGORYS );
    }

    /**
     * Returns the form to update info about a featurecategory
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_FEATURECATEGORY )
    public String getModifyFeatureCategory( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_FEATURECATEGORY ) );

        if ( ( _featurecategory == null ) || ( _featurecategory.getId(  ) != nId ) )
        {
            _featurecategory = FeatureCategoryHome.findByPrimaryKey( nId );
        }

        Map<String, Object> model = getModel(  );
        model.put( MARK_FEATURECATEGORY, _featurecategory );
        model.put( Constants.MARK_COLORS_LIST, ColorService.getColorList(  ) );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_FEATURECATEGORY, TEMPLATE_MODIFY_FEATURECATEGORY, model );
    }

    /**
     * Process the change form of a featurecategory
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_FEATURECATEGORY )
    public String doModifyFeatureCategory( HttpServletRequest request )
    {
        populate( _featurecategory, request );

        // Check constraints
        if ( !validateBean( _featurecategory, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_FEATURECATEGORY, PARAMETER_ID_FEATURECATEGORY,
                _featurecategory.getId(  ) );
        }

        FeatureCategoryHome.update( _featurecategory );
        addInfo( INFO_FEATURECATEGORY_UPDATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_FEATURECATEGORYS );
    }
}
