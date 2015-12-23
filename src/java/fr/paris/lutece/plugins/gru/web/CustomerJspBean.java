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

import fr.paris.lutece.plugins.gru.business.customer.Customer;
import fr.paris.lutece.plugins.gru.business.customer.CustomerHome;
import fr.paris.lutece.plugins.gru.business.demand.Demand;
import fr.paris.lutece.plugins.gru.service.CustomerActionsService;
import fr.paris.lutece.plugins.gru.service.FeatureService;
import fr.paris.lutece.plugins.gru.service.demand.DemandService;
import fr.paris.lutece.plugins.gru.service.demand.IDemandService;
import fr.paris.lutece.plugins.gru.service.demand.MokeDemandService;
import fr.paris.lutece.plugins.gru.web.actions.model.ActionPanel;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.util.mvc.admin.MVCAdminJspBean;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.web.util.LocalizedPaginator;
import fr.paris.lutece.util.html.Paginator;
import fr.paris.lutece.util.url.UrlItem;

import java.util.List;
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
    private static final String TEMPLATE_MODIFY_CUSTOMER = "/admin/plugins/gru/modify_customer.html";
    private static final String TEMPLATE_VIEW_CUSTOMER = "/admin/plugins/gru/view_customer.html";
    private static final String TEMPLATE_VIEW_DEMAND = "/admin/plugins/gru/view_demand.html";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_CUSTOMERS = "gru.manage_customers.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_CUSTOMER = "gru.modify_customer.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_CUSTOMER = "gru.create_customer.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_SEARCH_CUSTOMER = "gru.search_customer.pageTitle";
    private static final String JSP_MANAGE_CUSTOMERS = "jsp/admin/plugins/gru/ManageCustomers.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_CUSTOMER = "gru.message.confirmRemoveCustomer";
    private static final String PROPERTY_DEFAULT_LIST_CUSTOMER_PER_PAGE = "gru.listCustomers.itemsPerPage";
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "gru.model.entity.customer.attribute.";

    // Views
    private static final String VIEW_MANAGE_CUSTOMERS = "manageCustomers";
    private static final String VIEW_CREATE_CUSTOMER = "createCustomer";
    private static final String VIEW_MODIFY_CUSTOMER = "modifyCustomer";
    private static final String VIEW_SEARCH_CUSTOMER = "searchCustomer";
    private static final String VIEW_CUSTOMER = "viewCustomer";
    private static final String VIEW_DEMAND = "viewDemand";

    // Actions
    private static final String ACTION_CREATE_CUSTOMER = "createCustomer";
    private static final String ACTION_MODIFY_CUSTOMER = "modifyCustomer";
    private static final String ACTION_REMOVE_CUSTOMER = "removeCustomer";
    private static final String ACTION_CONFIRM_REMOVE_CUSTOMER = "confirmRemoveCustomer";
    private static final String ACTION_SEARCH = "search";

    // Infos
    private static final String INFO_CUSTOMER_CREATED = "gru.info.customer.created";
    private static final String INFO_CUSTOMER_UPDATED = "gru.info.customer.updated";
    private static final String INFO_CUSTOMER_REMOVED = "gru.info.customer.removed";

    // Right
    public static final String RIGHT_MANAGECUSTOMERS = "GRU_MANAGEMENT";
    private static final String PROPERTY_DEFAULT_LIST_ITEM_PER_PAGE = "gru.listItems.itemsPerPage";
    private static final long serialVersionUID = 1L;

    //Variables
    private int _nDefaultItemsPerPage;
    private String _strCurrentPageIndex;
    private int _nItemsPerPage;

    // Session variable to store working values
    private Customer _customer;

    @View( value = VIEW_SEARCH_CUSTOMER, defaultView = true )
    public String getSearchCustomer( HttpServletRequest request )
    {
        List<ActionPanel> listPanels = CustomerActionsService.getPanels( null );
        Map<String, Object> model = getModel(  );
        model.put( Constants.MARK_ACTION_PANELS, listPanels );
        model.put( Constants.MARK_CUSTOMER, new Customer(  ) );
        model.put( Constants.MARK_FEATURES_LIST, FeatureService.getHomeFeatures( ) );

        return getPage( PROPERTY_PAGE_TITLE_SEARCH_CUSTOMER, TEMPLATE_SEARCH_CUSTOMER, model );
    }

    @Action( ACTION_SEARCH )
    public String doSearch( HttpServletRequest request )
    {
        String strQuery = request.getParameter( Constants.PARAMETER_QUERY );

        // TODO search implementation
        return redirect( request, VIEW_CUSTOMER, Constants.PARAMETER_ID_CUSTOMER, 1 );
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

    @View( VIEW_CUSTOMER )
    public String getViewCustomer( HttpServletRequest request )
    {
        String strId = request.getParameter( Constants.PARAMETER_ID_CUSTOMER );
        Customer customer = null;

        if ( strId != null )
        {
            try
            {
                int nId = Integer.parseInt( strId );
                customer = CustomerHome.findByPrimaryKey( nId );

                List<ActionPanel> listPanels = CustomerActionsService.getPanels( customer );
                Map<String, Object> model = getModel(  );
                model.put( Constants.MARK_ACTION_PANELS, listPanels );
                model.put( Constants.MARK_CUSTOMER, customer );

                return getPage( "", TEMPLATE_VIEW_CUSTOMER, model );
            }
            catch ( NumberFormatException e )
            {
            }
        }

        return "Invalid ID";
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

        Demand demand = DemandService.getDemand( strIdDemand , strIdDemandType );
        Customer customer = null;

        if ( strId != null )
        {
            try
            {
                int nId = Integer.parseInt( strId );
                customer = CustomerHome.findByPrimaryKey( nId );

                List<ActionPanel> listPanels = CustomerActionsService.getPanels( customer );
                Map<String, Object> model = getModel(  );
                model.put( Constants.MARK_ACTION_PANELS, listPanels );
                model.put( Constants.MARK_CUSTOMER, customer );
                model.put( Constants.MARK_DEMAND, demand );

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
     * Handles the removal form of a customer
     *
     * @param request The Http request
     * @return the jsp URL to display the form to manage customers
     */
    @Action( ACTION_REMOVE_CUSTOMER )
    public String doRemoveCustomer( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( Constants.PARAMETER_ID_CUSTOMER ) );
        CustomerHome.remove( nId );
        addInfo( INFO_CUSTOMER_REMOVED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_CUSTOMERS );
    }

    /**
     * Returns the form to update info about a customer
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_CUSTOMER )
    public String getModifyCustomer( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( Constants.PARAMETER_ID_CUSTOMER ) );

        if ( ( _customer == null ) || ( _customer.getId(  ) != nId ) )
        {
            _customer = CustomerHome.findByPrimaryKey( nId );
        }

        List<ActionPanel> listPanels = CustomerActionsService.getPanels( null );
        Map<String, Object> model = getModel(  );
        model.put( Constants.MARK_ACTION_PANELS, listPanels );
        model.put( Constants.MARK_CUSTOMER, _customer );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_CUSTOMER, TEMPLATE_MODIFY_CUSTOMER, model );
    }

    /**
     * Process the change form of a customer
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_CUSTOMER )
    public String doModifyCustomer( HttpServletRequest request )
    {
        populate( _customer, request );

        // Check constraints
        if ( !validateBean( _customer, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_CUSTOMER, Constants.PARAMETER_ID_CUSTOMER, _customer.getId(  ) );
        }

        CustomerHome.update( _customer );
        addInfo( INFO_CUSTOMER_UPDATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_CUSTOMERS );
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
