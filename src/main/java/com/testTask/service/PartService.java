package com.testTask.service;

import com.testTask.model.Part;
import com.testTask.model.PartFilter;

import java.util.List;

public interface PartService {
   void save(Part part);

   void update(Part part);

   void delete(Part part);

   List<Part> list(PartFilter filter, String query);

   Part byId(long id);
}
