/*
* Copyright (c) 2002-2012, Mairie de Paris
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
package fr.paris.lutece.plugins.gru.web.rs;

import fr.paris.lutece.plugins.gru.business.customer.Customer;
import fr.paris.lutece.plugins.gru.business.customer.CustomerHome;
import fr.paris.lutece.plugins.rest.service.RestConstants;
import fr.paris.lutece.plugins.rest.util.json.JSONUtil;
import fr.paris.lutece.plugins.rest.util.xml.XMLUtil;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.util.xml.XmlUtil;

import net.sf.json.JSONObject;

import java.io.IOException;

import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Page resource
 */
@Path( RestConstants.BASE_PATH + Constants.PLUGIN_PATH + Constants.CUSTOMER_PATH )
public class CustomerRest
{
    private static final String KEY_CUSTOMERS = "customers";
    private static final String KEY_CUSTOMER = "customer";
    private static final String KEY_ID = "id";
    private static final String KEY_ID_TITLE = "id_title";
    private static final String KEY_FIRSTNAME = "firstname";
    private static final String KEY_LASTNAME = "lastname";
    private static final String KEY_HAS_ACCOUNT = "has_account";
    private static final String KEY_ACCOUNT_LOGIN = "account_login";
    private static final String KEY_ACCOUNT_GUID = "account_guid";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_IS_EMAIL_VERIFIED = "is_email_verified";
    private static final String KEY_FIXED_PHONE_NUMBER = "fixed_phone_number";
    private static final String KEY_MOBILE_PHONE = "mobile_phone";
    private static final String KEY_IS_MOBILE_PHONE_VERIFIED = "is_mobile_phone_verified";
    private static final String KEY_EXTRAS_ATTRIBUTES = "extras_attributes";

    @GET
    @Path( Constants.ALL_PATH )
    public Response getCustomers( @HeaderParam( HttpHeaders.ACCEPT )
    String accept, @QueryParam( Constants.FORMAT_QUERY )
    String format ) throws IOException
    {
        String entity;
        String mediaType;

        if ( ( ( accept != null ) && accept.contains( MediaType.APPLICATION_JSON ) ) ||
                ( ( format != null ) && format.equals( Constants.MEDIA_TYPE_JSON ) ) )
        {
            entity = getCustomersJson(  );
            mediaType = MediaType.APPLICATION_JSON;
        }
        else
        {
            entity = getCustomersXml(  );
            mediaType = MediaType.APPLICATION_XML;
        }

        return Response.ok( entity, mediaType ).build(  );
    }

    /**
     * Gets all resources list in XML format
     * @return The list
     */
    public String getCustomersXml(  )
    {
        StringBuffer sbXML = new StringBuffer( XmlUtil.getXmlHeader(  ) );
        Collection<Customer> list = CustomerHome.getCustomersList(  );

        XmlUtil.beginElement( sbXML, KEY_CUSTOMERS );

        for ( Customer customer : list )
        {
            addCustomerXml( sbXML, customer );
        }

        XmlUtil.endElement( sbXML, KEY_CUSTOMERS );

        return sbXML.toString(  );
    }

    /**
     * Gets all resources list in JSON format
     * @return The list
     */
    public String getCustomersJson(  )
    {
        JSONObject jsonCustomer = new JSONObject(  );
        JSONObject json = new JSONObject(  );

        Collection<Customer> list = CustomerHome.getCustomersList(  );

        for ( Customer customer : list )
        {
            addCustomerJson( jsonCustomer, customer );
        }

        json.accumulate( KEY_CUSTOMERS, jsonCustomer );

        return json.toString(  );
    }

    @GET
    @Path( "{" + Constants.ID_PATH + "}" )
    public Response getCustomer( @PathParam( Constants.ID_PATH )
    String strId, @HeaderParam( HttpHeaders.ACCEPT )
    String accept, @QueryParam( Constants.FORMAT_QUERY )
    String format ) throws IOException
    {
        String entity;
        String mediaType;

        if ( ( ( accept != null ) && accept.contains( MediaType.APPLICATION_JSON ) ) ||
                ( ( format != null ) && format.equals( Constants.MEDIA_TYPE_JSON ) ) )
        {
            entity = getCustomerJson( strId );
            mediaType = MediaType.APPLICATION_JSON;
        }
        else
        {
            entity = getCustomerXml( strId );
            mediaType = MediaType.APPLICATION_XML;
        }

        return Response.ok( entity, mediaType ).build(  );
    }

    /**
     * Gets a resource in XML format
     * @param strId The resource ID
     * @return The XML output
     */
    public String getCustomerXml( String strId )
    {
        StringBuffer sbXML = new StringBuffer(  );

        try
        {
            int nId = Integer.parseInt( strId );
            Customer customer = CustomerHome.findByPrimaryKey( nId );

            if ( customer != null )
            {
                sbXML.append( "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" );
                addCustomerXml( sbXML, customer );
            }
        }
        catch ( NumberFormatException e )
        {
            sbXML.append( XMLUtil.formatError( "Invalid customer number", 3 ) );
        }
        catch ( Exception e )
        {
            sbXML.append( XMLUtil.formatError( "Customer not found", 1 ) );
        }

        return sbXML.toString(  );
    }

    /**
     * Gets a resource in JSON format
     * @param strId The resource ID
     * @return The JSON output
     */
    public String getCustomerJson( String strId )
    {
        JSONObject json = new JSONObject(  );
        String strJson = "";

        try
        {
            int nId = Integer.parseInt( strId );
            Customer customer = CustomerHome.findByPrimaryKey( nId );

            if ( customer != null )
            {
                addCustomerJson( json, customer );
                strJson = json.toString(  );
            }
        }
        catch ( NumberFormatException e )
        {
            strJson = JSONUtil.formatError( "Invalid customer number", 3 );
        }
        catch ( Exception e )
        {
            strJson = JSONUtil.formatError( "Customer not found", 1 );
        }

        return strJson;
    }

    @DELETE
    @Path( "{" + Constants.ID_PATH + "}" )
    public Response deleteCustomer( @PathParam( Constants.ID_PATH )
    String strId, @HeaderParam( HttpHeaders.ACCEPT )
    String accept, @QueryParam( Constants.FORMAT_QUERY )
    String format ) throws IOException
    {
        try
        {
            int nId = Integer.parseInt( strId );

            if ( CustomerHome.findByPrimaryKey( nId ) != null )
            {
                CustomerHome.remove( nId );
            }
        }
        catch ( NumberFormatException e )
        {
            AppLogService.error( "Invalid customer number" );
        }

        return getCustomers( accept, format );
    }

    @POST
    public Response createCustomer( @FormParam( KEY_ID )
    String id, @FormParam( "id_title" )
    String id_title, @FormParam( "firstname" )
    String firstname, @FormParam( "lastname" )
    String lastname, @FormParam( "has_account" )
    String has_account, @FormParam( "account_login" )
    String account_login, @FormParam( "account_guid" )
    String account_guid, @FormParam( "email" )
    String email, @FormParam( "is_email_verified" )
    String is_email_verified, @FormParam( "fixed_phone_number" )
    String fixed_phone_number, @FormParam( "mobile_phone" )
    String mobile_phone, @FormParam( "is_mobile_phone_verified" )
    String is_mobile_phone_verified, @FormParam( "extras_attributes" )
    String extras_attributes, @HeaderParam( HttpHeaders.ACCEPT )
    String accept, @QueryParam( Constants.FORMAT_QUERY )
    String format ) throws IOException
    {
        if ( id != null )
        {
            int nId = Integer.parseInt( KEY_ID );

            Customer customer = CustomerHome.findByPrimaryKey( nId );

            if ( customer != null )
            {
                customer.setIdTitle( Integer.parseInt( id_title ) );
                customer.setFirstname( firstname );
                customer.setLastname( lastname );
                customer.setHasAccount( Boolean.valueOf( has_account ) );
                customer.setAccountLogin( account_login );
                customer.setAccountGuid( account_guid );
                customer.setEmail( email );
                customer.setIsEmailVerified( Boolean.valueOf( is_email_verified ) );
                customer.setFixedPhoneNumber( fixed_phone_number );
                customer.setMobilePhone( mobile_phone );
                customer.setIsMobilePhoneVerified( Boolean.valueOf( is_mobile_phone_verified ) );
                customer.setExtrasAttributes( extras_attributes );
                CustomerHome.update( customer );
            }
        }
        else
        {
            Customer customer = new Customer(  );

            customer.setIdTitle( Integer.parseInt( id_title ) );
            customer.setFirstname( firstname );
            customer.setLastname( lastname );
            customer.setHasAccount( Boolean.valueOf( has_account ) );
            customer.setAccountLogin( account_login );
            customer.setAccountGuid( account_guid );
            customer.setEmail( email );
            customer.setIsEmailVerified( Boolean.valueOf( is_email_verified ) );
            customer.setFixedPhoneNumber( fixed_phone_number );
            customer.setMobilePhone( mobile_phone );
            customer.setIsMobilePhoneVerified( Boolean.valueOf( is_mobile_phone_verified ) );
            customer.setExtrasAttributes( extras_attributes );
            CustomerHome.create( customer );
        }

        return getCustomers( accept, format );
    }

    /**
     * Write a customer into a buffer
     * @param sbXML The buffer
     * @param customer The customer
     */
    private void addCustomerXml( StringBuffer sbXML, Customer customer )
    {
        XmlUtil.beginElement( sbXML, KEY_CUSTOMER );
        XmlUtil.addElement( sbXML, KEY_ID, customer.getId(  ) );
        XmlUtil.addElement( sbXML, KEY_ID_TITLE, customer.getIdTitle(  ) );
        XmlUtil.addElement( sbXML, KEY_FIRSTNAME, customer.getFirstname(  ) );
        XmlUtil.addElement( sbXML, KEY_LASTNAME, customer.getLastname(  ) );
        XmlUtil.addElement( sbXML, KEY_HAS_ACCOUNT, customer.getHasAccount(  ) ? "1" : "0" );
        XmlUtil.addElement( sbXML, KEY_ACCOUNT_LOGIN, customer.getAccountLogin(  ) );
        XmlUtil.addElement( sbXML, KEY_ACCOUNT_GUID, customer.getAccountGuid(  ) );
        XmlUtil.addElement( sbXML, KEY_EMAIL, customer.getEmail(  ) );
        XmlUtil.addElement( sbXML, KEY_IS_EMAIL_VERIFIED, customer.getIsEmailVerified(  ) ? "1" : "0" );
        XmlUtil.addElement( sbXML, KEY_FIXED_PHONE_NUMBER, customer.getFixedPhoneNumber(  ) );
        XmlUtil.addElement( sbXML, KEY_MOBILE_PHONE, customer.getMobilePhone(  ) );
        XmlUtil.addElement( sbXML, KEY_IS_MOBILE_PHONE_VERIFIED, customer.getIsMobilePhoneVerified(  ) ? "1" : "0" );
        XmlUtil.addElement( sbXML, KEY_EXTRAS_ATTRIBUTES, customer.getExtrasAttributes(  ) );
        XmlUtil.endElement( sbXML, KEY_CUSTOMER );
    }

    /**
     * Write a customer into a JSON Object
     * @param json The JSON Object
     * @param customer The customer
     */
    private void addCustomerJson( JSONObject json, Customer customer )
    {
        JSONObject jsonCustomer = new JSONObject(  );
        jsonCustomer.accumulate( KEY_ID, customer.getId(  ) );
        jsonCustomer.accumulate( KEY_ID_TITLE, customer.getIdTitle(  ) );
        jsonCustomer.accumulate( KEY_FIRSTNAME, customer.getFirstname(  ) );
        jsonCustomer.accumulate( KEY_LASTNAME, customer.getLastname(  ) );
        jsonCustomer.accumulate( KEY_HAS_ACCOUNT, customer.getHasAccount(  ) );
        jsonCustomer.accumulate( KEY_ACCOUNT_LOGIN, customer.getAccountLogin(  ) );
        jsonCustomer.accumulate( KEY_ACCOUNT_GUID, customer.getAccountGuid(  ) );
        jsonCustomer.accumulate( KEY_EMAIL, customer.getEmail(  ) );
        jsonCustomer.accumulate( KEY_IS_EMAIL_VERIFIED, customer.getIsEmailVerified(  ) );
        jsonCustomer.accumulate( KEY_FIXED_PHONE_NUMBER, customer.getFixedPhoneNumber(  ) );
        jsonCustomer.accumulate( KEY_MOBILE_PHONE, customer.getMobilePhone(  ) );
        jsonCustomer.accumulate( KEY_IS_MOBILE_PHONE_VERIFIED, customer.getIsMobilePhoneVerified(  ) );
        jsonCustomer.accumulate( KEY_EXTRAS_ATTRIBUTES, customer.getExtrasAttributes(  ) );
        json.accumulate( KEY_CUSTOMER, jsonCustomer );
    }
}
