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

/**
 * Constants
 */
public final class Constants
{
    public static final String PLUGIN_NAME = "gru";

    // Parameters
    public static final String PARAMETER_ID_CUSTOMER = "id_customer";
    public static final String PARAMETER_QUERY = "query";
    public static final String PARAMETER_SEARCH_QUERY = "searchquery";
    public static final String PARAMETER_PAGE_INDEX = "page_index";
    public static final String PARAMETER_URL_FRAME = "url";
    public static final String PARAMETER_ID_DEMAND = "id_demand";
    public static final String PARAMETER_ID_DEMAND_TYPE = "id_demand_type";

    // Markers
    public static final String MARK_CUSTOMER = "customer";
    public static final String MARK_ACTION_PANELS = "panels";
    public static final String MARK_PAGINATOR = "paginator";
    public static final String MARK_NB_ITEMS_PER_PAGE = "nb_items_per_page";
    public static final String MARK_URL_FRAME = "url";
    public static final String MARK_DEMANDS_LIST = "demands_list";
    public static final String MARK_DEMAND = "demand";
    public static final String MARK_FEATURES_LIST = "features_list";
    public static final String MARK_BUTTONS_LIST = "buttons_list";
    public static final String MARK_BUTTONS_GROUPS_LIST = "buttons_groups_list";
    public static final String MARK_COLORS_LIST = "colors_list";
    public static final String MARK_RESULTS_LIST = "results_list";
    public static final String MARK_AUTOCOMPLETE = "auto_complete";
    public static final String MARK_AUTOCOMPLETE_URL = "auto_complete_url";
    public static final String MARK_RETURN_URL = "return_url";
    public static final String MARK_USER_PREFERENCE_CREATION_DATE_DISPLAY = "creationDateDisplay";
    public static final String MARK_CREATION_DATE_AS_DATE = "creation_date_as_date";
    public static final String MARK_CUSTOMER_STATUS = "customer_status";
    public static final String MARK_AGENT_STATUS = "agent_status";
    public static final String USER_PREFERENCE_CREATION_DATE_DISPLAY_DATE = "date";
    public static final String MARKER_DEMAND_ID = "demand_id";
    public static final String MARKER_DEMAND_TYPE_ID = "demand_type_id";
    public static final String MARKER_CUSTOMER_ID = "user_cid";
    public static final String MARKER_FIRST_NAME = "first_name";
    public static final String MARKER_LAST_NAME = "last_name";

    // Properties
    public static final String PROPERTY_AUTOCOMPLETE_URL = "gru.search.autocomplete.url";
    public static final String PROPERTY_AUTOCOMPLETE_ENABLED = "gru.search.autocomplete.enabled";

    private Constants( )
    {
    }
}
