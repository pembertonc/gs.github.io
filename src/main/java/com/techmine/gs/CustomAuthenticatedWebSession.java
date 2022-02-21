/*
 * Copyright 2022 Cedric-Pemberton.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.techmine.gs;

import com.techmine.gs.service.AuthenticationService;
import javax.inject.Inject;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

/**
 *
 * @author Cedric-Pemberton
 */
public class CustomAuthenticatedWebSession extends AuthenticatedWebSession {

    @Inject
    private AuthenticationService authenticationService;

    public CustomAuthenticatedWebSession(Request request) {
        super(request);
    }

    @Override
    protected boolean authenticate(String userName, String password) {
        return authenticationService.login(userName, password);

    }

    @Override
    public Roles getRoles() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
