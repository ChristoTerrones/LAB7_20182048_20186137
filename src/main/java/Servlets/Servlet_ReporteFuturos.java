package Servlets;
import Bean.reporte;
import Daos.reporte_futurosDao;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Servlet_ReporteFuturos", value = "/Servlet_ReporteFuturos")
public class Servlet_ReporteFuturos extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reporte_futurosDao reporteFuturosDao = new reporte_futurosDao();
        request.setAttribute("listaToursporCiudad", reporteFuturosDao.obtenerReporte());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("reporte_futuros.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
