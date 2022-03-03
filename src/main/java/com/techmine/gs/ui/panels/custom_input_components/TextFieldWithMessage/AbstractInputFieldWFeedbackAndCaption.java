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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.IValidator;

/**
 * Creates a component that contains a internal TextFieldDecedent. The type of
 * Decendent is indicated by passing A FieldType to the Constructor. The
 * Initialized TexField is passed an an AjaxtFormComponentUpdatingBehavior.
 * Validation is done by calling the
 *
 * @author Cedric-Pemberton
 */
public abstract class AbstractInputFieldWFeedbackAndCaption extends FormComponentPanel {

    protected FormComponent formComponent;
    protected Label caption;
    protected FeedbackPanel errorMessage;
    protected FieldType fieldType;
    protected List<IValidator> validators;
    protected String captionValue;

    private AbstractInputFieldWFeedbackAndCaption(String id, IModel<?> model) {
        super(id, model);
    }

    public AbstractInputFieldWFeedbackAndCaption(String id, IModel<?> model, String captionValue, FieldType fieldType) {
        this(id, model);
        this.fieldType = fieldType;
        this.validators = new ArrayList();
        this.captionValue = captionValue;

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(caption = new Label("fieldCaption", captionValue));
        add(formComponent = initializeInputField(fieldType, getDefaultModel(), captionValue));
        addAjaxUpdatingBehaviorToFormComponent();
        applyValidators();
        add(errorMessage = new FeedbackPanel("errorMessage", new ComponentFeedbackMessageFilter(formComponent)));
    }

    private void addAjaxUpdatingBehaviorToFormComponent() {
        formComponent.add(new AjaxFormComponentUpdatingBehavior("blur") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                Optional.of(target).ifPresent((AjaxRequestTarget t) -> t.add(errorMessage));
            }

            @Override
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
                super.onError(target, e);
                Optional.of(target).ifPresent((AjaxRequestTarget t) -> t.add(errorMessage));
            }
        });
    }

    private void applyValidators() {
        validators.iterator().forEachRemaining((IValidator v) -> formComponent.add(v));

    }

    protected FormComponentPanel addInputFieldValidator(IValidator validator) {
        this.validators.add(validator);
        return this;

    }

    protected FormComponent initializeInputField(FieldType fieldType, IModel model, String id) {
        FormComponent result;
        switch (fieldType) {
            case TEXT:
                result = initializeTextField(model);
                break;
            case NUMBER:
                result = initializeNumberTextField(model);
                break;
            case PASSWORD:
                result = initializePasswordTextField(model);
                break;
            case EMAIL:
                result = initializeEmailTextField(model);
                break;
            default:
                throw new UnsupportedOperationException("InputFieldWFeedbackAndCaption does not support the specified field type");
        }
        return result;
    }

    private FormComponent initializeTextField(IModel model) {
        return new TextField("inputComponent", model);
    }

    private FormComponent initializeNumberTextField(IModel model) {
        return new NumberTextField("inputComponent", model);

    }

    private FormComponent initializePasswordTextField(IModel model) {
        return (FormComponent) new PasswordTextField("inputComponent", model);

    }

    private FormComponent initializeEmailTextField(IModel model) {

        return new EmailTextField("inputComponent", model);

    }

    /*
    protected String getInputType() {
    String inputType = null;
    switch (this.fieldType) {
    case TEXT:
    inputType = " type=\"text\" ";
    break;
    case NUMBER:
    inputType = " type=\"number\" ";
    break;
    case EMAIL:
    inputType = " type=\"email\" ";
    break;
    case PASSWORD:
    inputType = " type=\"password\" ";
    break;
    default:
    throw new UnsupportedOperationException("Html Input type not supported");
    }
    return inputType ;
}
     */
    public static enum FieldType {
        EMAIL, NUMBER, PASSWORD, TEXT
    }

}
