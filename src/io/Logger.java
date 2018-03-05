package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static void log(String logStr){
        DateFormat df = new SimpleDateFormat("dd_MM_YYYY");
        DateFormat timeFormat = new SimpleDateFormat
                ("YYYY_MM_dd HH:mm:ss.SSS");
        Date date = new Date();
        String fileName = "log_"+df.format(date)+".txt";
        File file = new File(fileName);
        try {
            PrintWriter pw = new PrintWriter
                    (new FileWriter(file,true));
            pw.println(timeFormat.format(date)+" "+ logStr);
            pw.flush();//write to physical stream. you must to do it
            pw.close();
        } catch (IOException e) {
            System.out.println("Can't to create file");
        }
    }
}
