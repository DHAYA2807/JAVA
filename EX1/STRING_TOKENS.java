import java.util.*;

public class Tokeniser {
    public static void main(String[] args) {
                  System.out.println();
        String javaProgram = "public class Helloworld { public static void main ( String [] args ) { int a = 10 ; int b = 20    ; int sum = a + b ; System . out . println ( \"Sum of the two number is \" + sum + \"Hello, World!\") ; } } ";

        StringTokenizer tokenizer = new StringTokenizer(javaProgram, " ");
        List<String> keywords = new ArrayList<>();

        Set<String> keywordSet = new HashSet<>(Arrays.asList(
            "public", "static", "void", "main", "String", "args", "System", "out", "println", "int"
        ));

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (keywordSet.contains(token)) {
                keywords.add(token);
            }
        }
        Collections.sort(keywords);
        System.out.println("\nKeywords in ascending order: " + keywords);
        String modifiedProgram1 = javaProgram.replace("public", "private");
        System.out.println("\nModified Program: " + modifiedProgram1);
                  System.out.println();
        String modifiedProgram2 = modifiedProgram1.replace("int", "INT").replace("Helloworld", "helloworld");
        System.out.println("\nProgram after modifications: " + modifiedProgram2);
                  System.out.println();
        String printMethod = javaProgram;
        int positionOfL = printMethod.indexOf('l');
        String substringPrint = printMethod.substring(printMethod.indexOf("println"), printMethod.indexOf("println") + 5);
        System.out.println("\nPosition of 'l' in 'println': " + positionOfL);
        System.out.println("\nSubstring 'print' from 'println': " + substringPrint);
                  System.out.println();
        int totalTokens = keywords.size();
        System.out.println("\nTotal number of tokens in the program: " + totalTokens);
