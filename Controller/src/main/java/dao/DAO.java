package dao;

import java.sql.*;
import java.util.ArrayList;

public class DAO {
    private static final String url = "jdbc:mysql://localhost:3306/ripetizioni?autoReconnect=true&useSSL=false";
    private static final String user = "root";
    private static final String pw = " ";

    // 1. Registrazione del driver JDBC
    public static void registerDriver(){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // STAMPA ELENCO DEI DOCENTI
    public static ArrayList<Docente> mostraDocenti(){
        // 1. Apertura della connessione
        Connection conn = null;

        // 2. Creazione lista di docenti
        ArrayList<Docente> out = new ArrayList<>();

        try{
            conn = DriverManager.getConnection(url, user, pw);
            if(conn != null){
                System.out.println("Connected to the database \"ripetizioni\".");
            }

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM DOCENTE");
            while(rs.next()){
                Docente d = new Docente(rs.getString("nomeDocente"), rs.getString("cognomeDocente"), rs.getInt(
                        "idDocente"));
                out.add(d);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        // 4. Chiudo la connessione
        finally { // succede sempre
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection is now closed.");
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

        return out;
    }

    // STAMPA ELENCO DEI CORSI IN CATALOGO
    public static ArrayList<Corso> mostraCorsi(){
        // 1. Apertura della connessione
        Connection conn = null;

        // 2. Creazione lista di docenti
        ArrayList<Corso> out = new ArrayList<>();

        try{
            conn = DriverManager.getConnection(url, user, pw);
            if(conn != null){
                System.out.println("Connected to the database \"ripetizioni\".");
            }

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM corso");
            while(rs.next()){
                Corso c = new Corso(rs.getString("nomeCorso"));
                out.add(c);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        // 4. Chiudo la connessione
        finally { // succede sempre
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection is now closed.");
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

        return out;
    }

    // INSERIMENTO DI UN CORSO
    public static void addCourse(String courseName) throws SQLException{
        PreparedStatement pst = null;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");
            String sql="INSERT INTO corso(nomeCorso) VALUES (?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, courseName);
            pst.execute();
            System.out.println("Corso aggiunto correttamente.");
        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
        // 4. Chiudo la connessione
        finally { // succede sempre
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection is now closed.");
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    // RIMOZIONE DI UN CORSO
    public static void deleteCourse(String courseName) throws SQLException{
        PreparedStatement pst = null;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");
            String sql="DELETE FROM corso WHERE nomeCorso=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, courseName);
            pst.execute();
            System.out.println("Corso eliminato correttamente.");
        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
        // 4. Chiudo la connessione
        finally { // succede sempre
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection is now closed.");
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    // INSERIMENTO DI UN DOCENTE
    public static void addDocente(String nomeDocente, String cognomeDocente) throws SQLException{
        PreparedStatement pst = null;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");
            String sql="INSERT INTO docente(nomeDocente, cognomeDocente) VALUES (?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, nomeDocente);
            pst.setString(2, cognomeDocente);
            pst.execute();
            System.out.println("Docente aggiunto correttamente.");
        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
        // 4. Chiudo la connessione
        finally { // succede sempre
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection is now closed.");
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    // RIMOZIONE DI UN DOCENTE
    public static void deleteDocente(String nomeDocente, String cognomeDocente) throws SQLException{
        PreparedStatement pst = null;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");
            String sql="DELETE FROM docente WHERE nomeDocente=? AND cognomeDocente=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, nomeDocente);
            pst.setString(2, cognomeDocente);
            pst.execute();
            System.out.println("Docente eliminato correttamente.");
        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
        // 4. Chiudo la connessione
        finally { // succede sempre
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection is now closed.");
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    //INSERIMENTO ASSOCIAZIONE DOCENTE-CORSO
    public static void insertCorsoDocenteAssociation(String nomeCorso, String nomeDocente, String cognomeDocente)throws SQLException{
        PreparedStatement pstInserisciAssociazione = null, pstControllaEsistenzaCorso = null, pstInserisciCorso = null, pstControllaEsistenzaDocente = null, pstInserisciDocente = null;
        Connection conn = null;

        boolean courseExists = false, docenteExists = false, associazioneExists = false;

        int idDocente = 0;

        try{
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");

            // Controllo esistenza corso: se non esiste, lo aggiungo alla tabella "corso"
            String sqlControllaEsistenzaCorso = "SELECT * FROM corso WHERE nomeCorso = ?";
            pstControllaEsistenzaCorso = conn.prepareStatement(sqlControllaEsistenzaCorso);
            pstControllaEsistenzaCorso.setString(1, nomeCorso);
            ResultSet rsCorsi = pstControllaEsistenzaCorso.executeQuery();

            while(rsCorsi.next()){
                courseExists = true;
            }
            if(!courseExists){
                String sqlInserisciCorso="INSERT INTO corso(nomeCorso) VALUES (?)";
                pstInserisciCorso = conn.prepareStatement(sqlInserisciCorso);
                pstInserisciCorso.setString(1, nomeCorso);
                pstInserisciCorso.execute();
            }

            // Controllo esistenza docente: se non esiste, lo aggiungo alla tabella "docente"
            String sqlControllaEsistenzaDocente = "SELECT * FROM docente WHERE nomeDocente = ? AND cognomeDocente = ?";
            pstControllaEsistenzaDocente = conn.prepareStatement(sqlControllaEsistenzaDocente);
            pstControllaEsistenzaDocente.setString(1, nomeDocente);
            pstControllaEsistenzaDocente.setString(2, cognomeDocente);
            ResultSet rsDocenti = pstControllaEsistenzaDocente.executeQuery();

            while(rsDocenti.next()){
                docenteExists = true;
                idDocente = rsDocenti.getInt("idDocente");
            }
            if(!docenteExists){
                String sqlInserisciDocente="INSERT INTO docente(nomeDocente, cognomeDocente) VALUES (?, ?)";
                pstInserisciDocente = conn.prepareStatement(sqlInserisciDocente);
                pstInserisciDocente.setString(1, nomeDocente);
                pstInserisciDocente.setString(2, cognomeDocente);
                pstInserisciDocente.execute();
                idDocente = rsDocenti.getInt("idDocente");
            }

            // Controllo esistenza associazione corso-docente: se esiste, non devo far nulla
            String sqlControllaAssociazione = "SELECT * FROM insegna WHERE nomeCorso = ? AND idDocente = ?";
            pstControllaEsistenzaDocente = conn.prepareStatement(sqlControllaAssociazione);
            pstControllaEsistenzaDocente.setString(1, nomeCorso);
            pstControllaEsistenzaDocente.setInt(2, idDocente);
            ResultSet rsAssociazioni = pstControllaEsistenzaDocente.executeQuery();

            while(rsAssociazioni.next()){ // l'associazione esiste gia': non devo far nulla
                associazioneExists = true;
            }
            if(!associazioneExists){
                // Inserimento connessione docente-corso nella tabella "insegna"
                String sqlInserisciAssociazione = "INSERT INTO insegna(nomeCorso, idDocente) VALUES (?,?)";
                pstInserisciAssociazione = conn.prepareStatement(sqlInserisciAssociazione);
                pstInserisciAssociazione.setString(1, nomeCorso);
                pstInserisciAssociazione.setInt(2, idDocente);
                pstInserisciAssociazione.executeUpdate();
                System.out.println("Associazione aggiunta correttamente.");
            }



        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
        // 4. Chiudo la connessione
        finally { // succede sempre
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection is now closed.");
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    // ELIMINARE ASSOCIAZIONE DOCENTE-CORSO
    public static void deleteCorsoDocenteAssociation(String nomeCorso, String nomeDocente, String cognomeDocente)throws SQLException{
        PreparedStatement pstEliminaAssociazione = null, pstOttieniIdDocente = null;
        Connection conn = null;
        int idDocente = 0;
        try{
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");

            // Estrazione id docente in base al nome e al cognome fornito
            String sqlOttieniIdDocente = "SELECT * FROM docente WHERE nomeDocente = ? AND cognomeDocente = ?";
            pstOttieniIdDocente = conn.prepareStatement(sqlOttieniIdDocente);
            pstOttieniIdDocente.setString(1, nomeDocente);
            pstOttieniIdDocente.setString(2, cognomeDocente);
            ResultSet rsDocenti = pstOttieniIdDocente.executeQuery();
            while(rsDocenti.next()){
                idDocente = rsDocenti.getInt("idDocente");
            }

            // Eliminazione dalla tabella "insegna"
            String sqlEliminaAssociazione="DELETE FROM insegna WHERE nomeCorso=? AND idDocente=?";
            pstEliminaAssociazione = conn.prepareStatement(sqlEliminaAssociazione);
            pstEliminaAssociazione.setString(1, nomeCorso);
            pstEliminaAssociazione.setInt(2, idDocente);
            pstEliminaAssociazione.execute();
            System.out.println("Associazione eliminata correttamente.");

        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
        // 4. Chiudo la connessione
        finally { // succede sempre
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection is now closed.");
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    // VISUALIZZARE LISTA DELLE POSSIBILI RIPETIZIONI PER UN CERTO CORSO
    public static ArrayList<Docente> mostraDocentiConCorso(String nomeCorso){
        // 1. Apertura della connessione
        Connection conn = null;

        // 2. Creazione lista di docenti
        ArrayList<Docente> out = new ArrayList<>();

        try{
            conn = DriverManager.getConnection(url, user, pw);
            if(conn != null){
                System.out.println("Connected to the database \"ripetizioni\".");
            }

            // Estrazione id dei docenti che insegnano quel corso e che sono disponibili
            String sql = "SELECT docente.nomeDocente, docente.cognomeDocente, insegna.idDocente FROM docente,insegna" +
                    " WHERE docente.idDocente=insegna.idDocente AND insegna.nomeCorso = ? and disponibilita != 0";

            // Controllo disponibilita' del docente
            String sqlControllaDisponibilita = "SELECT * FROM insegna WHERE nomeCorso=? AND " +
                    "idDocente=? AND disponibilita != 0"; // != 0 significa che il docente è disponibile
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nomeCorso);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Docente d = new Docente(rs.getString("nomeDocente"), rs.getString("cognomeDocente"), rs.getInt(
                        "idDocente"));
                out.add(d);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        // 4. Chiudo la connessione
        finally { // succede sempre
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection is now closed.");
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

        return out;
    }

    // PRENOTAZIONE DI UNA RIPETIZIONE
    public static void prenotaRipetizione(String nomeCorso, int idDocente, int idUtente, String slot) throws SQLException{
        PreparedStatement pstControllaDisponibilita = null, pstInserisciPrenotazione = null, pstRimuoviDisponibilita =
                null;
        Connection conn = null;
        boolean isDisponibile = true;
        try{
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");

            // Controllo se quello slot è già occupato
            String sqlControllaDisponibilita = "SELECT * FROM prenotazione WHERE nomeCorso = ? AND idDocente = ? AND " +
                    "slot=?";
            pstControllaDisponibilita = conn.prepareStatement(sqlControllaDisponibilita);
            pstControllaDisponibilita.setString(1, nomeCorso);
            pstControllaDisponibilita.setInt(2, idDocente);
            pstControllaDisponibilita.setString(3, slot);
            ResultSet rsDisponibilita = pstControllaDisponibilita.executeQuery();

            while(rsDisponibilita.next()){
                isDisponibile = false;
            }
            if(isDisponibile){ // quello slot e' libero, quindi posso inserire una prenotazione
                System.out.println("Il docente è disponibile in quello slot");
                String sqlInserisciPrenotazione="INSERT INTO prenotazione(nomeCorso, idDocente, idUtente, slot) " +
                        "VALUES (?, ?, ?, ?)";
                pstInserisciPrenotazione = conn.prepareStatement(sqlInserisciPrenotazione);
                pstInserisciPrenotazione.setString(1, nomeCorso);
                pstInserisciPrenotazione.setInt(2, idDocente);
                pstInserisciPrenotazione.setInt(3, idUtente);
                pstInserisciPrenotazione.setString(4, slot);
                pstInserisciPrenotazione.execute();
                System.out.println("Prenotazione aggiunta correttamente.");
            }
            else
                System.out.println("Quello slot non è disponibile.");

            /*
            String sqlRimuoviDisponibilita="UPDATE insegna SET disponibilita=0 WHERE idDocente=?";
            pstRimuoviDisponibilita = conn.prepareStatement(sqlRimuoviDisponibilita);
            pstRimuoviDisponibilita.setInt(1, idDocente);
            pstRimuoviDisponibilita.execute();*/

        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
        // 4. Chiudo la connessione
        finally { // succede sempre
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection is now closed.");
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

    }

    public static void deletePrenotazione(String nomeCorso, String nomeDocente, String cognomeDocente, int idUtente,
                                          String slot){
        PreparedStatement pstEliminaPrenotazione = null, pstOttieniIdDocente = null;
        Connection conn = null;
        int idDocente = 0;
        try{
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");

            // Estrazione id docente in base al nome e al cognome fornito
            String sqlOttieniIdDocente = "SELECT * FROM docente WHERE nomeDocente = ? AND cognomeDocente = ?";
            pstOttieniIdDocente = conn.prepareStatement(sqlOttieniIdDocente);
            pstOttieniIdDocente.setString(1, nomeDocente);
            pstOttieniIdDocente.setString(2, cognomeDocente);
            ResultSet rsDocenti = pstOttieniIdDocente.executeQuery();
            while(rsDocenti.next()){
                idDocente = rsDocenti.getInt("idDocente");
            }

            // Eliminazione dalla tabella "prenotazione"
            String sqlEliminaAssociazione="DELETE FROM prenotazione WHERE nomeCorso=? AND idDocente=? AND idUtente=? " +
                    "AND slot=?";
            pstEliminaPrenotazione = conn.prepareStatement(sqlEliminaAssociazione);
            pstEliminaPrenotazione.setString(1, nomeCorso);
            pstEliminaPrenotazione.setInt(2, idDocente);
            pstEliminaPrenotazione.setInt(3, idUtente);
            pstEliminaPrenotazione.setString(4, slot);
            pstEliminaPrenotazione.execute();
            System.out.println("Prenotazione eliminata correttamente.");

        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
        // 4. Chiudo la connessione
        finally { // succede sempre
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection is now closed.");
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

}
