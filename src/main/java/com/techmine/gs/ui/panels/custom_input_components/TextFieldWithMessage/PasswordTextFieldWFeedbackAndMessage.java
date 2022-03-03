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
public class PasswordTextFieldWFeedbackAndMessage extends AbstractInputFieldWFeedbackAndCaption {

    public PasswordTextFieldWFeedbackAndMessage(String id, IModel<?> model, String captionValue) {
        super(id, model, captionValue, FieldType.PASSWORD);
    }

}
