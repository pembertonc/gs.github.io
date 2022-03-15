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
package com.techmine.gs.ui.panels.views.user_view;

import com.techmine.gs.domain.Subject;
import com.techmine.gs.service.UserService;
import com.techmine.gs.ui.events.CRUDEventActions;
import com.techmine.gs.ui.events.NotificationEvent;
import com.techmine.gs.ui.events.SelectedEntity;
import com.techmine.gs.ui.panels.custom_input_components.TextFieldWithMessage.InputFieldWFeedbackAndCaption;
import com.techmine.gs.ui.panels.custom_input_components.TextFieldWithMessage.InputFieldWFeedbackAndCaption.FieldType;
import java.util.Optional;
import javax.inject.Inject;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;

/**
 *
 * @author Cedric-Pemberton
 */
public class UserEditor extends Panel {

    private UserService userService;

    private FeedbackPanel formErrors;
    private CRUDEventActions mode;


    @Inject
    void setUserService(UserService userService) {
        this.userService = userService;
    }

    private Form<Subject> editForm;

    public UserEditor(String id) {
        this(id, new Model<Subject>());

    }

    public UserEditor(String id, IModel<Subject> model) {
        super(id, model);
        mode = CRUDEventActions.NONE;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setOutputMarkupId(true); // needed for ajax support.


        add(editForm = initializeEditForm("editForm", (IModel<Subject>) getDefaultModel()));

        editForm.add(initializeSave("save", null));
        editForm.add(initializeCancel("cancel", null));
        editForm.add(initializeNew("new", null));

        editForm.add(new InputFieldWFeedbackAndCaption("userName", PropertyModel.of(getDefaultModel(), "userName"), "User Name", FieldType.TEXT).setRequired(true));
        editForm.add(new InputFieldWFeedbackAndCaption("password", PropertyModel.of(getDefaultModel(), "password"), "Password", FieldType.PASSWORD).setRequired(true));
        editForm.add(new InputFieldWFeedbackAndCaption("firstName", PropertyModel.of(getDefaultModel(), "person.firstName"), "First Name", FieldType.TEXT).setRequired(true));
        editForm.add(new InputFieldWFeedbackAndCaption("familyName", PropertyModel.of(getDefaultModel(), "person.familyName"), "Family Name", FieldType.TEXT).setRequired(true));
        editForm.add(new InputFieldWFeedbackAndCaption("otherName", PropertyModel.of(getDefaultModel(), "person.otherName"), "Other Name", FieldType.TEXT).setRequired(false));

        editForm.add(new InputFieldWFeedbackAndCaption("telephone1", PropertyModel.of(getDefaultModel(), "person.contact.telephone1"), "Telephone 1", FieldType.TEXT).setRequired(true));
        editForm.add(new InputFieldWFeedbackAndCaption("telephone2", PropertyModel.of(getDefaultModel(), "person.contact.telephone2"), "Telephone 2", FieldType.TEXT).setRequired(false));
        editForm.add(new InputFieldWFeedbackAndCaption("email", PropertyModel.of(getDefaultModel(), "person.contact.email"), "Email", FieldType.EMAIL).setRequired(true));

        formErrors = new FeedbackPanel("formErrors");
        add(formErrors);
        formErrors.setOutputMarkupId(true);

    }

    private Form<Subject> initializeEditForm(String id, IModel<Subject> model) {
        return new Form(id, model) {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                setOutputMarkupId(true);
            }

            @Override
            protected void onSubmit() {
                super.onSubmit();
            }

            @Override
            protected void onError() {
                super.onError();
            }
        };
    }

    /*
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
    }*/
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

                        persist((Subject) getParent().getDefaultModel().getObject());
                        target.add(UserEditor.this);
                        emitUpdateEvent(target);
                    }

                    @Override
                    protected void onError(AjaxRequestTarget target) {
                        super.onError(target);
                        target.add(formErrors);
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

    private void persist(Subject subject) {
        this.userService.persisteUser(subject);

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
        Object payload = event.getPayload();
        if (payload instanceof SelectedEntity) {
            SelectedEntity selectedPayload = ((SelectedEntity) payload);
            if (selectedPayload.getEntity() instanceof Subject) {
                this.setDefaultModelObject(selectedPayload.getEntity());
                this.mode = ((SelectedEntity) payload).getAction();
            }

            Optional<AjaxRequestTarget> target = selectedPayload.getTarget();
            target.ifPresent((t) -> t.add(editForm));
        }
    }

    private void emitUpdateEvent(AjaxRequestTarget target) {
        NotificationEvent<Subject> event = new NotificationEvent<>();
        event.setAction(CRUDEventActions.UPDATE);
        event.setEntityType(Subject.class);
        event.setTarget(target);
        send(this.getPage(), Broadcast.DEPTH, event);

    }

}
