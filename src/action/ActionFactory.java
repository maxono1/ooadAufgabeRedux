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
        case FINISH:
          if (value.length == 0) {
            throw new IllegalArgumentException("FINSIH benoetigt Parameter");
          }
          return new FinishAction((Integer) value[0]);
        default:
          throw new IllegalArgumentException("Action(" + command + ","
                  + Arrays.asList(value) + ") existiert nicht");
      }
    } catch (ClassCastException e) {
      throw new IllegalArgumentException("Action(" + command + ","
              + Arrays.asList(value) + ") hat falschen Parametertyp :" + e);
    }
  }
}
