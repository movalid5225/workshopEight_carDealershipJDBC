package com.ps.DataManagers;

import javax.sql.DataSource;

public class DealershipDAO {
    private final DataSource dataSource;

    public DealershipDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }


}
