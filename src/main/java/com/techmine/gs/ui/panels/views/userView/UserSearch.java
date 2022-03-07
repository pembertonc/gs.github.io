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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.LambdaColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

/**
 *
 * @author Cedric-Pemberton
 *
 * Displays a search results grid based on Wicket-extensions "Datatable" and a
 * test field.
 */
public class UserSearch extends Panel {

    private DataTable<Subject, String> resultTable;

    public UserSearch(String id) {
        super(id);
    }

    public UserSearch(String id, IModel<?> model) {
        super(id, model);

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        resultTable = initializeDataTable("resultTable");
        add(resultTable);
    }

    private List<IColumn<Subject, String>> getColumns() {
        List<IColumn<Subject, String>> columns = new ArrayList<>();
        columns.add(new LambdaColumn<>(Model.of("User Name"), Subject::getUserName));
        return columns;
    }

    private SortableSubjectProvider getSubjectDataProvider() {
        return new SortableSubjectProvider();
    }

    private DataTable<Subject, String> initializeDataTable(String id) {
        return new DefaultDataTable<>(id, getColumns(), getSubjectDataProvider(), 8);

    }

}

class SubjectDataProvider implements IDataProvider<Subject> {

    IModel<List<Subject>> listModel;

    public SubjectDataProvider(IModel<List<Subject>> listModel) {
        this.listModel = listModel;
    }

    public IModel<List<Subject>> getListModel() {
        return listModel;
    }

    public void setListModel(IModel<List<Subject>> listModel) {
        this.listModel = listModel;
    }

    public void invalidate() {
        this.listModel = null;
    }

    @Override
    public Iterator<? extends Subject> iterator(long l, long l1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public long size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public IModel<Subject> model(Subject t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
