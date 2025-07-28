import java.io.FileReader;
import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ShamirCatalog {
    public static void main(String[] args) {
        try {
            // Choose file: testcase1.json or testcase2.json
            String filename = "testcase1.json";

            // Parse JSON file
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(new FileReader(filename));

            // Read n and k
            JSONObject keys = (JSONObject) json.get("keys");
            int n = Integer.parseInt(keys.get("n").toString());
            int k = Integer.parseInt(keys.get("k").toString());

            // Collect points in sorted order by key (1 to n)
            TreeMap<Integer, BigInteger> xToY = new TreeMap<>();
            for (int i = 1; i <= n; i++) {
                String key = String.valueOf(i);
                if (json.containsKey(key)) {
                    JSONObject point = (JSONObject) json.get(key);
                    int base = Integer.parseInt(point.get("base").toString());
                    String val = point.get("value").toString();

                    // Convert base-N to BigInteger
                    BigInteger y = new BigInteger(val, base);
                    xToY.put(i, y);
                }
            }

            // Display the first k points
            System.out.println("First " + k + " points (x, y):");
            int count = 0;
            for (Map.Entry<Integer, BigInteger> entry : xToY.entrySet()) {
                if (count >= k) break;
                System.out.println("(" + entry.getKey() + ", " + entry.getValue() + ")");
                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


