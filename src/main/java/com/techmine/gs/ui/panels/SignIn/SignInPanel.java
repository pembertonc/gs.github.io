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

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
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
        this.signInForm = (StatelessForm) new StatelessForm<>("signInForm")
                .setMarkupId("signInForm");  // manually set the markup id for the stateless form.
        add(signInForm);
        signInForm.add(new TextField<>("userName", LambdaModel.of(user::getUserName, user::setUserName)));
        signInForm.add(new PasswordTextField("password", LambdaModel.of(user::getPassword, user::setPassword)));

        signInForm.add(new Label("messageLabel", "something"));
        Button submit;
        signInForm.add(submit = new Button("signIn"));
        submit.add(new AjaxFormSubmitBehavior(this.signInForm, "submit") {

            //Override the getStalessHint mentod to return true so that it does not cause to the component to be statefull.
            @Override
            public boolean getStatelessHint(Component component) {
                return super.getStatelessHint(component); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                ((Label) signInForm.get("messageLabel")).setDefaultModelObject("walla walla dumplin");
                target.add(signInForm.get("messageLabel"));
            }

            @Override
            protected void onError(AjaxRequestTarget target) {
                super.onError(target);

                ((Label) signInForm.get("messageLabel")).setDefaultModelObject("walla walla dumplin");
                target.add(signInForm.get("messageLabel"));// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

        });

    }

    private class Subject {

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
