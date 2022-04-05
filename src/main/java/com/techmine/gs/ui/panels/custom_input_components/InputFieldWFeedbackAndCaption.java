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
package com.techmine.gs.ui.panels.custom_input_components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.IMarkupCacheKeyProvider;
import org.apache.wicket.markup.IMarkupResourceStreamProvider;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.validation.IValidator;

/**
 *
 * @author Cedric-Pemberton
 */
public class InputFieldWFeedbackAndCaption extends FormComponentPanel implements IMarkupResourceStreamProvider, IMarkupCacheKeyProvider, Serializable {

    private static final String INPUT_WICKET_ID = "inputComponent";
    protected FieldType fieldType;
    protected List<IValidator> validators;
    protected String captionValue;
    @Deprecated
    protected boolean inputRequired;

    protected FormComponent formComponent;
    protected Label caption;
    protected FeedbackPanel errorMessage;

    public InputFieldWFeedbackAndCaption(String id, IModel<?> model, String captionValue, FieldType fieldType) {
        super(id, model);
        this.fieldType = fieldType;
        this.captionValue = captionValue;
        this.validators = new ArrayList<>();

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        //setRenderBodyOnly(true);
        this.caption = (Label) new Label("caption", captionValue).setOutputMarkupId(true);
        this.formComponent = initializeInputField(fieldType, getDefaultModel(), captionValue);
        formComponent.setOutputMarkupId(true)
                .add(new AttributeModifier("class", "form-control"));
        addAjaxUpdatingBehaviorToFormComponent();
        applyValidators();
        this.errorMessage = new FeedbackPanel("errorMessage", new ComponentFeedbackMessageFilter(formComponent));
        errorMessage.setOutputMarkupPlaceholderTag(true);
        add(formComponent).add(caption).add(errorMessage);
    }

    @Override
    public IResourceStream getMarkupResourceStream(MarkupContainer mc, Class<?> type) {
        //<input class="form-control" placeholder="User Name" type="text" wicket:id="inputComponent" />

        String markup = new StringBuilder("<wicket:panel>")
                .append("<div class=\"control-group\">")
                .append("<input class=\"form-control\" placeholder=\"Place Holder\" ")
                .append(getInputType())
                .append(" wicket:id=\"inputComponent\" />")
                .append("<label class=\"control-label\" for=\"inputComponent\" wicket:id=\"caption\">User Name</label>")
                .append(" <div  wicket:id=\"errorMessage\"></div>")
                .append("</div>")
                .append("</wicket:panel>").toString();
        return new StringResourceStream(markup);

    }

    @Override
    public String getCacheKey(MarkupContainer mc, Class<?> type) {

        String className = type.isAnonymousClass() ? type.getSuperclass().getSimpleName() : type.getSimpleName();
        /*
        if we return null then evertime the markup is required it will be rendered. if we
        include the id int he cacheKey then ever usage will have a templae stored similar to the static template.
        -- need to look at the actually page store mechanism to determine difference between static page and
        storage of each instance / usage .  --
        if we leave out the id then we only store copies for each type of internal input.

        Configure gets called on each rendering so we should not have an issue

         */
        return className + fieldType + captionValue;

    }

    private void addAjaxUpdatingBehaviorToFormComponent() {
        formComponent.add(new AjaxFormComponentUpdatingBehavior("blur") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                Optional.of(target).ifPresent((AjaxRequestTarget t) -> {
                    t.add(errorMessage);
                    t.add(caption);
                    if (fieldType != FieldType.PASSWORD) {
                        t.add(formComponent);
                    }
                });
            }

            @Override
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
                super.onError(target, e);
                Optional.of(target).ifPresent((AjaxRequestTarget t) -> {
                    t.add(errorMessage);
                    t.add(caption);
                    t.add(formComponent);
                });
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

    protected FormComponent initializeInputField(FieldType fieldType, IModel model, String captionValue) {
        FormComponent result;
        switch (fieldType) {
            case TEXT:
                result = initializeTextField(model, captionValue);
                break;
            case NUMBER:
                result = initializeNumberTextField(model, captionValue);
                break;
            case PASSWORD:
                result = initializePasswordTextField(model, captionValue);
                break;
            case EMAIL:
                result = initializeEmailTextField(model, captionValue);
                break;
            default:
                throw new UnsupportedOperationException("InputFieldWFeedbackAndCaption does not support the specified field type");
        }
        return result;
    }

    private FormComponent initializeTextField(IModel model, String captionValue) {
        return new TextField(INPUT_WICKET_ID, model) {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                setOutputMarkupId(true);
                add(new AttributeModifier("class", "form-control"));
                setLabel(Model.of(captionValue));
            }

        }.setLabel(Model.of(captionValue));
    }

    private FormComponent initializeNumberTextField(IModel model, String captionValue) {
        return new NumberTextField(INPUT_WICKET_ID, model).setLabel(Model.of(captionValue));

    }

    private FormComponent initializePasswordTextField(IModel model, String captionValue) {
        return new PasswordTextField(INPUT_WICKET_ID, model) {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                setOutputMarkupId(true);
                add(new AttributeModifier("class", "form-control"));
                setLabel(Model.of(captionValue));
                /*  Need to trace down why setting resetPassword to true
                casues a Attempted to set property value on a null object. Property
                express: password ...
                 */
                setResetPassword(false);
            }

            @Override
            public void convertInput() {
                super.convertInput();
                InputFieldWFeedbackAndCaption.this.setConvertedInput(getConvertedInput());
            }
        };
    }

    private FormComponent initializeEmailTextField(IModel model, String captionValue) {
        return new EmailTextField(INPUT_WICKET_ID, model).setLabel(Model.of(captionValue));
    }

    @Deprecated
    public boolean isInputRequired() {
        return inputRequired;
    }

    @Deprecated
    public void setInputRequired(boolean inputRequired) {
        this.inputRequired = inputRequired;
    }

    public InputFieldWFeedbackAndCaption inputRequired(boolean inputRequired) {
        setInputRequired(inputRequired);
        return this;

    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        PackageResourceReference cssFile = new PackageResourceReference(this.getClass(), "TextFieldWFeedback.css");
        CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);
        response.render(cssItem);

    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();

    }

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
        return inputType;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        this.formComponent.setLabel(Model.of(captionValue));
        formComponent.setRequired(this.isRequired());
        /* if (isRequired()) {
        formComponent.add(AttributeModifier.append("required", true));
        }*/

    }

    /**
     * The FormComonent does not receive the input so it reports Required
     * Violation. the convert input is over ridden to pass the value received by
     * the fromComponent to the SetConvertedInput of the FormComponentPane.
     */
    @Override
    public void convertInput() {
        super.convertInput();

        setConvertedInput(formComponent.getConvertedInput());
    }

    public enum FieldType {
        EMAIL, NUMBER, PASSWORD, TEXT
    }

}
