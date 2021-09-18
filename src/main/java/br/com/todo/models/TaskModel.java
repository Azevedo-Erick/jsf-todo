package br.com.todo.models;

public class TaskModel {
	private int id;
	private String name;
	private boolean completed;
	
	public TaskModel() {
		
	}
	public TaskModel(int id, String name, boolean completed) {
		this.setId(id);
		this.setName(name);
		this.setCompleted(completed);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public String controlStyle() {
		if(this.isCompleted()) {
			return "completa ";
			
		}else {
			return "incompleta";
		}
	}
	
	@Override
	public String toString() {
		return this.getId() + " - " + this.getName() +" - " + this.isCompleted();
	}
	
	
}
