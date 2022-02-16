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

 /*  NOTES For Clarification.
* Notes: The SignIn panel must be stateless,  and be Ajaxified at the same time.
* Wicket generates non-static ids for components we must set the id or self either in the markup or in the code.
* Ajax components force a component to be non-static.  to over come this we must overrid eht getStateLessHint() mentod
* So that it returns true.
 */
package com.techmine.gs.ui.panels.SignIn;

import java.util.Optional;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;

/**
 *
 * @author Cedric-Pemberton
 */
public class SignInPanel extends Panel {

    StatelessForm<Void> signInForm;
    private Subject user;

    public StatelessForm<Void> getSignInForm() {
        return signInForm;
    }

    public void setSignInForm(StatelessForm<Void> signInForm) {
        this.signInForm = signInForm;
    }

    public Subject getUser() {
        return user;
    }

    public void setUser(Subject user) {
        this.user = user;
    }

    public SignInPanel(String id) {
        super(id);
    }

    public SignInPanel(String id, IModel<?> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setMarkupId("signIn");
        user = new Subject();
        this.signInForm = (StatelessForm) new StatelessForm<>("signInForm") {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                setMarkupId("signInForm");
            }

            @Override
            protected void onSubmit() {
                super.onSubmit(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

            @Override
            protected void onError() {
                super.onError(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

            @Override
            public void onEvent(IEvent<?> event) {
                super.onEvent(event); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

        };
        add(signInForm);
        signInForm.add(new TextField<>("userName", LambdaModel.of(user::getUserName, user::setUserName)).setMarkupId("userName"));
        signInForm.add(new PasswordTextField("password", LambdaModel.of(user::getPassword, user::setPassword)).setMarkupId("password"));

        signInForm.add(new Label("messageLabel", "something"));

        Button submit;
        signInForm.add(submit = new AjaxFallbackButton("signIn", this.signInForm) {
            @Override
            protected void onInitialize() {
                super.onInitialize(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                setMarkupId("submit");
                setDefaultFormProcessing(false);
            }

            @Override
            protected void onSubmit(Optional<AjaxRequestTarget> target) {
                super.onSubmit(target); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

            @Override
            protected void onError(Optional<AjaxRequestTarget> target) {
                super.onError(target); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

        });

    }

    public class Subject {

        private String userName;
        private String Password;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

    }
}
