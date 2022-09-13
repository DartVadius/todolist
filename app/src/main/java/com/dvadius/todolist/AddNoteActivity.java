package com.dvadius.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextNote;
    private RadioButton radioButtonLowPriority;
    private RadioButton radioButtonMediumPriority;
    private Button buttonSave;

    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initView();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void initView () {
        editTextNote = findViewById(R.id.editTextNote);
        radioButtonLowPriority = findViewById(R.id.radioButtonLowPriority);
        radioButtonMediumPriority = findViewById(R.id.radioButtonMediumPriority);
        buttonSave = findViewById(R.id.buttonSave);
    }

    private void saveNote () {
        String text = editTextNote.getText().toString().trim();
        int priority = getPriority();
        int id = database.getNotes().size();
        Note note = new Note(id, text, priority);
        database.add(note);
        finish();
    }

    private int getPriority () {
        int priority;
        if (radioButtonLowPriority.isChecked()) {
            priority = 0;
        } else if (radioButtonMediumPriority.isChecked()) {
            priority = 1;
        } else {
            priority = 2;
        }

        return priority;
    }

    public static Intent newIntent (Context context) {
        return new Intent(context, AddNoteActivity.class);
    }
}