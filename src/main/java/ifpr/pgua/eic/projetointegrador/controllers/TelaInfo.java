package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.model.daos.JDBCInfoDAO;

public class TelaInfo extends BaseController{

    private JDBCInfoDAO jdbcInfoDAO;

    public TelaInfo(JDBCInfoDAO jdbcInfoDAO) {
        this.jdbcInfoDAO = jdbcInfoDAO;
    }

    
}
