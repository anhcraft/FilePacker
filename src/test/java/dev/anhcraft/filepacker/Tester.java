package dev.anhcraft.filepacker;

import org.junit.Test;

public class Tester {
    @Test
    public void q() {
        Main.main(new String[]{
                "--in",
                "\"C:/Users/DuyAnh/IdeaProjects/FilePacker/src/test/resources/in1\"",
                "--in",
                "\"C:/Users/DuyAnh/IdeaProjects/FilePacker/src/test/resources/in2\"",
                "--out",
                "\"C:/Users/DuyAnh/IdeaProjects/FilePacker/src/test/resources/out\""
        });
    }
}
