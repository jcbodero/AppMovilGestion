package espol.edu.gestionaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Niveles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles);
    }
    public void go_productos(View view){
        Intent intent = new Intent(getApplicationContext(),Productos.class);
        startActivity(intent);
    }
}
