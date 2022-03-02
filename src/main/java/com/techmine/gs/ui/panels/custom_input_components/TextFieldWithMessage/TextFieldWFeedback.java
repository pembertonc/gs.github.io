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

import java.util.Optional;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.validation.IValidator;

/**
 *
 * @author Cedric-Pemberton
 * @param <T>
 */
public class TextFieldWFeedback extends FormComponentPanel {

    private FormComponent component;

    private Label fieldLabel;

    private FeedbackPanel feedback;
    private String caption;

    public TextFieldWFeedback(String id, IModel model, String caption) {
        this(id, model);
        this.caption = caption;

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
        this.fieldLabel = (Label) new Label("inputLabel", caption)
                .setOutputMarkupPlaceholderTag(true);
        this.component = (FormComponent) initializeInputField();

        internalAdd(initializeUpdatingBehavior());

        this.feedback = (FeedbackPanel) new FeedbackPanel("feedback", new ComponentFeedbackMessageFilter(component))
                .setOutputMarkupPlaceholderTag(true);
        add(this.component)
                .add(fieldLabel)
                .add(feedback);

    }

    private AjaxFormComponentUpdatingBehavior initializeUpdatingBehavior() {
        return new AjaxFormComponentUpdatingBehavior("blur") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                Optional.of(target).ifPresent((t) -> {
                    t.add(feedback);
                    //t.add(fieldLabel);
                });
            }

            @Override
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
                super.onError(target, e);
                Optional.of(target).ifPresent((AjaxRequestTarget t) -> {
                    t.add(feedback);
                    //t.add(fieldLabel);
                });

            }

        };
    }

    public FormComponent internalSetRequired(boolean required) {
        return setRequired(required);
    }

    private Component initializeInputField() {
        return new TextField("inputComponent", getDefaultModel()) {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                setLabel(Model.of(caption));

            }

            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                tag.put("required", ((FormComponent) getParent()).isRequired());

            }

        }.setRequired(this.isRequired());
        //.setLabel(Model.of(caption))
        // .setRequired(this.isRequired());

    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        PackageResourceReference cssFile = new PackageResourceReference(this.getClass(), "TextFieldWFeedback.css");
        CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);
        response.render(cssItem);

    }

}
