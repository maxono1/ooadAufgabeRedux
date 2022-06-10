package state;

import entity.TaskList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class State implements Cloneable{

  private TaskList taskList;
  
  public State(){
    this.taskList = new TaskList();
  }

  private State(TaskList taskList) {
    this.taskList = taskList;
  }

  public TaskList getTaskList() {
    return this.taskList;
  }

  public void finishTask(int id){
    taskList.finishTask(id);
  }
  public void add(String text, String responsible){
    this.taskList.add(text, responsible);
  }
  
  public void delete(int id){
    this.taskList.delete(id);
  }
  
  @Override
  public State clone() {
    State result = new State(this.taskList.clone());
    return result;
  }

  public void showTaskFromPerson(String person){
    this.taskList.showTaskFromPerson(person);
  }
}
