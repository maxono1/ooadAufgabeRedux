package action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActionFactory {

  // auch mehrere create-Methoden denkbar
  public static Action create(Art command, Object... value) {
    try {
      switch (command) {
        case ADD:
          List<String> tmp = new ArrayList<>();
//          Arrays.stream(value).forEach(v -> tmp.add((String) v));
          for(Object o:value){
            tmp.add(o.toString());
          }
          if (tmp.size() < 2) {
            throw new IllegalArgumentException(
                    "Hinzufuegen benoetigt zwei Parameter");
          }
          return new AddAction(tmp);
        case DELETE:
          if (value.length == 0) {
            throw new IllegalArgumentException("DELETE benoetigt Parameter");
          }
          return new DeleteAction((Integer) value[0]);
        case SHOW:
          if (value.length == 0){
            throw new IllegalArgumentException("SHOW benoetigt Parameter");
          }
          return new ShowAction((String) value[0]);
        case FINISH:
          if (value.length == 0) {
            throw new IllegalArgumentException("FINSIH benoetigt Parameter");
          }
          return new FinishAction((Integer) value[0]);
        case REARRANGE:
          List<String> tmp2 = new ArrayList<>();
          for(Object o:value){
            tmp2.add(o.toString());
          }
          if (tmp2.size() < 2) {
            throw new IllegalArgumentException(
                    "Hinzufuegen benoetigt zwei Parameter");
          }
          return new RearrangeTasksAction(tmp2);
        default:
          throw new IllegalArgumentException("Action(" + command + ","
                  + Arrays.asList(value) + ") existiert nicht");
      }
    } catch (ClassCastException e) {
      throw new IllegalArgumentException("Action(" + command + ","
              + Arrays.asList(value) + ") hat falschen Parametertyp :" + e);
    }
  }
  public static Action create(List<Integer> values){

    if(values.size() == 0) {
      throw new IllegalArgumentException("MAKE_UNDELETABLE benoetigt Parameter");
    }

    return new MakeUndeletableAction(values);
  }
}
