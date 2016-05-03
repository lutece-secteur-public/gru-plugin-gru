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
package fr.paris.lutece.plugins.gru.business.customer;

import org.hibernate.validator.constraints.*;

import java.io.Serializable;

import javax.validation.constraints.*;


// TODO: Auto-generated Javadoc
/**
 * This is the business class for the object Customer.
 */
public class Customer implements Serializable
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The _n id. */
    // Variables declarations 
    private int _nId;

    /** The _n id title. */
    private int _nIdTitle;

    /** The _str firstname. */
    @NotEmpty( message = "#i18n{gru.validation.customer.Firstname.notEmpty}" )
    @Size( max = 50, message = "#i18n{gru.validation.customer.Firstname.size}" )
    private String _strFirstname;

    /** The _str lastname. */
    @NotEmpty( message = "#i18n{gru.validation.customer.Lastname.notEmpty}" )
    @Size( max = 50, message = "#i18n{gru.validation.customer.Lastname.size}" )
    private String _strLastname;

    /** The _b has account. */
    private boolean _bHasAccount;

    /** The _str account login. */
    @Size( max = 50, message = "#i18n{gru.validation.customer.AccountLogin.size}" )
    private String _strAccountLogin;

    /** The _str account guid. */
    @Size( max = 50, message = "#i18n{gru.validation.customer.AccountGuid.size}" )
    private String _strAccountGuid;

    /** The _str email. */
    @Email( message = "#i18n{portal.validation.message.email}" )
    @Size( max = 255, message = "#i18n{gru.validation.customer.Email.size}" )
    private String _strEmail;

    /** The _b is email verified. */
    private boolean _bIsEmailVerified;

    /** The _str fixe phone. */
    @Size( max = 50, message = "#i18n{gru.validation.customer.FixedPhoneNumber.size}" )
    private String _strFixedPhoneNumber;

    /** The _str mobile phone. */
    @Size( max = 50, message = "#i18n{gru.validation.customer.MobilePhone.size}" )
    private String _strMobilePhone;

    /** The _b is mobile phone verified. */
    private boolean _bIsMobilePhoneVerified;

    /** The _str extras attributes. */
    @NotEmpty( message = "#i18n{gru.validation.customer.ExtrasAttributes.notEmpty}" )
    private String _strExtrasAttributes;

    /**
     * Returns the Id.
     *
     * @return The Id
     */
    public int getId(  )
    {
        return _nId;
    }

    /**
     * Sets the Id.
     *
     * @param nId The Id
     */
    public void setId( int nId )
    {
        _nId = nId;
    }

    /**
     * Returns the IdTitle.
     *
     * @return The IdTitle
     */
    public int getIdTitle(  )
    {
        return _nIdTitle;
    }

    /**
     * Sets the IdTitle.
     *
     * @param nIdTitle The IdTitle
     */
    public void setIdTitle( int nIdTitle )
    {
        _nIdTitle = nIdTitle;
    }

    /**
     * Returns the Firstname.
     *
     * @return The Firstname
     */
    public String getFirstname(  )
    {
        return _strFirstname;
    }

    /**
     * Sets the Firstname.
     *
     * @param strFirstname The Firstname
     */
    public void setFirstname( String strFirstname )
    {
        _strFirstname = strFirstname;
    }

    /**
     * Returns the Lastname.
     *
     * @return The Lastname
     */
    public String getLastname(  )
    {
        return _strLastname;
    }

    /**
     * Sets the Lastname.
     *
     * @param strLastname The Lastname
     */
    public void setLastname( String strLastname )
    {
        _strLastname = strLastname;
    }

    /**
     * Returns the HasAccount.
     *
     * @return The HasAccount
     */
    public boolean getHasAccount(  )
    {
        return _bHasAccount;
    }

    /**
     * Sets the HasAccount.
     *
     * @param bHasAccount The HasAccount
     */
    public void setHasAccount( boolean bHasAccount )
    {
        _bHasAccount = bHasAccount;
    }

    /**
     * Returns the AccountLogin.
     *
     * @return The AccountLogin
     */
    public String getAccountLogin(  )
    {
        return _strAccountLogin;
    }

    /**
     * Sets the AccountLogin.
     *
     * @param strAccountLogin The AccountLogin
     */
    public void setAccountLogin( String strAccountLogin )
    {
        _strAccountLogin = strAccountLogin;
    }

    /**
     * Returns the AccountGuid.
     *
     * @return The AccountGuid
     */
    public String getAccountGuid(  )
    {
        return _strAccountGuid;
    }

    /**
     * Sets the AccountGuid.
     *
     * @param strAccountGuid The AccountGuid
     */
    public void setAccountGuid( String strAccountGuid )
    {
        _strAccountGuid = strAccountGuid;
    }

    /**
     * Returns the Email.
     *
     * @return The Email
     */
    public String getEmail(  )
    {
        return _strEmail;
    }

    /**
     * Sets the Email.
     *
     * @param strEmail The Email
     */
    public void setEmail( String strEmail )
    {
        _strEmail = strEmail;
    }

    /**
     * Returns the IsEmailVerified.
     *
     * @return The IsEmailVerified
     */
    public boolean getIsEmailVerified(  )
    {
        return _bIsEmailVerified;
    }

    /**
     * Sets the IsEmailVerified.
     *
     * @param bIsEmailVerified The IsEmailVerified
     */
    public void setIsEmailVerified( boolean bIsEmailVerified )
    {
        _bIsEmailVerified = bIsEmailVerified;
    }

    /**
     * Returns the MobilePhone.
     *
     * @return The MobilePhone
     */
    public String getMobilePhone(  )
    {
        return _strMobilePhone;
    }

    /**
     * Sets the MobilePhone.
     *
     * @param strMobilePhone The MobilePhone
     */
    public void setMobilePhone( String strMobilePhone )
    {
        _strMobilePhone = strMobilePhone;
    }

    /**
     * Gets the fixe phone.
     *
     * @return the fixe phone
     */
    public String getFixedPhoneNumber(  )
    {
        return _strFixedPhoneNumber;
    }

    /**
     * Sets the fixe phone.
     *
     * @param strFixedPhoneNumber the new fixe phone
     */
    public void setFixedPhoneNumber( String strFixedPhoneNumber )
    {
        _strFixedPhoneNumber = strFixedPhoneNumber;
    }

    /**
     * Returns the IsMobilePhoneVerified.
     *
     * @return The IsMobilePhoneVerified
     */
    public boolean getIsMobilePhoneVerified(  )
    {
        return _bIsMobilePhoneVerified;
    }

    /**
     * Sets the IsMobilePhoneVerified.
     *
     * @param bIsMobilePhoneVerified The IsMobilePhoneVerified
     */
    public void setIsMobilePhoneVerified( boolean bIsMobilePhoneVerified )
    {
        _bIsMobilePhoneVerified = bIsMobilePhoneVerified;
    }

    /**
     * Returns the ExtrasAttributes.
     *
     * @return The ExtrasAttributes
     */
    public String getExtrasAttributes(  )
    {
        return _strExtrasAttributes;
    }

    /**
     * Sets the ExtrasAttributes.
     *
     * @param strExtrasAttributes The ExtrasAttributes
     */
    public void setExtrasAttributes( String strExtrasAttributes )
    {
        _strExtrasAttributes = strExtrasAttributes;
    }
}
