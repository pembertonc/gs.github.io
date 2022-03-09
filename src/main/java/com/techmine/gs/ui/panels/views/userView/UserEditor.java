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
package com.techmine.gs.ui.panels.views.userView;

import com.techmine.gs.domain.Contact;
import com.techmine.gs.domain.Person;
import com.techmine.gs.domain.Subject;
import com.techmine.gs.service.AuthenticationService;
import com.techmine.gs.service.UserService;
import com.techmine.gs.ui.panels.custom_input_components.TextFieldWithMessage.InputFieldWFeedbackAndCaption;
import com.techmine.gs.ui.panels.custom_input_components.TextFieldWithMessage.InputFieldWFeedbackAndCaption.FieldType;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.IEvent;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;

/**
 *
 * @author Cedric-Pemberton
 */
public class UserEditor extends Panel {

    private AuthenticationService authenticatedService;

    private IModel<Subject> selected;

    @Inject
    void setAuthenticatedService(AuthenticationService authenticatedService) {
        this.authenticatedService = authenticatedService;
    }

    private Form<Subject> editForm;

    public UserEditor(String id) {
        this(id, Model.of(new Subject()));

    }

    public UserEditor(String id, IModel<Subject> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        setRenderBodyOnly(true);
        setOutputMarkupId(true); // needed for ajax support.
        Subject sub = (Subject) getDefaultModelObject();
        add(editForm = initializeEditForm("editForm", (IModel<Subject>) getDefaultModel()));
        /*
        editForm.add(initializeTextField("userName", LambdaModel.of(sub::getUserName, sub::setUserName), true)
        .add(new AjaxFormComponentUpdatingBehavior("blur") {
        @Override
        protected void onUpdate(AjaxRequestTarget target) {
        target.add(get("userNameFeedback"));
        }
        }));*/
        //editForm.add(initializePassword("password", LambdaModel.of(sub::getPassword, sub::setPassword), true));

        Person person = sub.getPerson();
        // editForm.add(initializeTextField("firstName", LambdaModel.of(person::getFirstName, person::setFirstName), true));
        // editForm.add(initializeTextField("familyName", LambdaModel.of(person::getFamilyName, person::setFamilyName), true));
        // editForm.add(initializeTextField("otherName", LambdaModel.of(person::getOtherName, person::setOtherName), true));
        Contact cont = person.getContact().get();
        // editForm.add(initializeEmailField("email", LambdaModel.of(cont::getEmail, cont::setEmail), true));
        //  editForm.add(initializeTextField("telephone1", LambdaModel.of(cont::getTelephone1, cont::setTelephone1), true));
        //  editForm.add(initializeTextField("telephone2", LambdaModel.of(cont::getTelephone2, cont::setTelephone2), true));
        // Buttons
        editForm.add(initializeSave("save", null));
        editForm.add(initializeCancel("cancel", null));
        editForm.add(initializeNew("new", null));

        editForm.add(new InputFieldWFeedbackAndCaption("userName", LambdaModel.of(sub::getUserName, sub::setUserName), "User Name", FieldType.TEXT).setRequired(true));
        editForm.add(new InputFieldWFeedbackAndCaption("password", LambdaModel.of(sub::getPassword, sub::setPassword), "Password", FieldType.PASSWORD).setRequired(true));
        editForm.add(new InputFieldWFeedbackAndCaption("firstName", LambdaModel.of(person::getFirstName, person::setFirstName), "firstName", FieldType.TEXT).setRequired(true));
        editForm.add(new InputFieldWFeedbackAndCaption("familyName", LambdaModel.of(person::getFamilyName, person::setFamilyName), "Family Name", FieldType.TEXT).setRequired(true));
        editForm.add(new InputFieldWFeedbackAndCaption("otherName", LambdaModel.of(person::getOtherName, person::setOtherName), "Other Name", FieldType.TEXT).setRequired(false));

        editForm.add(new InputFieldWFeedbackAndCaption("telephone1", LambdaModel.of(cont::getTelephone1, cont::setTelephone1), "Telephone 1", FieldType.TEXT).setRequired(true));
        editForm.add(new InputFieldWFeedbackAndCaption("telephone2", LambdaModel.of(cont::getTelephone2, cont::setTelephone2), "Telephone 2", FieldType.TEXT).setRequired(false));
        editForm.add(new InputFieldWFeedbackAndCaption("email", LambdaModel.of(cont::getEmail, cont::setEmail), "Email", FieldType.EMAIL).setRequired(true));

    }

    private Form<Subject> initializeEditForm(String id, IModel<Subject> model) {
        return new Form(id, model);
    }

    @Deprecated
    private TextField initializeTextField(String id, IModel<String> model, boolean required) {
        return (TextField) new TextField(id, model).setRequired(required);
    }

    @Deprecated
    private FormComponent initializePassword(String id, IModel<String> model, boolean required) {
        return new PasswordTextField(id, model).setResetPassword(false);

    }

    @Deprecated
    private FormComponent initializeEmailField(String email, IModel<String> model, boolean required) {
        return new EmailTextField("email", model).setRequired(required);
    }

    private Button initializeSave(String id, Form<Void> form) {
        return new AjaxButton(id, form) {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                setDefaultFormProcessing(true);
                add(new AjaxFormSubmitBehavior("click") {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target) {
                        super.onSubmit(target);

                        save((Subject) getParent().getDefaultModel().getObject());
                        target.add(UserEditor.this);
                    }

                    @Override
                    protected void onError(AjaxRequestTarget target) {
                        super.onError(target);

                    }

                });
            }

        };
    }

    private Button initializeCancel(String id, Form<Void> form) {
        return new AjaxButton(id, form) {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                add(new AjaxFormSubmitBehavior("click") {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target) {
                        super.onSubmit(target);

                        target.add(getParent());

                    }

                    @Override
                    protected void onError(AjaxRequestTarget target) {
                        super.onError(target);
                    }

                });
            }

        };
    }

    private Button initializeNew(String id, Form<Void> form) {
        return new AjaxButton(id, form) {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                add(new AjaxFormSubmitBehavior("click") {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target) {
                        super.onSubmit(target);

                    }

                    @Override
                    protected void onError(AjaxRequestTarget target) {
                        super.onError(target);
                    }
                });
            }
        };
    }

    private Subject save(Subject subject) {
        return this.authenticatedService.save(subject);
    }

    private IValidator getUserNameValidator() {
        return new IValidator<String>() {
            @Inject
            UserService service;

            @Override
            public void validate(IValidatable<String> iv) {
                boolean exists = service.checkUserExistxByUserName(iv.getValue());

            }

        };
    }

    @Override
    public void onEvent(IEvent<?> event) {
        super.onEvent(event);
        if (event.getPayload() instanceof HashMap) {
            Map<String, Object> payload = (Map) event.getPayload();
            if (payload.containsKey("Subject")) {
                this.selected = (IModel<Subject>) payload.get("Subject");
            }
            if (payload.containsKey("AjaxRequestTarget")) {
                AjaxRequestTarget target = (AjaxRequestTarget) payload.get("AjaxRequestTarget");
                target.add(this);
            }
        }

    }

}
