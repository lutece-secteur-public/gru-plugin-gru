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
package fr.paris.lutece.plugins.gru.web.actions.panels.builders.impl;

import fr.paris.lutece.plugins.gru.business.customer.Customer;
import fr.paris.lutece.plugins.gru.business.feature.Feature;
import fr.paris.lutece.plugins.gru.business.feature.FeatureCategory;
import fr.paris.lutece.plugins.gru.business.feature.FeatureCategoryHome;
import fr.paris.lutece.plugins.gru.service.ActionLinkService;
import fr.paris.lutece.plugins.gru.web.actions.model.ActionGroup;
import fr.paris.lutece.plugins.gru.web.actions.model.ActionItem;
import fr.paris.lutece.plugins.gru.web.actions.panels.builders.PanelBuilder;

import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Feature Actions Panel Builder
 */
public class FeatureActionsPanelBuilder implements PanelBuilder
{
    private String _strTitle;

    /**
     * {@inheritDoc }
     */
    @Override
    public String getTitle(  )
    {
        return _strTitle;
    }

    /**
     * Sets the Title
     *
     * @param strTitle The Title
     */
    public void setTitle( String strTitle )
    {
        _strTitle = strTitle;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<ActionGroup> getActionGroups( Customer customer )
    {
        List<ActionGroup> listActionGroups = new ArrayList<ActionGroup>(  );
        List<FeatureCategory> listCategories = FeatureCategoryHome.getFeatureCategorysList(  );

        for ( FeatureCategory category : listCategories )
        {
            ActionGroup group = new ActionGroup(  );
            group.setTitle( category.getName(  ) );
            group.setIcon( category.getIcon(  ) );

            for ( Feature feature : category.getFeatures(  ) )
            {
                if(  ! feature.isHidden() )
                {    
                    ActionItem item = new ActionItem(  );
                    item.setTitle( feature.getName(  ) );

                    String strLinkTemplate = feature.getLink(  );

                    if ( ( customer != null ) && ( customer.getId(  ) != 0 ) )
                    {
                        strLinkTemplate += feature.getLinkCustomerParams(  );
                    }

                    item.setLink( ActionLinkService.buildLink( strLinkTemplate, feature.getTarget(  ), customer ) );
                    group.addActionItem( item );
                }
            }

            listActionGroups.add( group );
        }

        return listActionGroups;
    }
}
