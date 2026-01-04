package org.template.tables;

import jakarta.persistence.*;
import org.template.common.tables.BaseTable;

import java.util.Date;

@Entity
@Table(name = "profiles")
public class TbProfile extends BaseTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_profile_id")
    private Long pkProfileId;

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "id_no")
    private String idNo;

    @Column(name = "id_type")
    private String idType;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    private String email;

    public Long getPkProfileId() {
        return pkProfileId;
    }

    public void setPkProfileId(Long pkProfileId) {
        this.pkProfileId = pkProfileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
