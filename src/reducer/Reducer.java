package reducer;

import action.Action;
import action.AddAction;
import action.DeleteAction;
import action.FinishAction;
import state.State;

public class Reducer {
  
  public State reduce(State state, Action action) {
    this.reduceIntern(state, action);
    return state.clone();
  }

  private void reduceIntern(State state, Action action) {
    if (action instanceof AddAction) {
      state.add(action.getParameter().get(0),
               action.getParameter().get(1));
      return;
    }

    if (action instanceof DeleteAction) {
      state.delete(((DeleteAction) action).getDeleteId());
      return;
    }
    if (action instanceof FinishAction) {
      state.finishTask(((FinishAction) action).getFinishId());
      return;
    }

    throw new IllegalArgumentException(
            "Action " + action + " nicht unterstuetzt");
  }

}
