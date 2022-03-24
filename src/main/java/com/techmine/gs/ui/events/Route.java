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
package com.techmine.gs.ui.events;

import com.techmine.gs.ui.panels.Dashboard.Dashboard;
import com.techmine.gs.ui.panels.SignIn.SignInPanel;
import com.techmine.gs.ui.panels.views.user_view.UserView;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author Cedric-Pemberton
 */
public class Route {

    private final RouteName action;

    private static final Map<RouteName, Class<? extends Panel>> routes = new HashMap<>();

    static {
        routes.put(RouteName.LOGIN, Dashboard.class);
        routes.put(RouteName.LOGOUT, SignInPanel.class);
        routes.put(RouteName.USER, UserView.class);
        // routes.put(RouteName.OWNER, OwnerView.class);

    }

    private final Optional<AjaxRequestTarget> target;

    public Route(RouteName action, Optional<AjaxRequestTarget> target) {
        this.action = action;
        this.target = target;
    }

    public RouteName getAction() {
        return this.action;
    }

    public Optional<AjaxRequestTarget> getTarget() {
        return target;
    }

    public static enum RouteName {
        LOGIN, LOGOUT, USER, OWNER, CHECKIN_CYLINDER, CHECK_OUT_CYLINDER, CYLINDER
    }

    public static Class<? extends Panel> getDisplayViewClass(RouteName action) {

        return routes.get(action);

    }
}
