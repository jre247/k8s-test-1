package queries;

import java.sql.Connection;
import java.util.ArrayList;

public class QueryTest {
    private ArrayList<Object> params;
    private String queryString;
    private final OdbcQuery query;

    public QueryTest() throws Exception {
        this.query = new OdbcQuery();
        ArrayList<Object> paramsList = new ArrayList<>();
        this.params = paramsList;
        this.queryString = getQueryString();
    }

    public OdbcQueryResult executeQuery(Connection connection) throws Exception
    {
        OdbcQueryResult result = query.executeQuery(
                connection,
                params,
                queryString
        );

        return result;

    }

    private String getQueryString() throws Exception {
        StringBuilder builder = new StringBuilder();

        builder.append("SELECT count(1) as row_count");
        builder.append("\n");
        builder.append("FROM " + System.getenv("TABLE_NAME"));

        return builder.toString();
    }



}
