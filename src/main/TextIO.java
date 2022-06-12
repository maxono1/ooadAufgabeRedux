package main;

import action.Action;
import action.ActionFactory;
import action.Art;
import entity.Task;
import entity.TaskList;
import reducer.Reducer;
import state.State;
import store.Store;
import store.StoreInterface;
import store.Subscriber;
import store.TimerStore;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TextIO {

  private StoreInterface store = 
          new TimerStore(
                  new Store(new State(), new Reducer()));

  public TextIO() {
//    this.store.subscribe(newState -> {
//      System.out.println(newState.getTaskList());
//    });
    this.store.subscribe(new Subscriber(){
      @Override
      public void onChange(State s){
        System.out.println(s.getTaskList());
      }
    });
  }

  public void dialog() {
    int eingabe = -1;
    while (eingabe != 0) {
      System.out.print(""
              + "(0) beenden\n"
              + "(1) Task hinzu\n"
              + "(2) Task loeschen\n"
              + "(3) Testdaten einspielen \n"
              + "(4) Task abschliessen\n"
              + "(5) zugeordnete Tasks einer Person\n"
              + "(6) Tasks schuetzen \n"
              + "(7) Tasks neuzuordnen \n"
      );
      eingabe = Eingabe.leseInt();
      try {
        switch (eingabe) {
          case 1: {
            this.newTask();
            break;
          }
          case 2: {
            this.deleteTask();
            break;
          }
          case 3: {
            this.testData();
            break;
          }
          case 4: {
            this.finishTask();
            break;
          }
          case 5: {
            this.showResponsibilities();
            break;
          }
          case 6: {
            this.protectTasks();
            break;
          }
          case 7: {
            this.rearrangeTasks();
            break;
          }
        }
      } catch (Exception e) {
        System.out.println(e);
      }
    }
  }

  private void deleteTask() {
    System.out.print("welche Id: ");
    int id = Eingabe.leseInt();
    Action action = ActionFactory.create(Art.DELETE, id);
    store.dispatch(action);
  }

  private void newTask() {
    System.out.print("neue Aufgabe: ");
    String text = Eingabe.leseString();
    System.out.print("Bearbeiter*in: ");
    String responsible = Eingabe.leseString();
    Action action = ActionFactory.create(Art.ADD, text, responsible);
    store.dispatch(action);
  }

  private void testData() {
    TaskList testdaten = TaskList.testdaten();
    Map<Integer, Task> taskMap = testdaten.getTasks();
    Collection<Task> taskList = taskMap.values();
    State newState = new State();
    for (Task t: taskList) {
      newState.add(t.getText(),t.getResponsible());
    }
    store = new TimerStore(new Store(newState, new Reducer()));
  }

  private void finishTask() {
    System.out.print("welche Id: ");
    int id = Eingabe.leseInt();
    Action action = ActionFactory.create(Art.FINISH, id);
    store.dispatch(action);
    System.out.println("Ist abgeschlossen!");
  }

  private void showResponsibilities() {
    System.out.print("fuer welche Person: ");
    String bearbeiter = Eingabe.leseString();

    Action action = ActionFactory.create(Art.SHOW, bearbeiter);
    store.dispatch(action);

    //System.out.println();
  }

  private void protectTasks() {
    System.out.print("kommaseparierte Liste nicht loeschbarer Tasks: ");
    String prot = Eingabe.leseString();

  }

  private void rearrangeTasks() {
    System.out.print("aktuelle bearbeitende Person: ");
    String alt = Eingabe.leseString();
    System.out.print("neue bearbeitende Person: ");
    String neu = Eingabe.leseString();

    store.rearrangeTasks(alt, neu);
  }
}
