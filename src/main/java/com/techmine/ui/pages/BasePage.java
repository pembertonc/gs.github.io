/*
 * Copyright 2021 CodeCamp4.
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
package com.techmine.ui.pages;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

/**
 *
 * @author CodeCamp4
 */
public class BasePage extends WebPage {

    @Override
    protected void onInitialize() {
        super.onInitialize(); 
        WebMarkupContainer pageHeader = new WebMarkupContainer("pageHeader");
        pageHeader.add(new Label("headerText").setDefaultModel(Model.of("Gas Supplies")));
        add(pageHeader);

    }
    
    
    
}
