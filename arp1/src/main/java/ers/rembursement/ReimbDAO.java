package ers.rembursement;


import ers.common.datasource.ConnectionFactory;
import ers.common.exceptions.DataSourceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ReimbDAO {

    private final String baseSelect = "SELECT er.reimb_id, er.amount, er.submitted, er.resolved, er.description, er.payment_id, er.author_id, er.resolver_id, er.status_id, er.type_id, ers.status, ert.type " +
            "FROM ers_reimbursements er " +
            "JOIN ers_reimbursement_statuses ers " +
            "ON er.status_id = ers.status_id " +
            "JOIN ers_reimbursement_types ert " +
            "ON er.type_id = ert.type_id ";

    public List<Reimbursement> getAllReimbs() {

        List<Reimbursement> allReimbs = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            Statement stmt = conn.createStatement();
            ResultSet rs = ((java.sql.Statement) stmt).executeQuery(baseSelect);

            allReimbs = mapResultSet(rs);

        } catch (SQLException e) {
            System.err.println("Something went wrong when connection to database.");
            e.printStackTrace();
        }

        return allReimbs;
    }

    public Optional<Reimbursement> findReimbById(String reimbId) {

        String sql = baseSelect + "WHERE er.reimb_id = ?";

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, reimbId);
            ResultSet rs = pstmt.executeQuery();
            return mapResultSet(rs).stream().findFirst();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataSourceException(e);
        }

    }

    public Optional<Reimbursement> findReimbByStatus(String status) {

        String sql = baseSelect + "WHERE er.status = ?";

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, status);
            ResultSet rs = pstmt.executeQuery();
            return mapResultSet(rs).stream().findFirst();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataSourceException(e);
        }
    }

    public Optional<Reimbursement> findReimbByType(String type) {

        String sql = baseSelect + "WHERE er.type = ?";

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, type);
            ResultSet rs = pstmt.executeQuery();
            return mapResultSet(rs).stream().findFirst();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataSourceException(e);
        }
    }

    public String save(Reimbursement newReimbursement) {

        String sql = "INSERT INTO ers_reimbursements (amount, description, author_id, status_id, type_id) " +
                "VALUES (?, ?, ?, 'PENDING', ?)";

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"reimb_id"});
            pstmt.setFloat(1, newReimbursement.getAmount());
            pstmt.setString(3, newReimbursement.getDescription());
            pstmt.setString(4, newReimbursement.getAuthor_id());
            pstmt.setString(5, newReimbursement.getType_id());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            newReimbursement.setId(rs.getString("reimb_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newReimbursement.getId();

    }

    private List<Reimbursement> mapResultSet(ResultSet rs) throws SQLException {

        List<Reimbursement> reimbursements = new ArrayList<>();

        while (rs.next()) {
            Reimbursement reimbursement = new Reimbursement();
            reimbursement.setId(rs.getString("reimb_id"));
            reimbursement.setAmount(rs.getInt("amount"));
            reimbursement.setSubmitted(Timestamp.valueOf(rs.getString("submitted")));
            reimbursement.setResolved(Timestamp.valueOf(rs.getString("resolved")));
            reimbursement.setDescription(rs.getString("description"));
            reimbursement.setAuthor_id("author_id");
            reimbursement.setResolver_id("resolver_id");
            reimbursement.setStatus_id ("status_id");
            reimbursement.setType_id("type_id");
            reimbursements.add(reimbursement);
        }

        return reimbursements;
    }

    public void updateReimb(Reimbursement reimb) {
        String sql = "UPDATE ers_reimbursements " +
                "SET amount = ?, description = ?, status_id = ?, resolver_id = ? " +
                "WHERE author_id = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setFloat(1, reimb.getAmount());
            pstmt.setString(2, reimb.getDescription());
            pstmt.setString(1, reimb.getStatus_id());
            pstmt.setString(2, reimb.getResolver_id());
            pstmt.setString(3, reimb.getAuthor_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}