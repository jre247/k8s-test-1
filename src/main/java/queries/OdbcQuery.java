package queries;

import java.sql.*;
import java.util.*;

public class OdbcQuery {
    public OdbcQueryResult executeQuery(
            Connection connection,
            ArrayList<Object> params,
            String queryString
    ) throws Exception {
        PreparedStatement ps = connection.prepareStatement(queryString);
        ResultSet rs = null;

        setParamValues(ps, params);

        OdbcQueryResult result = new OdbcQueryResult();

        try {
            rs = ps.executeQuery();

            List<Map<String, Object>> entities = getResults(rs);

            result.Items = entities;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (rs != null) { rs.close(); }
            ps.close();
        }

        return result;
    }

    private void setParamValues(PreparedStatement ps, ArrayList<Object> params) throws Exception {
        for(int i = 0; i < params.size(); i++){
            Object obj = params.get(i);

            if(obj instanceof Timestamp){
                Timestamp timestamp = (Timestamp)obj;
                ps.setTimestamp(i + 1, timestamp);

            }
            else{
                ps.setObject(i + 1, obj);
            }
        }
    }

    private List<Map<String, Object>> getResults(ResultSet resultSet) throws SQLException {
        ArrayList<Map<String, Object>> entities = new ArrayList<>();

        while (resultSet.next()) {
            entities.add(getResult(resultSet));
        }

        return entities;
    }

    private Map<String, Object> getResult(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        Map<String, Object> resultsMap = new HashMap<>();
        for (int i = 1; i <= columnCount; ++i) {
            String columnName = metaData.getColumnName(i);
            Object object = resultSet.getObject(i);
            resultsMap.put(columnName, object);
        }
        return resultsMap;
    }
}