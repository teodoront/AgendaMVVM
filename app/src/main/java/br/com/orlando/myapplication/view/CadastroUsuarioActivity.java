package br.com.orlando.myapplication.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import br.com.orlando.myapplication.R;


public class CadastroUsuarioActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cadastro_usuario);
        //Aqui estou ocultando a actionbar da tela
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


    }



}