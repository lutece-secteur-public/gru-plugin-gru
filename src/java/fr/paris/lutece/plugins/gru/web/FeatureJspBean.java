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

import fr.paris.lutece.plugins.gru.business.feature.Feature;
import fr.paris.lutece.plugins.gru.business.feature.FeatureCategoryHome;
import fr.paris.lutece.plugins.gru.business.feature.FeatureHome;
import fr.paris.lutece.plugins.gru.service.ActionLinkService;
import fr.paris.lutece.plugins.gru.service.FeatureService;
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
 * This class provides the user interface to manage Feature features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageFeatures.jsp", controllerPath = "jsp/admin/plugins/gru/", right = "GRU_ADMIN_MANAGEMENT" )
public class FeatureJspBean extends ManageAdminGRUJspBean
{
    ////////////////////////////////////////////////////////////////////////////
    // Constants

    // templates
    private static final String TEMPLATE_MANAGE_FEATURES = "/admin/plugins/gru/manage_features.html";
    private static final String TEMPLATE_CREATE_FEATURE = "/admin/plugins/gru/create_feature.html";
    private static final String TEMPLATE_MODIFY_FEATURE = "/admin/plugins/gru/modify_feature.html";

    // Parameters
    private static final String PARAMETER_ID_FEATURE = "id";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_FEATURES = "gru.manage_features.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_FEATURE = "gru.modify_feature.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_FEATURE = "gru.create_feature.pageTitle";

    // Markers
    private static final String MARK_FEATURE_LIST = "feature_list";
    private static final String MARK_CATEGORIES_LIST = "categories_list";
    private static final String MARK_TARGETS_LIST = "targets_list";
    private static final String MARK_DISPLAY_LEVELS_LIST = "display_levels_list";
    private static final String MARK_FEATURE = "feature";
    private static final String JSP_MANAGE_FEATURES = "jsp/admin/plugins/gru/ManageFeatures.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_FEATURE = "gru.message.confirmRemoveFeature";
    private static final String PROPERTY_DEFAULT_LIST_FEATURE_PER_PAGE = "gru.listFeatures.itemsPerPage";
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "gru.model.entity.feature.attribute.";

    // Views
    private static final String VIEW_MANAGE_FEATURES = "manageFeatures";
    private static final String VIEW_CREATE_FEATURE = "createFeature";
    private static final String VIEW_MODIFY_FEATURE = "modifyFeature";

    // Actions
    private static final String ACTION_CREATE_FEATURE = "createFeature";
    private static final String ACTION_MODIFY_FEATURE = "modifyFeature";
    private static final String ACTION_REMOVE_FEATURE = "removeFeature";
    private static final String ACTION_CONFIRM_REMOVE_FEATURE = "confirmRemoveFeature";

    // Infos
    private static final String INFO_FEATURE_CREATED = "gru.info.feature.created";
    private static final String INFO_FEATURE_UPDATED = "gru.info.feature.updated";
    private static final String INFO_FEATURE_REMOVED = "gru.info.feature.removed";

    // Session variable to store working values
    private Feature _feature;

    /**
     * Build the Manage View
     * @param request The HTTP request
     * @return The page
     */
    @View( value = VIEW_MANAGE_FEATURES, defaultView = true )
    public String getManageFeatures( HttpServletRequest request )
    {
        _feature = null;

        List<Feature> listFeatures = (List<Feature>) FeatureHome.getFeaturesList(  );
        Map<String, Object> model = getPaginatedListModel( request, MARK_FEATURE_LIST, listFeatures, JSP_MANAGE_FEATURES );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_FEATURES, TEMPLATE_MANAGE_FEATURES, model );
    }

    /**
     * Returns the form to create a feature
     *
     * @param request The Http request
     * @return the html code of the feature form
     */
    @View( VIEW_CREATE_FEATURE )
    public String getCreateFeature( HttpServletRequest request )
    {
        _feature = ( _feature != null ) ? _feature : new Feature(  );

        Map<String, Object> model = getModel(  );
        model.put( MARK_FEATURE, _feature );
        model.put( MARK_CATEGORIES_LIST, FeatureCategoryHome.getCategoriesList(  ) );
        model.put( MARK_TARGETS_LIST, ActionLinkService.getTargetList( getLocale(  ) ) );
        model.put( MARK_DISPLAY_LEVELS_LIST, FeatureService.getFeatureDisplayLevels( getLocale() ) );

        return getPage( PROPERTY_PAGE_TITLE_CREATE_FEATURE, TEMPLATE_CREATE_FEATURE, model );
    }

    /**
     * Process the data capture form of a new feature     *
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_FEATURE )
    public String doCreateFeature( HttpServletRequest request )
    {
        populate( _feature, request );

        // Check constraints
        if ( !validateBean( _feature, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_FEATURE );
        }

        FeatureHome.create( _feature );
        addInfo( INFO_FEATURE_CREATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_FEATURES );
    }

    /**
     * Manages the removal form of a feature whose identifier is in the http
     * request
     *
     * @param request The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_FEATURE )
    public String getConfirmRemoveFeature( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_FEATURE ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_FEATURE ) );
        url.addParameter( PARAMETER_ID_FEATURE, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_FEATURE,
                url.getUrl(  ), AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a feature
     *
     * @param request The Http request
     * @return the jsp URL to display the form to manage features
     */
    @Action( ACTION_REMOVE_FEATURE )
    public String doRemoveFeature( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_FEATURE ) );
        FeatureHome.remove( nId );
        addInfo( INFO_FEATURE_REMOVED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_FEATURES );
    }

    /**
     * Returns the form to update info about a feature
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_FEATURE )
    public String getModifyFeature( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_FEATURE ) );

        if ( ( _feature == null ) || ( _feature.getId(  ) != nId ) )
        {
            _feature = FeatureHome.findByPrimaryKey( nId );
        }

        Map<String, Object> model = getModel(  );
        model.put( MARK_CATEGORIES_LIST, FeatureCategoryHome.getCategoriesList(  ) );
        model.put( MARK_TARGETS_LIST, ActionLinkService.getTargetList( getLocale(  ) ) );
        model.put( MARK_DISPLAY_LEVELS_LIST, FeatureService.getFeatureDisplayLevels( getLocale() ) );
        model.put( MARK_FEATURE, _feature );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_FEATURE, TEMPLATE_MODIFY_FEATURE, model );
    }

    /**
     * Process the change form of a feature
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_FEATURE )
    public String doModifyFeature( HttpServletRequest request )
    {
        populate( _feature, request );

        // Check constraints
        if ( !validateBean( _feature, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_FEATURE, PARAMETER_ID_FEATURE, _feature.getId(  ) );
        }

        FeatureHome.update( _feature );
        addInfo( INFO_FEATURE_UPDATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_FEATURES );
    }
}
