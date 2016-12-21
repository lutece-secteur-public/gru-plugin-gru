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

import fr.paris.lutece.plugins.gru.business.customer.CustomerHome;
import fr.paris.lutece.plugins.gru.service.CustomerActionsService;
import fr.paris.lutece.plugins.gru.service.demand.DemandService;
import fr.paris.lutece.plugins.gru.service.demandtype.DemandTypeService;
import fr.paris.lutece.plugins.gru.service.search.SearchService;
import fr.paris.lutece.plugins.gru.utils.CustomerUtils;
import fr.paris.lutece.plugins.gru.utils.UrlUtils;
import fr.paris.lutece.plugins.gru.web.actions.buttons.builders.impl.HomeButtonListBuilder;
import fr.paris.lutece.plugins.gru.web.actions.model.ActionGroup;
import fr.paris.lutece.plugins.gru.web.actions.model.ActionPanel;
import fr.paris.lutece.plugins.grubusiness.business.customer.Customer;
import fr.paris.lutece.plugins.grubusiness.business.demand.Demand;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.prefs.AdminUserPreferencesService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.util.mvc.admin.MVCAdminJspBean;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.web.util.LocalizedPaginator;
import fr.paris.lutece.util.html.Paginator;
import fr.paris.lutece.util.url.UrlItem;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * This class provides the user interface to manage Customer features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageCustomers.jsp", controllerPath = "jsp/admin/plugins/gru/", right = "GRU_MANAGEMENT" )
public class CustomerJspBean extends MVCAdminJspBean
{
    ////////////////////////////////////////////////////////////////////////////
    // Constants

    // templates
    private static final String TEMPLATE_SEARCH_CUSTOMER = "/admin/plugins/gru/search_customer.html";
    private static final String TEMPLATE_MANAGE_CUSTOMERS = "/admin/plugins/gru/manage_customers.html";
    private static final String TEMPLATE_CREATE_CUSTOMER = "/admin/plugins/gru/create_customer.html";
    private static final String TEMPLATE_VIEW_CUSTOMER_DEMANDS = "/admin/plugins/gru/view_customer_demands.html";
    private static final String TEMPLATE_VIEW_CUSTOMER_OLD_DEMANDS = "/admin/plugins/gru/view_customer_old_demands.html";
    private static final String TEMPLATE_VIEW_CUSTOMER_NEW_DEMANDS = "/admin/plugins/gru/view_customer_new_demands.html";
    private static final String TEMPLATE_VIEW_DEMAND = "/admin/plugins/gru/view_demand.html";
    private static final String TEMPLATE_SEARCH_RESULTS = "/admin/plugins/gru/search_results.html";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_CUSTOMERS = "gru.manage_customers.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_CUSTOMER = "gru.create_customer.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_SEARCH_CUSTOMER = "gru.search_customer.pageTitle";
    private static final String JSP_MANAGE_CUSTOMERS = "jsp/admin/plugins/gru/ManageCustomers.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_CUSTOMER = "gru.message.confirmRemoveCustomer";
    private static final String MESSAGE_NO_CUSTOMER_FOUND = "gru.message.noCustomerFound";
    private static final String PROPERTY_DEFAULT_LIST_CUSTOMER_PER_PAGE = "gru.listCustomers.itemsPerPage";
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "gru.model.entity.customer.attribute.";

    // Views
    private static final String VIEW_MANAGE_CUSTOMERS = "manageCustomers";
    private static final String VIEW_CREATE_CUSTOMER = "createCustomer";
    private static final String VIEW_SEARCH_CUSTOMER = "searchCustomer";
    private static final String VIEW_SEARCH_RESULTS = "searchResults";
    private static final String VIEW_CUSTOMER_DEMANDS = "viewCustomerDemands";
    private static final String VIEW_CUSTOMER_OLD_DEMANDS = "viewCustomerOldDemands";
    private static final String VIEW_CUSTOMER_NEW_DEMANDS = "viewCustomerNewDemands";
    private static final String VIEW_DEMAND = "viewDemand";

    // Actions
    private static final String ACTION_CREATE_CUSTOMER = "createCustomer";
    private static final String ACTION_REMOVE_CUSTOMER = "removeCustomer";
    private static final String ACTION_CONFIRM_REMOVE_CUSTOMER = "confirmRemoveCustomer";
    private static final String ACTION_SEARCH = "search";

    // Infos
    private static final String INFO_CUSTOMER_CREATED = "gru.info.customer.created";

    // Right
    public static final String RIGHT_MANAGECUSTOMERS = "GRU_MANAGEMENT";
    private static final String PROPERTY_DEFAULT_LIST_ITEM_PER_PAGE = "gru.listItems.itemsPerPage";
    private static final long serialVersionUID = 1L;
    private static HomeButtonListBuilder _homeButtonListBuilder = new HomeButtonListBuilder(  );

    //Variables
    private int _nDefaultItemsPerPage;
    private String _strCurrentPageIndex;
    private int _nItemsPerPage;

    // Session variable to store working values
    private Customer _customer;
    private List<Customer> _listCustomer;

    @View( value = VIEW_SEARCH_CUSTOMER, defaultView = true )
    public String getSearchCustomer( HttpServletRequest request )
    {
        Customer customer = null;
        List<ActionPanel> listPanels = CustomerActionsService.getPanels( customer, getUser(  ) );
        Map<String, Object> model = getModel(  );
        model.put( Constants.MARK_ACTION_PANELS, listPanels );
        model.put( Constants.MARK_CUSTOMER, new Customer(  ) );
        model.put( Constants.MARK_BUTTONS_LIST, _homeButtonListBuilder.buildActionButtonList( customer, getUser(  ) ) );
        model.put( Constants.MARK_AUTOCOMPLETE, SearchService.instance(  ).isAutoComplete(  ) );
        model.put( Constants.MARK_AUTOCOMPLETE_URL, SearchService.instance(  ).getAutoCompleteUrl(  ) );
        model.put( Constants.MARK_RETURN_URL,
            UrlUtils.buildReturnUrl( AppPathService.getBaseUrl( request ) + getControllerPath(  ) +
                getControllerJsp(  ), VIEW_SEARCH_CUSTOMER, customer ) );

        return getPage( PROPERTY_PAGE_TITLE_SEARCH_CUSTOMER, TEMPLATE_SEARCH_CUSTOMER, model );
    }

    @Action( ACTION_SEARCH )
    public String doSearch( HttpServletRequest request )
        throws UnsupportedEncodingException
    {
        String strQuery = request.getParameter( Constants.PARAMETER_QUERY );

        _listCustomer = SearchService.instance(  ).searchCustomer( strQuery );

        if ( _listCustomer.size(  ) == 0 )
        {
            String strError = I18nService.getLocalizedString( MESSAGE_NO_CUSTOMER_FOUND, getLocale(  ) );
            addError( strError );

            return redirectView( request, VIEW_SEARCH_CUSTOMER );
        }

        if ( _listCustomer.size(  ) == 1 )
        {
            Map<String, String> mapParameters = new HashMap<String, String>(  );
            mapParameters.put( Constants.PARAMETER_ID_CUSTOMER, _listCustomer.get( 0 ).getId(  ) );

            return redirect( request, VIEW_CUSTOMER_DEMANDS, mapParameters );
        }

        return redirectView( request, VIEW_SEARCH_RESULTS );
    }

    @View( VIEW_SEARCH_RESULTS )
    public String getSearchResults( HttpServletRequest request )
    {
        Customer customer = null;
        List<ActionPanel> listPanels = CustomerActionsService.getPanels( customer, getUser(  ) );

        Map<String, Object> model = getModel(  );
        model.put( Constants.MARK_RESULTS_LIST, _listCustomer );
        model.put( Constants.MARK_ACTION_PANELS, listPanels );
        model.put( Constants.MARK_CUSTOMER, new Customer(  ) );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_CUSTOMERS, TEMPLATE_SEARCH_RESULTS, model );
    }

    /**
     * Build the Manage View
     * @param request The HTTP request
     * @return The page
     */
    @View( VIEW_MANAGE_CUSTOMERS )
    public String getManageCustomers( HttpServletRequest request )
    {
        _customer = null;

        List<Customer> listCustomers = (List<Customer>) CustomerHome.getCustomersList(  );
        Map<String, Object> model = getPaginatedListModel( request, Constants.MARK_CUSTOMER_LIST, listCustomers,
                JSP_MANAGE_CUSTOMERS );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_CUSTOMERS, TEMPLATE_MANAGE_CUSTOMERS, model );
    }

    @View( VIEW_CUSTOMER_DEMANDS )
    public String getViewCustomerDemands( HttpServletRequest request )
    {
        Customer customer = CustomerUtils.getCustomer( request );

        if ( customer != null )
        {
            List<ActionPanel> listPanels = CustomerActionsService.getPanels( customer, getUser(  ) );
            List<Demand> listDemands = DemandService.getDemands( customer, getUser(  ), Demand.STATUS_INPROGRESS );

            Map<String, Object> model = getModel(  );
            model.put( Constants.MARK_ACTION_PANELS, listPanels );
            model.put( Constants.MARK_CUSTOMER, customer );
            model.put( Constants.MARK_DEMANDS_LIST, listDemands );
            model.put( Constants.MARK_RETURN_URL,
                UrlUtils.buildReturnUrl( AppPathService.getBaseUrl( request ) + getControllerPath(  ) +
                    getControllerJsp(  ), VIEW_CUSTOMER_DEMANDS, customer ) );

            //display demand with date preference
            String strCreationDateDisplay = AdminUserPreferencesService.instance(  )
                                                                       .get( String.valueOf( getUser(  ).getUserId(  ) ),
                    Constants.MARK_USER_PREFERENCE_CREATION_DATE_DISPLAY, StringUtils.EMPTY );

            model.put( Constants.MARK_CREATION_DATE_AS_DATE,
                Constants.USER_PREFERENCE_CREATION_DATE_DISPLAY_DATE.equals( strCreationDateDisplay ) );

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
            List<ActionPanel> listPanels = CustomerActionsService.getPanels( customer, getUser(  ) );
            List<Demand> listDemands = DemandService.getDemands( customer, getUser(  ), Demand.STATUS_CLOSED );

            Map<String, Object> model = getModel(  );
            model.put( Constants.MARK_ACTION_PANELS, listPanels );
            model.put( Constants.MARK_CUSTOMER, customer );
            model.put( Constants.MARK_DEMANDS_LIST, listDemands );
            model.put( Constants.MARK_RETURN_URL,
                UrlUtils.buildReturnUrl( AppPathService.getBaseUrl( request ) + getControllerPath(  ) +
                    getControllerJsp(  ), VIEW_CUSTOMER_OLD_DEMANDS, customer ) );

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
            List<ActionPanel> listPanels = CustomerActionsService.getPanels( customer, getUser(  ) );
            List<ActionGroup> listButtonsGroups = _homeButtonListBuilder.buildButtonGroupList( customer, getUser(  ) );

            Map<String, Object> model = getModel(  );
            model.put( Constants.MARK_ACTION_PANELS, listPanels );
            model.put( Constants.MARK_CUSTOMER, customer );
            model.put( Constants.MARK_BUTTONS_GROUPS_LIST, listButtonsGroups );
            model.put( Constants.MARK_RETURN_URL,
                UrlUtils.buildReturnUrl( AppPathService.getBaseUrl( request ) + getControllerPath(  ) +
                    getControllerJsp(  ), VIEW_CUSTOMER_NEW_DEMANDS, customer ) );

            return getPage( "", TEMPLATE_VIEW_CUSTOMER_NEW_DEMANDS, model );
        }

        return "Invalid Customer";
    }

    /**
     * View Demand
     * @param request The HTTP request
     * @return The page
     */
    @View( VIEW_DEMAND )
    public String getViewDemand( HttpServletRequest request )
    {
        String strId = request.getParameter( Constants.PARAMETER_ID_CUSTOMER );
        String strIdDemand = request.getParameter( Constants.PARAMETER_ID_DEMAND );
        String strIdDemandType = request.getParameter( Constants.PARAMETER_ID_DEMAND_TYPE );

        Demand demand = DemandService.getDemand( strIdDemand, strIdDemandType, getUser(  ) );
        Customer customer = CustomerUtils.getCustomer( request );

        if ( strIdDemand != null && demand != null )
        {
            try
            {
            	List<ActionPanel> listPanels = new ArrayList<ActionPanel>(  );
            	Map<String, Object> model = getModel(  );
            	Map<String, String> mapParameters = new HashMap<String, String>(  );
            	listPanels = CustomerActionsService.getPanels( customer, getUser(  ) );
        		model.put( Constants.MARK_ACTION_PANELS, listPanels );
        		demand = DemandTypeService.setDemandActions( demand, customer, getUser(  ) );
                
            	if ( strId != null && customer != null )
                {
                    mapParameters.put( Constants.PARAMETER_ID_CUSTOMER, customer.getId(  ) );
                    model.put( Constants.MARK_CUSTOMER, customer );
                } 
            	else 
                {
                	model.put( Constants.MARK_CUSTOMER, new Customer(  ) );
                }
                
                model.put( Constants.MARK_DEMAND, demand );

                mapParameters.put( Constants.PARAMETER_ID_DEMAND, String.valueOf( demand.getId(  ) ) );
                mapParameters.put( Constants.PARAMETER_ID_DEMAND_TYPE, String.valueOf( demand.getDemandTypeId(  ) ) );

                model.put( Constants.MARK_RETURN_URL,
                    UrlUtils.buildReturnUrl( AppPathService.getBaseUrl( request ) + getControllerPath(  ) +
                        getControllerJsp(  ), VIEW_DEMAND, mapParameters ) );

                return getPage( "", TEMPLATE_VIEW_DEMAND, model );
            }
            catch ( NumberFormatException e )
            {
            }
        }

        return "Invalid ID";
    }

    /**
     * Returns the form to create a customer
     *
     * @param request The Http request
     * @return the html code of the customer form
     */
    @View( VIEW_CREATE_CUSTOMER )
    public String getCreateCustomer( HttpServletRequest request )
    {
        _customer = ( _customer != null ) ? _customer : new Customer(  );

        Map<String, Object> model = getModel(  );
        model.put( Constants.MARK_CUSTOMER, _customer );

        return getPage( PROPERTY_PAGE_TITLE_CREATE_CUSTOMER, TEMPLATE_CREATE_CUSTOMER, model );
    }

    /**
     * Process the data capture form of a new customer
     *
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_CUSTOMER )
    public String doCreateCustomer( HttpServletRequest request )
    {
        populate( _customer, request );

        // Check constraints
        if ( !validateBean( _customer, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_CUSTOMER );
        }

        CustomerHome.create( _customer );
        addInfo( INFO_CUSTOMER_CREATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_CUSTOMERS );
    }

    /**
     * Manages the removal form of a customer whose identifier is in the http
     * request
     *
     * @param request The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_CUSTOMER )
    public String getConfirmRemoveCustomer( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( Constants.PARAMETER_ID_CUSTOMER ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_CUSTOMER ) );
        url.addParameter( Constants.PARAMETER_ID_CUSTOMER, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_CUSTOMER,
                url.getUrl(  ), AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Return a model that contains the list and paginator infos
     * @param request The HTTP request
     * @param strBookmark The bookmark
     * @param list The list of item
     * @param strManageJsp The JSP
     * @return The model
     */
    private Map<String, Object> getPaginatedListModel( HttpServletRequest request, String strBookmark, List list,
        String strManageJsp )
    {
        _strCurrentPageIndex = Paginator.getPageIndex( request, Paginator.PARAMETER_PAGE_INDEX, _strCurrentPageIndex );
        _nDefaultItemsPerPage = AppPropertiesService.getPropertyInt( PROPERTY_DEFAULT_LIST_ITEM_PER_PAGE, 50 );
        _nItemsPerPage = Paginator.getItemsPerPage( request, Paginator.PARAMETER_ITEMS_PER_PAGE, _nItemsPerPage,
                _nDefaultItemsPerPage );

        UrlItem url = new UrlItem( strManageJsp );
        String strUrl = url.getUrl(  );

        // PAGINATOR
        LocalizedPaginator paginator = new LocalizedPaginator( list, _nItemsPerPage, strUrl,
                Constants.PARAMETER_PAGE_INDEX, _strCurrentPageIndex, getLocale(  ) );

        Map<String, Object> model = getModel(  );

        model.put( Constants.MARK_NB_ITEMS_PER_PAGE, "" + _nItemsPerPage );
        model.put( Constants.MARK_PAGINATOR, paginator );
        model.put( strBookmark, paginator.getPageItems(  ) );

        return model;
    }
}
