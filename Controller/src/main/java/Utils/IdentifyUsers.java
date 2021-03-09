package utils;

import javax.servlet.http.Cookie;
//import java.net.http.HttpRequest;

public class IdentifyUsers {

    public static String userRole;
    public static String sessionId;
    public static String nome;
    public static String cognome;

    public static boolean identifyIdCookie(Cookie cook[]) {
        if (cook != null) {
            for (Cookie cookie : cook) {
                if (cookie.getValue().equals(IdentifyUsers.sessionId))
                    return true;
            }
        }
        return false;
    }

    public static boolean identifyRoleCookie (Cookie cook[]) {
        if (cook != null) {
            for (Cookie cookie : cook) {
                if (cookie.getValue().equals("admin"))
                    return true;
            }
        }
        return false;

    }

    public static String getUserId (Cookie cook[]) {
        if(cook != null) {
            for (Cookie cookie : cook) {
                if (cookie.getName().equals("idUtente")) {
                    return cookie.getValue();
                }
            }
        }
        return "-1";
    }

//    TODO: togliere il commento (capire problema libreria)
//    public static void destroyCookies (Cookie cook[], HttpRequest request) {
//        if(cook != null){
//            for(Cookie cookie: cook) {
//                cookie.setMaxAge(0);
//            }
//        }
//    }



}
