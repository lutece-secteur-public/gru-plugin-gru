/*
 * Copyright (c) 2002-2016, Mairie de Paris
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.paris.lutece.plugins.gru.service.CustomerActionsService;
import fr.paris.lutece.plugins.gru.service.customer.CustomerService;
import fr.paris.lutece.plugins.gru.service.demand.DemandService;
import fr.paris.lutece.plugins.gru.service.demandtype.DemandTypeService;
import fr.paris.lutece.plugins.gru.utils.CustomerUtils;
import fr.paris.lutece.plugins.gru.utils.UrlUtils;
import fr.paris.lutece.plugins.gru.web.actions.buttons.builders.impl.HomeButtonListBuilder;
import fr.paris.lutece.plugins.gru.web.actions.model.ActionGroup;
import fr.paris.lutece.plugins.gru.web.actions.model.ActionPanel;
import fr.paris.lutece.plugins.gru.web.utils.ModelUtils;
import fr.paris.lutece.plugins.grubusiness.business.customer.Customer;
import fr.paris.lutece.plugins.grubusiness.business.demand.Demand;
import fr.paris.lutece.plugins.rest.service.RestConstants;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.prefs.AdminUserPreferencesService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.util.mvc.admin.MVCAdminJspBean;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * This class provides the user interface to manage Customer features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageCustomers.jsp", controllerPath = "jsp/admin/plugins/gru/", right = "GRU_MANAGEMENT" )
public class CustomerJspBean extends MVCAdminJspBean
{
    // //////////////////////////////////////////////////////////////////////////
    // Constants

    // templates
    private static final String TEMPLATE_SEARCH_CUSTOMER = "/admin/plugins/gru/search_customer.html";
    private static final String TEMPLATE_VIEW_CUSTOMER_DEMANDS = "/admin/plugins/gru/view_customer_demands.html";
    private static final String TEMPLATE_VIEW_CUSTOMER_OLD_DEMANDS = "/admin/plugins/gru/view_customer_old_demands.html";
    private static final String TEMPLATE_VIEW_CUSTOMER_NEW_DEMANDS = "/admin/plugins/gru/view_customer_new_demands.html";
    private static final String TEMPLATE_VIEW_DEMAND = "/admin/plugins/gru/view_demand.html";
    private static final String TEMPLATE_SEARCH_RESULTS = "/admin/plugins/gru/search_results.html";

    // Messages for page titles
    private static final String PROPERTY_PAGE_TITLE_LIST_CUSTOMERS = "gru.list_customers.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_SEARCH_CUSTOMER = "gru.search_customer.pageTitle";

    // Messages
    private static final String MESSAGE_NO_CUSTOMER_FOUND = "gru.message.noCustomerFound";
    private static final String MESSAGE_NO_DEMAND_FOUND = "gru.message.noDemandFound";

    // Views
    private static final String VIEW_SEARCH_CUSTOMER = "searchCustomer";
    private static final String VIEW_SEARCH_RESULTS = "searchResults";
    private static final String VIEW_CUSTOMER_DEMANDS = "viewCustomerDemands";
    private static final String VIEW_CUSTOMER_OLD_DEMANDS = "viewCustomerOldDemands";
    private static final String VIEW_CUSTOMER_NEW_DEMANDS = "viewCustomerNewDemands";
    private static final String VIEW_DEMAND = "viewDemand";

    // Actions
    private static final String ACTION_SEARCH = "search";

    private static final long serialVersionUID = 1L;
    private static HomeButtonListBuilder _homeButtonListBuilder = new HomeButtonListBuilder( );

    // Session variable to store working values
    private List<Customer> _listCustomer;

    @View( value = VIEW_SEARCH_CUSTOMER, defaultView = true )
    public String getSearchCustomer( HttpServletRequest request )
    {
        Customer customer = null;
        List<ActionPanel> listPanels = CustomerActionsService.getPanels( customer, getUser( ) );
        Map<String, Object> model = getModel( );
        model.put( Constants.MARK_ACTION_PANELS, listPanels );
        model.put( Constants.MARK_CUSTOMER, new Customer( ) );
        model.put( Constants.MARK_BUTTONS_LIST, _homeButtonListBuilder.buildActionButtonList( customer, getUser( ) ) );
        model.put( Constants.MARK_AUTOCOMPLETE, Boolean.parseBoolean( AppPropertiesService.getProperty( Constants.PROPERTY_AUTOCOMPLETE_ENABLED ) ) );
        model.put( Constants.MARK_AUTOCOMPLETE_URL, AppPropertiesService.getProperty( Constants.PROPERTY_AUTOCOMPLETE_URL ) );
        model.put( Constants.MARK_RETURN_URL,
                UrlUtils.buildReturnUrl( AppPathService.getBaseUrl( request ) + getControllerPath( ) + getControllerJsp( ), VIEW_SEARCH_CUSTOMER, customer ) );

        return getPage( PROPERTY_PAGE_TITLE_SEARCH_CUSTOMER, TEMPLATE_SEARCH_CUSTOMER, model );
    }

    @Action( ACTION_SEARCH )
    public String doSearch( HttpServletRequest request ) throws UnsupportedEncodingException
    {
        String strSearchQuery = request.getParameter( Constants.PARAMETER_SEARCH_QUERY );
        String strQuery = request.getParameter( Constants.PARAMETER_QUERY );

        if ( !StringUtils.isEmpty( strSearchQuery ) && StringUtils.isEmpty( strQuery ) )
        {
            Map<String, String> mapParameters = new HashMap<String, String>( );
            mapParameters.put( Constants.PARAMETER_SEARCH_QUERY, strSearchQuery );

            return redirect( request, VIEW_DEMAND, mapParameters );
        }
        else
        {
            return searchRedirectCustomer( request.getParameter( Constants.PARAMETER_QUERY ), request );
        }
    }

    /**
     * Get redirection for customer search
     * 
     * @param strQuery
     *            The strQuery
     * @param request
     *            The HTTP request
     * @return The page
     */
    private String searchRedirectCustomer( String strQuery, HttpServletRequest request )
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper( );
            JsonNode jsonQuery = mapper.readTree( strQuery );
            String strFirstName = StringUtils.EMPTY;
            String strLastName = StringUtils.EMPTY;

            if ( !jsonQuery.path( Constants.MARKER_LAST_NAME ).isMissingNode( ) )
            {
                strLastName = jsonQuery.get( Constants.MARKER_LAST_NAME ).asText( );
            }

            if ( !jsonQuery.path( Constants.MARKER_FIRST_NAME ).isMissingNode( ) )
            {
                strFirstName = jsonQuery.get( Constants.MARKER_FIRST_NAME ).asText( );
            }

            _listCustomer = CustomerService.instance( ).findbyName( strFirstName, strLastName );

            if ( _listCustomer.size( ) == 0 )
            {
                String strError = I18nService.getLocalizedString( MESSAGE_NO_CUSTOMER_FOUND, getLocale( ) );
                addError( strError );

                return redirectView( request, VIEW_SEARCH_CUSTOMER );
            }

            if ( _listCustomer.size( ) == 1 )
            {
                Map<String, String> mapParameters = new HashMap<String, String>( );
                mapParameters.put( Constants.PARAMETER_ID_CUSTOMER, _listCustomer.get( 0 ).getId( ) );

                return redirect( request, VIEW_CUSTOMER_DEMANDS, mapParameters );
            }

            return redirectView( request, VIEW_SEARCH_RESULTS );
        }
        catch( IOException e )
        {
            AppLogService.error( e + " :" + e.getMessage( ), e );

            return redirectView( request, VIEW_SEARCH_CUSTOMER );
        }
    }

    @View( VIEW_SEARCH_RESULTS )
    public String getSearchResults( HttpServletRequest request )
    {
        Customer customer = null;
        List<ActionPanel> listPanels = CustomerActionsService.getPanels( customer, getUser( ) );

        Map<String, Object> model = getModel( );
        model.put( Constants.MARK_RESULTS_LIST, _listCustomer );
        model.put( Constants.MARK_ACTION_PANELS, listPanels );
        model.put( Constants.MARK_CUSTOMER, new Customer( ) );

        return getPage( PROPERTY_PAGE_TITLE_LIST_CUSTOMERS, TEMPLATE_SEARCH_RESULTS, model );
    }

    @View( VIEW_CUSTOMER_DEMANDS )
    public String getViewCustomerDemands( HttpServletRequest request )
    {
        Customer customer = CustomerUtils.getCustomer( request );

        if ( customer != null )
        {
            List<ActionPanel> listPanels = CustomerActionsService.getPanels( customer, getUser( ) );
            List<Demand> listDemands = DemandService.getDemands( customer, getUser( ), Demand.STATUS_INPROGRESS );

            Map<String, Object> model = getModel( );
            model.put( Constants.MARK_ACTION_PANELS, listPanels );
            model.put( Constants.MARK_CUSTOMER, customer );
            model.put( Constants.MARK_DEMANDS_LIST, listDemands );
            model.put( Constants.MARK_RETURN_URL, UrlUtils.buildReturnUrl( AppPathService.getBaseUrl( request ) + getControllerPath( ) + getControllerJsp( ),
                    VIEW_CUSTOMER_DEMANDS, customer ) );

            // display demand with date preference
            String strCreationDateDisplay = AdminUserPreferencesService.instance( ).get( String.valueOf( getUser( ).getUserId( ) ),
                    Constants.MARK_USER_PREFERENCE_CREATION_DATE_DISPLAY, StringUtils.EMPTY );

            model.put( Constants.MARK_CREATION_DATE_AS_DATE, Constants.USER_PREFERENCE_CREATION_DATE_DISPLAY_DATE.equals( strCreationDateDisplay ) );

            return getPage( "", TEMPLATE_VIEW_CUSTOMER_DEMANDS, model );
        }

        return "Invalid Customer";
    }

    @View( VIEW_CUSTOMER_OLD_DEMANDS )
    public String getViewCustomerOldDemands( HttpServletRequest request )
    {
        Customer customer = CustomerUtils.getCustomer( request );

        if ( customer != null )
        {
            List<ActionPanel> listPanels = CustomerActionsService.getPanels( customer, getUser( ) );
            List<Demand> listDemands = DemandService.getDemands( customer, getUser( ), Demand.STATUS_CLOSED );

            Map<String, Object> model = getModel( );
            model.put( Constants.MARK_ACTION_PANELS, listPanels );
            model.put( Constants.MARK_CUSTOMER, customer );
            model.put( Constants.MARK_DEMANDS_LIST, listDemands );
            model.put( Constants.MARK_RETURN_URL, UrlUtils.buildReturnUrl( AppPathService.getBaseUrl( request ) + getControllerPath( ) + getControllerJsp( ),
                    VIEW_CUSTOMER_OLD_DEMANDS, customer ) );

            return getPage( "", TEMPLATE_VIEW_CUSTOMER_OLD_DEMANDS, model );
        }

        return "Invalid Customer";
    }

    @View( VIEW_CUSTOMER_NEW_DEMANDS )
    public String getViewCustomerNewDemands( HttpServletRequest request )
    {
        Customer customer = CustomerUtils.getCustomer( request );

        if ( customer != null )
        {
            List<ActionPanel> listPanels = CustomerActionsService.getPanels( customer, getUser( ) );
            List<ActionGroup> listButtonsGroups = _homeButtonListBuilder.buildButtonGroupList( customer, getUser( ) );

            Map<String, Object> model = getModel( );
            model.put( Constants.MARK_ACTION_PANELS, listPanels );
            model.put( Constants.MARK_CUSTOMER, customer );
            model.put( Constants.MARK_BUTTONS_GROUPS_LIST, listButtonsGroups );
            model.put( Constants.MARK_RETURN_URL, UrlUtils.buildReturnUrl( AppPathService.getBaseUrl( request ) + getControllerPath( ) + getControllerJsp( ),
                    VIEW_CUSTOMER_NEW_DEMANDS, customer ) );

            return getPage( "", TEMPLATE_VIEW_CUSTOMER_NEW_DEMANDS, model );
        }

        return "Invalid Customer";
    }

    /**
     * View Demand
     * 
     * @param request
     *            The HTTP request
     * @return The page
     */
    @View( VIEW_DEMAND )
    public String getViewDemand( HttpServletRequest request )
    {
        String strReference = request.getParameter( Constants.PARAMETER_SEARCH_QUERY );
        String demandId = null;
        String demandTypeId = null;

        if ( !StringUtils.isEmpty( strReference ) )
        {
            List<Demand> demandsByRef = DemandService.getDemandsByRef( strReference, getUser( ), Demand.STATUS_INPROGRESS );

            if ( demandsByRef.isEmpty( ) )
            {
                String strError = I18nService.getLocalizedString( MESSAGE_NO_DEMAND_FOUND, getLocale( ) );
                addError( strError );

                return redirectView( request, VIEW_SEARCH_CUSTOMER );
            }
            demandId = demandsByRef.get( 0 ).getId( );
            demandTypeId = demandsByRef.get( 0 ).getTypeId( );
        }
        else
        {
            demandId = request.getParameter( Constants.PARAMETER_ID_DEMAND );
            demandTypeId = request.getParameter( Constants.PARAMETER_ID_DEMAND_TYPE );
        }

        Demand demand = DemandService.getDemand( demandId, demandTypeId, getUser( ) );

        Customer customer = new Customer( );

        if ( demand.getCustomer( ) != null )
        {
            customer = CustomerService.instance( ).findById( demand.getCustomer( ).getId( ) );
        }

        List<ActionPanel> listPanels = new ArrayList<ActionPanel>( );
        Map<String, Object> model = getModel( );
        Map<String, String> mapParameters = new HashMap<String, String>( );
        listPanels = CustomerActionsService.getPanels( customer, getUser( ) );
        model.put( Constants.MARK_ACTION_PANELS, listPanels );
        demand = DemandTypeService.setDemandActions( demand, customer, getUser( ) );

        if ( customer != null )
        {
            mapParameters.put( Constants.PARAMETER_ID_CUSTOMER, customer.getId( ) );
            model.put( Constants.MARK_CUSTOMER, customer );
        }
        else
        {
            model.put( Constants.MARK_CUSTOMER, new Customer( ) );
        }

        mapParameters.put( Constants.PARAMETER_ID_DEMAND, String.valueOf( demand.getId( ) ) );
        mapParameters.put( Constants.PARAMETER_ID_DEMAND_TYPE, String.valueOf( demand.getTypeId( ) ) );

        model.put( Constants.MARK_DEMAND, demand );

        ModelUtils.storeStatus( model, demand.getNotifications( ) );

        model.put( Constants.MARK_RETURN_URL,
                UrlUtils.buildReturnUrl( AppPathService.getBaseUrl( request ) + getControllerPath( ) + getControllerJsp( ), VIEW_DEMAND, mapParameters ) );

        return getPage( "", TEMPLATE_VIEW_DEMAND, model );
    }
}
