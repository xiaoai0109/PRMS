package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 * RadioProgram Data Access Object (DAO). This class contains all database
 * handling that is needed to permanently store and retrieve RadioProgram object
 * instances.
 */
public class ScheduleDAOImpl implements ScheduleDAO {

    Connection connection;

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.ProgramSlot.dao.impl.ProgramDAO#createValueObject()
     */
    @Override
    public ProgramSlot createValueObject() {
        return new ProgramSlot();
    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.ProgramSlot.dao.impl.ProgramDAO#getObject(java.lang.String)
     */
    @Override
    public ProgramSlot getObject(String rpname) throws NotFoundException,
            SQLException {

        // mia don't know when to use, may be incorrect
        ProgramSlot valueObject = createValueObject();
        valueObject.setRpname(rpname);
        load(valueObject);
        return valueObject;
    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.ProgramSlot.dao.impl.ProgramDAO#load(sg.edu.nus.iss.phoenix.ProgramSlot.entity.ProgramSlot)
     */
    @Override
    // mia previous : load (One), not sure functionality
    public void load(ProgramSlot valueObject) throws NotFoundException,
            SQLException {

        if (valueObject.getRpname() == null || valueObject.getDate() == null
                || valueObject.getSttime() == null) {
            // System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("Can not select without Primary-Key!");
        }

        String sql = "SELECT * FROM `program-slot` WHERE (`program-name` = ? " +
                "AND `dateOfProgram` = ? AND `startTime` = ? ); ";
        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, valueObject.getRpname());
            stmt.setDate(2, valueObject.getDate());
            stmt.setTime(3, valueObject.getSttime());

            singleQuery(stmt, valueObject);

        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.ProgramSlot.dao.impl.ProgramDAO#loadAll()
     */
    @Override
    public List<ProgramSlot> loadAll() throws SQLException {
        openConnection();
        String sql = "SELECT * FROM `program-slot` ORDER BY `dateOfProgram` DESC; ";
        List<ProgramSlot> searchResults = listQuery(connection
                .prepareStatement(sql));
        closeConnection();
        System.out.println("record size"+searchResults.size());
        return searchResults;
    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.ProgramSlot.dao.impl.ProgramDAO#create(sg.edu.nus.iss.phoenix.ProgramSlot.entity.ProgramSlot)
     */
    @Override
    public synchronized void create(ProgramSlot valueObject)
            throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        openConnection();
        try {
            sql = "INSERT INTO `program-slot` (`program-name`, `dateOfProgram`, `startTime`,  `duration`, " +
                    "`presenter`,   `producer`) VALUES (?,?,?,?,?,?); ";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, valueObject.getRpname());
            stmt.setDate(2, valueObject.getDate());
            stmt.setTime(3, valueObject.getSttime());
            stmt.setTime(4, valueObject.getDuration());
            stmt.setString(5, valueObject.getPresenter());
            stmt.setString(6, valueObject.getProducer());
            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                // System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PrimaryKey Error when updating DB!");
            }

        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();
        }

    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.ProgramSlot.dao.impl.ProgramDAO#save(sg.edu.nus.iss.phoenix.ProgramSlot.entity.ProgramSlot)
     */
    @Override
    public void save(ProgramSlot valueObject, ProgramSlot oldValueObject) throws NotFoundException,
            SQLException {
        // mia: need to check overlap first, maybe use searchMatching method
        String sql = "UPDATE `program-slot` SET `program-name` = ?, `dateOfProgram` = ?, `startTime` = ?, " +
                "`duration` = ?, `presenter` = ?, `producer` = ?  " +
                "WHERE (`dateOfProgram` = ?) and (`startTime` = ?);\n";
        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, valueObject.getRpname());
            stmt.setDate(2, valueObject.getDate());
            stmt.setTime(3, valueObject.getSttime());
            stmt.setTime(4, valueObject.getDuration());
            stmt.setString(5, valueObject.getPresenter());
            stmt.setString(6, valueObject.getProducer());;

            stmt.setDate(7, oldValueObject.getDate());
            stmt.setTime(8, oldValueObject.getSttime());

            System.out.println("save stmt" + stmt.toString());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                // System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException(
                        "Object could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                // System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException(
                        "PrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.ProgramSlot.dao.impl.ProgramDAO#delete(sg.edu.nus.iss.phoenix.ProgramSlot.entity.ProgramSlot)
     */
    @Override
    public void delete(ProgramSlot valueObject) throws NotFoundException,
            SQLException {

        if (valueObject.getRpname() == null || valueObject.getDate() == null
                || valueObject.getSttime() == null) {
            // System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("Can not select without Primary-Key!");
        }

        String sql = "DELETE FROM `program-slot` WHERE (`program-name` = ? AND `dateOfProgram` = ? AND `startTime` = ? ); ";
        PreparedStatement stmt = null;
        System.out.println("del sql:" + sql);
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, valueObject.getRpname());
            stmt.setDate(2, valueObject.getDate());
            stmt.setTime(3, valueObject.getSttime());

            System.out.print("del stmt:" + stmt.toString());
            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                // System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException(
                        "Object could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                // System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException(
                        "PrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.ProgramSlot.dao.impl.ProgramDAO#deleteAll(java.sql.Connection)
     */
    @Override
    public void deleteAll(Connection conn) throws SQLException {

        String sql = "DELETE FROM `program-slot`";
        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            int rowcount = databaseUpdate(stmt);
            System.out.println(""+rowcount);
        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();

        }
    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.ProgramSlot.dao.impl.ProgramDAO#countAll()
     */
    @Override
    public int countAll() throws SQLException {

        String sql = "SELECT count(*) FROM `program-slot`";
        PreparedStatement stmt = null;
        ResultSet result = null;
        int allRows = 0;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery();

            if (result.next())
                allRows = result.getInt(1);
        } finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
        return allRows;
    }

    /* (non-Javadoc)
     * @see sg.edu.nus.iss.phoenix.ProgramSlot.dao.impl.ProgramDAO#searchMatching(sg.edu.nus.iss.phoenix.ProgramSlot.entity.ProgramSlot)
     */
    @Override
    public List<ProgramSlot> searchMatching(ProgramSlot valueObject) throws SQLException {

        // mia: not sure
        @SuppressWarnings("UnusedAssignment")
        List<ProgramSlot> searchResults = new ArrayList<>();
        openConnection();
        boolean first = true;
        StringBuilder sql = new StringBuilder(
                "SELECT * FROM `program-slot` WHERE 1=1 ");

        if (valueObject.getRpname() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND `program-name` LIKE '").append(valueObject.getRpname())
                    .append("%' ");
        }

        if (valueObject.getDate() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND `dateOfProgram` LIKE '").append(valueObject.getDate())
                    .append("%' ");
        }

        if (valueObject.getSttime() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND `startTime` = '")
                    .append(valueObject.getSttime()).append("' ");
        }

        sql.append("ORDER BY `name` ASC ");

        // Prevent accidential full table results.
        // Use loadAll if all rows must be returned.
        if (first)
            searchResults = new ArrayList<>();
        else
            searchResults = listQuery(connection.prepareStatement(sql
                    .toString()));
        closeConnection();
        return searchResults;
    }

    /**
     * databaseUpdate-method. This method is a helper method for internal use.
     * It will execute all database handling that will change the information in
     * tables. SELECT queries will not be executed here however. The return
     * value indicates how many rows were affected. This method will also make
     * sure that if cache is used, it will reset when data changes.
     *
     * @param stmt
     *            This parameter contains the SQL statement to be excuted.
     * @return
     * @throws java.sql.SQLException
     */
    protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

        int result = stmt.executeUpdate();

        return result;
    }

    /**
     * databaseQuery-method. This method is a helper method for internal use. It
     * will execute all database queries that will return only one row. The
     * resultset will be converted to valueObject. If no rows were found,
     * NotFoundException will be thrown.
     *
     * @param stmt
     *            This parameter contains the SQL statement to be excuted.
     * @param valueObject
     *            Class-instance where resulting data will be stored.
     * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
     * @throws java.sql.SQLException
     */
    protected void singleQuery(PreparedStatement stmt, ProgramSlot valueObject)
            throws NotFoundException, SQLException {

        ResultSet result = null;
        openConnection();
        try {
            result = stmt.executeQuery();

            if (result.next()) {

                valueObject.setRpname(result.getString("program-name"));
                valueObject.setDate(result.getDate("dateOfProgram"));
                valueObject.setSttime(result.getTime("startTime"));
                valueObject.setDuration(result.getTime("duration"));
                valueObject.setPresenter(result.getString("presenter"));
                valueObject.setProducer(result.getString("producer"));

            } else {
                // System.out.println("ProgramSlot Object Not Found!");
                throw new NotFoundException("ProgramSlot Object Not Found!");
            }
        } finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
    }

    /**
     * databaseQuery-method. This method is a helper method for internal use. It
     * will execute all database queries that will return multiple rows. The
     * resultset will be converted to the List of valueObjects. If no rows were
     * found, an empty List will be returned.
     *
     * @param stmt
     *            This parameter contains the SQL statement to be excuted.
     * @return
     * @throws java.sql.SQLException
     */
    protected List<ProgramSlot> listQuery(PreparedStatement stmt) throws SQLException {

        ArrayList<ProgramSlot> searchResults = new ArrayList<>();
        ResultSet result = null;
        openConnection();
        try {
            result = stmt.executeQuery();

            while (result.next()) {
                ProgramSlot temp = createValueObject();

                temp.setRpname(result.getString("program-name"));
                temp.setDate(result.getDate("dateOfProgram"));
                temp.setSttime(result.getTime("startTime"));
                temp.setDuration(result.getTime("Duration"));
                temp.setPresenter(result.getString("presenter"));
                temp.setProducer(result.getString("producer"));

                searchResults.add(temp);
            }

        } finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
            closeConnection();
        }

        return (List<ProgramSlot>) searchResults;
    }

    /**
     * Mia: No doc yet
     *
     * @param stmt
     *            This parameter contains the SQL statement to be excuted.
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public boolean checkOverlap(Date date, Time sttime, Time duration) throws SQLException {

        String sql = "select * \n" +
                "from `program-slot` \n" +
                "where dateOfProgram = ? AND\n" +
                "    ((startTime >= ?  AND startTime < ?) \n" +
                "    OR (SEC_TO_TIME(TIME_TO_SEC(`startTime`) + TIME_TO_SEC(`duration`)) > ? \n" +
                "    AND SEC_TO_TIME(TIME_TO_SEC(`startTime`) + TIME_TO_SEC(`duration`)) <= ? ));";

//        String sql = "select * \n" +
//                "from `program-slot` \n" +
//                "where dateOfProgram = ? AND\n" +
//                "    ((startTime >= ?  AND startTime < timediff(?, -?)) \n" +
//                "    OR (timediff(`startTime`, -`duration`) > ?\n" +
//                "    AND timediff(`startTime`, -`duration`) <= timediff(?, -?)));";
        PreparedStatement stmt = null;
        ResultSet result = null;
        int allRows = 0;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setDate(1, date);
            stmt.setTime(2, sttime);
            stmt.setTime(3, sttime);
            stmt.setTime(4, duration);
            stmt.setTime(5, sttime);
            stmt.setTime(6, sttime);
            stmt.setTime(7, duration);


//            stmt.setDate(1, valueObject.getDate());
//            stmt.setTime(2, valueObject.getSttime());
//            stmt.setTime(3, valueObject.getSttime() + valueObject.getDuration());
//            stmt.setTime(4, valueObject.getSttime());
//            stmt.setTime(5, valueObject.getSttime() + valueObject.getDuration());
            System.out.print("check overlap:" + stmt.toString());
            result = stmt.executeQuery();

            if (result.next())
                allRows = result.getInt(1);
            if (allRows != 0) {
                return true;
            }
        } finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
        return false;
    }


    private void openConnection() {
        try {
            Class.forName(DBConstants.COM_MYSQL_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            this.connection = DriverManager.getConnection(DBConstants.dbUrl,
                    DBConstants.dbUserName, DBConstants.dbPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
