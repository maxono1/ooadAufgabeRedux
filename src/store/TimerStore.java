package store;

import action.Action;

public class TimerStore extends AbstractDecoratorStore {

  private long start;

  public TimerStore(StoreInterface store) {
    super(store);
  }

  @Override
  public void dispatch(Action action) {
    start = System.nanoTime();
    super.store.dispatch(action);
    System.out.println("Dauer von " + action
            + ": " + (System.nanoTime() - start));
  }

  @Override
  public void showTaskFromPerson(String person) {
    super.store.showTaskFromPerson(person);
  }

  @Override
  public void rearrangeTasks(String alt, String neu) {
    super.store.rearrangeTasks(alt, neu);
  }
}
