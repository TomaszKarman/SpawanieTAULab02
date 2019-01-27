package tomaszkarman.com.dao;

import tomaszkarman.com.domain.SpawarkaCTR;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface SpawarkaDao {

    Connection getConnection();

    void setConnection(Connection connection) throws SQLException;

    SpawarkaCTR findSpawarkaById(int id) throws SQLException;

    void updateDateInSql(int spawarkaId, int typeUpdate) throws SQLException;

    Date checkDateInDatabase(SpawarkaCTR spawarkaCTR, int dateVariant) throws SQLException;

    Date currentDate();

    int addSpawarka(SpawarkaCTR spawarkaCTR);

    int updateNameOfSpawarka(int idSpawarki, String nameToUpdate) throws SQLException;

    List<SpawarkaCTR> getAllSpawarki();
}
