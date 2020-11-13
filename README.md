# TWEB
Progetto di TWEB aa 2020/2021


## Istruzioni per clonare correttamente la repo in modo da poterla utilizzare su IntelliJ

1. Creare un nuovo progetto (webapp, servlet) su IntelliJ con lo stesso nome della repo (in questo caso "TWEB", in maiuscolo)
2. Settare Tomcat (locale), assicurandosi di scegliere come artefatto il "......war exploded"
3. Chiudere IntelliJ
4. Clonare la repo in una cartella di nostra scelta
      ```bash
      git clone https://github.com/AdrianaLongo/TWEB.git
      ```
   Questo creerà una nuova cartella "TWEB"
5. Spostare in questa nuova cartella TWEB tutte le sottocartelle del progetto creato al punto 1 TRANNE SRC
6. Eliminare la vecchia cartella del progetto creato al punto 1
7. Aprire in IntelliJ la nuova cartella in cui abbiamo la repo
8. Caricare la libreria JDBC

## Librerie da importare
mysql-connector-java

## Problemi frequenti
### Error 404
Causa: non viene trovata la servlet
Soluzione: controllare che nei punti in cui viene chiamata la servlet (ad esempio dall'index.html) sia impsotato l'URL corretto (in particoalre, controllare che il nome della servlet sia giusto)

### "CLassNotFoundException", "NoClassDefFoundError"
Project structure > Artifacts > Selezionare ".....war exploded" nella seconda colonna > click dx su "mysql-connector-java" in "Available Elements" (colonna di dx) e selezionare "put into /WEB-INF/lib" > Apply > OK
