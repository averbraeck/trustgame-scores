package org.transsonic.trustgame.scores;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public final class SessionUtils {

    private SessionUtils() {
        // utility class
    }

    public static ScoreData getData(final HttpSession session) {
        return (ScoreData) session.getAttribute("scoreData");
    }

    public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("userId") == null) {
            response.sendRedirect("jsp/scores/login.jsp");
            return false;
        }
        @SuppressWarnings("unchecked")
        Map<Integer, String> idSessionMap = (Map<Integer, String>) request.getServletContext()
                .getAttribute("idSessionMap");
        String storedSessionId = idSessionMap.get(request.getSession().getAttribute("userId"));
        if (!request.getSession().getId().equals(storedSessionId)) {
            response.sendRedirect("jsp/scores/login-session.jsp"); // TODO: session management
            return false;
        }
        return true;
    }

}
