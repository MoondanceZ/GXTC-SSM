package com.rk.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
public class GenEntityMysql {

    private String packageOutPath = "com.rk.entity";//指定实体生成所在包的路径
    private String authorName = "Qin_Yikai";//作者名字
    private String tablename = "product";//表名
    private String[] colnames; // 列名数组
    private String[] colTypes; //列名类型数组
    private int[] colSizes; //列名大小数组
    private Map comment;     //字段注释
    private boolean f_util = false; // 是否需要导入包java.util.*
    private boolean f_sql = false; // 是否需要导入包java.sql.*

    //数据库连接
    private static final String URL = "jdbc:mysql://localhost:3306/gxtc?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String NAME = "root";
    private static final String PASS = "123456";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    /*
     * 构造函数
     */
    public GenEntityMysql() {
        //创建连接
        Connection con = null;
        //查要生成实体类的表
        String sql = "select * from " + tablename;
        PreparedStatement pStemt = null;
        PreparedStatement pStemt2 = null;
        try {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            con = DriverManager.getConnection(URL, NAME, PASS);
            pStemt = con.prepareStatement(sql);
            ResultSetMetaData rsmd = pStemt.getMetaData();
            int size = rsmd.getColumnCount();    //统计列
            colnames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            for (int i = 0; i < size; i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
                colTypes[i] = rsmd.getColumnTypeName(i + 1);

                if (colTypes[i].equalsIgnoreCase("datetime")) {
                    f_util = true;
                }
                if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
                    f_sql = true;
                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }

            //获取数据库字段注释
            String commentSql = " show full fields from " + tablename;
            comment = new HashMap();
            pStemt2 = con.prepareStatement(commentSql);
            ResultSet rs2 = pStemt2.executeQuery();
            while (rs2.next()) {
                String comm = rs2.getString("Comment");
                if (null != comm && !comm.equals("")) {
                    comment.put(rs2.getString("Field"), comm);
                } else {
                    comment.put(rs2.getString("Field"), rs2.getString("Field"));
                }
            }
//            System.out.println(comment);
            String content = parse(colnames, colTypes, colSizes);

            try {
                File directory = new File("");
                String outputPath = directory.getAbsolutePath() + "\\src\\main\\java\\" + this.packageOutPath.replace(".", "\\") + "\\";
                File file = new File(outputPath);
                file.mkdirs();
                FileWriter fw = new FileWriter(outputPath + initcap(tabnameToHump(tablename)) + ".java");
                PrintWriter pw = new PrintWriter(fw);
                pw.println(content);
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 功能：生成实体类主体代码
     *
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return
     */
    private String parse(String[] colnames, String[] colTypes, int[] colSizes) {
        StringBuffer sb = new StringBuffer();
        sb.append("package " + this.packageOutPath + ";\r\n");
        sb.append("\r\n");
        //判断是否导入工具包
        if (f_util) {
            sb.append("import java.util.Date;\r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n");
        }
        //注释部分
        sb.append("\r\n");
        sb.append("/**\r\n");
        sb.append(" * " + tablename + " 实体类\r\n");
        sb.append(" * " + new Date() + " " + this.authorName + "\r\n");
        sb.append(" */ \r\n");
        //实体部分
        sb.append("\r\n");
        sb.append("public class " + initcap(tabnameToHump(tablename)) + " {\r\n");
        processAllAttrs(sb);//属性
        processAllMethod(sb);//get set方法
        sb.append("}\r\n");

        return sb.toString();
    }

    /**
     * 功能：生成所有属性
     *
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            sb.append("\r\n");
            sb.append("   /**\r\n");
            sb.append("     * " + comment.get(colnames[i]) + "\r\n");
            sb.append("     */ \r\n");
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colnameToHump(colnames[i]) + ";\r\n");
        }

    }

    /**
     * 功能：生成getter,setter方法
     *
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnameToHump(colnames[i])) + "(){\r\n");
            sb.append("\t\treturn " + colnameToHump(colnames[i]) + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\r\n");
            sb.append("\tpublic void set" + initcap(colnameToHump(colnames[i])) + "(" + sqlType2JavaType(colTypes[i]) + " " +
                    colnameToHump(colnames[i]) + "){\r\n");
            sb.append("\t\tthis." + colnameToHump(colnames[i]) + " = " + colnameToHump(colnames[i]) + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\r\n");
        }

    }

    /**
     * 功能：将数据库的字段改成驼峰命名的方式
     */
    public String colnameToHump(String colnames) {
        String[] tf = colnames.split("_");
        String newColName = "";
        if (tf.length > 1) {
            for (int i = 1; i < tf.length; i++) {

                tf[i] = initcap(tf[i]);
                newColName = newColName + tf[i];
            }
        }else{
            newColName = colnames;
        }
        return newColName;
    }

    public String tabnameToHump(String tabname) {
        String[] tf = tabname.split("_");
        String newTabName = "";
        for (int i = 0; i < tf.length; i++) {
            tf[i] = initcap(tf[i]);
            newTabName = newTabName + tf[i];
        }
        return newTabName;
    }


    /**
     * 功能：将输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private String initcap(String str) {

        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType) {

        if (sqlType.equalsIgnoreCase("bit")) {
            return "boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "int";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }

        return null;
    }

    /**
     * 出口
     *
     * @param args
     */
    public static void main(String[] args) {

        new GenEntityMysql();
    }

}
