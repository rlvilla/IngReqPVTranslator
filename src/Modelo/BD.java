package Modelo;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BD {

    // Configuración de la base de datos.
    private static final String BD_FILE_NAME = "PVTranslator.db";
    private static final String BD_DDL_BD = "PVT.ddl";
    private static final String DB_URL = "jdbc:sqlite:" + BD_FILE_NAME;
    // Fin configuración.

    private static BD instance = null;
    private Connection con;

    private BD() {
        boolean ddl_loaded = false;
        try {
            Class.forName("org.sqlite.JDBC");
            ddl_loaded = (new File(BD_FILE_NAME)).exists();
            con = DriverManager.getConnection(DB_URL);
            execute("PRAGMA foreign_keys = ON");

            if (!ddl_loaded) {

                if (BD_DDL_BD == null)
                    cargarDDL();
                else
                    leerDDL();

            }

        } catch (ClassNotFoundException ex) {
            System.err.println("No se ha encontrado el driver de SQLite");
            System.exit(1);
        } catch (SQLException e) {
            System.err.println("Hubo un error al iniciar la base de datos");
            System.exit(1);
        }
    }

    //Singleton
    public static BD getInstance() {

        if (instance == null) {
            instance = new BD();
        }

        return instance;
    }

    private void cargarDDL() {
        // DDL va aqui
        //execute("CREATE TABLE user (name NUMBER PRIMARY KEY)");

    }

    private void leerDDL() {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        int c;

        try {
            br = new BufferedReader(new FileReader(BD_DDL_BD));

            c = br.read();

            while (c != -1) {

                if (((char) c) != ';')
                    sb.append((char) c);
                else {
                    execute(sb.toString().trim().replace("\n", ""));
                    sb = new StringBuilder();
                }

                c = br.read();
            }


        } catch (IOException ex) {
            System.err.println("Error al leer el fichero DDL : " + ex.getMessage());
            borrarDB();
        } finally {

            if (br != null)
                try {
                    br.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar el fichero : " + ex.getMessage());
                }

        }
    }

    public List<String[]> select(String query) {
        ArrayList<String[]> list = new ArrayList<>();

        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmt = rs.getMetaData();

            while (rs.next()) {
                String[] sm = new String[rsmt.getColumnCount()];

                for (int i = 0; i < sm.length; i++) {
                    sm[i] = rs.getString(i + 1);
                }

                list.add(sm);
            }

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar sentencia : " + query + "\nError : " + ex.getMessage());
        }

        return list;
    }

    public String selectEscalar(String query) {
        String result = null;

        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmt = rs.getMetaData();

            if (rsmt.getColumnCount() > 0 && rs.next())
                result = rs.getString(1);

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar sentencia : " + query + "\nError : " + ex.getMessage());
        }

        return result;
    }

    private void borrarDB() {
        File file_db = new File(BD_FILE_NAME);

        if (file_db.exists())
            file_db.delete();


    }

    public void execute(String statement) {

        try {
            Statement stm = con.createStatement();
            stm.execute(statement);
        } catch (SQLException ex) {
            System.err.println("Error al ejecutar la sentencia : " + ex.getMessage());
        }
    }

    public void insert(String statement) {
        execute(statement);
    }

    public void delete(String statement) {
        execute(statement);
    }

    public void update(String statement) {
        execute(statement);
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar la base de datos");
        }
    }


}
