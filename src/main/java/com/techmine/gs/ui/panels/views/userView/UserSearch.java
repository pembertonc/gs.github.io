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

import com.techmine.gs.domain.Subject;
import com.techmine.gs.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.LambdaColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;

/**
 *
 * @author Cedric-Pemberton
 *
 * Displays a search results grid based on Wicket-extensions "Datatable" and a
 * test field.
 */
public class UserSearch extends Panel {

    @Inject
    private UserService userService;

    private IModel<List<Subject>> results;
    private String criteria = "";

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    private DataTable<Subject, String> resultTable;
    private Form<String> searchForm;

    public UserSearch(String id) {
        super(id);
        // need to initilize the model and model object at startup.
        results = Model.ofList(userService.findLikeUserName(criteria));
        setDefaultModel(results);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        processSearchCriteria(criteria);
        add(searchForm = new Form<String>("searchForm", LambdaModel.of(this::getCriteria, this::setCriteria)) {
            @Override
            protected void onSubmit() {
                super.onSubmit();
            }
        });
        searchForm.add(new TextField("criteria", LambdaModel.of(this::getCriteria, this::setCriteria)));
        searchForm.add(new AjaxFallbackButton("search", searchForm) {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                add(new AjaxFormSubmitBehavior(searchForm, "click") {
                });
            }

            @Override
            protected void onSubmit(Optional<AjaxRequestTarget> target) {
                super.onSubmit(target);
                processSearchCriteria(criteria);
                target.ifPresent((t) -> t.add(resultTable));
            }
        });
        resultTable = initializeDataTable("resultTable");
        add(resultTable);
    }

    private List<IColumn<Subject, String>> getColumns() {
        List<IColumn<Subject, String>> columns = new ArrayList<>();
        columns.add(new LambdaColumn<>(Model.of("User Name"), Subject::getUserName));
        columns.add(new PropertyColumn<>(Model.of("First Name"), "person.firstName"));
        return columns;
    }

    private SortableSubjectProvider getSubjectDataProvider(IModel<List<Subject>> resultDataModel) {

        return new SortableSubjectProvider(resultDataModel);

    }

    private DataTable<Subject, String> initializeDataTable(String id) {
        DataTable dataTable = (DataTable) new DefaultDataTable<>(id, getColumns(), getSubjectDataProvider(results), 8)
                .setOutputMarkupId(true);
        return dataTable;

    }

    private void processSearchCriteria(String criteria) {
        this.results.setObject(this.userService.findLikeUserName(criteria));
    }
}
