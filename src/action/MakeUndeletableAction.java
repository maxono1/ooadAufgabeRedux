package action;

import java.util.List;

public class MakeUndeletableAction extends Action{
    private List<Integer> parameters;

    public MakeUndeletableAction(List<Integer> parameters){
        this.parameters = parameters;
    }

    /*
    public Integer getSizeOfList(){
        return parameters.size();
    }

    public Integer getValueAt(Integer pos){
        return parameters.get(pos);
    }*/
    public List<Integer> getIntegerParameters(){
        return parameters;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MakeUndeletableAction{");
        for (int i = 0; i < parameters.size(); i++){
            sb.append(parameters.get(i));
            if(i!=parameters.size()-1){
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }

}
