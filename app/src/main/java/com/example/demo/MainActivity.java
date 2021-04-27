package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final String KEY_NAME="Name";
private static final String KEY_AGE="age";
private EditText editTextName;
private EditText editTextage;
private TextView textViewi;
private final FirebaseFirestore db=FirebaseFirestore.getInstance();
private DocumentReference noteref= db.collection("NoteBooks").document("Mys");
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName=findViewById(R.id.text);
        editTextage=findViewById(R.id.age);
        textViewi=findViewById(R.id.but);
    }
    public void saveNote(View v){
        String Name=editTextName.getText().toString();
        String age=editTextage.getText().toString();
        Map<String, Object> note=new HashMap<>();
        note.put(KEY_NAME,Name);
        note.put(KEY_AGE,age);
        db.collection("NoteBooks").document("Mys").set(note)
                .addOnSuccessListener(aVoid -> Toast.makeText(MainActivity.this,"Note saved",Toast.LENGTH_LONG).show())
             .addOnFailureListener(e -> {
                 Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                 Log.d(TAG,e.toString());
             })   ;


    }
    public void yesyouretrivedpavi(View v){
        noteref.get()
                .addOnSuccessListener(documentSnapshot -> {
                    if(documentSnapshot.exists()){
                        String Name=documentSnapshot.getString(KEY_NAME);
                        String age=documentSnapshot.getString(KEY_AGE);
                        textViewi.setText("Name--" + Name + "\n" + "Age:" + age);
                    }else {
                        Toast.makeText(MainActivity.this,"Not there",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(MainActivity.this,"oops",Toast.LENGTH_LONG).show();
                });
    }
}