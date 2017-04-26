import queries.OdbcQueryResult;
import queries.QueryTest;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class runner {
    public static void main(String[] args) throws Exception {
        while(true) {
          try{
            System.out.println("Run start");
            //run();
            runQuery();
            Thread.sleep(2000);
            System.out.println("Run complete");
          }
          catch(Exception ex) {
             System.out.println(ex.getMessage());
          }
        }
    }

    private static void runQuery() throws Exception {
        String url = System.getenv("URL");
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");
        Connection connection = DriverManager.getConnection(url, username, password);
        QueryTest query = new QueryTest();
        OdbcQueryResult result = query.executeQuery(connection);
        System.out.println("Result size: " + result.Items.size());
    }

    private static void run() throws IOException {
        // test
        List<RunItem> ary = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ary.add(
                    new RunItem(Integer.toString(i))
            );
        }

        write(ary);
    }

    private static void write(List<RunItem> list) throws IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/tmp/filename.txt"), "utf-8"))) {
            for (RunItem item: list) {
                writer.write(item.Name);
            }

        }
    }



}
