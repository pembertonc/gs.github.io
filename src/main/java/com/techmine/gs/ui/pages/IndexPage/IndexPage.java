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

import com.techmine.gs.ui.events.Route;
import static com.techmine.gs.ui.events.Route.getDisplayViewClass;
import com.techmine.gs.ui.pages.unauthenticated_base_page.UnAuthenticatedBasePage;
import com.techmine.gs.ui.panels.Dashboard.Dashboard;
import com.techmine.gs.ui.panels.Menu.MenuPanel;
import com.techmine.gs.ui.panels.SignIn.SignInPanel;
import com.techmine.gs.ui.panels.views.user_view.UserView;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author Cedric-Pemberton
 */
public class IndexPage extends UnAuthenticatedBasePage {

    private static final String BODY_MARKUP_ID = "body";
    private static final String WICKET_BODY_ID = "body";
    private MenuPanel mainMenu;
    @Deprecated(forRemoval = true, since = "2022-03-24")
    private Component currentView;

    public IndexPage() {
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(mainMenu = (MenuPanel) new MenuPanel("menubar"));
        add(new SignInPanel(WICKET_BODY_ID).setMarkupId(BODY_MARKUP_ID));
        //add(currentView = new SignInPanel(WICKET_BODY_ID).setMarkupId(BODY_MARKUP_ID));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        mainMenu.setVisible(AuthenticatedWebSession.get().isSignedIn());
    }

    @Override
    public void onEvent(IEvent<?> event) {
        super.onEvent(event);

        if (event.getPayload() instanceof Route) {
            Route route = (Route) event.getPayload();
            //routeTo(route);
            routeToView(route);

        }
    }

    @Deprecated(forRemoval = true, since = "2022-03-24")
    private void routeTo(Route route) {

        switch (route.getAction()) {
            case LOGIN:
                // routeOnLogin(route);
                routeToView(route);
                break;
            case LOGOUT:
                routeOnLogout(route);
                break;
            case USER:
                routeOnUser(route);
                break;
            case OWNER:
                routeOnOwner(route);
                break;
            default:
                throw new AssertionError("Unknown Action.");
        }
    }

    private void routeToView(Route route) {

        try {
            Panel comp = Route.getDisplayViewClass(route.getAction()).getConstructor(String.class).newInstance(WICKET_BODY_ID);
            comp.setMarkupId(BODY_MARKUP_ID);
            this.replace(comp);
            route.getTarget().ifPresent((target) -> {
                target.add(mainMenu);
                target.add(comp);
            });
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(IndexPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(IndexPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(IndexPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(IndexPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(IndexPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(IndexPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        ;

        // Panel com = route.
    }

    @Deprecated(forRemoval = true, since = "2022-03-24")
    private void routeOnLogin(Route route) {
        Dashboard comp = (Dashboard) new Dashboard(WICKET_BODY_ID).setMarkupId(BODY_MARKUP_ID);
        this.replace(comp);
        route.getTarget().ifPresent((target) -> {
            target.add(mainMenu);
            target.add(comp);
        });
    }

    @Deprecated(forRemoval = true, since = "2022-03-24")
    private void routeOnLogout(Route route) {
        SignInPanel comp = (SignInPanel) new SignInPanel(WICKET_BODY_ID).setMarkupId(BODY_MARKUP_ID);
        replace(comp);

        route.getTarget().ifPresent((AjaxRequestTarget target) -> {
            target.add(comp);
        });
        //setResponsePage(IndexPage.class);
    }

    @Deprecated(forRemoval = true, since = "2022-03-24")
    private void routeOnUser(Route route) {
        Component userView = new UserView(WICKET_BODY_ID).setMarkupId(BODY_MARKUP_ID);
        replace(userView);
        route.getTarget().ifPresent((AjaxRequestTarget target) -> {
            target.add(userView);
        });
    }

    @Deprecated(forRemoval = true, since = "2022-03-24")
    private void routeOnOwner(Route route) {
        /*  Component userView = new OwnerView(WICKET_BODY_ID).setMarkupId(BODY_MARKUP_ID);
        replace(userView);
        route.getTarget().ifPresent((AjaxRequestTarget target) -> {
        target.add(userView);
        });*/
    }

}
