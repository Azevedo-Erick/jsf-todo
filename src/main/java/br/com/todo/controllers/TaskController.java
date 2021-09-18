package br.com.todo.controllers;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.todo.models.TaskModel;
import br.com.todo.utils.ConnectionManager;
import br.com.todo.utils.LogMessages;

@Named
@ViewScoped
public class TaskController implements Serializable{
	
	private List<TaskModel> taskList = new ArrayList<TaskModel>();
	private TaskModel task;
	private ConnectionManager con;
	
	public void createTable() {
		String create = "CREATE TABLE IF NOT EXISTS public.task(idTask serial NOT NULL, name varchar NOT NULL, completed boolean NOT NULL, PRIMARY KEY(idTask))";
		try {
			con = new ConnectionManager();
			con.connect();
			Statement newTable = con.getCon().createStatement();
			newTable.executeUpdate(create);
			newTable.close();
			con.disconnect();
			LogMessages.sucessOnTablesCreation();
			
		}catch (Exception e) {
			e.printStackTrace();
			LogMessages.errorOnTablesCreation();
		}
	}
	
	public void searchForTasks() {
		this.createTable();
		String query = "SELECT * FROM task";
		
		try {
			if(this.getTaskList().isEmpty())
			{
				con = new ConnectionManager();
				con.connect();
				PreparedStatement tasks = con.getCon().prepareStatement(query, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
				ResultSet res = tasks.executeQuery();
				res.last();
				int rows = res.getRow();
				res.beforeFirst();
				if(rows>0) {
					while(res.next()) {
					taskList.add(new TaskModel(
							Integer.parseInt(res.getString(1)),
									res.getString(2),
									res.getBoolean(3)));
					}
				}else {
					LogMessages.noNewData();
				}
				con.disconnect();
			}
		}catch(Exception e){
			e.printStackTrace();
			LogMessages.errorOnTaskSearch();
		}
	}
	public void insertData() {
		try {
			String INSERT = "INSERT INTO task(name, completed) VALUES(?,?)";
			con = new ConnectionManager();
			con.connect();
			PreparedStatement save = con.getCon().prepareStatement(INSERT);
			save.setString(1, this.getTask().getName());
			save.setBoolean(2, false);
			
			save.executeUpdate();
			save.close();
			con.disconnect();
			this.getTaskList().clear();
			
			this.clearTask();
			LogMessages.dataInsertWithSucess();
		}catch (Exception e) {
			e.printStackTrace();
			LogMessages.errorOnSaveTask();
		}
	}
	public void deleteTask(TaskModel tk) {
		try {
			con = new ConnectionManager();
			con.connect();
			String DELETE = "DELETE FROM task WHERE idtask=?";
			PreparedStatement del = con.getCon().prepareStatement(DELETE);
			del.setInt(1, tk.getId());
			del.executeUpdate();
			del.close();
			LogMessages.dataRemoved();
			con.disconnect();
			this.getTaskList().clear();
			this.searchForTasks();
		}catch(Exception e){
			e.printStackTrace();
			LogMessages.dataRemovedProblem();
		}
	}
	public void finishTask(TaskModel tk) {
		
		try {
			con = new ConnectionManager();
			con.connect();
			String UPDATE = "UPDATE task SET completed=? WHERE idtask = ?";
			PreparedStatement upd = con.getCon().prepareStatement(UPDATE);
			upd.setBoolean(1, !tk.isCompleted());
			upd.setInt(2, tk.getId());
			
			upd.executeUpdate();
			upd.close();
			con.disconnect();
			LogMessages.taskFinish();
			this.getTaskList().clear();
			this.searchForTasks();
		}catch(Exception e) {
			e.printStackTrace();
			LogMessages.taskFinishProblem();
		}
	}
	
	private void clearTask() {
		this.setTask(null);
	}
	
	public List<TaskModel> getTaskList() {
		return taskList;
	}

	



	public void setTaskList(List<TaskModel> taskList) {
		this.taskList = taskList;
	}

	public TaskModel getTask() {
		if(this.task==null)
			this.setTask(new TaskModel());
		return task;
	}

	public void setTask(TaskModel task) {
		this.task = task;
	}





	public ConnectionManager getCon() {
		return con;
	}
public void setCon(ConnectionManager con) {
		this.con = con;
	}





	private static final long serialVersionUID = 1L;
	
	

}
