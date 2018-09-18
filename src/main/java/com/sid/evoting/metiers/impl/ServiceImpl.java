package com.sid.evoting.metiers.impl;

import com.sid.evoting.dao.ServiceRepository;
import com.sid.evoting.entities.Service;
import com.sid.evoting.metiers.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements IService {
    @Autowired
    private ServiceRepository ser;
    @Override
    public List<Service> listService() {
        return ser.findAll();
    }

    @Override
    public Service saveService(Service service) {
        return ser.save(service);
    }

    @Override
    public Service getService(Long id) {
        return ser.getOne(id);
    }
}
