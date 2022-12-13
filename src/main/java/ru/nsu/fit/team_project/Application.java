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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.nsu.fit.team_project.model.CommandExecutor;
import ru.nsu.fit.team_project.model.MObject;
import ru.nsu.fit.team_project.model.Model;
import ru.nsu.fit.team_project.model.commands.AddFieldCommand;
import ru.nsu.fit.team_project.model.commands.Command;
import ru.nsu.fit.team_project.model.commands.CreateObjectCommand;
import ru.nsu.fit.team_project.model.commands.SetFieldValueCommand;
import ru.nsu.fit.team_project.model.fields.Field;
import ru.nsu.fit.team_project.serialization.Serializer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class Application extends javafx.application.Application {
    private List<MObject> objects;

    public void addObjectToModel(Model model, Task task) {
        UUID objectID = UUID.randomUUID();
        UUID taskNameFieldID = UUID.randomUUID();
        UUID taskDescFieldID = UUID.randomUUID();
        UUID statusDescFieldID = UUID.randomUUID();

        Command createTaskObjectCommand = new CreateObjectCommand(objectID, "task");
        Command addTaskNameFieldCommand = new AddFieldCommand(objectID, taskNameFieldID, "taskName", "String");
        Command setTaskNameFieldValue = new SetFieldValueCommand(taskNameFieldID, task.getName());
        Command addTaskDescFieldCommand = new AddFieldCommand(objectID, taskDescFieldID, "taskDesc", "String");
        Command setTaskDescFieldValue = new SetFieldValueCommand(taskDescFieldID, task.getDescription());
        Command addTaskStatusFieldCommand = new AddFieldCommand(objectID, statusDescFieldID, "taskStatus", "String");
        Command setTaskStatusFieldValue = new SetFieldValueCommand(statusDescFieldID, task.getStatus());

        model.addCommand(createTaskObjectCommand);
        model.addCommand(addTaskNameFieldCommand);
        model.addCommand(setTaskNameFieldValue);
        model.addCommand(addTaskDescFieldCommand);
        model.addCommand(setTaskDescFieldValue);
        model.addCommand(addTaskStatusFieldCommand);
        model.addCommand(setTaskStatusFieldValue);

        Serializer serializer = new Serializer();
        serializer.serialize(model);
        objects = model.getObjects();
    }

    public List<Task> createTasks(List<MObject> objects) {
        List<Task> tasks = new ArrayList<>();
        objects.forEach(obj -> {
            if (obj.getFieldByName("deleted") == null) {
                tasks.add(new Task(
                        obj.getFieldByName("taskName").getValue().toString(),
                        obj.getFieldByName("taskDesc").getValue().toString(),
                        TaskStatus.valueOf(obj.getFieldByName("taskStatus").getValue().toString())
                ));
            }
        });
        return tasks;
    }

    @Override
    public void start(Stage stage) {
        Model model = new Model();

        CommandExecutor ce = new CommandExecutor(model);
        ce.executeAll();

        objects = model.getObjects();
        List<Task> tasks = createTasks(objects);

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
                addObjectToModel(model, newTask);
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
        ToggleButton tbShowAll = new ToggleButton("Show All");
        tbShowAll.setSelected(true);
        tbShowAll.setToggleGroup(filterTG);
        tbShowAll.setOnAction(toggleHandler);
        tbShowAll.setUserData((Predicate<Task>) (Task t) -> true);

        ToggleButton tbShowActive = new ToggleButton("Active");
        tbShowActive.setSelected(false);
        tbShowActive.setToggleGroup(filterTG);
        tbShowActive.setOnAction(toggleHandler);
        tbShowActive.setUserData((Predicate<Task>) (Task t) -> t.getStatus().equals(TaskStatus.ACTIVE));

        ToggleButton tbShowCompleted = new ToggleButton("Completed");
        tbShowCompleted.setSelected(false);
        tbShowCompleted.setToggleGroup(filterTG);
        tbShowCompleted.setOnAction(toggleHandler);
        tbShowCompleted.setUserData((Predicate<Task>) (Task t) -> t.getStatus().equals(TaskStatus.COMPLETED));

        topControls.getChildren().addAll(tbShowAll, tbShowActive, tbShowCompleted);
        bottomControls.getChildren().add(addTaskButton);

        ContextMenu contextMenu = new ContextMenu();
        //MenuItem markAsActive = new MenuItem("Mark as Active");
        MenuItem markAsCompleted = new MenuItem("Mark as Completed");
        //contextMenu.getItems().add(markAsActive);
        contextMenu.getItems().add(markAsCompleted);


        ListView<Task> tasksListView = new ListView<>();
        tasksListView.setEditable(true);
        tasksListView.itemsProperty().bind(viewableTasksProperty);


        tasksListView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                contextMenu.show(tasksListView, event.getScreenX(), event.getScreenY());
            }
        });

        EventHandler<ActionEvent> createTaskButtonClick = (event1) -> {
            Task tsk = tasksListView.getSelectionModel().getSelectedItems().get(0);
            int idx = tasksListView.getSelectionModel().getSelectedIndex();

            Task newTask = new Task(tsk.getName(), tsk.getDescription(), TaskStatus.COMPLETED);
            tsk.setStatus(TaskStatus.COMPLETED);

            MObject object = objects.stream()
                                    .filter(obj -> obj.getFieldByName("taskName").getValue().equals(tsk.getName()))
                                    .findAny()
                                    .orElseThrow();

            UUID fieldID = UUID.randomUUID();
            model.addCommand(new AddFieldCommand(object.getId(), fieldID, "deleted", "String"));

            tasksProperty.get().remove(tsk);
            tasksProperty.get().add(newTask);
            tasks.add(newTask);
            tasks.remove(tsk);

            addObjectToModel(model, newTask);
        };
        markAsCompleted.setOnAction(createTaskButtonClick);

        vbox.getChildren().addAll(topControls, tasksListView, bottomControls);

        Scene scene = new Scene(vbox);

        stage.setScene(scene);
        stage.setOnShown(event -> tasksProperty.get().addAll(tasks));
        stage.setTitle("PlannerScape");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}