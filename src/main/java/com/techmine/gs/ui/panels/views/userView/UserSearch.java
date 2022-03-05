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

import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.kendo.ui.datatable.DataTable;
import com.googlecode.wicket.kendo.ui.datatable.column.IColumn;
import com.googlecode.wicket.kendo.ui.datatable.column.IdPropertyColumn;
import com.googlecode.wicket.kendo.ui.datatable.column.PropertyColumn;
import com.techmine.gs.domain.Subject;
import com.techmine.gs.repository.SubjectRepository;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Generics;

/**
 *
 * @author Cedric-Pemberton
 *
 * Displays a search results grid based on Wicket-extensions "Datatable" and a
 * test field.
 */
public class UserSearch extends Panel {

    @Inject
    private SubjectRepository subjectRepository;

    private DataTable datatable;

    public UserSearch(String id) {
        super(id);
    }

    public UserSearch(String id, IModel<?> model) {
        super(id, model);

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(datatable = getSearchResultsDisplay("dataTable"));

    }

    private IDataProvider getSubjectDataProvider() {
        return new ListDataProvider(this.subjectRepository.findAll());
    }

    private List<IColumn> getColumns() {
        List<IColumn> columns = Generics.newArrayList();
        columns.add(new IdPropertyColumn("ID", "id", 36));
        columns.add(new PropertyColumn("User Name", "userName"));
        //columns.add(new PropertyColumn("First Name", "f"));
        return columns;
    }

    private Options getOptions() {
        return new Options()
                .set("height", 430)
                .set("pageable", "{ pageSizes: [25, 50, 100]}")
                //.set("sortable") // already if providable is  a ISortStateLocator
                .set("groupable", true)
                .set("columnNemu", true)
                .set("selectable", false);

    }

    private DataTable getSearchResultsDisplay(String id) {
        return new DataTable<Subject>(id, getColumns(), getSubjectDataProvider(), 25, getOptions()) {
            private static final long serialVersionUID = 1L;

        };
    }

    private DataTable initializeResultsDisplay() {
        throw new UnsupportedOperationException("initializeResultsDesplay not yet implmented");
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
