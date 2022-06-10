package store;

import action.Action;
import state.State;

public interface StoreInterface {

  public void dispatch(Action action);
  public State getState();
  public void subscribe(Subscriber subscriber);
  public void notifySubscribers();
  public void finishTask(int id);
  public void showTaskFromPerson(String person);

}
