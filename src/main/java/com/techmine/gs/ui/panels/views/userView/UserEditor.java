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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 *
 * @author Cedric-Pemberton
 */
public class UserEditor extends Panel {

    private Form<Void> editForm;

    public UserEditor(String id) {
        super(id);
    }

    public UserEditor(String id, IModel<?> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        add(editForm = initializeEditForm());
        editForm.add(initializeTextField("userName", null, true));
        editForm.add(initializePassword("password", null, true));
        editForm.add(initializeTextField("firstName", null, true));
        editForm.add(initializeTextField("familyName", null, true));
        editForm.add(initializeTextField("otherName", null, true));
        editForm.add(initializeEmailField("email", null, true));
        editForm.add(initializeTextField("telephone1", null, true));
        editForm.add(initializeTextField("telephone2", null, true));
        editForm.add(initializeSave("save", null));
        editForm.add(initializeCancel("cancel", null));
        editForm.add(initializeNew("new", null));

    }

    private Form<Void> initializeEditForm() {
        return new Form("editForm");
    }

    private TextField initializeTextField(String id, IModel<String> model, boolean required) {
        return (TextField) new TextField(id, model).setRequired(required);
    }

    private FormComponent initializePassword(String id, IModel<String> model, boolean required) {
        return new PasswordTextField(id, model).setRequired(required);

    }

    private FormComponent initializeEmailField(String email, IModel<String> model, boolean required) {
        return new EmailTextField("email", model).setRequired(required);
    }

    private Button initializeSave(String id, Form<Void> form) {
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

    private Button initializeCancel(String id, Form<Void> form) {
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

}
