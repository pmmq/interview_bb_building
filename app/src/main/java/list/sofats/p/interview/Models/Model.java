package list.sofats.p.interview.Models;

/**
 * Created by P on 28/12/2558.
 */
public class Model {
    private Status status;
    public class Status{
        public String code;
        public String message;
    }

    public Status getStatus() {
        return status;
    }
}
