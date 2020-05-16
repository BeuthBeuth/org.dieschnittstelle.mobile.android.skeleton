package tasks;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.util.function.Consumer;

import model.IToDoCRUDOperations;
import model.ToDo;

public class CreateToDoTask extends AsyncTask<ToDo, Void,ToDo> {

    private IToDoCRUDOperations crudOperations;
    private ProgressBar progressBar;
    private Consumer<ToDo> onDoneConsumer;

    @Override
    protected void onPreExecute() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public CreateToDoTask(ProgressBar progressBar, IToDoCRUDOperations crudOperations, Consumer<ToDo> onDoneConsumer) {
        this.crudOperations = crudOperations;
        this.progressBar = progressBar;
        this.onDoneConsumer = onDoneConsumer;
    }

    @Override
    protected ToDo doInBackground(ToDo... toDos) {
        return crudOperations.createToDo(toDos[0]);
    }

    @Override
    protected void onPostExecute(ToDo toDo) {
        onDoneConsumer.accept(toDo);
        if (progressBar != null) {
            progressBar.setVisibility(View.INVISIBLE);
            progressBar = null;
        }
    }
}
