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

import org.apache.wicket.model.IModel;

/**
 *
 * @author Cedric-Pemberton
 */
@Deprecated(forRemoval = true, since = "2022-03-04")
public class InputFieldWFeedbackAndCaption extends AbstractInputFieldWFeedbackAndCaption /*  implements IMarkupResourceStreamProvider, IMarkupCacheKeyProvider */ {

    public InputFieldWFeedbackAndCaption(String id, IModel<?> model, String captionValue, FieldType fieldType) {
        super(id, model, captionValue, fieldType);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

    }

    /*
    @Override
    public IResourceStream getMarkupResourceStream(MarkupContainer mc, Class<?> type) {
    //<input class="form-control" placeholder="User Name" type="text" wicket:id="inputComponent" />

    String markup = new StringBuilder("<wicket:panel><wicket:extend>")
    .append("<input class=\"form-control\" placeholder=\"User Name\" ")
    .append(getInputType())
    .append(" id=\"inputComponent\" />")
    .append("</wicket:extend></wicket:panel>").toString();

    StringResourceStream resourceStream = new StringResourceStream(markup);
    return resourceStream;
    }*/
 /*
    @Override
    public String getCacheKey(MarkupContainer mc, Class<?> type) {
        String className = type.isAnonymousClass() ? type.getSuperclass().getSimpleName() : type.getSimpleName();
        return className + fieldType;

    }
     */
}
