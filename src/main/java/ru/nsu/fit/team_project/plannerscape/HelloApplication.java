package ru.nsu.fit.team_project.plannerscape;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //
        // Test data
        //
        Task[] tasks = {new Task("Task1", "Task1 description"),
                        new Task("Task2", "Task2 description"),
                        new Task("Task3", "Task3 description")};

        tasks[2].setStatus(TaskStatus.COMPLETED);

        //
        // Set up the model which is two lists of Players and a filter criteria
        //
        ReadOnlyObjectProperty<ObservableList<Task>> tasksProperty =
                new SimpleObjectProperty<>(FXCollections.observableArrayList());

        ReadOnlyObjectProperty<FilteredList<Task>> viewableTasksProperty =
                new SimpleObjectProperty<>(new FilteredList<>(tasksProperty.get()));

        ObjectProperty<Predicate<? super Task>> filterProperty =
                viewableTasksProperty.get().predicateProperty();

        //
        // Build the UI
        //
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(4);

        HBox hbox = new HBox();
        hbox.setSpacing(2);

        ToggleGroup filterTG = new ToggleGroup();

        //
        // The toggleHandler action wills set the filter based on the TB selected
        //
        @SuppressWarnings("unchecked")
        EventHandler<ActionEvent> toggleHandler = (event) -> {
            ToggleButton tb = (ToggleButton) event.getSource();
            Predicate<Task> filter = (Predicate<Task>) tb.getUserData();
            filterProperty.set(filter);
        };

        ToggleButton tbShowAll = new ToggleButton("Show All");
        tbShowAll.setSelected(true);
        tbShowAll.setToggleGroup(filterTG);
        tbShowAll.setOnAction(toggleHandler);
        tbShowAll.setUserData((Predicate<Task>) (Task t) -> true);

        //
        // Create a distinct list of statuses from the Task objects, then create
        // ToggleButtons
        //
        List<ToggleButton> tbs = Arrays.stream(tasks)
                .map(Task::getStatus)
                .distinct()
                .map(status -> {
                    ToggleButton tb = new ToggleButton(status.name());
                    tb.setToggleGroup(filterTG);
                    tb.setOnAction(toggleHandler);
                    tb.setUserData((Predicate<Task>) (Task t) -> status.equals(t.getStatus()));
                    return tb;
                })
                .toList();

        hbox.getChildren().add(tbShowAll);
        hbox.getChildren().addAll(tbs);

        ListView<Task> lv = new ListView<>();
        lv.itemsProperty().bind(viewableTasksProperty);

        vbox.getChildren().addAll(hbox, lv);

        Scene scene = new Scene(vbox);

        stage.setScene(scene);
        stage.setOnShown(event -> {
            tasksProperty.get().addAll(tasks);
        });

        stage.show();

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(),
        //        320,
        //        240);


        /*stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/
    }

    public static void main(String[] args) {
        launch();
    }
}