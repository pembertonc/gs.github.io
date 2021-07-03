/*
 * Copyright 2021 CodeCamp4.
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
package com.techmine.ui.pages;

import com.techmine.dto.CheckInCylinderDTO;
import java.util.Optional;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author CodeCamp4
 */
public class CylinderCheckInPage extends BasePage {

    private Form<?> form;
    private CheckInCylinderDTO checkInDTO;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        setDefaultModel(CompoundPropertyModel.of(new CheckInCylinderDTO()));

        form = initializeCylinderCheckInForm();
        add(form);
        form.add(initializeSerialNo());
        form.add(initializeBroughtInBy());
        form.add(initializeCheckedInBy());
        form.add(initializeCheckIn());
        form.add(initializeTurnedInDate());
        form.add(initializeCancel());
    }

    private Form<?> initializeCylinderCheckInForm() {
        return new Form<>("cylinderCheckInForm", getDefaultModel());
    }

    private TextField initializeSerialNo() {
        return (TextField) new TextField("serialNo", PropertyModel.of(getDefaultModel(), "serialNo")).add(new PropertyValidator());
    }

    private Button initializeCheckIn() {
        return new AjaxFallbackButton("checkIn", form) {
            @Override
            protected void onSubmit(Optional<AjaxRequestTarget> target) {
                super.onSubmit(target);
                processCheckIn();
                //  setResponsePage(CheckinSuccessPage.class);
            }

            @Override
            protected void onError(Optional<AjaxRequestTarget> target) {
                super.onError(target); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    private TextField initializeBroughtInBy() {
        return new TextField("broughtInBy", PropertyModel.of(getDefaultModel(), "customerRepName"));
    }

    private TextField initializeCheckedInBy() {
        CheckInCylinderDTO iSignInDTO = (CheckInCylinderDTO) getDefaultModelObject();
        return new TextField("checkedInBy", LambdaModel.of(iSignInDTO.getEmployee()::getFirstName, iSignInDTO.getEmployee()::setFirstName));
    }

    private DateTextField initializeTurnedInDate() {
        return new DateTextField("turnedInDate", PropertyModel.of(getDefaultModel(), "dateReturned"));
    }

    private Button initializeCancel() {
        return new AjaxFallbackButton("cancel", form) {
            @Override
            protected void onSubmit(Optional<AjaxRequestTarget> target) {
                super.onSubmit(target); //To change body of generated methods, choose Tools | Templates.
            }

        };
    }

    private void processCheckIn() {
        getDefaultModelObject();
    }
}
