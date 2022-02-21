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
package com.techmine.gs.ui.pages.IndexPage;

import com.techmine.gs.Route;
import com.techmine.gs.ui.pages.unauthenticated_base_page.UnAuthenticatedBasePage;
import com.techmine.gs.ui.panels.Dashboard.Dashboard;
import com.techmine.gs.ui.panels.Menu.MenuPanel;
import com.techmine.gs.ui.panels.SignIn.SignInPanel;
import com.techmine.gs.ui.panels.views.userView.UserView;
import org.apache.wicket.Component;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.event.IEvent;

/**
 *
 * @author Cedric-Pemberton
 */
public class IndexPage extends UnAuthenticatedBasePage {

    private MenuPanel mainMenu;
    private Component currentView;

    public IndexPage() {
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(mainMenu = (MenuPanel) new MenuPanel("menubar"));
        add(currentView = new SignInPanel("body").setMarkupId("body"));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        System.out.println("Stop 6 IndexPage onConfigure");
        mainMenu.setVisible(AuthenticatedWebSession.get().isSignedIn());

    }

    @Override
    public void onEvent(IEvent<?> event) {
        super.onEvent(event);
        System.out.println("Stop 2 onEvent");
        if (event.getPayload() instanceof Route) {
            Route route = (Route) event.getPayload();
            routeTo(route);

        }
    }

    private void routeTo(Route route) {

        switch (route.getAction()) {
            case LOGIN:
                routeOnLogin(route);
                break;
            case LOGOUT:
                routeOnLogout(route);
                break;
            case USER:
                routeOnUser(route);
                break;
            default:
                throw new AssertionError("Unknown Action.");
        }
    }

    private void routeOnLogin(Route route) {
        Dashboard comp = (Dashboard) new Dashboard("body").setMarkupId("dashboard");
        System.out.println("Stop 3 routeLogin");
        //currentView.replaceWith(comp);

        this.replace(comp);
        route.getTarget().ifPresent((var target) -> {
            target.add(mainMenu);
            target.add(comp);
        });

    }

    private void routeOnLogout(Route route) {
        SignInPanel comp = (SignInPanel) new SignInPanel("body");
        currentView.replaceWith(comp);
        currentView = comp;
        route.getTarget().ifPresent((var target) -> {
            target.add(comp);
        });
        //setResponsePage(IndexPage.class);
    }

    private void routeOnUser(Route route) {
        Component userView = new UserView("body");
        replace(userView);
        route.getTarget().ifPresent((var target) -> {
            target.add(userView);
        });
    }

}
