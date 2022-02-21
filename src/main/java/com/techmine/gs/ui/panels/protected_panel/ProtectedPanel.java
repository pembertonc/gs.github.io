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

 /* checks for for authentication and redirects to load SignInPanel if not authenticated

 */
package com.techmine.gs.ui.panels.protected_panel;

import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author Cedric-Pemberton
 */
public abstract class ProtectedPanel extends Panel {

    public ProtectedPanel() {
        super(null);
    }

    public ProtectedPanel(String id) {
        super(id);
    }

}
