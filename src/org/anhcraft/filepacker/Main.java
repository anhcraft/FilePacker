package org.anhcraft.filepacker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Main {
    private static final String INPUT_ARG = "--input";
    private static final String OUTPUT_ARG = "--output";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("FilePacker coded by anhcraft!");
        System.out.println("Initializing...");
        int arg_mode = -1;
        LinkedHashMap<File, File> input = new LinkedHashMap<>();
        List<File> output = new ArrayList<>();
        for(String s : args){
            if(arg_mode == 0){
                for(String fn : s.split(",")){
                    File f = new File(fn);
                    if(f.exists()) {
                        if(f.isFile()) {
                            input.put(f,f);
                        } else {
                            for(File sf : getFiles(f)){
                                input.put(sf,f);
                            }
                        }
                    } else {
                        System.out.println("Input - File doesn't exist: " + fn);
                        System.exit(0);
                    }
                }
                arg_mode = -1;
            }
            else if(arg_mode == 1){
                for(String fn : s.split(",")){
                    File f = new File(fn);
                    if(f.exists()) {
                        if(f.isFile()) {
                            System.out.println("Output - File must be a directory: " + fn);
                            System.exit(0);
                        }
                        f.delete();
                    }
                    f.mkdirs();
                    output.add(f);
                }
                arg_mode = -1;
            }
            else if(s.startsWith(INPUT_ARG)){
                arg_mode = 0;
            }
            else if(s.startsWith(OUTPUT_ARG)){
                arg_mode = 1;
            }
        }
        System.out.println("Starting...");
        try {
            for(File f : output) {
                System.out.println("Copying files to " + f.getAbsolutePath() + "...");
                int inc = 1;
                for(File s : input.keySet()) {
                    System.out.println(inc + "/" + input.size() + "...");
                    File p = input.get(s);
                    File x = new File(f, s.getAbsolutePath().substring(p.getAbsolutePath().length()+1));
                    if(!x.getParentFile().exists()) {
                        x.getParentFile().mkdirs();
                    }
                    if(!x.exists()) {
                        x.createNewFile();
                    }
                    try {
                        FileInputStream in = new FileInputStream(s);
                        FileOutputStream out = new FileOutputStream(x);
                        byte[] buffer = new byte[1024];
                        int length;
                        while((length = in.read(buffer)) > 0) {
                            out.write(buffer, 0, length);
                        }
                        out.close();
                        in.close();
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                    inc++;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Done! ("+((System.currentTimeMillis()-start)/1000)+"s)");
        System.exit(0);
    }

    private static List<File> getFiles(File f) {
        List<File> files = new ArrayList<>();
        for(File x : f.listFiles(file -> {
            Path path = Paths.get(file.getAbsolutePath());
            DosFileAttributes dfa;
            try {
                dfa = Files.readAttributes(path, DosFileAttributes.class);
            } catch (IOException e) {
                return false;
            }
            return (!dfa.isHidden() && !dfa.isSystem());
        })) {
            if(x.isFile()){
                files.add(x);
            } else {
                files.addAll(getFiles(x));
            }
        }
        return files;
    }
}
