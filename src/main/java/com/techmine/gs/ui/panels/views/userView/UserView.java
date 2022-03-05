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
package com.techmine.gs.ui.panels.views.userView;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 *
 * @author Cedric-Pemberton
 */
public class UserView extends Panel {

    private Component editor;
    private UserSearch userSearch;

    public UserView(String id, IModel<?> model) {
        super(id, model);
    }

    public UserView(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        setOutputMarkupId(true);
        add(editor = initializeUserEditor());
        add(userSearch = new UserSearch("userSearch"));
    }

    private Component initializeUserEditor() {
        return new UserEditor("editor");
    }

}
