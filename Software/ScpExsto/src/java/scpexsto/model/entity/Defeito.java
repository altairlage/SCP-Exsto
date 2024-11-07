package scpexsto.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DEFEITO")
public class Defeito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private long id;

    @Column(name = "LOC1", length = 10)
    private String loc1;

    @Column(name = "LOC2", length = 10)
    private String loc2;

    @Column(name = "LOC3", length = 10)
    private String loc3;

    @Column(name = "LOC4", length = 10)
    private String loc4;

    @Column(name = "LOC5", length = 10)
    private String loc5;

    @Column(name = "OBSERVACOES", length = 255)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "CLASSE_DEFEITO_FK")
    private ClasseDefeitos classeDefeito;
    
    @ManyToOne
    @JoinColumn(name = "CLASSE_SOLUCAO_FK")
    private ClasseSolucao classeSolucao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoc1() {
        return loc1;
    }

    public void setLoc1(String loc1) {
        this.loc1 = loc1;
    }

    public String getLoc2() {
        return loc2;
    }

    public void setLoc2(String loc2) {
        this.loc2 = loc2;
    }

    public String getLoc3() {
        return loc3;
    }

    public void setLoc3(String loc3) {
        this.loc3 = loc3;
    }

    public String getLoc4() {
        return loc4;
    }

    public void setLoc4(String loc4) {
        this.loc4 = loc4;
    }

    public String getLoc5() {
        return loc5;
    }

    public void setLoc5(String loc5) {
        this.loc5 = loc5;
    }

    public ClasseDefeitos getClasseDefeito() {
        return classeDefeito;
    }

    public void setClasseDefeito(ClasseDefeitos classe) {
        this.classeDefeito = classe;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 71 * hash + (this.loc1 != null ? this.loc1.hashCode() : 0);
        hash = 71 * hash + (this.loc2 != null ? this.loc2.hashCode() : 0);
        hash = 71 * hash + (this.loc3 != null ? this.loc3.hashCode() : 0);
        hash = 71 * hash + (this.loc4 != null ? this.loc4.hashCode() : 0);
        hash = 71 * hash + (this.loc5 != null ? this.loc5.hashCode() : 0);
        hash = 71 * hash + (this.observacoes != null ? this.observacoes.hashCode() : 0);
        hash = 71 * hash + (this.classeDefeito != null ? this.classeDefeito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Defeito other = (Defeito) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.loc1 == null) ? (other.loc1 != null) : !this.loc1.equals(other.loc1)) {
            return false;
        }
        if ((this.loc2 == null) ? (other.loc2 != null) : !this.loc2.equals(other.loc2)) {
            return false;
        }
        if ((this.loc3 == null) ? (other.loc3 != null) : !this.loc3.equals(other.loc3)) {
            return false;
        }
        if ((this.loc4 == null) ? (other.loc4 != null) : !this.loc4.equals(other.loc4)) {
            return false;
        }
        if ((this.loc5 == null) ? (other.loc5 != null) : !this.loc5.equals(other.loc5)) {
            return false;
        }
        if ((this.observacoes == null) ? (other.observacoes != null) : !this.observacoes.equals(other.observacoes)) {
            return false;
        }
        if (this.classeDefeito != other.classeDefeito && (this.classeDefeito == null || !this.classeDefeito.equals(other.classeDefeito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Defeito{" + "id=" + id + ", loc1=" + loc1 + ", loc2=" + loc2 + ", loc3=" + loc3 + ", loc4=" + loc4 + ", loc5=" + loc5 + ", observacoes=" + observacoes + ", classe=" + classeDefeito + '}';
    }

    public ClasseSolucao getClasseSolucao() {
        return classeSolucao;
    }

    public void setClasseSolucao(ClasseSolucao classeSolucao) {
        this.classeSolucao = classeSolucao;
    }
}
