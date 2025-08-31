package edu.dnu.fpm.pz.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.dnu.fpm.pz.dao.EmployerDAO;
import edu.dnu.fpm.pz.dao.VacancyDAO;
import edu.dnu.fpm.pz.entity.Employer;
import edu.dnu.fpm.pz.entity.Vacancy;
import edu.dnu.fpm.pz.myexceptions.MyException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
public class VacancyController extends HttpServlet {
    private VacancyDAO vacancyDAO = new VacancyDAO();
    private Gson gson = new Gson();

    private String getStringFromRequest(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        return reader.lines()
                .collect(Collectors.joining(System.lineSeparator()));
    }
    private void writeJson(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json");
        String result = gson.toJson(obj);
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(HttpServletResponse.SC_OK);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if(pathInfo == null || pathInfo.equals("/")){
            try {
                List<Vacancy> vacancies = vacancyDAO.read();
                writeJson(resp, vacancies);
            } catch (MyException e) {
                e.toLog();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            return;
        }

        String[] params = pathInfo.split("/");
        if(params.length != 2){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number of parameters");
            return;
        }

        try {
            int entityId = Integer.parseInt(params[1]);
            Vacancy vacancy = vacancyDAO.read(entityId);
            if(vacancy == null){
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Employer not found");
            }else {
                writeJson(resp, vacancy);
            }
        } catch (NumberFormatException ex){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Illegal parameter value: " + params[1]);
        } catch (MyException e) {
            e.toLog();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){
            String request = getStringFromRequest(req);
            Vacancy vacancy = null;
            List<Vacancy> vacancies = null;

            if(request.contains("[") && request.contains("]")){
                vacancies = gson.fromJson(request, new TypeToken<List<Vacancy>>() {}.getType());
            }else{
                vacancy = gson.fromJson(request, Vacancy.class);
            }
            if(vacancy != null){
                try {
                    vacancyDAO.create(vacancy);
                    writeJson(resp, vacancy);
                } catch (MyException e) {
                    e.toLog();
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
                }
            }
            if(vacancies != null){
                try {
                    vacancyDAO.create(vacancies);
                    writeJson(resp, vacancies);
                } catch (MyException e) {
                    e.toLog();
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
                }
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")) {
            String request = getStringFromRequest(req);
            Vacancy vacancy = null;
            List<Vacancy> vacancies = null;

            if (request.contains("[") && request.contains("]")) {
                vacancies = gson.fromJson(request, new TypeToken<List<Vacancy>>() {
                }.getType());
            } else {
                vacancy = gson.fromJson(request, Vacancy.class);
            }
            if (vacancy != null) {
                try {
                    vacancyDAO.update(vacancy);
                    writeJson(resp, vacancy);
                } catch (MyException e) {
                    e.toLog();
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
                }
            }
            if (vacancies != null) {
                try {
                    vacancyDAO.update(vacancies);
                    writeJson(resp, vacancies);
                } catch (MyException e) {
                    e.toLog();
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
                }
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if(pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] params = pathInfo.split("/");
        if(params.length != 2){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number of parameters");
            return;
        }

        try {
            int entityId = Integer.parseInt(params[1]);
            vacancyDAO.delete(entityId);
        }catch (NumberFormatException ex){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Illegal parameter value: " + params[1]);
        } catch (MyException e) {
            e.toLog();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
