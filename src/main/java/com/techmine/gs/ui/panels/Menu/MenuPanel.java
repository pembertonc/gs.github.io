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

import com.techmine.gs.Route;
import com.techmine.gs.Route.Actions;
import java.util.Optional;
import java.util.logging.Logger;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.event.Broadcast;
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
                AuthenticatedWebSession.get().signOut();
                Route route = new Route(Actions.LOGOUT, optnl);
                send(this.getPage(), Broadcast.EXACT, route);
            }

        }
        );

    }

    private static final Logger LOG = Logger.getLogger(MenuPanel.class.getName());

    @Override
    public boolean isVisible() {
        return AuthenticatedWebSession.get().isSignedIn();
    }
}
