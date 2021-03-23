package loader;
import com.company.BalanceTree;
import com.company.HashMapThingyMAbob;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Load
{
    public Load() throws IOException {
        loadEverything();
    }

    public void loadHashMap(HashMapThingyMAbob test)
    {

        try
        {
            FileOutputStream fos =
                    new FileOutputStream("myhashmap.ser", false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(test);
            oos.close();
            fos.close();
            System.out.printf("Serialized HashMap data is saved in hashmap.ser");
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public void loadEverything() throws IOException {
        String[] names_array = new String[10000];
        int index = 0;
        int check = 0;
        HashMapThingyMAbob test = new HashMapThingyMAbob();

        try{
            File file = new File("/home/ntrut/IdeaProjects/BalanceTree/src/com/company/Businesses_names.txt");
            File file2 = new File("/home/ntrut/IdeaProjects/BalanceTree/src/com/company/reviews.txt");
            Scanner read = new Scanner(file);
            Scanner read2 = new Scanner(file2);

            while(read.hasNextLine() && read2.hasNextLine())
            {
                Pattern p = Pattern.compile("^[a-zA-Z0-9_ &-'+,.\\\"-/!|@:;]*$");

                String line = read.nextLine();
                String line2 = read2.nextLine();
                Matcher m = p.matcher(line);
                boolean b;
                if(b = m.matches())
                {
                    check++;
                    names_array[index] = line;
                    System.out.println(line + ": REVIEW: " + line2);
                    line2 = line2.toLowerCase();
                    test.put(line, line2);

                }
                index++;
            }
            read.close();
        }catch(FileNotFoundException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }



        BalanceTree tree = new BalanceTree();
        tree.createTree(names_array[0]);

        for(int i = 1; i < names_array.length; i++)
        {

            if(names_array[i] != null)
            {

                tree.insert(names_array[i]);
            }

        }

        loadHashMap(test);
    }
}
