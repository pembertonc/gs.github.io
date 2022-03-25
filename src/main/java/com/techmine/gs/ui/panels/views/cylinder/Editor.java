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
package com.techmine.gs.ui.panels.views.cylinder;

import static com.techmine.gs.Constants.BUTTON_CLICK;
import com.techmine.gs.domain.BaseEntity;
import com.techmine.gs.domain.Cylinder;
import com.techmine.gs.service.CylinderService;
import com.techmine.gs.service.UserService;
import com.techmine.gs.ui.events.CRUDEventAction;
import com.techmine.gs.ui.events.NotificationEvent;
import com.techmine.gs.ui.panels.custom_input_components.InputFieldWFeedbackAndCaption;
import com.techmine.gs.ui.panels.views.user_view.UserEditor;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
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
public class Editor extends Panel {

    @Inject
    private CylinderService cylinderService;
    private static final Logger LOG = Logger.getLogger(Editor.class.getName());

    private CRUDEventAction mode;
    private Form<Cylinder> editForm;
    private WebMarkupContainer sectionContainer;
    private Button save;
    private Button cancel;
    private Button addNew;
    private FeedbackPanel formErrors;
    private Button delete;

    public Editor(String id) {
        this(id, new Model<Cylinder>());
    }

    public Editor(String id, IModel<Cylinder> model) {
        super(id, model);
        mode = CRUDEventAction.NONE;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        setOutputMarkupId(true);
        editForm = initializeEditForm("editForm", (IModel<Cylinder>) getDefaultModel());

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

        sectionContainer.add(new InputFieldWFeedbackAndCaption("serialNumber", PropertyModel.of(getDefaultModel(), "serialNumber"), "Serial Number", InputFieldWFeedbackAndCaption.FieldType.TEXT).setRequired(true));
        sectionContainer.add(new InputFieldWFeedbackAndCaption("size", PropertyModel.of(getDefaultModel(), "cylinderSize.value"), "Cylinder Size", InputFieldWFeedbackAndCaption.FieldType.TEXT).setRequired(true));

        formErrors = new FeedbackPanel("formErrors");
        add(formErrors);

    }

    private Form<Cylinder> initializeEditForm(String id, IModel<Cylinder> model) {
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
                this.setEnabled(!Objects.isNull(Editor.this.getDefaultModelObject()));

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

    private void setModelObject(BaseEntity entity) {
        this.setDefaultModelObject(entity);
    }

    private Button initializeNew(String id, Form<Cylinder> form) {
        return new AjaxButton(id, form) {
            @Override
            protected void onInitialize() {
                super.onInitialize();

            }

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                Editor.this.setModelObject(new Cylinder());

                target.add(editForm);

            }

            @Override
            protected void onError(AjaxRequestTarget target) {
                super.onError(target);
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setEnabled(Objects.isNull(Editor.this.getDefaultModelObject()));
            }
        };
    }

    private void persist(Cylinder cylinder) {
        this.cylinderService.persisteCylinder(cylinder);

    }

    private Button initializeCancel(String id, Form<Cylinder> form) {
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
                setEnabled(!Objects.isNull(Editor.this.getDefaultModelObject()));
            }

        };
    }

    private Button initializeSave(String id, Form<Cylinder> form) {
        return new AjaxButton(id, form) {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                setDefaultFormProcessing(true);
                add(new AjaxFormSubmitBehavior(BUTTON_CLICK) {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target) {
                        super.onSubmit(target);

                        persist((Cylinder) getParent().getDefaultModel().getObject());

                        target.add(Editor.this);
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
                setEnabled(!Objects.isNull(Editor.this.getDefaultModelObject()));
                LOG.log(Level.ALL, "on Config Save");
            }

            @Override
            protected void onBeforeRender() {
                super.onBeforeRender();
                LOG.log(Level.ALL, "on Before Render Save");
            }

        };
    }

    private void emitUpdateEvent(AjaxRequestTarget target) {
        NotificationEvent<Cylinder> event = new NotificationEvent<>();
        event.setAction(CRUDEventAction.UPDATE);
        event.setEntityType(Cylinder.class);
        event.setTarget(target);
        send(this.getPage(), Broadcast.DEPTH, event);

    }

    private Button initializeDelete(String delete, Form<Cylinder> form) {
        return new AjaxButton(delete, form) {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                target.add(editForm);
                CylinderService.deleteCylinder((Cylinder) Editor.this.getDefaultModel().getObject());
                emmitEvent(target, CRUDEventAction.DELETE);
                nullDefaultModelObject();
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setEnabled(!Objects.isNull(Editor.this.getDefaultModelObject()));

            }

        };
    }

    private void emmitEvent(AjaxRequestTarget target, CRUDEventAction action) {
        NotificationEvent<Cylinder> event = new NotificationEvent<>();
        event.setAction(action);
        event.setEntityType(Cylinder.class);
        event.setTarget(target);
        send(this.getPage(), Broadcast.DEPTH, event);
    }

    private void nullDefaultModelObject() {
        setDefaultModelObject(null);
    }

}
