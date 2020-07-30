package selab.mvc.views;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

public abstract class View {
    public abstract String getBody() throws IOException;
    public abstract HashMap<String, String> getResponseHeaders();
}
