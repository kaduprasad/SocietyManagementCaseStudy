package com.society.csv.api;

import java.io.*;

public class CustomFileWriter {
    String name;
    String path;
    String type;
    public FileWriter fileWriter;
    public BufferedReader fileReader;
    File file;


    public CustomFileWriter(String name, String path, String type) {
        this.name = name;
        this.path = path;
        this.type = type;
        file = new File(getFullPath());
    }

    public FileWriter getFileWriter() {

        if(fileWriter == null) {
            try {
                fileWriter = new FileWriter( getFullPath(),true); // FileWriter
            } catch (IOException e) {
                throw  new RuntimeException(e);
            }
        }

        return fileWriter;
    }

    public FileWriter getFileWriterToOverwriteFile() throws IOException {

        if(fileWriter == null) {
            fileWriter = new FileWriter( getFullPath()); // FileWriter
        }

        return fileWriter;
    }

    public BufferedReader getFileReader(){
        if(fileReader == null) {
            try {
                fileReader = new BufferedReader(new FileReader(getFullPath())); // reader
            } catch (IOException e) {
                throw  new RuntimeException(e);
            }
        }
        return fileReader;
    }

    public String readLine(){
        try {
            return getFileReader().readLine(); // reader.readLine to read matter inside string
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    public boolean isFileExists(){
        return file.exists();
    }

    public void flushWriter(){
        try {
            getFileWriter().flush();
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    public  void  append(String row){
        try {
            getFileWriter().append(System.lineSeparator()); // this is "\n"
            getFileWriter().append(row);
            flushWriter();
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    public  void  write(String row) throws IOException{
        getFileWriterToOverwriteFile().write(System.lineSeparator());// this is "\n"
        getFileWriterToOverwriteFile().write(row);
        flushWriter();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getFullPath(){

        return path+name+ "" +type;
    }
}
