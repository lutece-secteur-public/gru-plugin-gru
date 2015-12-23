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
package fr.paris.lutece.plugins.gru.business.demand;

/**
 *
 * @author pierre
 */
public class BaseDemand
{
    // Variables declarations 
    private String _strDemandId;
    private String _strDemandReference;
    private String _strDemandTypeId;
    private int _nDemandStatus;
    
    /**
     * Returns the DemandId
     *
     * @return The DemandId
     */
    public String getId(  )
    {
        return _strDemandId;
    }

    /**
     * Sets the DemandId
     *
     * @param strDemandId The DemandId
     */
    public void setId( String strDemandId )
    {
        _strDemandId = strDemandId;
    }


    /**
     * Returns the DemandReference
     *
     * @return The DemandReference
     */
    public String getReference(  )
    {
        return _strDemandReference;
    }

    /**
     * Sets the DemandReference
     *
     * @param strDemandReference The DemandReference
     */
    public void setReference( String strDemandReference )
    {
        _strDemandReference = strDemandReference;
    }


    /**
     * Returns the DemandStatus
     *
     * @return The DemandStatus
     */
    public int getStatus(  )
    {
        return _nDemandStatus;
    }

    /**
     * Sets the DemandStatus
     *
     * @param nDemandStatus The DemandStatus
     */
    public void setStatus( int nDemandStatus )
    {
        _nDemandStatus = nDemandStatus;
    }

    /**
     * Sets the DemandTypeId
     *
     * @param strDemandTypeId The DemandType Id
     */
    public void setDemandTypeId( String strDemandTypeId )
    {
        _strDemandTypeId = strDemandTypeId;
    }

    /**
     * Returns the DemandTypeId
     *
     * @return The DemandTypeId
     */
    public String getDemandTypeId(  )
    {
        return _strDemandTypeId;
    }

}
