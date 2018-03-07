/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocolo;

import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

public class Convert {

    private static byte[] getBytes(File file) {
        int len = (int) file.length();
        byte[] sendBuf = new byte[len];
        FileInputStream inFile = null;
        try {
            inFile = new FileInputStream(file);
            inFile.read(sendBuf, 0, len);
        } catch (FileNotFoundException fnfex) {
        } catch (IOException ioex) {
        }
        return sendBuf;
    }

    private static File getFile(byte[] bytes) throws FileNotFoundException, IOException {

        File file = new File("file.json");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        }
        return file;
    }

    private static File toJson(Menssagem msg) {

        Gson gson = new Gson();
        String json = gson.toJson(msg);

        try {

            File file = new File("file.json");
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(json);
            }
            return file;

        } catch (IOException e) {

            System.out.println("Erro ao converter menssagem em um arquivo");
        }
        return null;

    }

    private static Menssagem jsonToObject(File file) {

        Gson gson = new Gson();

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));        
            JsonReader jr = gson.newJsonReader(br);
            jr.setLenient(true);
            Menssagem msg = gson.fromJson(jr, Menssagem.class);


            return msg;

        } catch (IOException e) {
            System.out.println("Erro ao converter arquivo em uma menssagem");
        }
        return null;

    }

    public static byte[] convertEnvio(Menssagem msg) {

        File file = toJson(msg);
        byte[] bytes = getBytes(file);
        file.delete();
        return bytes;

    }

    public static Menssagem convertResposta(byte[] bytes) throws IOException {

        File file = getFile(bytes);
        Menssagem msg = jsonToObject(file);
        file.delete();
        return msg;
    }

}
