package com.testTask.service;

import com.testTask.dao.PartDao;
import com.testTask.model.Part;
import com.testTask.model.PartFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartServiceImpl implements PartService {
    @Autowired
    private PartDao partDao;

    @Override
    public void save(Part part) {
        partDao.save(part);
    }

    @Override
    public void update(Part part) {
        partDao.update(part);
    }

    @Override
    public void delete(Part part) {
        partDao.delete(part);
    }

    @Override
    public List<Part> list(PartFilter filter, String query) {
        return partDao.list(filter, query);
    }

    @Override
    public Part byId(long id) {
        return partDao.byId(id);
    }
}
