package utils;

import javax.servlet.http.Cookie;


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

    public static void destroyCookies (Cookie cook[]) {
        if(cook != null){
            for(Cookie cookie: cook) {
                cookie.setMaxAge(0);
            }
        }
    }


}
