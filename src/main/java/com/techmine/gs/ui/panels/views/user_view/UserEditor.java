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

import com.techmine.gs.domain.BaseEntity;
import com.techmine.gs.domain.Subject;
import com.techmine.gs.service.UserService;
import com.techmine.gs.ui.events.CRUDEventAction;
import com.techmine.gs.ui.events.NotificationEvent;
import com.techmine.gs.ui.events.SelectedEntity;
import com.techmine.gs.ui.panels.custom_input_components.InputFieldWFeedbackAndCaption;
import com.techmine.gs.ui.panels.custom_input_components.InputFieldWFeedbackAndCaption.FieldType;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;

/**
 *
 * @author Cedric-Pemberton
 */
public class UserEditor extends Panel {

    private UserService userService;

    private FeedbackPanel formErrors;
    private CRUDEventAction mode;

    private static final String BUTTON_CLICK = "click";
    private Button save;
    private Button cancel;
    private Button addNew;
    private Button delete;
    private WebMarkupContainer sectionContainer;
    private static final Logger LOG = Logger.getLogger(UserEditor.class.getName());

    @Inject
    void setUserService(UserService userService) {
        this.userService = userService;
    }

    private Form<Subject> editForm;

    public UserEditor(String id) {
        this(id, new Model<>());

    }

    public UserEditor(String id, IModel<Subject> model) {
        super(id, model);
        mode = CRUDEventAction.NONE;

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        setOutputMarkupId(true); // needed for ajax support.

        editForm = initializeEditForm("editForm", (IModel<Subject>) getDefaultModel());
        add(editForm);
        sectionContainer = initializeSectionContainer();
        editForm.add(sectionContainer);
        save = initializeSave("save", editForm);
        editForm.add(save);
        cancel = initializeCancel("cancel", editForm);
        editForm.add(cancel);
        addNew = initializeNew("new", editForm);
        editForm.add(addNew);
        delete = initializeDelete("delete", editForm);
        editForm.add(delete);
        sectionContainer.add(new InputFieldWFeedbackAndCaption("userName", PropertyModel.of(getDefaultModel(), "userName"), "User Name", FieldType.TEXT).setRequired(true));
        sectionContainer.add(new InputFieldWFeedbackAndCaption("password", PropertyModel.of(getDefaultModel(), "password"), "Password", FieldType.PASSWORD).setRequired(true));
        sectionContainer.add(new InputFieldWFeedbackAndCaption("firstName", PropertyModel.of(getDefaultModel(), "person.firstName"), "First Name", FieldType.TEXT).setRequired(true));
        sectionContainer.add(new InputFieldWFeedbackAndCaption("familyName", PropertyModel.of(getDefaultModel(), "person.familyName"), "Family Name", FieldType.TEXT).setRequired(true));
        sectionContainer.add(new InputFieldWFeedbackAndCaption("otherName", PropertyModel.of(getDefaultModel(), "person.otherName"), "Other Name", FieldType.TEXT).setRequired(false));

        sectionContainer.add(new InputFieldWFeedbackAndCaption("telephone1", PropertyModel.of(getDefaultModel(), "person.contact.telephone1"), "Telephone 1", FieldType.TEXT).setRequired(true));
        sectionContainer.add(new InputFieldWFeedbackAndCaption("telephone2", PropertyModel.of(getDefaultModel(), "person.contact.telephone2"), "Telephone 2", FieldType.TEXT).setRequired(false));
        sectionContainer.add(new InputFieldWFeedbackAndCaption("email", PropertyModel.of(getDefaultModel(), "person.contact.email"), "Email", FieldType.EMAIL).setRequired(true));

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

    private Button initializeSave(String id, Form<Subject> form) {
        return new AjaxButton(id, form) {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                setDefaultFormProcessing(true);
                add(new AjaxFormSubmitBehavior(BUTTON_CLICK) {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target) {
                        super.onSubmit(target);

                        persist((Subject) getParent().getDefaultModel().getObject());

                        target.add(UserEditor.this);
                        emitUpdateEvent(target);
                        nullDefaultModelObject();
                    }

                    @Override
                    protected void onError(AjaxRequestTarget target) {
                        super.onError(target);
                        target.add(formErrors);
                    }
                });
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setEnabled(!Objects.isNull(UserEditor.this.getDefaultModelObject()));
                LOG.log(Level.ALL, "on Config Save");
            }

            @Override
            protected void onBeforeRender() {
                super.onBeforeRender();
                LOG.log(Level.ALL, "on Before Render Save");
            }

        };
    }

    @Override
    protected void onConfigure() {
        super.onConfigure(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        LOG.log(Level.ALL, "onConfig on UserEditor");

    }

    private void nullDefaultModelObject() {
        setDefaultModelObject(null);
    }

    private Button initializeCancel(String id, Form<Subject> form) {
        return new AjaxButton(id, form) {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                add(new AjaxFormSubmitBehavior(BUTTON_CLICK) {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target) {
                        super.onSubmit(target);
                        nullDefaultModelObject();
                        target.add(editForm);
                    }

                    @Override
                    protected void onError(AjaxRequestTarget target) {
                        super.onError(target);
                    }
                });
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setEnabled(!Objects.isNull(UserEditor.this.getDefaultModelObject()));
            }

        };
    }

    private Button initializeNew(String id, Form<Subject> form) {
        return new AjaxButton(id, form) {
            @Override
            protected void onInitialize() {
                super.onInitialize();

            }

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                UserEditor.this.setModelObject(new Subject());

                target.add(editForm);

            }

            @Override
            protected void onError(AjaxRequestTarget target) {
                super.onError(target);
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setEnabled(Objects.isNull(UserEditor.this.getDefaultModelObject()));
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

                setModelObject(selectedPayload.getEntity());
                this.mode = ((SelectedEntity) payload).getAction();
            }

            Optional<AjaxRequestTarget> target = selectedPayload.getTarget();
            //target.ifPresent(t -> t.add(editForm));
            target.ifPresent(t -> t.add(editForm));
        }
    }

    private void setModelObject(BaseEntity entity) {
        this.setDefaultModelObject(entity);
    }

    // Replace call to method emitUpdateEvent with call to emmitEvent
    private void emitUpdateEvent(AjaxRequestTarget target) {
        NotificationEvent<Subject> event = new NotificationEvent<>();
        event.setAction(CRUDEventAction.UPDATE);
        event.setEntityType(Subject.class);
        event.setTarget(target);
        send(this.getPage(), Broadcast.DEPTH, event);

    }

    private Button initializeDelete(String delete, Form<Subject> form) {
        return new AjaxButton(delete, form) {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                target.add(editForm);
                userService.deleteUser((Subject) UserEditor.this.getDefaultModel().getObject());
                emmitEvent(target, CRUDEventAction.DELETE);
                nullDefaultModelObject();
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setEnabled(!Objects.isNull(UserEditor.this.getDefaultModelObject()));

            }

        };
    }

    private void emmitEvent(AjaxRequestTarget target, CRUDEventAction action) {
        NotificationEvent<Subject> event = new NotificationEvent<>();
        event.setAction(action);
        event.setEntityType(Subject.class);
        event.setTarget(target);
        send(this.getPage(), Broadcast.DEPTH, event);
    }

    private WebMarkupContainer initializeSectionContainer() {
        return new WebMarkupContainer("sectionContainer") {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                setOutputMarkupId(true);
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();
                LOG.log(Level.ALL, "SectionContainer#OnConfig");
                this.setEnabled(!Objects.isNull(UserEditor.this.getDefaultModelObject()));

            }

            @Override
            protected void onBeforeRender() {
                super.onBeforeRender();
                LOG.log(Level.ALL, "SectionContainer#onBefore Render");
                visitChildren(InputFieldWFeedbackAndCaption.class, new IVisitor<Component, String>() {
                    @Override
                    public void component(Component t, IVisit<String> ivisit) {
                        if (isEnabled()) {
                            t.add(AttributeModifier.remove("class"));
                        } else {
                            t.add(AttributeModifier.append("class", "w3-disabled"));
                        }
                    }

                });
            }

        };

    }

}
