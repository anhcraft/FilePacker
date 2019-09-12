package dev.anhcraft.filepacker;

import dev.anhcraft.jvmkit.utils.FileUtil;
import org.apache.commons.cli.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", "in", true, "Define the input files");
        options.addOption("o", "out", true, "Define the output files");
        long start = System.currentTimeMillis();
        System.out.println("Initializing...");
        List<File> input = new ArrayList<>();
        List<File> output = new ArrayList<>();
        try {
            CommandLine parser = new DefaultParser().parse(options, args);
            if(parser.hasOption("in")) {
                for (String fn : parser.getOptionValues("in")) {
                    String file = fn.trim();
                    if (file.isEmpty()) continue;
                    File f = new File(file);
                    if (f.exists()) input.add(f);
                    else {
                        System.out.println("Input - File doesn't exist: " + file);
                        System.exit(0);
                        return;
                    }
                }
            } else {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("FilePacker", options);
                System.exit(0);
                return;
            }
            if(parser.hasOption("out")) {
                for (String fn : parser.getOptionValues("out")) {
                    String file = fn.trim();
                    if (file.isEmpty()) continue;
                    File f = new File(file);
                    if (f.exists() && f.isFile()) {
                        System.out.println("Output - File must be a directory: " + file);
                        System.exit(0);
                        return;
                    }
                    f.mkdirs();
                    output.add(f);
                }
            } else {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("FilePacker", options);
                System.exit(0);
                return;
            }
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("FilePacker", options);
            e.printStackTrace();
        }
        System.out.println("Starting...");
        for(File f : output) {
            System.out.println("Copying files to " + f.getAbsolutePath() + "...");
            int inc = 0;
            for(File inp : input) {
                System.out.println(++inc + "/" + input.size() + "...");
                FileUtil.copy(inp, f);
            }
        }
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
            return;
        }
        System.out.println("Done! ("+((System.currentTimeMillis()-start)/1000)+"s)");
    }
}
