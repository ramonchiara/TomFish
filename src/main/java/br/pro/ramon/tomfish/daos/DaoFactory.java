package br.pro.ramon.tomfish.daos;

import br.pro.ramon.tomfish.daos.memory.DaoArrayList;

public class DaoFactory {

    public static Dao getDao() {
        return new DaoArrayList();
    }

}
