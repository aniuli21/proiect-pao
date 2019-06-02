package com.company.Services;

import java.io.*;

public class TimestampFunction {
    public void SetTimestamp (String functionName, long timestamp) throws IOException {
        File timestampFile = new File("./src/Resources/Timestamp.csv");
        FileWriter timestampWriter = new FileWriter(timestampFile);
        timestampWriter.append(functionName+ "," + timestamp + "\n");
        timestampWriter.flush();
        timestampWriter.close();
    }
}
