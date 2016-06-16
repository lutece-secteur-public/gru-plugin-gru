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
package fr.paris.lutece.plugins.gru.service.search;


/**
 * CustomerResult
 */
public class CustomerResult
{
    private int _nId;
    private String _strFirstname;
    private String _strLastname;
    private String _strEmail;
    private String _strMobilePhone;

    /**
     * Returns the Id
     * @return The Id
     */
    public int getId(  )
    {
        return _nId;
    }

    /**
     * Sets the Id
     * @param nId The Id
     */
    public void setId( int nId )
    {
        _nId = nId;
    }

    /**
     * Returns the Firstname
     * @return The Firstname
     */
    public String getFirstname(  )
    {
        return _strFirstname;
    }

    /**
     * Sets the Firstname
     * @param strFirstname The Firstname
     */
    public void setFirstname( String strFirstname )
    {
        _strFirstname = strFirstname;
    }

    /**
     * Returns the Lastname
     * @return The Lastname
     */
    public String getLastname(  )
    {
        return _strLastname;
    }

    /**
     * Sets the Lastname
     * @param strLastname The Lastname
     */
    public void setLastname( String strLastname )
    {
        _strLastname = strLastname;
    }

    /**
     * Returns the Email
     * @return The Email
     */
    public String getEmail(  )
    {
        return _strEmail;
    }

    /**
     * Sets the Email
     * @param strEmail The Email
     */
    public void setEmail( String strEmail )
    {
        _strEmail = strEmail;
    }

    /**
     * Returns the MobilePhone
     * @return The MobilePhone
     */
    public String getMobilePhone(  )
    {
        return _strMobilePhone;
    }

    /**
     * Sets the MobilePhone
     * @param strMobilePhone The MobilePhone
     */
    public void setMobilePhone( String strMobilePhone )
    {
        _strMobilePhone = strMobilePhone;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _nId;
		result = prime * result
				+ ((_strEmail == null) ? 0 : _strEmail.hashCode());
		result = prime * result
				+ ((_strFirstname == null) ? 0 : _strFirstname.hashCode());
		result = prime * result
				+ ((_strLastname == null) ? 0 : _strLastname.hashCode());
		result = prime * result
				+ ((_strMobilePhone == null) ? 0 : _strMobilePhone.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerResult other = (CustomerResult) obj;
		if (_nId != other._nId)
			return false;
		if (_strEmail == null) {
			if (other._strEmail != null)
				return false;
		} else if (!_strEmail.equals(other._strEmail))
			return false;
		if (_strFirstname == null) {
			if (other._strFirstname != null)
				return false;
		} else if (!_strFirstname.equals(other._strFirstname))
			return false;
		if (_strLastname == null) {
			if (other._strLastname != null)
				return false;
		} else if (!_strLastname.equals(other._strLastname))
			return false;
		if (_strMobilePhone == null) {
			if (other._strMobilePhone != null)
				return false;
		} else if (!_strMobilePhone.equals(other._strMobilePhone))
			return false;
		return true;
	}
    
    
}
