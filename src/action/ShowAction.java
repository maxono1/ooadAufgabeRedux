package action;

public class ShowAction extends Action{
    String responsible;
    public ShowAction(String s) {
        this.responsible = s;
    }

    public String getResponsible() {
        return responsible;
    }

    @Override
    public String toString() {
        return "ShowAction{" + "responsible=" + responsible + '}';
    }
}
