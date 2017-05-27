package com.estilotech.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.estilotech.agenda.modelo.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinic on 21/05/2017.
 */

public class AlunoDAO extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "Agenda";
    private static final int VERSAO_BANCO = 1;

    public AlunoDAO(Context context) {

        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Alunos;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Aluno aluno) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(aluno);

        db.insert("Alunos", null,dados);

    }

    @NonNull
    private ContentValues getContentValues(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("site", aluno.getSite());
        dados.put("nota", aluno.getNota());
        return dados;
    }

    public List<Aluno> buscaAlunos() {

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM Alunos;";
        Cursor c = db.rawQuery(sql, null);
        List<Aluno> alunos = new ArrayList<Aluno>();
        while (c.moveToNext()) {
            Aluno aluno = new Aluno(
                    c.getLong(c.getColumnIndex("id")),
                    c.getString(c.getColumnIndex("nome")),
                    c.getString(c.getColumnIndex("endereco")),
                    c.getString(c.getColumnIndex("telefone")),
                    c.getString(c.getColumnIndex("site")),
                    c.getDouble(c.getColumnIndex("nota"))
            );
            alunos.add(aluno);
        }
        return alunos;
    }

    public void deletar(Aluno aluno) {

        SQLiteDatabase db = getWritableDatabase();
        db.delete("Alunos","id = ?", new String[]{aluno.getId().toString()});
    }

    public void atualiza(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        db.update("Alunos", getContentValues(aluno), "id = ?", new String[]{aluno.getId().toString()});
    }
}
