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
package com.techmine.gs.ui.panels.custom_input_components.TextFieldWithMessage;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.IMarkupCacheKeyProvider;
import org.apache.wicket.markup.IMarkupResourceStreamProvider;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.resource.IResourceStream;

/**
 *
 * @author Cedric-Pemberton
 */
public abstract class AbstractInputFieldWFeedbackAndCaption extends Panel implements IMarkupResourceStreamProvider, IMarkupCacheKeyProvider {

    private AbstractInputFieldWFeedbackAndCaption(String id, IModel<?> model) {
        super(id, model);
    }

    public AbstractInputFieldWFeedbackAndCaption(String id, IModel<?> model, FieldType fieldType) {
        super(id, model);
    }

    /*
    @Override
    public IResourceStream getMarkupResourceStream(MarkupContainer mc, Class<?> type) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     */
    @Override
    public String getCacheKey(MarkupContainer mc, Class<?> type) {
        return null;
    }

    public static enum FieldType {
        EMAIL, NUMBER, PASSWORD, TEXT
    }

}
