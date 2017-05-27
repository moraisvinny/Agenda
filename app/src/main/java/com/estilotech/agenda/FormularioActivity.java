package com.estilotech.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.estilotech.agenda.dao.AlunoDAO;
import com.estilotech.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new FormularioHelper(this);

        aluno = (Aluno) getIntent().getSerializableExtra("aluno");
        if(aluno != null) {
            helper.preencherFormulario(aluno);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_formnulario_ok:
                Aluno aluno = helper.pegaAluno();
                AlunoDAO dao = new AlunoDAO(this);
                if(aluno.getId() == null) {
                    dao.insere(aluno);
                } else {
                    dao.atualiza(aluno);
                }

                dao.close();

                Toast.makeText(
                        FormularioActivity.this,
                        "Aluno "+ aluno.getNome() + " salvo com sucesso!",
                        Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
