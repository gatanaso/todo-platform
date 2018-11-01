package org.vaadin.gatanaso;

import java.util.Optional;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@SuppressWarnings("serial")
@Tag("todo-component")
@HtmlImport("src/todo-component.html")
public class TodoComponent extends PolymerTemplate<TemplateModel>  {
	
	private Optional<Runnable> deleteHandler;

	public TodoComponent(Todo task) {
		deleteHandler = Optional.empty();
		getElement().setProperty("task", task.getTask());
		getElement().setProperty("done", task.getDone());
	}
	
	@EventHandler
	public void doneTaskClicked() {
		getElement().setProperty("done", true);
	}
	
	@EventHandler
	public void deleteTaskClicked() {
		this.deleteHandler.ifPresent(Runnable::run);
	}

	public void setDeleteHandler(Runnable deleteHandler) {
		this.deleteHandler = Optional.ofNullable(deleteHandler);
	}

	
}
