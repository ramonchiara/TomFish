package br.pro.ramon.tomfish.daos.memory;

import br.pro.ramon.tomfish.daos.Dao;
import br.pro.ramon.tomfish.daos.DaoException;
import br.pro.ramon.tomfish.daos.JaCadastradoDaoException;
import br.pro.ramon.tomfish.daos.NaoEncontradoDaoException;
import br.pro.ramon.tomfish.models.Pessoa;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaoArrayList implements Dao {

    private static final List<Pessoa> DB = new ArrayList<>();

    @Override
    public void create(Pessoa pessoa) throws DaoException {
        if (pessoa == null) {
            return;
        }
        Pessoa encontrada = findByNome(pessoa.getNome());
        if (encontrada == null) {
            DB.add(pessoa);
        } else {
            throw new JaCadastradoDaoException();
        }
    }

    @Override
    public Pessoa findByNome(String nome) throws DaoException {
        Pessoa resultado = null;
        for (Pessoa pessoa : DB) {
            if (pessoa.getNome().equals(nome)) {
                resultado = pessoa;
                break;
            }
        }
        return resultado;
    }

    @Override
    public List<Pessoa> findAll() throws DaoException {
        return Collections.unmodifiableList(DB);
    }

    @Override
    public void update(Pessoa pessoa) throws DaoException {
        if (pessoa == null) {
            return;
        }
        Pessoa encontrada = findByNome(pessoa.getNome());
        if (encontrada == null) {
            throw new NaoEncontradoDaoException();
        } else {
            int index = DB.indexOf(encontrada);
            DB.set(index, pessoa);
        }
    }

    @Override
    public void delete(Pessoa pessoa) throws DaoException {
        if (pessoa == null) {
            return;
        }
        Pessoa encontrada = findByNome(pessoa.getNome());
        if (encontrada == null) {
            throw new NaoEncontradoDaoException();
        } else {
            int index = DB.indexOf(encontrada);
            DB.remove(index);
        }
    }

}
