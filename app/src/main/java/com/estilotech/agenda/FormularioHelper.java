package com.estilotech.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import com.estilotech.agenda.modelo.Aluno;

/**
 * Created by vinic on 21/05/2017.
 */

public class FormularioHelper {

    private final EditText nome;
    private final EditText endereco;
    private final EditText telefone;
    private final EditText site;
    private final RatingBar nota;
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity) {
        this.aluno = new Aluno();
        nome = (EditText) activity.findViewById(R.id.formulario_nome);
        endereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        telefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        site = (EditText) activity.findViewById(R.id.formulario_site);
        nota = (RatingBar) activity.findViewById(R.id.formulario_nota);

    }

    public Aluno pegaAluno() {

        Aluno retorno = new Aluno(
            null,
            nome.getText().toString(),
            endereco.getText().toString(),
            telefone.getText().toString(),
            site.getText().toString(),
            Double.valueOf(nota.getRating())
        );
        if(this.aluno != null)  {
            retorno.setId(aluno.getId());
        }
        return retorno;
    }

    public void preencherFormulario(Aluno aluno) {
        this.aluno = aluno;
        nome.setText(aluno.getNome());
        endereco.setText(aluno.getEndereco());
        telefone.setText(aluno.getTelefone());
        site.setText(aluno.getSite());
        nota.setProgress(aluno.getNota().intValue());

    }
}
