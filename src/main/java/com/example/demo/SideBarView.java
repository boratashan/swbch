package com.example.demo;

import com.example.demo.model.NavItem;
import com.example.demo.model.NavItemType;
import com.example.demo.utils.HierarchicalList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.DataProviderListener;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.hierarchy.*;
import com.vaadin.flow.shared.Registration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SideBarView extends VerticalLayout {


    private HierarchicalList<String> hierarchicalList;

    public SideBarView() {
        hierarchicalList = new HierarchicalList<>();
        buildUI();
    }


    private void populateHList() {
        hierarchicalList.setRootValue("Root")
                .addChildren("Child 01")
                .addChildren("Child 02")
                .addChildren("Child 03");
    }

    private void buildUI() {
        add(buildToolBar());
        add(buildNavigator());
    }

    private HorizontalLayout buildToolBar() {
        HorizontalLayout result = new HorizontalLayout();
        Button leftButton = new Button(new Icon(VaadinIcon.ARROW_LEFT));
        Button rightButton = new Button(new Icon(VaadinIcon.ARROW_RIGHT));
        Button thumbsUpButton = new Button(new Icon(VaadinIcon.THUMBS_UP));
        result.add(leftButton, rightButton, thumbsUpButton);
        result.add("This is a toolbar");
        return  result;
    }

    private VerticalLayout buildNavigator() {
        VerticalLayout result = new VerticalLayout();

        List<NavItem> list = new ArrayList<>();
        list.add(new NavItem("Bora").setItemType(NavItemType.ARRAY));
        list.add(new NavItem("Mehmet").setItemType(NavItemType.ARRAY));
        list.add(new NavItem("Nuriye").setItemType(NavItemType.STRUCT));
        list.add(new NavItem("Anil").setItemType(NavItemType.ENUM));





        ListBox<String> listBox = new ListBox<>();

        ListDataProvider<NavItem> provider = DataProvider.ofCollection(list);


//        listBox.setItems("Bora", "Anıl", "Mehmet", "Nuray");

        List<String> stringList = List.of("Bora", "Anıl", "Mehmet", "Nuray");
        DataProvider<String, Void> dataProvider = DataProvider.fromCallbacks(query -> {
            return stringList.stream();
        }, query -> stringList.size());
        listBox.setDataProvider(dataProvider);

        //result.add(listBox);


        TreeData<String> treeData = new TreeData<>();
        TreeDataProvider<String> treeDataProvider = new TreeDataProvider<>(treeData);
        treeData.addRootItems("Hello", "Mello");
        treeData.addItem("Hello", "child 01");
        treeData.addItem("Hello", "child 02");
        treeData.addItem("child 01", "child 01 01");
        HierarchicalDataProvider<String, Void> hierarchicalDataProvider = new AbstractBackEndHierarchicalDataProvider<String, Void>() {
            @Override
            public int getChildCount(HierarchicalQuery<String, Void> query) {
                return stringList.size();
            }

            @Override
            public boolean hasChildren(String item) {
                return true;
            }

            @Override
            protected Stream<String> fetchChildrenFromBackEnd(HierarchicalQuery<String, Void> query) {
                return stringList.stream();
            }
        };

        TreeGrid<String> grid = new TreeGrid();
        grid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS);
        grid.setDataProvider(treeDataProvider);

        grid.addHierarchyColumn(s -> s.toString());
        add(grid);

        Button btnChangeTree = new Button("Change tree");
        btnChangeTree.addClickListener(event -> {
            String item = LocalTime.now().toString();
           treeData.addItem(null, item);
           treeDataProvider.refreshAll();
        });
        add(btnChangeTree);

        /*
        TreeGrid<NavItem> grid = new TreeGrid();


        grid.setDataProvider(dataProvider);
        grid.setHierarchyColumn("itemType", NavItem::getItemType);
        grid.addColumn(NavItem::getItemType);
        grid.addColumn(NavItem::getCaption);
        result.add(grid);

         */
        return  result;
    }
}
