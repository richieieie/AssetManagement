package data;

import utils.IOExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ObjectList<T> extends ArrayList<T> {
    private String path = "";

    public ObjectList() {

    }

    @SuppressWarnings("unchecked")
    public ObjectList(String path) {
        if(IOExecutor.readFromFile(path, (List<Object>) this))
            System.out.println("Data is read (" + this.getClass() + ")");
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @SuppressWarnings("unchecked")
    public boolean addNew(T t) {
        if (this.contains(t)) {
            return false;
        }

        add(t);
        if (!IOExecutor.writeToFile(getPath(), (List<Object>) this)) {
            this.remove(t);
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    public boolean deleteOne(T t) {
        if (!remove(t))
            return false;

        if (!IOExecutor.writeToFile(getPath(), (List<Object>) this)) {
            this.add(t);
            return false;
        }

        return true;
    }

    public T searchById(String id) {
        return null;
    }

    public boolean objectNotFound(String id) {
        return Objects.isNull(searchById(id));
    }

    public void showAll() {
        this.forEach(System.out::println);
    }

}

// If you want to delete by index, you can use this
//    @SuppressWarnings("unchecked")
//    public boolean deleteOne(int i) {
//        if (i < 0 || i >= size())
//            return false;
//
//        T t = this.get(i);
//
//        if (!remove(t))
//            return false;
//
//        if (!IOExecutor.writeToFile(getPath(), (List<Object>) this)) {
//            this.add(t);
//            return false;
//        }
//
//        return true;
//    }
