package scpexsto.model;

import java.util.List;
import java.util.Map;

public interface BaseService<E> {
    
    public void create(E e);
    
    public void update(E e);
    
    public List<E> readAll();
    
    public List<E> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc);
}
