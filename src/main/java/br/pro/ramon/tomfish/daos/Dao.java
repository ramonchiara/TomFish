package br.pro.ramon.tomfish.daos;

import br.pro.ramon.tomfish.models.Pessoa;
import java.util.List;

public interface Dao {

    void create(Pessoa pessoa) throws JaCadastradoDaoException, DaoException;

    Pessoa findByNome(String nome) throws DaoException;

    List<Pessoa> findAll() throws DaoException;

    void update(Pessoa pessoa) throws NaoEncontradoDaoException, DaoException;

    void delete(Pessoa pessoa) throws NaoEncontradoDaoException, DaoException;

}
