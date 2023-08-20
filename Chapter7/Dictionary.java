package Chapter7.StringPermutation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
* This class implements an English dictionary with over 466k words.
* File name: words.txt
* */

public class Dictionary
{

    private String fileName ;
    private Set<String> wordSet;

    private static Dictionary dictionaryInstance;

    static {
        try {
            dictionaryInstance = new Dictionary();
        } catch (IOException e) {
            System.out.print("File non found");
        }
    }

    private Dictionary()
            throws IOException
    {
        wordSet = new HashSet<>();
        fileName = "C:\\Users\\HP\\IdeaProjects\\ADM\\src\\Chapter7\\StringPermutation\\words.txt";
        byte[] readBytes = Files.readAllBytes(Path.of(fileName));
        String wordListContents = new String(readBytes, "UTF-8");
        String[] words = wordListContents.split("\n");
        Collections.addAll(wordSet,words);
    }

    public static Dictionary getDictionary()
    {
        return dictionaryInstance;
    }

    public boolean checkForWord(String word)
        throws IOException
    {
        return wordSet.contains(word);
    }



    public static void main(String[] args)
            throws IOException
    {
        Dictionary d = Dictionary.getDictionary();
        System.out.println(d.checkForWord("lip"));
    }


}
