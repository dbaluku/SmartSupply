package org.smartsupply.model.admin;

import org.apache.commons.lang.StringUtils;
import org.smartsupply.model.BaseData;
import org.smartsupply.model.enums.OrgUnitType;
import org.smartsupply.model.enums.RecordStatus;
import org.smartsupply.model.product.Stock;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.smartsupply.model.util.MyStringUtil.isBlankOrEmpty;


@Entity
@Table(name = "org_unit")
public class Branch extends BaseData implements Comparable<Branch> {

    private static final long serialVersionUID = -2636551734031785042L;

    public String name, abbreviation;
    private OrgUnitType type;
    private User head, asstHead, agHead;
    private JobTitle headTitle, asstHeadTitle, agHeadTitle;
    private Branch parent;
    private List<Branch> childUnits;
    private String location;
    private Date date_created;
    private Stock stock;

    public Branch() {

    }

    public Branch(String name, String abbreviation, OrgUnitType type, User head,
                  JobTitle headTitle, Branch parent, String location, Date date_created, Stock stock) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.type = type;
        this.head = head;
        this.headTitle = headTitle;
        this.parent = parent;
        this.location = location;
        this.date_created = date_created;
        this.stock = stock;
    }

    public Branch(String name, String abbreviation, Stock stock) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.stock=stock;
    }

    public Branch(String name, String abbreviation, OrgUnitType type,Stock stock) {
        this(name, abbreviation,stock);
        this.type = type;
    }

    public Branch(String id, RecordStatus recordStatus, String name, String abbreviation, OrgUnitType type
            ,Stock stock,Branch parent) {
        this(name, abbreviation, type,stock);
        this.setId(id);
        this.setRecordStatus(recordStatus);
        this.parent = parent;
    }

    public Branch(String id) {
        super(id);
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHead(User head) {
        this.head = head;
    }

    @ManyToOne
    @JoinColumn(name = "head_id", nullable = true)
    public User getHead() {
        return head;
    }

    public void setAsstHead(User asstHead) {
        this.asstHead = asstHead;
    }

    @ManyToOne
    @JoinColumn(name = "asst_head_id", nullable = true)
    public User getAsstHead() {
        return asstHead;
    }

    @ManyToOne
    @JoinColumn(name = "ag_head_id", nullable = true)
    public User getAgHead() {
        return agHead;
    }

    public void setAgHead(User agHead) {
        this.agHead = agHead;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Column(name = "abbreviation", nullable = false)
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setHeadTitle(JobTitle headTitle) {
        this.headTitle = headTitle;
    }

    @ManyToOne
    @JoinColumn(name = "head_title_id", nullable = true)
    public JobTitle getHeadTitle() {
        return headTitle;
    }

    public void setAsstHeadTitle(JobTitle asstHeadTitle) {
        this.asstHeadTitle = asstHeadTitle;
    }

    @ManyToOne
    @JoinColumn(name = "asst_head_title_id", nullable = true)
    public JobTitle getAsstHeadTitle() {
        return asstHeadTitle;
    }

    @ManyToOne
    @JoinColumn(name = "ag_head_title_id", nullable = true)
    public JobTitle getAgHeadTitle() {
        return agHeadTitle;
    }

    public void setAgHeadTitle(JobTitle agHeadTitle) {
        this.agHeadTitle = agHeadTitle;
    }

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    public Branch getParent() {
        return parent;
    }

    public void setParent(Branch parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent", cascade = {CascadeType.ALL}, orphanRemoval = false)
    public List<Branch> getChildUnits() {
        return childUnits;
    }

    public void setChildUnits(List<Branch> childUnits) {
        this.childUnits = childUnits;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "org_unit_type", nullable = true)
    public OrgUnitType getType() {
        return type;
    }

    public void setType(OrgUnitType type) {
        this.type = type;
    }

    @Transient
    public String getChildUnitsString() {
        String returnString = "";

        if (this.childUnits != null && this.getChildUnits().size() > 1) {
            for (int i = 0; i < this.childUnits.size() && i < 3; i++) {
                if (StringUtils.isBlank(returnString))
                    returnString += this.childUnits.get(i).getName();
                else
                    returnString += ", " + this.childUnits.get(i).getName();
            }
            if (this.childUnits.size() > 3) {
                int x = this.childUnits.size() - 3;
                returnString += ", and " + x + " more";
            }
        } else
            returnString = "--";

        return returnString;
    }

    @Override
    public int compareTo(Branch o) {
        return this.getName().compareToIgnoreCase(o.getName());
    }

    public static void copy(Branch dest, Branch source) {
        dest.name = source.name;
        dest.abbreviation = source.abbreviation;
        dest.type = source.type;
        dest.head = source.head;
        dest.asstHead = source.asstHead;
        dest.agHead = source.agHead;
        dest.headTitle = source.headTitle;
        dest.asstHeadTitle = source.asstHeadTitle;
        dest.agHeadTitle = source.agHeadTitle;
        dest.location=source.location;
        BaseData.copy(dest, source);
    }

    @Override
    public <T extends BaseData> void copyFrom(T source) {
        copy(this, (Branch) source);
    }

    private boolean hasParent() {
        return parent != null;
    }

    @Transient
    public String getHeirarchyStr(String str) {
        if (hasParent()) {
            str += isBlankOrEmpty(str) ? " in the " + name : ", " + name;
            return parent.getHeirarchyStr(str);
        } else {
            str += isBlankOrEmpty(str) ? " in " + name : ", " + name;
            return str;
        }
    }

    @Transient
    public List<User> getHeadsList() {
        List<User> list = new ArrayList<>();
        if (this.getHead() != null)
            list.add(this.getHead());
        if (this.getAsstHead() != null)
            list.add(this.getAsstHead());
        if (this.getAgHead() != null)
            list.add(this.getAgHead());

        return list;
    }

    @Transient
    public List<JobTitle> getHeadsTitleList() {
        List<JobTitle> list = new ArrayList<>();
        if (this.getHeadTitle() != null)
            list.add(this.getHeadTitle());
        if (this.getAsstHeadTitle() != null)
            list.add(this.getAsstHeadTitle());
        if (this.getAgHeadTitle() != null)
            list.add(this.getAgHeadTitle());

        return list;
    }

    @Column(name = "location", nullable = true)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "date_created", nullable = true)
    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    @OneToOne
    @JoinColumn(name = "stock_id",nullable = false)
    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
