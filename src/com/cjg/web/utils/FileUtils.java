package com.cjg.web.utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
// ʵ���ļ������Ĺ�����
public class FileUtils {
    /**
     * �õ�·���¶�Ӧ���е��ļ����ļ���
     * 
     * @param path
     * @return
     */
    public static File[] fileList(String path) {
        File file = new File(path);
        return file.listFiles();
    }

    /**
     * �õ�·���·����������ļ����ļ���
     * 
     * @param path
     * @param cond
     * @return
     */
    public static File[] fileList(String path, final String cond) {
        File file = new File(path);
        return file.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.indexOf(cond) >= 0;
            }
        });
    }

    /**
     * ��ȡ�ļ�
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public static String readFile(String path) throws IOException {
        File file = new File(path);
        StringBuffer strBuffer = new StringBuffer();
        Reader reader = null;
        BufferedReader breader = null;
        try {
            reader = new FileReader(file);
            breader = new BufferedReader(reader);
            String strline = null;
            while ((strline = breader.readLine()) != null) {
                strBuffer.append(strline + "\n");
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("�ļ����ܱ����֣�");
        } catch (IOException e) {
            throw new IOException("�ļ���ȡ�г������쳣��");
        } finally {
            if (breader != null) breader.close();
            if (reader != null) reader.close();
        }
        return strBuffer.toString();
    }

    /**
     * д�ļ�
     * 
     * @param path
     * @param content
     * @throws IOException
     */
    public static void writeFile(String path, String content) throws IOException {
        File file = new File(path);
        Writer writer = null;
        BufferedWriter bwriter = null;
        PrintWriter out = null;
        try {
            writer = new FileWriter(file, true);
            bwriter = new BufferedWriter(writer);
            out = new PrintWriter(bwriter);
            out.println(content);
        } catch (IOException e) {
            throw new IOException("д�ļ��������쳣��");
        } finally {
            if (out != null) out.close();
            if (bwriter != null) bwriter.close();
            if (writer != null) writer.close();
        }
    }

    /**
     * �����ļ�
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public static boolean createFile(String path) throws IOException {
        File file = new File(path);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            throw new IOException("�ļ�����ʧ�ܣ�");
        }
    }

    /**
     * ��������ļ���
     * 
     * @param path
     * @return
     */
    public static boolean mkdirs(String path) {
        File file = new File(path);
        return file.mkdirs();
    }

    /**
     * ��������
     * 
     * @param oldpath
     * @param newfname
     * @return
     */
    public static boolean renameFile(String oldpath, String newfname) {
        File oldfile = new File(oldpath);
        File newfile = new File(oldpath.substring(0, oldpath.indexOf("\\") + 1) + newfname);
        return oldfile.renameTo(newfile);
    }

    /**
     * ɾ���ļ����ļ���
     * 
     * @param path
     * @return
     */
    public static boolean delete(String path) {
        File file = new File(path);
        return file.delete();
    }
}
