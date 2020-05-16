package model;

import java.util.ArrayList;
import java.util.List;



public class SimpleToDoCRUDOperationsImpl implements IToDoCRUDOperations {

    private static String[] ITEM_NAMES = new String[] {"lirem", "dopsum", "dolor", "sit", "consectetur", "elit", "direm", "lopsum"};

    @Override
    public ToDo createToDo(ToDo item) {
        return item;
    }

    @Override
    public List<ToDo> readAllToDos() {
        try {
            Thread.sleep(5000);
        }
        catch (Exception e) {

        }

//        return Arrays
//                .stream(ITEM_NAMES)
//                .map( name -> new DataItem (name) )
//                .collect( Collectors.toList() );

        List<ToDo> items = new ArrayList<>();
        for (String name : ITEM_NAMES) {
            items.add(new ToDo( name ));
        }
        return items;
    }

    @Override
    public ToDo readToDo() {
        return null;
    }

    @Override
    public boolean updateToDo(ToDo item) {
        return false;
    }

    @Override
    public boolean deleteToDo(long id) {
        return false;
    }

    @Override
    public ToDo readToDo(long id) {
        return null;
    }
}
