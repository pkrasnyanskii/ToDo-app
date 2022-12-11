package ru.nsu.fit.team_project;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.nsu.fit.team_project.model.CommandExecutor;
import ru.nsu.fit.team_project.model.MObject;
import ru.nsu.fit.team_project.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Application extends javafx.application.Application {
    public List<Task> createTasks(List<MObject> objects) {
        List<Task> tasks = new ArrayList<>();
        objects.forEach(obj -> tasks.add(new Task(
                obj.getFieldByName("taskName").getValue().toString(),
                ""
        )));
        return tasks;
    }

    @Override
    public void start(Stage stage) {
        Model model = new Model();

        CommandExecutor ce = new CommandExecutor(model);
        ce.executeAll();

        List<MObject> objects = model.getObjects();

        // Test data
        List<Task> tasks = createTasks(objects);
        /*tasks.add(new Task("Task1", "Task1 description"));
        tasks.add(new Task("Task2", "Task2 description"));
        tasks.add(new Task("Task3", "Task3 description"));*/

        //tasks.get(2).setStatus(TaskStatus.COMPLETED);

        // Set up the model which is two lists of Tasks and a filter criteria
        ReadOnlyObjectProperty<ObservableList<Task>> tasksProperty =
                new SimpleObjectProperty<>(FXCollections.observableArrayList());

        ReadOnlyObjectProperty<FilteredList<Task>> viewableTasksProperty =
                new SimpleObjectProperty<>(new FilteredList<>(tasksProperty.get()));

        ObjectProperty<Predicate<? super Task>> filterProperty =
                viewableTasksProperty.get().predicateProperty();

        // Build the UI
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(4);

        HBox topControls = new HBox();
        topControls.setSpacing(2);

        HBox bottomControls = new HBox();
        Button addTaskButton = new Button("Add");

        ToggleGroup filterTG = new ToggleGroup();

        // The toggleHandler action wills set the filter based on the TB selected
        EventHandler<ActionEvent> toggleHandler = (event) -> {
            ToggleButton tb = (ToggleButton) event.getSource();
            @SuppressWarnings("unchecked")
            Predicate<Task> filter = (Predicate<Task>) tb.getUserData();
            filterProperty.set(filter);
        };

        ToggleButton tbShowAll = new ToggleButton("Show All");
        tbShowAll.setSelected(true);
        tbShowAll.setToggleGroup(filterTG);
        tbShowAll.setOnAction(toggleHandler);
        tbShowAll.setUserData((Predicate<Task>) (Task t) -> true);

        // AddButton onClick action
        EventHandler<ActionEvent> addTaskButtonOnClick = (event) -> {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);

            VBox dialogVbox = new VBox();
            dialogVbox.setPadding(new Insets(10));
            dialogVbox.setSpacing(4);

            Text taskNameText = new Text("Task Name");
            TextField taskName = new TextField();
            Text taskDescText = new Text("Task Description");
            TextField taskDesc = new TextField();
            Button createTaskButton = new Button("Create Task");

            EventHandler<ActionEvent> createTaskButtonClick = (eventInner) -> {
                Task newTask = new Task(taskName.getText(), taskDesc.getText());
                tasksProperty.get().add(newTask);
                tasks.add(newTask);
                dialog.close();
            };
            createTaskButton.setOnAction(createTaskButtonClick);

            dialogVbox.getChildren().addAll(taskNameText, taskName, taskDescText, taskDesc, createTaskButton);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        };
        addTaskButton.setOnAction(addTaskButtonOnClick);

        // Create a distinct list of statuses from the Task objects, then create ToggleButtons
        List<ToggleButton> tbs = tasks.stream()
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

        topControls.getChildren().add(tbShowAll);
        topControls.getChildren().addAll(tbs);
        bottomControls.getChildren().add(addTaskButton);

        ListView<Task> tasksListView = new ListView<>();
        tasksListView.itemsProperty().bind(viewableTasksProperty);

        vbox.getChildren().addAll(topControls, tasksListView, bottomControls);

        Scene scene = new Scene(vbox);

        stage.setScene(scene);
        stage.setOnShown(event -> tasksProperty.get().addAll(tasks));

        stage.show();

    }

    public static void main1(String[] args) {
        launch();
    }
}