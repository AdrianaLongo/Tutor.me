package dao;

import java.sql.*;
import java.util.ArrayList;

//jdbc:mysql://localhost:3306/ripetizioni?autoReconnect=true&amp;useSSL=false
public class DAO {

    private static String url;
    private static String user;
    private static String pw;

    public DAO(String url, String user, String password) {
        DAO.user = user;
        DAO.url = url;
        DAO.pw = password;
        registerDriver();
    }

    // 1. Registrazione del driver JDBC
    public static void registerDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public Utente retrieveUtente(String username, String password) throws SQLException {

        PreparedStatement pst = null;
        Connection conn = null;

        int id = 0;
        String nome = "";
        String cognome = "";
        String ruolo = "";
        String usernameExtracted = "";
        String passwordExtracted = "";

        try {

            conn = DriverManager.getConnection(url, user, pw);
            if (conn != null) {
                System.out.println("Connected to DB");

                String sql = "SELECT idUtente, username, password, nomeUtente, cognomeUtente, ruolo FROM utente WHERE username=? AND password=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                ResultSet userValues = pst.executeQuery();
                while (userValues.next()) {
                    id = userValues.getInt("idUtente");
                    usernameExtracted = userValues.getString("username");
                    passwordExtracted = userValues.getString("password");
                    nome = userValues.getString("nomeUtente");
                    cognome = userValues.getString("cognomeUtente");
                    ruolo = userValues.getString("ruolo");
                }
            }
            System.out.println("Retrieve Utente fatto");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally { // succede sempre
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection is now closed.");
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return new Utente(id, nome, cognome, ruolo, usernameExtracted, passwordExtracted);
    }


    public void addUtente(String nomeUtente, String cognomeUtente) throws SQLException {
        PreparedStatement pst = null;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, pw);
            if (conn != null) {
                System.out.println("Connected to DB");
            }
            String sql = "INSERT INTO utente (nomeUtente, cognomeUtente, ruolo)" +
                    " VALUES ( ?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, nomeUtente);
            pst.setString(2, cognomeUtente);
            pst.setString(3, "utente");
            pst.execute();
            System.out.println("Utente aggiunto correttamente.");
        } catch (SQLException e) {
            System.out.println("Diofa socio manco una query ?");
        } finally { // succede sempre
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

    public ArrayList<Docente> cercaTutor(String corso) throws SQLException {

        Connection conn = null;
        PreparedStatement pst;
        PreparedStatement pstNested;
        ResultSet rsNested;

        // 2. Creazione lista di docenti
        ArrayList<Docente> out = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, pw);
            if (conn != null) {
                System.out.println("Connected to the database \"insegna\".");
            }

            String sql = "SELECT idDocente FROM insegna WHERE nomeCorso = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, corso);
            ResultSet rs = pst.executeQuery();
            System.out.println("Main query andata");

            while (rs.next()) {
                //idTutor.add(rs.getInt("idDocente"));
                try {
                    String sqlNested = "SELECT * FROM docenti WHERE idDocente = ?";
                    pstNested = conn.prepareStatement(sqlNested);
                    pstNested.setInt(1, rs.getInt("idDocente"));
                    rsNested = pstNested.executeQuery();
                    Docente docente = new Docente(rsNested.getString("Nome"),
                            rsNested.getString("Cognome"),
                            rsNested.getInt("idDocente"));
                    out.add(docente);
                } catch (SQLException e2) {
                    System.out.println("NestedQuery failed");
                    System.out.println(e2.getMessage());
                }

            }

        } catch (SQLException e1) {
            System.out.println("MainQuery failed");
        } finally {
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


    // STAMPA ELENCO DEI DOCENTI
    public ArrayList<Docente> mostraDocenti() throws SQLException {
        // 1. Apertura della connessione
        Connection conn = null;

        // 2. Creazione lista di docenti
        ArrayList<Docente> out = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, pw);
            if (conn != null) {
                System.out.println("Connected to the database \"ripetizioni\".");
            }

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM docente");
            while (rs.next()) {
                Docente d = new Docente(rs.getString("nomeDocente"), rs.getString("cognomeDocente"), rs.getInt(
                        "idDocente"));
                out.add(d);
            }
        } catch (SQLException e) {
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

    public ArrayList<Slot> getSlotOccupati(int idDocente) throws SQLException {
        Connection conn = null;

        // 2. Creazione lista di slot occupati del docente
        ArrayList<Slot> out = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, pw);
            if (conn != null) {
                System.out.println("Connected to the database \"ripetizioni\".");
            }

            // Estrazione id dei docenti che insegnano quel corso e che sono disponibili
            String sql = "SELECT prenotazione.slot FROM prenotazione" +
                    " WHERE prenotazione.idDocente = ?";

            // Controllo disponibilita' del docente

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idDocente);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Slot d = new Slot(rs.getString("slot"));
                out.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Occhio che magari non hai tolto disponibilità dalla query");
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
    public ArrayList<Corso> mostraCorsi() throws SQLException {
        // 1. Apertura della connessione
        Connection conn = null;

        // 2. Creazione lista di docenti
        ArrayList<Corso> out = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, pw);
            if (conn != null) {
                System.out.println("Connected to the database \"ripetizioni\".");
            }

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM corso");
            while (rs.next()) {
                Corso c = new Corso(rs.getString("nomeCorso"));
                out.add(c);
            }
        } catch (SQLException e) {
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
    public void checkAssociation(int idDocente, String nomeCorso) {

        Connection conn = null;
        boolean check = false;
        PreparedStatement pst;

        try {
            conn = DriverManager.getConnection(url, user, pw);
            if (conn != null) {
                System.out.println("Connected to the database \"ripetizioni\".");
            }
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM insegna WHERE idDocente = ? AND nomeCorso = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, idDocente);
            pst.setString(2,nomeCorso);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException e) {
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

    }
    public boolean checkDocente (int idDocente) throws SQLException {
        Connection conn = null;
        boolean check = false;
        PreparedStatement pst;

        try {
            conn = DriverManager.getConnection(url, user, pw);
            if (conn != null) {
                System.out.println("Connected to the database \"ripetizioni\".");
            }
            Statement st = conn.createStatement();
            String sql = "SELECT * from docente WHERE idDocente = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, idDocente);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException e) {
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

        return check;
    }


    public boolean checkCourse(String course) throws SQLException {
        Connection conn = null;
        boolean check = false;
        PreparedStatement pst;

        try {
            conn = DriverManager.getConnection(url, user, pw);
            if (conn != null) {
                System.out.println("Connected to the database \"ripetizioni\".");
            }

            Statement st = conn.createStatement();
            String sql = "SELECT * FROM corso WHERE nomeCorso = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, course);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException e) {
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

        return check;

    }

    public boolean checkTutor(int idDocente) throws SQLException {
        Connection conn = null;
        boolean check = false;
        PreparedStatement pst;

        try {
            conn = DriverManager.getConnection(url, user, pw);
            if (conn != null) {
                System.out.println("Connected to the database \"ripetizioni\".");
            }

            Statement st = conn.createStatement();
            String sql = "SELECT * FROM docente WHERE idDocente = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, idDocente);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException e) {
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

        return check;

    }

    // INSERIMENTO DI UN CORSO
    public void addCourse(String courseName) throws SQLException {
        PreparedStatement pst = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");
            String sql = "INSERT INTO corso(nomeCorso) VALUES (?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, courseName);
            pst.execute();
            System.out.println("Corso aggiunto correttamente.");
        } catch (SQLException se) {
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
    public void deleteCourse(String courseName) throws SQLException {
        PreparedStatement pst = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");
            String sql = "DELETE FROM corso WHERE nomeCorso=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, courseName);
            pst.execute();
            System.out.println("Corso eliminato correttamente.");
        } catch (SQLException se) {
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
    public void addDocente(int idDocente, String nomeDocente, String cognomeDocente) throws SQLException {
        PreparedStatement pst = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");
            String sql = "INSERT INTO docente(idDocente,nomeDocente, cognomeDocente) VALUES (?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,idDocente);
            pst.setString(2, nomeDocente);
            pst.setString(3, cognomeDocente);
            pst.execute();
            System.out.println("Docente aggiunto correttamente.");
        } catch (SQLException se) {
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
    public void deleteDocente(int idDocente) throws SQLException {
        PreparedStatement pst = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");
            String sql = "DELETE FROM docente WHERE idDocente = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, idDocente);
            pst.execute();
            System.out.println("Docente eliminato correttamente.");
        } catch (SQLException se) {
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
    public void insertCorsoDocenteAssociation(String nomeCorso, int idDocente, String nomeDocente, String cognomeDocente) throws SQLException {
        PreparedStatement pstInserisciAssociazione = null, pstControllaEsistenzaCorso = null, pstInserisciCorso = null, pstControllaEsistenzaDocente = null, pstInserisciDocente = null;
        Connection conn = null;

        boolean courseExists = false, docenteExists = false, associazioneExists = false;

        try {
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");

            // Controllo esistenza corso: se non esiste, lo aggiungo alla tabella "corso"
            /*String sqlControllaEsistenzaCorso = "SELECT * FROM corso WHERE nomeCorso = ?";
            pstControllaEsistenzaCorso = conn.prepareStatement(sqlControllaEsistenzaCorso);
            pstControllaEsistenzaCorso.setString(1, nomeCorso);
            ResultSet rsCorsi = pstControllaEsistenzaCorso.executeQuery();

            while(rsCorsi.next()){
                courseExists = true;
            }
            if(!courseExists){

             */
            String sqlInserisciCorso = "INSERT INTO corso(nomeCorso) VALUES (?)";
            pstInserisciCorso = conn.prepareStatement(sqlInserisciCorso);
            pstInserisciCorso.setString(1, nomeCorso);
            pstInserisciCorso.execute();

            /*// Controllo esistenza docente: se non esiste, lo aggiungo alla tabella "docente"
            String sqlControllaEsistenzaDocente = "SELECT * FROM docente WHERE idDocente = ?";
            pstControllaEsistenzaDocente = conn.prepareStatement(sqlControllaEsistenzaDocente);
            pstControllaEsistenzaDocente.setInt(1, idDocente);
            ResultSet rsDocenti = pstControllaEsistenzaDocente.executeQuery();

            while (rsDocenti.next()) {
                docenteExists = true;
            }
            if (!docenteExists) {


                String sqlInserisciDocente = "INSERT INTO docente(nomeDocente, cognomeDocente) VALUES (?, ?)";
                pstInserisciDocente = conn.prepareStatement(sqlInserisciDocente);
                pstInserisciDocente.setString(1, nomeDocente);
                pstInserisciDocente.setString(2, cognomeDocente);
                pstInserisciDocente.execute();
            */

            // Controllo esistenza associazione corso-docente: se esiste, non devo far nulla
            String sqlControllaAssociazione = "SELECT * FROM insegna WHERE nomeCorso = ? AND idDocente = ?";
            pstControllaEsistenzaDocente = conn.prepareStatement(sqlControllaAssociazione);
            pstControllaEsistenzaDocente.setString(1, nomeCorso);
            pstControllaEsistenzaDocente.setInt(2, idDocente);
            ResultSet rsAssociazioni = pstControllaEsistenzaDocente.executeQuery();

            while (rsAssociazioni.next()) { // l'associazione esiste gia': non devo far nulla
                associazioneExists = true;
            }
            if (!associazioneExists) {
                // Inserimento connessione docente-corso nella tabella "insegna"
                String sqlInserisciAssociazione = "INSERT INTO insegna(nomeCorso, idDocente) VALUES (?,?)";
                pstInserisciAssociazione = conn.prepareStatement(sqlInserisciAssociazione);
                pstInserisciAssociazione.setString(1, nomeCorso);
                pstInserisciAssociazione.setInt(2, idDocente);
                pstInserisciAssociazione.executeUpdate();
                System.out.println("Associazione aggiunta correttamente.");
            }


        } catch (SQLException se) {
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
    public void deleteCorsoDocenteAssociation(String nomeCorso,int idDocente) throws SQLException {
        PreparedStatement pstEliminaAssociazione = null, pstOttieniIdDocente = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");

            // Eliminazione dalla tabella "insegna"
            String sqlEliminaAssociazione = "DELETE FROM insegna WHERE nomeCorso=? AND idDocente=?";
            pstEliminaAssociazione = conn.prepareStatement(sqlEliminaAssociazione);
            pstEliminaAssociazione.setString(1, nomeCorso);
            pstEliminaAssociazione.setInt(2, idDocente);
            pstEliminaAssociazione.execute();
            System.out.println("Associazione eliminata correttamente.");

        } catch (SQLException se) {
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
    public ArrayList<Docente> mostraDocentiConCorso(String nomeCorso) throws SQLException {
        // 1. Apertura della connessione
        Connection conn = null;

        // 2. Creazione lista di docenti
        ArrayList<Docente> out = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, pw);
            if (conn != null) {
                System.out.println("Connected to the database \"ripetizioni\".");
            }

            // Estrazione id dei docenti che insegnano quel corso e che sono disponibili
            String sql = "SELECT docente.nomeDocente, docente.cognomeDocente, insegna.idDocente FROM docente,insegna" +
                    " WHERE docente.idDocente=insegna.idDocente AND insegna.nomeCorso = ?";

            // Controllo disponibilita' del docente
            String sqlControllaDisponibilita = "SELECT * FROM insegna WHERE nomeCorso=? AND " +
                    "idDocente=? AND disponibilita != 0"; // != 0 significa che il docente è disponibile
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nomeCorso);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Docente d = new Docente(rs.getString("nomeDocente"), rs.getString("cognomeDocente"), rs.getInt(
                        "idDocente"));
                out.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Occhio che magari non hai tolto disponibilità dalla query");
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

    public ArrayList<Corso> mostraCorsiConDocenti(int idDocente) throws SQLException {
        // 1. Apertura della connessione
        Connection conn = null;

        // 2. Creazione lista di Corsi
        ArrayList<Corso> out = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, pw);
            if (conn != null) {
                System.out.println("Connected to the database \"ripetizioni\".");
            }
            //If(devo verificare la disponibilità prima del docente tramite la tavola prenotazioni)
            //e poi fare tutto il ramo sotto dentro l'if (Questo lo faccio nella servlet, faccio due chiamate al dato

            // Estrazione id dei docenti che insegnano quel corso e che sono disponibili
            String sql = "SELECT insegna.nomeCorso FROM insegna" +
                    " WHERE insegna.idDocente = ?"; //mi serve l'idDocente per lavorare con omonimi

            // Controllo disponibilita' del docente
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idDocente);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Corso d = new Corso(rs.getString("nomeCorso"));
                out.add(d);
            }
        } catch (SQLException e) {
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

    public boolean verificaDisponibilita(String slotlezione, int idDocente) throws SQLException {
        PreparedStatement pst = null;
        Connection conn = null;


        try {
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");
            if (conn != null)
                System.out.println("Connessione a DB eseguita");
            String sql = "SELECT * FROM prenotazioni WHERE slot = ? AND idDocente = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, slotlezione);
            pst.setInt(2, idDocente);
            ResultSet rs = pst.executeQuery();
            //devo verificare che in prenotazione il professore con quell'idDocente non sia impegnato nello slot
            //richiesto
            if (rs == null) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection is now closed.");
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return false;
    }

    public void prenotazioneEffettuata(int idPrenotazione) throws SQLException {

        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");
            String sql = "UPDATE prenotazione SET stato = ? WHERE idPrenotazione = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, 1);
            pst.setInt(2, idPrenotazione);
            pst.executeUpdate();

        } catch (SQLException e) {
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


    }


    public ArrayList<Prenotazione> retrievePrenotazioniUtente(int idUtente) throws SQLException {
        ArrayList<Prenotazione> out = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");
            String sql = "SELECT p.*, nomeDocente, cognomeDocente FROM prenotazione p JOIN docente d ON (p.idDocente = d.idDocente) WHERE idUtente = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, idUtente);
            ResultSet rs = pst.executeQuery();


            while (rs.next()) {
                Prenotazione pren = new Prenotazione(rs.getInt("idPrenotazione"), rs.getString("nomeCorso"), rs.getInt(
                        "idDocente"), rs.getString("nomeDocente"), rs.getString("cognomeDocente"), rs.getInt("idUtente"), rs.getString("slot"), rs.getString("stato"));
                out.add(pren);
            }
        } catch (SQLException e) {
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


    public ArrayList<Prenotazione> retrievePrenotazioni() throws SQLException {

        ArrayList<Prenotazione> out = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.*, nomeDocente, cognomeDocente FROM prenotazione p  JOIN docente d ON (p.idDocente = d.idDocente)");
            while (rs.next()) {
                Prenotazione pren = new Prenotazione(rs.getInt("idPrenotazione"), rs.getString("nomeCorso"), rs.getInt(
                        "idDocente"), rs.getString("nomeDocente"), rs.getString("cognomeDocente"), rs.getInt("idUtente"), rs.getString("slot"), rs.getString("stato"));
                out.add(pren);
            }
        } catch (SQLException e) {
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

    public boolean isDisponibile(String slot, int idDocente) throws SQLException {
        PreparedStatement pstControllaDisponibilita = null;
        boolean disponibilita = true;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");


            // Controllo se quello slot è già occupato

            String sqlControllaDisponibilita = "SELECT prenotazione.slot FROM prenotazione WHERE slot = ? AND idDocente = ? AND stato = ?";
            pstControllaDisponibilita = conn.prepareStatement(sqlControllaDisponibilita);
            pstControllaDisponibilita.setString(1, slot);
            pstControllaDisponibilita.setInt(2, idDocente);
            pstControllaDisponibilita.setString(3, "attiva");
            ResultSet rsDisponibilita = pstControllaDisponibilita.executeQuery();

            while (rsDisponibilita.next()) {
                disponibilita = false;
            }
        } catch (SQLException e) {
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
        return disponibilita;
    }

    // PRENOTAZIONE DI UNA RIPETIZIONE
    public void prenotaRipetizione(String nomeCorso, int idDocente, int idUtente, String slot) throws SQLException {
        PreparedStatement pstControllaDisponibilita = null, pstInserisciPrenotazione = null, pstRimuoviDisponibilita =
                null;
        Connection conn = null;
        boolean isDisponibile = true;
        try {
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");

            String sqlInserisciPrenotazione = "INSERT INTO prenotazione(nomeCorso, idDocente, idUtente, slot, stato) " +
                    "VALUES (?, ?, ?, ?,?)";
            pstInserisciPrenotazione = conn.prepareStatement(sqlInserisciPrenotazione);
            pstInserisciPrenotazione.setString(1, nomeCorso);
            pstInserisciPrenotazione.setInt(2, idDocente);
            pstInserisciPrenotazione.setInt(3, idUtente);
            pstInserisciPrenotazione.setString(4, slot);
            pstInserisciPrenotazione.setInt(5, 0);
            pstInserisciPrenotazione.execute();
            System.out.println("Prenotazione aggiunta correttamente.");

        } catch (SQLException se) {
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

    public void deletePrenotazione(int idPrenotazione) throws SQLException {
        PreparedStatement pstEliminaPrenotazione = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to the database \"ripetizioni\".");

            // Eliminazione dalla tabella "prenotazione"
            String sqlEliminaAssociazione = "UPDATE prenotazione SET stato = ? WHERE idPrenotazione = ?";
            pstEliminaPrenotazione = conn.prepareStatement(sqlEliminaAssociazione);
            pstEliminaPrenotazione.setInt(1, -1);
            pstEliminaPrenotazione.setInt(2, idPrenotazione);

            pstEliminaPrenotazione.execute();
            System.out.println("Prenotazione eliminata correttamente.");

        } catch (SQLException se) {
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
