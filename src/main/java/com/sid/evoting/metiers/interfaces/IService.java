package com.sid.evoting.metiers.interfaces;

import com.sid.evoting.entities.Service;

import java.util.List;

public interface IService {
    public List<Service> listService();
    public Service saveService(Service service);
    public Service getService(Long id);
}
