package ers.rembursement;

import javax.servlet.http.HttpServlet;

public class ReimServlet extends HttpServlet {
/*
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        ReimService reimservice = new ReimService();
        HttpSession session = request.getSession();

        User Loggedin = (User) session.getAttribute("user");

        String event = request.getParameter("referred");
        String time = request.getParameter("timedate");
        String location = request.getParameter("location");
        Double cost = Double.valueOf(request.getParameter("cost"));
        String proof = request.getParameter("proof");
        String descrition = request.getParameter("description");
        String justification = request.getParameter("justification");


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = new Date();

        String submissiontime = dateFormat.format(date);
        String email = Loggedin.getEmail();

        switch(event){
            case "course":
                cost = cost * .8;
                break;
            case "seminar":
                cost = cost * .6;
                break;
            case "prep":
                cost = cost * .75;
                break;
            case "cert":
                cost = cost * 1;
                break;
            case "technical":
                cost = cost * .9;
                break;
            case "other":
                cost = cost * .3;
                break;
        }


        Reim r = new Reim();

        r.setEvent(event);
        r.setDateofe(time);
        r.setLocation(location);
        r.setCost(cost);
        r.setProof(proof);
        r.setDescription(descrition);
        r.setJustification(justification);
        r.setNowtime(submissiontime);
        r.setStatus("Pending");
        r.setEmail(email);

        reimservice.add(r);


        session.setAttribute("reim", r);
        if (Loggedin.getRole().equalsIgnoreCase("manager")) {
            response.sendRedirect("home2.html");
        } else {
            response.sendRedirect("home1.html");


        }
    }*/
}
