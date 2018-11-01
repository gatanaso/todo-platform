package org.vaadin.gatanaso;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

@SuppressWarnings("serial")
@Route("")
@Tag("todo-app")
@HtmlImport("src/todo-app.html")
public class TodoApp extends PolymerTemplate<TemplateModel> {
	
	private List<Todo> tasks = new ArrayList<>();

	@Id("taskContainer")
	private Div taskContainer;
	
	@Id("taskInput")
	private TextField todoTxtField;
	
	@EventHandler
	private void addTaskClicked() {
		String taskName = todoTxtField.getValue();
		this.addTask(taskName);
	}
	
	@EventHandler
	private void keyUpListener(@EventData("event.key") String key) {
		if ("Enter".equals(key)) {
			addTaskClicked();
		}
	}

	private void addTask(String taskName) {
		if (taskName != null && taskName.trim().length() > 0) {
			Todo task = new Todo(taskName);
			tasks.add(task);
			
			TodoComponent taskViewComponent = new TodoComponent(task);
			taskViewComponent.setDeleteHandler(() -> {
				tasks.remove(task);
				taskContainer.remove(taskViewComponent);
			});
			taskContainer.add(taskViewComponent);

			todoTxtField.clear();
		}
	}	
}
