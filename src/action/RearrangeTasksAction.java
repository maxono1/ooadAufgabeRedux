package action;

import java.util.Arrays;
import java.util.List;

public class RearrangeTasksAction extends Action{
    public RearrangeTasksAction(String... par1){
        super(Arrays.asList(par1));
    }

    public RearrangeTasksAction(List<String> list) {
        super(list);
    }

    @Override
    public String toString() {
        return "RearrangeTasksAction{parameter=" + super.parameter + '}';
    }
}
