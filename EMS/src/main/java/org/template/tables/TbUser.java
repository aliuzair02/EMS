package org.template.tables;

import jakarta.persistence.*;
import org.template.common.tables.BaseTable;

@Entity
@Table(name = "users")
public class TbUser extends BaseTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_user_id")
    private Long pkUserId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "fk_profile_id",
            referencedColumnName = "pk_profile_id"
    )
    private TbProfile fkProfileId;

    public Long getPkUserId() {
        return pkUserId;
    }

    public void setPkUserId(Long pkUserId) {
        this.pkUserId = pkUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TbProfile getFkProfileId() {
        return fkProfileId;
    }

    public void setFkProfileId(TbProfile fkProfileId) {
        this.fkProfileId = fkProfileId;
    }
}
