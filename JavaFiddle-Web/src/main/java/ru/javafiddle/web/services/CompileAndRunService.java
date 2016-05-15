package ru.javafiddle.web.services;

import ru.javafiddle.core.ejb.FileBean;
import ru.javafiddle.core.ejb.CompileAndRunBean;

import ru.javafiddle.web.models.FileJF;
import ru.javafiddle.web.models.ProjectJF;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * It's a bad practise in RESTful API to name links with verbs
 * But I have no ideas how to afoid this
 */
@Path("/")
public class CompileAndRunService {

    @EJB
    CompileAndRunBean compileAndRunBean;

    @EJB
    FileBean filesBean;

    @Path("/compile/{projectHash}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response compile(@PathParam("projectHash") String projectHash) {
        //ExecutionResult must contain Streams and may be project files
        //becouse we cun write in file

        String stdout = null;
        try {
            stdout = compileAndRunBean.compile(projectHash);
            return Response.ok(stdout).build();
        } catch (Exception e) {
            return Response.serverError().entity("Error during compilation process!").build();
        }
    }

    @Path("/run/{projectHash}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response run(@PathParam("projectHash") String projectHash) {
        //ExecutionResult must contain Streams and may be project files
        //becouse we cun write in file

        String stdout = compileAndRunBean.run(projectHash);

        if (stdout == null || stdout.isEmpty()) {
            return Response.serverError().entity("You can not run project until you compiled it!").build();
        }
        return Response.ok(stdout).build();
    }

//    @Path("/compileAndRun")
//    @GET
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response compileAndRun(ProjectJF Project) {
//
//        try {
//            String projectHash = project.getProjectHash();
//            CompileAndRunResult compileAndRunResult = compileAndRunBean.compileAndRun(Project);
//            return Response.ok().entity(compileAndRunResult).build();
//        }catch (Exception e) {
//            return Response.serverError().build();
//        }
//    }

}
