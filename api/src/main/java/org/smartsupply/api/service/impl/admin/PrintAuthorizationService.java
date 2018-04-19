//package org.smartsupply.api.service.impl.admin;
//
//import com.googlecode.genericdao.search.Search;
//import com.jarcommons.JarValidate;
//import com.jarcommons.StringUtil;
//import com.jarcommons.search.WhereClauseBuilder;
//import org.apache.commons.lang.StringUtils;
//import org.smartsupply.api.dao.BaseDAO;
//import org.smartsupply.api.service.UserService;
//import org.smartsupply.api.service.baseclasses.BaseQuickService;
//import org.smartsupply.api.service.baseclasses.BaseServiceClass;
//import org.smartsupply.api.service.impl.ConnectionService;
//import org.smartsupply.api.service.impl.base.BaseQuickServiceImpl;
//import org.smartsupply.api.service.results.UtilStudentResultService;
//import org.smartsupply.api.utils.BuildSearchUtil;
//import org.smartsupply.api.utils.BuildWhereClauseUtil;
//import org.smartsupply.model.admin.User;
//import org.smartsupply.model.enums.PrintDocumentType;
//import org.smartsupply.model.enums.RecordStatus;
//import org.smartsupply.model.enums.TrueFalse;
//import org.smartsupply.model.search.UserSearchParams;
//import org.smartsupply.model.search.results.ResultSearchParams;
//import org.smartsupply.model.search.student.PrintAuthorizationSearchParams;
//import org.smartsupply.model.search.student.StudentSearchParams;
//import org.smartsupply.model.student.PrintAuthorization;
//import org.smartsupply.model.student.Student;
//import org.smartsupply.model.util.results.UtilStudentResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Service("printAuthorizationService")
//@Transactional
//public class PrintAuthorizationService extends BaseQuickServiceImpl<PrintAuthorization, PrintAuthorizationSearchParams>
//        implements BaseQuickService<PrintAuthorization, PrintAuthorizationSearchParams> {
//
//    @Autowired
//    private UtilStudentResultService utilStudentResultService;
//
//    @Autowired
//    private BaseQuickService<Student, StudentSearchParams> studentService;
//
//    @Autowired
//    private UserService userService;
//
//
//    @Autowired
//    public void setBaseDao(BaseDAO<PrintAuthorization> daoToSet) {
//        super.baseDAO = daoToSet;
//        super.baseDAO.setClazz(PrintAuthorization.class);
//    }
//
//    @Override
//    public PrintAuthorization save(PrintAuthorization printAuthorization) throws Exception {
//        return super.baseDAO.merge(printAuthorization);
//    }
//
//    @Override
//    public void validate(PrintAuthorization t) throws Exception {
//        JarValidate.disallowNull(t.getStudent(), "Student");
//        JarValidate.disallowNull(t.getDocument(), "Document type");
//        JarValidate.disallowNull(t.getSlave(), "Slave");
//        JarValidate.disallowNull(t.getCreatedBy(), "Creator");
//        JarValidate.disallowNull(t.getDateCreated(), "Date Created");
//        ResultSearchParams params = new ResultSearchParams(t.getStudent(), false, RecordStatus.ACTIVE);
//        UtilStudentResult utilStudentResult = utilStudentResultService.getUtilStudentResult(params);
//        if (!utilStudentResult.canPrintGradDocument(false)) {
//            throw new Exception(utilStudentResult.canPrintGradDocumentError(false));
//        }
//
//        if (t.isDeleted() && t.isUsed()) {
//            throw new Exception("You can not delete and already used Print-Authorization");
//        }
//    }
//
//    @Override
//    public Search prepareSearch(PrintAuthorizationSearchParams params, Integer pageNo) throws Exception {
//        Search search = new Search();
//        BuildSearchUtil.addEqual(search, "student", params.getStudent());
//        BuildSearchUtil.addEqual(search, "student.graduation", params.getGraduation());
//
//        BuildSearchUtil.addEqual(search, "createdBy", params.getCreatedBy());
//        BuildSearchUtil.addEqual(search, "deletedBy", params.getDeletedBy());
//        BuildSearchUtil.addEqual(search, "usedBy", params.getUsedBy());
//
//        BuildSearchUtil.addDateCond(search, "dateCreated", params.getDateCreated());
//        BuildSearchUtil.addDateCond(search, "dateDeleted", params.getDateDeleted());
//        BuildSearchUtil.addDateCond(search, "dateUsed", params.getDateUsed());
//
//        BuildSearchUtil.setPageNo(search, pageNo);
//        return search;
//    }
//
//    @Override
//    public List<PrintAuthorization> get(PrintAuthorizationSearchParams params, Integer pageNo) throws Exception {
//        {
//
//            Connection conn = ConnectionService.getConnection();
//
//            Statement s = conn.createStatement();
//            String sql = buildQuery(params, pageNo);
//
//            s.execute(sql);
//            ResultSet rs = s.getResultSet();
//
//            List<PrintAuthorization> list = new ArrayList<>();
//
//            String studentIds = "";
//            String userIds = "";
//
//            while ((rs != null) && (rs.next())) {
//                // pa.id, pa.student_id, pa.notes, pa.document, pa.slave, pa.created_by, pa.deleted_by,
//                // pa.used_by, pa.date_created, pa.date_deleted, pa.date_used, pa.record_status
//
//                String id = rs.getString("id");
//                String student_id = rs.getString("student_id");
//                studentIds = StringUtil.buildStr(studentIds, student_id);
//                Student student = new Student(student_id);
//                String notes = rs.getString("notes");
//                PrintDocumentType documentType = PrintDocumentType.getPrintDocumentType(rs.getInt("document"));
//                Boolean slave = rs.getBoolean("slave");
//                Date dateCreated = rs.getDate("date_created");
//                Date dateUsed = rs.getDate("date_used");
//                Date dateDeleted = rs.getDate("date_deleted");
//
//                String created_by = rs.getString("created_by");
//                User creator = created_by == null ? null : new User(created_by);
//                userIds = StringUtil.buildStr(userIds, created_by);
//
//                String deleted_by = rs.getString("deleted_by");
//                User deleter = deleted_by == null ? null : new User(deleted_by);
//                userIds = StringUtil.buildStr(userIds, deleted_by);
//
//                String used_by = rs.getString("used_by");
//                User user = used_by == null ? null : new User(used_by);
//                userIds = StringUtil.buildStr(userIds, used_by);
//
//                RecordStatus recordStatus = RecordStatus.getByOrdinal(rs.getInt("record_status"));
//
//                PrintAuthorization pa = new PrintAuthorization(id, student, documentType, slave, notes,
//                        creator, dateCreated, deleter, dateDeleted, user, dateUsed, recordStatus);
//
//                pa.setDateUsed(dateUsed);
//                pa.setUsedBy(user);
//                pa.setRecordStatus(recordStatus);
//
//                list.add(pa);
//            }
//
//            ConnectionService.close(conn, s, rs);
//
//            if (StringUtil.isNotBlankOrEmpty(studentIds)) {
//                List<Student> students = studentService.get(new StudentSearchParams(studentIds, true));
//                for (PrintAuthorization t : list) {
//                    if (t.getStudent() != null) {
//                        for (Student student : students) {
//                            if (student.equalsById(t.getStudent())) {
//                                t.setStudent(student);
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//
//            if (StringUtil.isNotBlankOrEmpty(userIds)) {
//                List<User> users = userService.get(new UserSearchParams(userIds, true));
//
//                for (PrintAuthorization t : list) {
//                    if (t.getCreatedBy() != null && users.contains(t.getCreatedBy())) {
//                        t.setCreatedBy(BaseServiceClass.get(t.getCreatedBy(), users));
//                    }
//                    if (t.getDeletedBy() != null && users.contains(t.getDeletedBy())) {
//                        t.setDeletedBy(BaseServiceClass.get(t.getDeletedBy(), users));
//                    }
//                    if (t.getUsedBy() != null && users.contains(t.getUsedBy())) {
//                        t.setUsedBy(BaseServiceClass.get(t.getUsedBy(), users));
//                    }
//                }
//            }
//
//            return list;
//        }
//    }
//
//    @Override
//    public String buildQuery(PrintAuthorizationSearchParams params, Integer pageNo) {
//        String sql = "select pa.id, pa.student_id, pa.notes, pa.document, pa.slave, pa.created_by, pa.deleted_by, \n"
//                + " pa.used_by, pa.date_created, pa.date_deleted, pa.date_used, pa.record_status \n"
//                + " from print_authorization pa inner join student s on pa.student_id = s.id";
//        // student, graduation createdBy, deletedBy, usedBy dateCreated, dateDeleted, dateUsed,
//        // TrueFalse used, deleted, slave;
//
//        StringBuilder whereClause = new StringBuilder();
//        BuildWhereClauseUtil.addEqual(whereClause, "student_id", params.getStudent());
//        String gradId = params.hasGraduation() ? params.getGraduation().getId() : null;
//        if (params.getSlave() == null) {
//            WhereClauseBuilder.addFieldsOr(whereClause, "s.graduation_id,s.slave_graduation_id", ",", gradId);
//        } else {
//            String field = params.getSlave().getAsBooleanValue() ? "s.slave_graduation_id" : "s.graduation_id";
//            WhereClauseBuilder.addEqual(whereClause, field, gradId);
//        }
//        BuildWhereClauseUtil.addEqual(whereClause, "pa.created_by", params.getCreatedBy());
//        BuildWhereClauseUtil.addEqual(whereClause, "pa.deleted_by", params.getDeletedBy());
//        BuildWhereClauseUtil.addEqual(whereClause, "pa.used_by", params.getUsedBy());
//
//        Boolean deleted = TrueFalse.getAsBooleanValue(params.getDeleted());
//        BuildWhereClauseUtil.addFieldIsNotNull(whereClause, "pa.deleted_by", deleted);
//
//        Boolean used = TrueFalse.getAsBooleanValue(params.getUsed());
//        BuildWhereClauseUtil.addFieldIsNotNull(whereClause, "pa.deleted_by", used);
//
//        sql += StringUtils.isNotBlank(whereClause.toString()) ? " where " + whereClause.toString() : "";
//
//        sql += " order by pa.date_created asc";
//
//        sql += BuildWhereClauseUtil.getPagingString(pageNo);
//
//        return sql;
//    }
//}
