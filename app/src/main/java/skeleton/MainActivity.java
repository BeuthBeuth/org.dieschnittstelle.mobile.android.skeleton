package skeleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import skeleton.R;

import skeleton.databinding.ActivityMainListitemBinding;

import model.RoomToDoCRUDOperationsImpl;
import model.ToDo;
import model.IToDoCRUDOperations;
import tasks.CreateToDoTask;
import tasks.ReadAllToDosTask;




public class MainActivity extends AppCompatActivity {

    public static final int CALL_DETAILVIEW_FOR_NEW_ITEM = 0;
    private ViewGroup listView;
    private ArrayAdapter<ToDo> listViewAdapter;
    private FloatingActionButton fab;
    private ProgressBar progressBar;

    private IToDoCRUDOperations crudOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        this.crudOperations = new RoomToDoCRUDOperationsImpl(this); //SimpleDataItemCRUDOperationsImpl();

        this.listView = this.findViewById( R.id.listView );
        this.fab = this.findViewById( R.id.fab );
        this.progressBar = findViewById(R.id.progressBar);

        this.listViewAdapter = new ArrayAdapter<ToDo>( this, R.layout.activity_main_listitem, R.id.itemName ) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                ActivityMainListitemBinding binding = DataBindingUtil.inflate( getLayoutInflater(),R.layout.activity_main_listitem,null,false );

                ToDo item = getItem( position );
                binding.setItem( item );

                return binding.getRoot() ;
            }

        };

        ((ListView)this.listView).setAdapter(this.listViewAdapter);

        ((ListView)this.listView).setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ToDo item = listViewAdapter.getItem( i );
                onListitemSelected(item);
            }
        } );

        this.fab.setOnClickListener( (view) -> {
            this.onAddNewListitem();
        } );


        new ReadAllToDosTask(progressBar,
                crudOperations,
                items -> listViewAdapter.addAll(items)
        ).execute();

    }

    private void onListitemSelected(ToDo item) {

        Intent callDetailviewIntent = new Intent(this, DetailviewActivity.class);
        callDetailviewIntent.putExtra( DetailviewActivity.ARG_ITEM, item );
        startActivity( callDetailviewIntent );

    }

    private void onAddNewListitem() {

        Intent callDetailviewIntentForReturnValue = new Intent(this, DetailviewActivity.class);
        startActivityForResult( callDetailviewIntentForReturnValue, CALL_DETAILVIEW_FOR_NEW_ITEM );

    }

    private void createTodoAndAddToList(ToDo item) {
        new CreateToDoTask(
                progressBar,
                crudOperations,
                createdTodo -> this.listViewAdapter.add(createdTodo)
        ).execute(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CALL_DETAILVIEW_FOR_NEW_ITEM) {
            if (resultCode == Activity.RESULT_OK) {
                ToDo item = (ToDo)data.getSerializableExtra( DetailviewActivity.ARG_ITEM );
//                showFeedbackMessage( "got new item: " + item);
                createTodoAndAddToList( item );
            }
            else if (resultCode == Activity.RESULT_CANCELED) {
                showFeedbackMessage( "cancelled." );
            }
            else {
                showFeedbackMessage( "no item name received and no cancellation." );
            }
        }
        else {
            super.onActivityResult( requestCode, resultCode, data );
        }
    }

    private void showFeedbackMessage(String msg) {
        Snackbar.make(findViewById(R.id.viewRoot),msg, Snackbar.LENGTH_SHORT).show();
    }
}
