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

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.validation.IValidator;

/**
 *
 * @author Cedric-Pemberton
 * @param <T>
 */
public class TextFieldWFeedback<T extends FormComponent> extends FormComponentPanel {

    private FormComponent component;

    private Label fieldLabel;

    private FeedbackPanel feedback;

    public TextFieldWFeedback(String id, LambdaModel<?> model, String caption, Class<? extends FormComponent> componentClass) {
        this(id, model);

        Class[] parameterTypes = {String.class, IModel.class};
        try {
            this.component = componentClass.getDeclaredConstructor(parameterTypes).newInstance("formComponent", model);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(TextFieldWFeedback.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.fieldLabel = new Label("fieldLabel", caption);
    }

    private TextFieldWFeedback(String id, IModel model) {
        super(id, model);

    }

    /**
     * Adds a validatior to the internal Input Component
     *
     * @param behavior
     * @return
     */
    public Component internalAdd(Behavior behavior) {

        return component.add(behavior);
    }

    /**
     * Adds a a behavior to the internal Input Component.
     *
     * @param validator
     * @return
     */
    public Component internalAdd(IValidator validator) {
        return this.component.add(validator);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        setOutputMarkupPlaceholderTag(true);
        this.fieldLabel.setOutputMarkupPlaceholderTag(true);
        this.component.add(initializeUpdatingBehavior());

    }

    private AjaxFormComponentUpdatingBehavior initializeUpdatingBehavior() {
        return new AjaxFormComponentUpdatingBehavior("blur") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                Optional.of(target).ifPresent((t) -> {
                    t.add(feedback);
                    t.add(fieldLabel);
                });
            }
        };
    }

    public FormComponent internalSetRequired(boolean required) {
        return this.component.setRequired(required);

    }

}
