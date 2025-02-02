import java.io.*; 
import java.util.*; 
import java.nio.file.*;  

public class Task9 {     

    public static void analyzeLogFile(String inputFile, String outputFile, List<String> keywords) {         
        Map<String, Integer> keywordCounts = new HashMap<>();          

        for (String keyword : keywords) {             
            keywordCounts.put(keyword, 0);         
        }          

        try {             
            List<String> lines = Files.readAllLines(Paths.get(inputFile));              

            for (String line : lines) {                 
                for (String keyword : keywords) {                     
                    if (line.contains(keyword)) {                         
                        keywordCounts.put(keyword, keywordCounts.get(keyword) + 1);                     
                    }                 
                }             
            }              

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {                 
                writer.write("Log Analysis Results:\n");                 
                for (Map.Entry<String, Integer> entry : keywordCounts.entrySet()) {                     
                    writer.write(entry.getKey() + ": " + entry.getValue() + "\n");                 
                }                 
                System.out.println("Log analysis complete. Results saved to " + outputFile);             
            }          

        } catch (IOException e) {             
            System.err.println("Error reading or writing file: " + e.getMessage());         
        }     
    }      

    public static void main(String[] args) {         
        String inputFile = "input.log";         
        String outputFile = "output.txt";          

        List<String> keywords = Arrays.asList("ERROR", "WARNING", "INFO", "CRITICAL");          

        analyzeLogFile(inputFile, outputFile, keywords);     
    } 
}
