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
package com.techmine.gs.ui.panels.views.cylinder;

import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 *
 * @author Cedric-Pemberton
 */
public class cylinder_view extends Panel {

    public cylinder_view(String id) {
        super(id);
    }

    public cylinder_view(String id, IModel<?> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        setOutputMarkupId(true);
        /*
        Un comment when editor and Search are impmented
         */
        add(initializeCylinderEditor("editor"));
        add(initiaizeCylinderSearch("searcher"));
    }

    private Panel initializeCylinderEditor(String wicketId) {
        throw new UnsupportedOperationException("Not supported yet.");
        //return new CylinderEditor(wicketId) // uncomment to initialize Editor.
    }

    private Panel initiaizeCylinderSearch(String wicketId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        // uncoment to initialize Search Comonent  return new CylinderSearch(wicketId);
    }
}
