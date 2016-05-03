package com.example.quentin.gsb_frais_mobile;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.net.URL;
import java.util.ArrayList;

public class Passerelle {
    /*private double coutDieselCV4 = 0.52;
    private double coutDieselCV5Et6 = 0.56;
    private double coutEssenceCV4 = 0.56;
    private double coutEssenceCV5Et6 = 0.58;

    public double GetCoutDieselCV4() {
        return coutDieselCV4;
    }

    public double GetCoutDieselCV5Et6() {
        return coutDieselCV5Et6;
    }

    public double GetCoutEssenceCV4() {
        return coutEssenceCV4;
    }

    public double GetCoutEssenceCV5Et6() {
        return coutEssenceCV5Et6;
    }*/

    private static String base = "bd_frais";
    private static int version = 1;
    private BdSQLiteOpenHelper acces_db;

    public Passerelle (Context ct) {
        acces_db = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    public ArrayList<Carburant> getCarburants(){
        Cursor curseur;
        String request = "SELECT * FROM carburant";
        curseur = acces_db.getReadableDatabase().rawQuery(request, null);
        return cursorToCarburantArrayList(curseur);
    }

    private ArrayList<Carburant> cursorToCarburantArrayList(Cursor curseur) {
        ArrayList<Carburant> listeCarburant = new ArrayList<Carburant>();
        int id;
        String libelle;

        curseur.moveToFirst();
        while(!curseur.isAfterLast()) {
            id = curseur.getInt(0);
            libelle = curseur.getString(1);
            listeCarburant.add(new Carburant(id, libelle));
            curseur.moveToNext();
        }

        return listeCarburant;
    }
}