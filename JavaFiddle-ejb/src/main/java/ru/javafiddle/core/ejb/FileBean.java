package ru.javafiddle.core.ejb;

import javax.ejb.EJB;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ru.javafiddle.jpa.entity.File;
import ru.javafiddle.jpa.entity.Hash;
import ru.javafiddle.jpa.entity.Type;
import ru.javafiddle.jpa.entity.Project;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Stateless

public class FileBean {

    @PersistenceContext(name = "JFPersistenceUnit")
    EntityManager em;

    @EJB
    TypeBean typeBean;

    public FileBean() {
    }


    public List<File> getProjectFiles(String projectHash) {

        TypedQuery<Hash> query = em.createQuery("SELECT h FROM Hash h WHERE h.hash=:projectHash", Hash.class);
        Hash h = query.setParameter("projectHash",projectHash).getSingleResult();

        return h.getProject().getFiles();

    }

    /**
     * Creates empty file in project
     * @param fileName
     * @param typeName
     * @param path
     */
    public void createEmptyFile(String projectHash, String fileName, String typeName, String path) {

        TypedQuery<Hash> query = em.createQuery("SELECT h FROM Hash h WHERE h.hash=:projectHash", Hash.class);
        Hash h = query.setParameter("projectHash",projectHash).getSingleResult();

        File file = new File();
        file.setFileName(fileName);
        file.setPath(path);
        file.setProject(h.getProject());
        file.setType(typeBean.getType(typeName));
        file.setData(new byte[]{});
        em.persist(file);

    }

    public void createFile(File file) {
        //!TODO validation check
        em.persist(file);
    }

    public File updateFile(int fileId, String fileName, byte[] data, String fileType, String pathToFile) {

        File file = getFile(fileId);

        Type type = typeBean.getType(fileType);
        file.setFileName(fileName);
        file.setData(data);
        file.setType(type);
        file.setPath(pathToFile);

        em.persist(file);

        return file;
    }

    public void deleteFile(String projectHash, int fileId) {

        File file = getFile(fileId);

        em.getTransaction().begin();
        em.remove(file);
        em.getTransaction().commit();


    }

    public File getFile(int fileId) {

        File file = (File) em.createQuery("SELECT f FROM File f where f.fileId =:fileid")
                .setParameter("fileid", fileId)
                .getSingleResult();
        return file;
    }
}

