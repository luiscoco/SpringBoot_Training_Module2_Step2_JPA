package com.luxoft.flights.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Qualifier("db")
@Profile("db")
public class DBDataLoader implements DataLoader {
    private DatabaseConnection databaseConnection;

    public DBDataLoader(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void loadData() throws Exception {
        System.out.println("Loading data from DB");
        System.out.println("DB URL: " + databaseConnection.getUrl());
    }

}
