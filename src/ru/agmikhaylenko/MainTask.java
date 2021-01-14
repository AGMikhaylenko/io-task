package ru.agmikhaylenko;

import java.io.*;
import java.util.ArrayList;

public class MainTask {

    private static final File outFile = new File("data/out.txt");
    final String space = "    ";
    final String spaceForDirectory = "----";
    final String separator = "|";

    public static void main(String[] args) {
        MainTask test = new MainTask();
        File testFile = new File("D:/ВКР");
        test.writeTree(test.getTreeFilter(testFile, 0));
    }

    public void writeTree(String tree) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            writer.write(tree);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getIndent(boolean isDirectory, int countIntent) {
        StringBuilder sb = new StringBuilder();
        if (countIntent == 0) {
            return sb.toString();
        }
        for (int i = 0; i < countIntent - 1; i++)
            sb.append(space);
        if (isDirectory)
            sb.append(separator).append(spaceForDirectory);
        else
            sb.append(space);
        return sb.toString();
    }

    public String getTreeFilter(File mainFile, int countIntent) {
        StringBuilder sb = new StringBuilder(getIndent(true, countIntent));
        sb.append(mainFile.getName() + "\n");

        if (mainFile.exists()) {
            File[] listFiles = mainFile.listFiles();
            ArrayList<File> listDirectory = new ArrayList<>();
            ArrayList<File> listNoDirectory = new ArrayList<>();
            for (File file : listFiles) {
                if (file.isDirectory())
                    listDirectory.add(file);
                else
                    listNoDirectory.add(file);
            }
            for (int i = 0; i < listNoDirectory.size(); i++) {
                sb.append(getIndent(false, countIntent+1));
                sb.append((i + 1) + "." + listNoDirectory.get(i).getName() + "\n");
            }
            for (int i = 0; i < listDirectory.size(); i++) {
                sb.append(getTreeFilter(listDirectory.get(i), countIntent + 1));
            }
        }
        return sb.toString();
    }
}
