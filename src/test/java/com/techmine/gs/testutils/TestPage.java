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
package com.techmine.gs.testutils;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author Cedric-Pemberton
 */
public class TestPage extends Page {

    public TestPage() {
    }

    public TestPage(IModel<?> model) {
        super(model);
    }

    public TestPage(PageParameters parameters) {
        super(parameters);
    }

    Form<Void> testForm;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        testForm = new Form("testForm", getDefaultModel());
    }

}
