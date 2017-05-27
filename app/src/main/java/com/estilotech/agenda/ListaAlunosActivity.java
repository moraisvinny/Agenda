package com.estilotech.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.estilotech.agenda.dao.AlunoDAO;
import com.estilotech.agenda.modelo.Aluno;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);



        listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        FloatingActionButton botaoNovoAluno = (FloatingActionButton) findViewById(R.id.lista_botao_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiPraFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intentVaiPraFormulario);
            }
        });
        registerForContextMenu(listaAlunos);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(position);
                Intent intentFormulario = new Intent(ListaAlunosActivity.this,FormularioActivity.class);
                intentFormulario.putExtra("aluno", aluno);
                startActivity(intentFormulario);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();

    }

    private void carregaLista() {
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem menuItem = menu.add("Deletar");

        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AdapterView.AdapterContextMenuInfo adaptMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(adaptMenuInfo.position);

                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.deletar(aluno);
                dao.close();
                carregaLista();
                return false;
            }
        });
    }
}
