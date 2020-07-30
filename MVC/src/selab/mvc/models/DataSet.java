package selab.mvc.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class DataSet<T extends Model> {
    private HashMap<String, T> set = new HashMap<>();

    public void add(T entity) {
        String key = entity.getPrimaryKey();
        if (set.containsKey(key))
            throw new IllegalArgumentException("Duplicate primary key.");

        set.put(key, entity);
    }

    public void remove(T entity) {
        String key = entity.getPrimaryKey();
        if (!set.containsKey(key))
            throw new IllegalArgumentException("There is no element");

        set.remove(key, entity);
    }

    public boolean containsKey(String key){
        return set.containsKey(key);
    }
    public T get(String key) {
        return set.get(key);
    }

    public ArrayList<T> getAll() {
        return new ArrayList(set.values());
    }
}
