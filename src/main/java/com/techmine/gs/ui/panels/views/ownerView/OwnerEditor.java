/*
 * Copyright 2022 bethy.
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
package com.techmine.gs.ui.panels.views.ownerView;

import com.techmine.gs.domain.Address;
import com.techmine.gs.domain.Contact;
import com.techmine.gs.domain.Institution;
import com.techmine.gs.domain.Person;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author bethy
 */
public class OwnerEditor extends Panel {

    public OwnerEditor(String id) {
        this(id, Model.of(new Institution()));
    }

    public OwnerEditor(String id, IModel<?> model) {
        super(id, model);
    }

    private Institution owner;
    private TextField institutionName;
    private TextField street1;
    private TextField street2;
    private TextField country;
    private TextField city;
    private TextField email;
    private TextField telephone1;
    private TextField telephone2;
    private TextField firstName;
    private TextField familyName;
    private TextField otherName;

    private Form<Institution> form;

    private AjaxFallbackButton save;
    private AjaxFallbackButton cancel;

    /**
     *
     */
    @Override
    protected void onInitialize() {
        super.onInitialize();

        // owner = Objects.requireNonNullElseGet(owner, Institution::new);
        add(form = new Form<>("form", (IModel<Institution>) getDefaultModel()));
        
        //institutionName
        Institution institution = (Institution) getDefaultModelObject();
        form.add(initializeInstitutionName("institutionName", LambdaModel.of(institution::getName, institution::setName), true));

       //street1
        Address addr = institution.getAddress();
        form.add(initializeStreet1("street1", LambdaModel.of(addr::getStreet1, addr::setStreet1), true));

       //street2 
       form.add(initializeStreet2("street2", LambdaModel.of(addr::getStreet2, addr::setStreet2)));
       
       //city
       form.add(initializeCity("city", LambdaModel.of(addr::getCity, addr::setCity)));

        //country
        List<String> country = Arrays.asList("Antigua and Barbuda", "The Bahamas", "Barbados", "Belize", "Dominica", 
                            "Grenada","Guyana","Haiti","Jamaica","Monsterrat","Saint Kitts and Nevis","Saint Lucia",
                            "Saint Vincent and the Grenadines","Suriname", "Trinidad and Tobago","Other");
        form.add(initializeCountry("country", LambdaModel.of(addr::getCountry, addr::setCountry), true));
        
        //telephone1
        Contact contact = institution.getContact();
        form.add(initializeTelephone1("telephone1", LambdaModel.of(contact::getTelephone1, contact::setTelephone1), true));

        //telephone2
        form.add(initializeTelephone2("telephone2", LambdaModel.of(contact::getTelephone2, contact::setTelephone2)));

        //email
        form.add(initializeEmail("email", LambdaModel.of(contact::getEmail, contact::setEmail)));

        //firstName
        Person person = institution.getContact().getPerson();
        form.add(initializeFirstName("firstName", LambdaModel.of(person::getFirstName, person::setFirstName), true));

        //familyName
        form.add(initializeFamilyName("fammilyName", LambdaModel.of(person::getFamilyName, person::setFamilyName), true));
        
        //otherName
        form.add(initializeOtherName("otherName", LambdaModel.of(person::getOtherName, person::setOtherName)));

        initializeSave();
        form.add(save);

        initializeCancel();
        form.add(cancel);

    }

    private TextField initializeStreet1(String id, IModel<String> model, boolean isRequired) {
       return (TextField) new TextField(id, model).setRequired(true);
    }

    private TextField initializeStreet2(String id, IModel<String> model) {
        return (TextField) new TextField(id, model);
    }

    private TextField initializeCity(String id, IModel<String> model) {
        return (TextField) new TextField(id, model);
    }

    private TextField initializeTelephone1(String id, IModel<String> model, boolean isRequired) {
        return (TextField) new TextField(id, model).setRequired(true);
    }

    private TextField initializeTelephone2(String id, IModel<String> model) {
        return (TextField) new TextField(id, model);
    }

    private TextField initializeEmail(String id, IModel<String> model) {
        return (TextField) new TextField(id, model);
    }

    private TextField initializeFirstName(String id, IModel<String> model, boolean isRequired) {
        return (TextField) new TextField(id, model).setRequired(true);
    }

    private TextField initializeFamilyName(String id, IModel<String> model, boolean isRequired) {
        return (TextField) new TextField(id, model).setRequired(true);
    }

    private TextField initializeOtherName(String id, IModel<String> model) {
        return (TextField) new TextField(id, model);
    }

    private TextField initializeInstitutionName(String id, IModel<String> model, boolean isRequired) {
        return (TextField) new TextField(id, model).setRequired(true);

    }

    private DropDownChoice initializeCountry(String id, IModel<String> model, boolean isRequired) {
        return (DropDownChoice) new DropDownChoice(id, model).setRequired(true);
    }

    private Form<Institution> initializeForm(String id, IModel<Institution> model) {
        return new Form<Institution>(id, model);
    }

    private void initializeSave() {
        save = new AjaxFallbackButton("save", form) {
        };
    }

    private void initializeCancel() {
        cancel = new AjaxFallbackButton("cancel", form) {
        };
    }

}
