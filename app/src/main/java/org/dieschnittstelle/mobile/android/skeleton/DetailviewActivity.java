package org.dieschnittstelle.mobile.android.skeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.dieschnittstelle.mobile.android.skeleton.databinding.ActivityDetailviewBinding;

import model.ToDo;



public class DetailviewActivity extends AppCompatActivity {

    public static final String ARG_ITEM = "item";

    private ToDo item;
    private ActivityDetailviewBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detailview);

        this.item = (ToDo)getIntent().getSerializableExtra( ARG_ITEM );
        if (this.item == null) {
            this.item = new ToDo( );
        }

        binding.setController( this );

    }

    public void onSaveItem(View view) {
        Intent returnData = new Intent();

        returnData.putExtra( ARG_ITEM,this.item);

        this.setResult( Activity.RESULT_OK,returnData );
        finish();
    }

    public ToDo getItem() {
        return item;
    }
}