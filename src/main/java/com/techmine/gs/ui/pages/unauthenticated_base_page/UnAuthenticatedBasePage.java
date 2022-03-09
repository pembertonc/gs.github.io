/*
 * Copyright 2021 Work.
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
package com.techmine.gs.ui.pages.unauthenticated_base_page;

import java.io.Serializable;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.https.RequireHttps;
import org.apache.wicket.request.resource.ContextRelativeResourceReference;

/**
 *
 * @author Work
 */
@RequireHttps
public abstract class UnAuthenticatedBasePage extends WebPage implements Serializable {

    public UnAuthenticatedBasePage() {
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

    }

    @Override
    public void renderHead(IHeaderResponse response) {
        String cssResourcePath = "resources/css/";
        ContextRelativeResourceReference cssFile = new ContextRelativeResourceReference(cssResourcePath + "w3.css", false);
        CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);
        response.render(cssItem);

        cssFile = new ContextRelativeResourceReference(cssResourcePath + "style.css", false);
        cssItem = CssHeaderItem.forReference(cssFile);
        response.render(cssItem);

        // by renaming the theme to w3-theme.css we can swap themes by simply replacing the file and giving it that name.
        cssFile = new ContextRelativeResourceReference(cssResourcePath + "w3-theme.css", false);
        cssItem = CssHeaderItem.forReference(cssFile);
        response.render(cssItem);

        cssItem = CssHeaderItem.forUrl("https://fonts.googleapis.com/icon?family=Material+Icons");
        response.render(cssItem);
    }

}
