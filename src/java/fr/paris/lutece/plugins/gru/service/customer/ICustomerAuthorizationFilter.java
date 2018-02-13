/*
 * Copyright (c) 2002-2017, Mairie de Paris
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
package fr.paris.lutece.plugins.gru.service.customer;

import java.util.List;

import fr.paris.lutece.plugins.grubusiness.business.customer.Customer;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.admin.AccessDeniedException;

/**
 * This interface filters the customer depending on the user authorizations
 *
 */
public interface ICustomerAuthorizationFilter
{
    /**
     * <p>
     * Filters the specified customer depending on the user authorizations
     * </p>
     * <p>
     * The implementation has to throw an {@code AccessDeniedException} if the user is not authorized to access the customer. Otherwise, it must call the method
     * {@code chain.doFilter( user, customer )}.
     * </p>
     * 
     * @param user
     *            the user
     * @param customer
     *            the customer to check
     * @param chain
     *            the filter chain to call if the user is authorized to access the customer
     * @throws AccessDeniedException
     *             if the user is not authorized to access the customer
     */
    void doFilter( AdminUser user, Customer customer, CustomerAuthorizationFilterChain chain ) throws AccessDeniedException;

    /**
     * <p>
     * Filters the specified list of customers depending on the user authorizations
     * </p>
     * <p>
     * The implementation has to remove from the list all the customers the user is not authorized to access. Once it is done, it must call the method
     * {@code chain.doFilter( user, listCustomer )}.
     * </p>
     * 
     * @param user
     *            the user
     * @param listCustomer
     *            the list of customers to filter
     * @param chain
     *            the filter chain to call once the treatment is done
     */
    void doFilter( AdminUser user, List<Customer> listCustomer, CustomerAuthorizationFilterChain chain );
}
