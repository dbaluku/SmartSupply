package org.smartsupply.model.search;

import com.jarcommons.StringUtil;
import org.smartsupply.model.admin.Branch;
import org.smartsupply.model.enums.OrgUnitType;
import org.smartsupply.model.util.MyStringUtil;

public class OrgUnitSearchParams extends BaseSearchParams{
    private String name;
    private OrgUnitType orgUnitType;
    private Branch parent;

    public OrgUnitSearchParams() {

    }

    public OrgUnitSearchParams(String name, boolean isName) {
        if (isName) {
            this.name = name;
        } else {
            this.setIds(name);
        }
    }

    public OrgUnitSearchParams(OrgUnitType type) {
        this.orgUnitType = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasName() {
        return MyStringUtil.isNotBlankOrEmpty(name);
    }

    public OrgUnitType getOrgUnitType() {
        return orgUnitType;
    }

    public void setOrgUnitType(OrgUnitType orgUnitType) {
        this.orgUnitType = orgUnitType;
    }

    public Branch getParent() {
        return parent;
    }

    public void setParent(Branch parent) {
        this.parent = parent;
    }

    public boolean hadIds() {
        return StringUtil.isNotBlankOrEmpty(getIds());
    }
}
