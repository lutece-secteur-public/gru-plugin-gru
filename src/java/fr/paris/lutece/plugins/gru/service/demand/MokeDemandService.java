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
package fr.paris.lutece.plugins.gru.service.demand;

import fr.paris.lutece.plugins.gru.business.customer.Customer;
import fr.paris.lutece.plugins.gru.business.customer.CustomerHome;
import fr.paris.lutece.plugins.gru.business.demand.BaseDemand;
import fr.paris.lutece.plugins.gru.business.demand.Demand;
import fr.paris.lutece.plugins.gru.business.demand.Notification;
import fr.paris.lutece.plugins.gru.service.demandtype.DemandTypeService;
import fr.paris.lutece.portal.business.user.AdminUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * DemandService
 */
public class MokeDemandService implements IDemandService
{
    private static final String JSON = "{\n" + "   \"notification\": {\n" + "    \"user_guid\": 1108,\n" +
        " \"notification_source\": \"Gestion des sollicitations\",\n" + "    \"email\": \"mdupont@domain.com\",\n" +
        "    \"crm_status_id\": 1,\n" + "    \"notification_type\": \"\",\n" + "    \"demand_id\": 1099,\n" +
        "    \"demand_id_type\": 14,\n" + "    \"demand_max_step\": 5,\n" + "    \"demand_user_current_step\": 3,\n" +
        "    \"demand_state\": 9,\n" + "\n" + "    \"user_email\": {\n" +
        "      \"sender_name\": \"Mairie de Paris\",\n" + "      \"sender_email\": \"no_reply@paris.fr\",\n" +
        "      \"recipient\": \"mdupont@domain.com\",\n" +
        "      \"subject\": \"[Mairie de Paris] Demande de carte de stationnement\",\n" +
        "      \"message\": \"Bonjour Monsieur Maurice Dupont, Votre demande de carte de stationnement est en attente de validation. ...\",\n" +
        "      \"cc\": \"\",\n" + "      \"cci\": \"\"\n" + "    },\n" + "\n" + "    \"user_dashboard\": {\n" +
        "      \"status_text\": \"En attente de validation\",\n" + "      \"sender_name\": \"Mairie de Paris\",\n" +
        "      \"subject\": \"test notif\",\n" + "      \"message\": \"this is a test\",\n" + "      \"data\": \"\"\n" +
        "    },\n" + "\n" + "    \"user_sms\": {\n" + "      \"phone_number\": \"0680125345\",\n" +
        "      \"message\": \"Votre demande de carte de stationnement est en attente de validation.\"\n" + "    },\n" +
        "    \n" + "    \"backoffice_logging\": {\n" + "      \"status_text\": \"En attente de validation\",\n" +
        "      \"message\": \"Traitement de la demande en cours par le service de la DVD\",\n" +
        "      \"notified_on_dashboard\": 1,\n" + "      \"notified_by_email\": 1,\n" +
        "      \"notified_by_sms\": 1,\n" + "      \"display_level_dashboard_notification\": 2,\n" +
        "      \"view_dashboard_notification\": \"\",\n" + "      \"display_level_email_notification\": 2,\n" +
        "      \"view_email_notification\": \"Email envoyé à l'adresse : mdupont@domain.com – Objet : ... _ Message : ...\",\n" +
        "      \"display_level_sms_notification\": 2,\n" +
        "      \"view_sms_notification\": \"SMS envoyé au numéro 0680125345 _ Message : Votre demande de carte de stationnement est en attente de validation.\"\n" +
        "    }\n" + "  }\n" + "}";
    private static List<BaseDemand> _listDemand;

    @Override
    public Demand getDemand( String strDemandId, String strDemandTypeId, AdminUser user )
    {
        BaseDemand base = getList(  ).get( Integer.parseInt( strDemandId ) );

        Customer customer = CustomerHome.findByPrimaryKey( 1 );
        Demand demand = DemandTypeService.buildDemand( base, customer, user );

        Notification notification = NotificationService.parseJSON( JSON );
        notification.setTimestamp( ( new Date(  ) ).getTime(  ) );
        notification.setTitle( "Prise en compte de la demande" );
        demand.addNotification( notification );

        return demand;
    }

    @Override
    public List<BaseDemand> getDemands( String strCustomerId, AdminUser user )
    {
        return getList(  );
    }

    private List<BaseDemand> getList(  )
    {
        if ( _listDemand == null )
        {
            _listDemand = new ArrayList<BaseDemand>(  );

            BaseDemand demand1 = new BaseDemand(  );
            demand1.setId( "0" );
            demand1.setReference( "DVD15031324" );
            demand1.setDemandTypeId( "100" );
            demand1.setStatus( Demand.STATUS_CLOSED );
            _listDemand.add( demand1 );

            BaseDemand demand2 = new BaseDemand(  );
            demand2.setId( "1" );
            demand2.setReference( "INFO15031324" );
            demand2.setDemandTypeId( "101" );
            demand2.setStatus( Demand.STATUS_INPROGRESS );
            _listDemand.add( demand2 );

            BaseDemand demand3 = new BaseDemand(  );
            demand3.setId( "2" );
            demand3.setReference( "SAV15031324" );
            demand3.setDemandTypeId( "102" );
            demand3.setStatus( Demand.STATUS_INPROGRESS );
            _listDemand.add( demand3 );

            BaseDemand demand4 = new BaseDemand(  );
            demand4.setId( "3" );
            demand4.setReference( "DU15031324" );
            demand4.setDemandTypeId( "103" );
            demand4.setStatus( Demand.STATUS_INPROGRESS );
            _listDemand.add( demand4 );
        }

        return _listDemand;
    }
}
