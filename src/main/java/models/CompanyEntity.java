package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity for Company table of Database
 */
@Entity
@Table(name = "company", schema = "public", catalog = "st2eedb")
@NamedQueries(
        @NamedQuery(name = "CompanyEntity.updateCompany", query = "update CompanyEntity set name = :name, address = :address Where companyId = :companyId")
)
public class CompanyEntity implements InterfaceEntity, Serializable {
    // Attributes
    @Id
    @Column(name = "company_id", nullable = false, columnDefinition="uuid")
    private UUID companyId;

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;

    @Basic
    @Column(name = "address", nullable = true, length = -1)
    private String address;

    // Getters and Setters
    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEntity that = (CompanyEntity) o;
        return Objects.equals(companyId, that.companyId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, name, address);
    }
}
