package org.template.tables;

import jakarta.persistence.*;
import org.template.common.tables.BaseTable;

@Entity
@Table(name = "userroles")
public class TbUserRole extends BaseTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_userrole_id")
    private Long pkUserRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "fk_user_id",
            referencedColumnName = "pk_user_id"
    )
    private TbUser fkUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "fk_role_id",
            referencedColumnName = "pk_role_id"
    )
    private TbRole fkRoleId;

    public Long getPkUserRoleId() {
        return pkUserRoleId;
    }

    public void setPkUserRoleId(Long pkUserRoleId) {
        this.pkUserRoleId = pkUserRoleId;
    }

    public TbUser getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(TbUser fkUserId) {
        this.fkUserId = fkUserId;
    }

    public TbRole getFkRoleId() {
        return fkRoleId;
    }

    public void setFkRoleId(TbRole fkRoleId) {
        this.fkRoleId = fkRoleId;
    }
}
