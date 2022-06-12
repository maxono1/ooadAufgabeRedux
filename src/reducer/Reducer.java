package reducer;

import action.*;
import state.State;
import util.User;

public class Reducer {
  
  public State reduce(State state, Action action) {
    this.reduceIntern(state, action);
    return state.clone();
  }

  private void reduceIntern(State state, Action action) {
    if (action instanceof AddAction) {
      if(User.allowed(action.getParameter().get(1))){
        state.add(action.getParameter().get(0),
                action.getParameter().get(1));
      } else {
        throw new IllegalArgumentException("User "+ action.getParameter().get(1) + " nicht autorisiert");
      }

      return;
    }

    if (action instanceof DeleteAction) {
      state.delete(((DeleteAction) action).getDeleteId());
      return;
    }

    if (action instanceof MakeUndeletableAction){
      state.makeTasksUndeletable(((MakeUndeletableAction) action).getIntegerParameters());
      return;
    }

    if (action instanceof ShowAction) {
      state.showTaskFromPerson(((ShowAction) action).getResponsible());
      return;
    }

    if (action instanceof FinishAction){
      state.finishTask(((FinishAction) action).getFinishId());
      return;
    }

    if (action instanceof RearrangeTasksAction){
      state.rearrangeTasks(action.getParameter().get(0),action.getParameter().get(1));
      return;
    }

    throw new IllegalArgumentException("Action " + action + " nicht unterstuetzt");
  }

}
