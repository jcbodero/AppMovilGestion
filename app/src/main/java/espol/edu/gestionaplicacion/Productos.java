package espol.edu.gestionaplicacion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.HashMap;

public class Productos extends AppCompatActivity {

    private static final String TAG = "tag";
    private ArrayList<Integer> distancias ;
    private int Distancia;
    private int cantidadProduct;
    LinearLayout contenedor1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        contenedor1 = findViewById(R.id.contenedor);
        distancias = new ArrayList<>();
        subscribeToUpdates();
        StartThread();
    }
    public void reducir(){
        /*
        for (int i = 0; i<contenedor1.getChildCount();i++){

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }*/

        while (true){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i<cantidadProduct;i++){

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LinearLayout text = (LinearLayout) contenedor1.getChildAt(i);
                text.setBackgroundResource(R.color.colorPrimary);

            }



        }





    }
    public void StartThread(){
        Thread thread = new Thread() {
            @Override
            public void run() {
                reducir();


            }
        };

        thread.start();
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                while(distancias.isEmpty()){
                   //System.out.println("vacio");
                }
                while (true){
                    Distancia = distancias.get(distancias.size()-1);
                    cantidadProduct = (int) Math.floor(Distancia / contenedor1.getChildCount());
                    System.out.println(cantidadProduct);
                }
            }
        };

        thread2.start();
    }
    private void subscribeToUpdates() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Lecturas");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                setMarker(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                setMarker(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void setMarker(DataSnapshot dataSnapshot) {
        // When a location update is received, put or update
        // its value in mMarkers, which contains all the markers
        // for locations received, so that we can build the
        // boundaries required to show them all on the map at once
        String key = dataSnapshot.getKey();
        String value = dataSnapshot.getValue().toString();
        //System.out.println(value);



        if(value!=null && value!="" ){
            value = dataSnapshot.getValue().toString().split("=")[1].replace("}","").trim();
            int metros = Integer.parseInt(value);
            distancias.add(metros);

        }

    }

}
