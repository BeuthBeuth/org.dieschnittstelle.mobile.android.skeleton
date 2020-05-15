package model;

import java.util.List;

public interface IDataItemCRUDOperations {

    public ToDo createToDo(ToDo item);

    public List<ToDo> readAllDataItems();

    public ToDo readToDo();

    public boolean updateToDo(ToDo item);

    public boolean deleteToDo(long id);

    public ToDo readToDo(long id);

}
