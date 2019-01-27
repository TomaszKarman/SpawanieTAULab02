package tomaszkarman.com.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tomaszkarman.com.domain.SpawarkaCTR;

import java.sql.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SpawarkaDaoTest {

    private SpawarkaDao spawarkaDao;

    @Mock
    Connection connectionMock;

    @Mock
    PreparedStatement addSpawarkaStmt;

    @Mock
    PreparedStatement updateDateSpawarka;

    @Mock
    PreparedStatement getSpawarkaById;

    @Mock
    PreparedStatement getTimeFromSpawarka;

    @Before
    public void setup() throws SQLException {

        when(connectionMock.prepareStatement("INSERT INTO Spawarka (name, model, kod, createTime) VALUES (?, ?, ?, now())")).thenReturn(addSpawarkaStmt);
        spawarkaDao = new SpawarkaDaoImpl();
        spawarkaDao.setConnection(connectionMock);
    }

    abstract class AbstractResultSet implements ResultSet {
        int i = 0;

        @Override
        public int getInt(String s) throws SQLException {
            return 1;
        }

        @Override
        public String getString(String columnLabel) throws SQLException {
            return "Majran";
        }

        @Override
        public Date getDate(String columnLabel) {
            return new Date(123456);
        }

        @Override
        public boolean next() throws SQLException {
            if (i == 1)
                return false;
            i++;
            return true;
        }
    }


    @Test
    public void checkGetting() throws SQLException {

        AbstractResultSet abstractResultSet = mock(AbstractResultSet.class);
        when(abstractResultSet.next()).thenCallRealMethod();
        when(abstractResultSet.getInt(any())).thenCallRealMethod();
        when(abstractResultSet.getString(any())).thenCallRealMethod();

        when(connectionMock.prepareStatement("SELECT * FROM Spawarka WHERE id = 1")).thenReturn(getSpawarkaById);
        when(getSpawarkaById.executeQuery()).thenReturn(abstractResultSet);
        when(connectionMock.prepareStatement("UPDATE Spawarka SET readTime = now() WHERE id = 1")).thenReturn(updateDateSpawarka);

        spawarkaDao.findSpawarkaById(1);

        verify(connectionMock).prepareStatement("SELECT * FROM Spawarka WHERE id = 1");
        verify(getSpawarkaById, times(1)).executeQuery();
        verify(connectionMock).prepareStatement("UPDATE Spawarka SET readTime = now() WHERE id = 1");

        verify(abstractResultSet, times(1)).getInt("id");
        verify(abstractResultSet, times(1)).getString("name");
        verify(abstractResultSet, times(1)).getString("model");
        verify(abstractResultSet, times(1)).getInt("kod");
        verify(abstractResultSet, times(1)).getDate("createTime");
    }

    @Test
    public void checkUpdate() throws SQLException {

        AbstractResultSet abstractResultSet = mock(AbstractResultSet.class);
//        when(abstractResultSet.next()).thenCallRealMethod();
//        when(abstractResultSet.getInt(any())).thenCallRealMethod();
//        when(abstractResultSet.getString(any())).thenCallRealMethod();

        when(connectionMock.prepareStatement("UPDATE Spawarka SET name = updatedName WHERE id = 1")).thenReturn(updateDateSpawarka);
        when(updateDateSpawarka.executeQuery()).thenReturn(abstractResultSet);
        when(connectionMock.prepareStatement("UPDATE Spawarka SET modifyTime = now() WHERE id = 1")).thenReturn(updateDateSpawarka);

        spawarkaDao.updateNameOfSpawarka(1, "updatedName");

        verify(connectionMock).prepareStatement("UPDATE Spawarka SET name = updatedName WHERE id = 1");
        verify(updateDateSpawarka, times(2)).executeQuery();
    }

    @Test
    public void checkAddNewRecord() throws SQLException {

        when(addSpawarkaStmt.executeUpdate()).thenReturn(1);

        SpawarkaCTR spawarkaCTR = new SpawarkaCTR("Spawarka", "Audi", 111);

        assertEquals(1, spawarkaDao.addSpawarka(spawarkaCTR));

        verify(addSpawarkaStmt, times(1)).setString(1, "Spawarka");
        verify(addSpawarkaStmt, times(1)).setString(2, "Audi");
        verify(addSpawarkaStmt, times(1)).setInt(3, 111);
        verify(connectionMock).prepareStatement("INSERT INTO Spawarka (name, model, kod, createTime) VALUES (?, ?, ?, now())");
    }

    @Test
    public void getTimeOfCreationEditOrUpdate() throws SQLException {

        AbstractResultSet abstractResultSet = mock(AbstractResultSet.class);
        when(abstractResultSet.getDate(any())).thenCallRealMethod();

        SpawarkaCTR spawarkaCTR = new SpawarkaCTR("aaa", "bbb", 111);
        spawarkaCTR.setId(1);

        /*
        /Test of check creation date
         */

        when(connectionMock.prepareStatement("SELECT createTime FROM Spawarka WHERE id = 1")).thenReturn(getTimeFromSpawarka);
        when(getTimeFromSpawarka.executeQuery()).thenReturn(abstractResultSet);

        spawarkaDao.checkDateInDatabase(spawarkaCTR, 1);

        verify(abstractResultSet, times(1)).getDate("creationDate");

        /*
        /Test of last check record date
         */

        when(connectionMock.prepareStatement("SELECT readTime FROM Spawarka WHERE id = 1")).thenReturn(getTimeFromSpawarka);
        when(getTimeFromSpawarka.executeQuery()).thenReturn(abstractResultSet);

        spawarkaDao.checkDateInDatabase(spawarkaCTR, 2);

        verify(abstractResultSet, times(1)).getDate("readTime");

        /*
        /Test of last edit date
         */

        when(connectionMock.prepareStatement("SELECT modifyTime FROM Spawarka WHERE id = 1")).thenReturn(getTimeFromSpawarka);
        when(getTimeFromSpawarka.executeQuery()).thenReturn(abstractResultSet);

        spawarkaDao.checkDateInDatabase(spawarkaCTR, 3);

        verify(abstractResultSet, times(1)).getDate("modifyTime");
    }
}