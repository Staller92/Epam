package webster;


public class StackTrace {

    public String getStackTraceFromExceptions(Throwable e) {
        String stack = "";
        StackTraceElement[] element = e.getStackTrace();
        for (int i = element.length - 1; i > 0; i--) {
            stack += " ==> " + element[i];
        }

        return stack;
    }
}
