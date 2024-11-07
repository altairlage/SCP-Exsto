package scpexsto.model.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import scpexsto.model.entity.EquipamentoMedicao;

public class EquipamentoMedicaoDao {

    private EntityManager em = null;

    public EquipamentoMedicaoDao(EntityManager em) {
        this.em = em;
    }

    public void create(EquipamentoMedicao equip) {
        this.em.persist(equip);
    }

    public EquipamentoMedicao readByPatrimonio(String patrimonio) {
        return em.find(EquipamentoMedicao.class, patrimonio);
    }

    public void update(EquipamentoMedicao equip) {
        em.merge(equip);
    }

    public void Delete(String patrimonio) {
        em.remove(this.readByPatrimonio(patrimonio));
    }
    
    public List<EquipamentoMedicao> readAll(){
        return (readByCriteria(new HashMap<String, Object>(), "patrimonio", true));
    }

    public List<EquipamentoMedicao> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {

        List<EquipamentoMedicao> equips = null;

        String jpql = "select e from EquipamentoMedicao e where 1=1";

        //CRITERIA
        //setor
        if (criteria.containsKey("setor")) {
            String setor = (String) criteria.get("setor");

            jpql += " and lower(e.setor) like lower('" + setor + "')";
        }

        //data de venciomento maior que
        if (criteria.containsKey("DataVencimentoMaior")) {
            Date data = (Date) criteria.get("DataVencimentoMaior");

            Timestamp ts = new Timestamp(data.getTime());

            jpql += "and e.dataVencimento >= '" + ts.toString() + "'";
        }

        //setor
        if (criteria.containsKey("status")) {
            String status = (String) criteria.get("status");

            jpql += " and lower(e.status) like lower('" + status + "')";
        }

        //ordenação
        jpql += " order by e." + orderField;
        if (!asc) {
            jpql += " desc";
        }

        TypedQuery<EquipamentoMedicao> query = em.createQuery(jpql, EquipamentoMedicao.class);
        equips = query.getResultList();

        return equips;
    }
}
