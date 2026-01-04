package org.template.common.tables;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseTable {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "status_db")
    private Integer statusDb;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
        updatedDate = new Date();

        if (Objects.isNull(statusDb)) {
            this.statusDb = 1;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getStatusDb() {
        return statusDb;
    }

    public void setStatusDb(Integer statusDb) {
        this.statusDb = statusDb;
    }
}

