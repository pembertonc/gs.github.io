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

import com.techmine.gs.Route;
import com.techmine.gs.Route.Actions;
import com.techmine.gs.domain.Subject;
import java.io.Serializable;
import java.util.Optional;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.event.Broadcast;
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
public class SignInPanel extends Panel implements Serializable {

    StatelessForm<Subject> signInForm;
    private Subject user;
    private Button submit;

    public StatelessForm<Subject> getSignInForm() {
        return signInForm;

    }

    public void setSignInForm(StatelessForm<Subject> signInForm) {
        this.signInForm = signInForm;

    }

    public Subject getUser() {
        return user;
    }

    public void setUser(Subject user) {
        this.user = user;
    }

    /**
     *
     * @param service
     */
    public SignInPanel(String id) {
        super(id);
    }

    public SignInPanel(String id, IModel<?> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setMarkupId("signInPanel");
        user = new Subject();
        this.signInForm = (StatelessForm) new StatelessForm("signInForm") {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                setMarkupId("signInForm");

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
        add(signInForm);
        signInForm.add(new TextField<>("userName", LambdaModel.of(user::getUserName, user::setUserName)).setMarkupId("userName"));
        signInForm.add(new PasswordTextField("password", LambdaModel.of(user::getPassword, user::setPassword)).setMarkupId("password"));

        signInForm.add(submit = new Button("signIn") {

            @Override
            protected void onInitialize() {
                super.onInitialize();
                setMarkupId("submit");
                setDefaultFormProcessing(false);

                this.add(new AjaxFormSubmitBehavior("click") {

                    @Override
                    protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                        super.updateAjaxAttributes(attributes);
                        attributes.setPreventDefault(true);
                    }

                    @Override
                    protected void onSubmit(AjaxRequestTarget target) {
                        super.onSubmit(target);

                        if (signIn(user.getUserName(), user.getPassword())) {
                            Route r = new Route(Actions.LOGIN, Optional.of(target));
                            send(this.getComponent().getPage(), Broadcast.EXACT, r);

                        }
                    }

                    @Override
                    public boolean getStatelessHint(Component component) {
                        return true;
                    }

                });
            }
        });

    }

    private boolean signIn(String userName, String password) {
        return AuthenticatedWebSession.get().signIn(userName, password);

    }

}
