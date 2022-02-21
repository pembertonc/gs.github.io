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
package com.techmine.gs.ui.panels.Menu;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 *
 * @author Cedric-Pemberton
 */
public class MenuPanel extends Panel {

    private AjaxFallbackLink logout;

    public MenuPanel(String id) {
        super(id);
    }

    public MenuPanel(String id, IModel<?> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        setOutputMarkupPlaceholderTag(true);
        setMarkupId("menubar");
        add(logout = new AjaxFallbackLink("logout") {
            @Override
            protected void onInitialize() {
                super.onInitialize();

            }

            @Override
            protected boolean getStatelessHint() {
                return true;
            }

            @Override
            public void onClick(Optional optnl) {
                getSession().invalidate();
                // SignInPanel newBody = new SignInPanel("body");
                // getPage().get("Body").replaceWith(newBody);
                // ((AjaxRequestTarget) optnl.get()).add(getPage().get("logout"));

                /*    if (!AuthenticatedWebSession.get().isSignedIn()) {
                ((AuthenticatedWebApplication) Application.get()).restartResponseAtSignInPage();
                }*/
            }

        }
        );

    }

    private static final Logger LOG = Logger.getLogger(MenuPanel.class.getName());

    /* @Override
    public boolean isVisible() {
    LOG.log(Level.INFO, "is Visible being called");
    return AuthenticatedWebSession.get().isSignedIn();

    }*/
    @Override
    protected void onConfigure() {
        LOG.log(Level.INFO, "On Config being called being called is Visisble =" + isVisible());
        setVisible(AuthenticatedWebSession.get().isSignedIn());
        LOG.log(Level.INFO, "On Config being called being called again is Visisble =" + isVisible());
        LOG.log(Level.INFO, "iS signed in Value " + AuthenticatedWebSession.get().isSignedIn());
        LOG.log(Level.INFO, "iIs Session Temporary " + AuthenticatedWebSession.get().isTemporary());
        super.onConfigure();
    }
}
