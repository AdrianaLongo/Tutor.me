package com.example.progetto_android.conndata;

import android.content.Context;

import com.example.progetto_android.R;
import com.example.progetto_android.conndata.repositories.BookRepository;
import com.example.progetto_android.conndata.repositories.CourseRepository;
import com.example.progetto_android.conndata.repositories.LoginRepository;
import com.example.progetto_android.conndata.repositories.ProfRepository;
import com.example.progetto_android.conndata.repositories.SlotRepository;
import com.example.progetto_android.conndata.utils.HttpRequest;

public class Connector {

    private final LoginRepository loginRep;
    private final ProfRepository profRep;
    private final SlotRepository slotRep;
    private final CourseRepository courseRep;
    private static Connector instance;
    private final BookRepository bookRep;

    private Connector(Context appContext) {
        String ip = appContext.getString(R.string.ip);
        String port = appContext.getString(R.string.port);
        String context = appContext.getString(R.string.context);
        String servlet = appContext.getString(R.string.servlet);

        HttpRequest http = new HttpRequest(ip, port, context,servlet);

        //istanzio repository così da evocare quella giusta per ogni chiamata
        loginRep = new LoginRepository(http);
        courseRep = new CourseRepository(http);
        profRep = new ProfRepository(http);
        slotRep = new SlotRepository(http);
        bookRep = new BookRepository(http);
    }

    public LoginRepository getLoginRep() {
        return loginRep;
    }

    public CourseRepository getCourseRep() {
        return courseRep;
    }

    public ProfRepository getProfRep() {
        return profRep;
    }

    public SlotRepository getSlotRep() {
        return slotRep;
    }

    public BookRepository getBookRep() {
        return bookRep;
    }

    //creo l'istanza di un connector, prima controllo che non sia già istanziato
    public static synchronized Connector getInstance(Context appContext) {
        if (instance == null) {
            instance = new Connector(appContext);
        }
        return instance;
    }

}
