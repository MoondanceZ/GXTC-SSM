package com.rk.util;


import org.apache.ibatis.type.JdbcType;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
public class GenEntityMysql {

    private String packageOutPath = "com.rk.entity";//指定实体生成所在包的路径
    private String serviceOutPutPath = "com.rk.service";
    private String serviceInterfaceOutPutPath = "com.rk.service.interfaces";
    private String daoOutPutPath = "com.rk.dao";
    private String authorName = "Qin_Yikai";//作者名字
    private String tablename = "shoppingCart";//表名
    private String[] colnames; // 列名数组
    private String[] colTypes; //列名类型数组
    private int[] colSizes; //列名大小数组
    private Map comment;     //字段注释
    private boolean f_util = false; // 是否需要导入包java.util.*
    private boolean f_sql = false; // 是否需要导入包java.sql.*

    //数据库连接
    private static String URL = "jdbc:mysql://localhost:3306/gxtc?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static String NAME = "root";
    private static String PASS = "123456";
    private static String DRIVER = "com.mysql.jdbc.Driver";

    /*
     * 构造函数
     */
    public GenEntityMysql() throws IOException {
        //配置链接
        File directory = new File("");
        Properties props = new Properties();
        InputStreamReader reader = new InputStreamReader(new FileInputStream(directory.getAbsolutePath() + "/src/main/resources/jdbc.properties"), "utf-8");
        //InputStream resourceAsStream = GenEntityMysql.class.getResourceAsStream(directory.getAbsolutePath() + "/src/main/resources/jdbc.properties");
        props.load(reader);
        reader.close();

        GenEntityMysql.URL = props.getProperty("jdbc.url");
        GenEntityMysql.DRIVER = props.getProperty("jdbc.driver");
        GenEntityMysql.PASS = props.getProperty("jdbc.password");
        GenEntityMysql.NAME = props.getProperty("jdbc.user");

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

            writeToFile(content, 1);
            genService();
            genServiceInterface();
            genDao();
            genDaoXml();

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

    private void writeToFile(String content, int pathType) {
        try {
            File directory = new File("");
            String initcapTabName = initcap(tabnameToHump(tablename));
            String pathTemp = "";
            String fileName = "";
            switch (pathType) {
                case 1:
                    pathTemp = "/src/main/java/" + this.packageOutPath.replace(".", "/") + "/";
                    fileName = initcapTabName + ".java";
                    break;
                case 2:
                    pathTemp = "/src/main/java/" + this.serviceInterfaceOutPutPath.replace(".", "/") + "/";
                    fileName = initcapTabName + "Service.java";
                    break;
                case 3:
                    pathTemp = "/src/main/java/" + this.serviceOutPutPath.replace(".", "/") + "/";
                    fileName = initcapTabName + "ServiceImpl.java";
                    break;
                case 4:
                    pathTemp = "/src/main/java/" + this.daoOutPutPath.replace(".", "/") + "/";
                    fileName = initcapTabName + "Mapper.java";
                    break;
                case 5:
                    pathTemp = "/src/main/resources/com.rk.dao/";
                    fileName = initcapTabName + "Mapper.xml";
                    break;
            }
            String outputPath = directory.getAbsolutePath() + pathTemp;
            File file = new File(outputPath);
            file.mkdirs();
            FileWriter fw = new FileWriter(outputPath + fileName);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
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
        processClassComment(sb);
        //实体部分
        sb.append("\r\n");
        sb.append("public class " + initcap(tabnameToHump(tablename)) + " extends BaseEntity<Long> {\r\n");
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
            if (colnames[i].equalsIgnoreCase("id"))
                continue;
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
            if (colnames[i].equalsIgnoreCase("id"))
                continue;
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
        } else {
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
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blob";
        }

        return null;
    }

    private String sqlType2JdbcType(String sqlType) {
        if (sqlType.equalsIgnoreCase("bit")) {
            return "BOOLEAN";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "TINYINT";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "SMALLINT";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "INTEGER";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "BIGINT";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "FLOAT";
        } else if (sqlType.equalsIgnoreCase("decimal")) {
            return "DECIMAL";
        } else if (sqlType.equalsIgnoreCase("numeric")) {
            return "NUMERIC";
        } else if (sqlType.equalsIgnoreCase("real")) {
            return "REAL";
        } else if (sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")) {
            return "DECIMAL";
        } else if (sqlType.equalsIgnoreCase("varchar")) {
            return "VARCHAR";
        } else if (sqlType.equalsIgnoreCase("char")) {
            return "CHAR";
        } else if (sqlType.equalsIgnoreCase("nvarchar")) {
            return "NVARCHAR";
        } else if (sqlType.equalsIgnoreCase("nchar")) {
            return "NCHAR";
        } else if (sqlType.equalsIgnoreCase("text")) {
            return "VARCHAR";
        } else if (sqlType.equalsIgnoreCase("datetime")) {
            return "TIMESTAMP";
        } else if (sqlType.equalsIgnoreCase("date")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "BLOB";
        }

        return null;
    }

    private void genServiceInterface() {
        try {
            String initcapTabName = initcap(tabnameToHump(tablename));
            StringBuffer sb = new StringBuffer();
            sb.append("package " + serviceInterfaceOutPutPath + ";\r\n");
            sb.append("\r\n");
            sb.append("import " + packageOutPath + "." + initcapTabName + ";\r\n");
            sb.append("\r\n");
            processClassComment(sb);

            //实体部分
            sb.append("\r\n");
            sb.append("public interface " + initcapTabName + "Service  extends BaseService<" + initcapTabName + ", Long> {\r\n");
            sb.append("}\r\n");

            writeToFile(sb.toString(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void genService() {
        try {
            String initcapTabName = initcap(tabnameToHump(tablename));

            StringBuffer sb = new StringBuffer();
            sb.append("package " + serviceOutPutPath + ";\r\n");
            sb.append("\r\n");
            sb.append("import com.rk.dao." + initcapTabName + "Mapper;\r\n");
            sb.append("import " + packageOutPath + "." + initcapTabName + ";\r\n");
            sb.append("import " + serviceInterfaceOutPutPath + "." + initcapTabName + "Service;\r\n");
            sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
            sb.append("import org.springframework.stereotype.Service;\r\n");
            sb.append("\r\n");
            processClassComment(sb);
            //实体部分
            sb.append("\r\n");
            sb.append("@Service\r\n");
            sb.append("public class " + initcapTabName + "ServiceImpl extends BaseServiceImpl<" + initcapTabName + ", Long> implements " + initcapTabName + "Service {\r\n");

            sb.append("\t@Autowired\r\n");
            sb.append("\tprivate " + initcapTabName + "Mapper " + colnameToHump(tablename) + "Mapper;\r\n");
            sb.append("}\r\n");

            writeToFile(sb.toString(), 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void genDao() {
        try {
            String initcapTabName = initcap(tabnameToHump(tablename));

            StringBuffer sb = new StringBuffer();
            sb.append("package " + daoOutPutPath + ";\r\n");
            sb.append("\r\n");
            sb.append("import " + packageOutPath + "." + initcapTabName + ";\r\n");
            sb.append("\r\n");
            processClassComment(sb);
            //实体部分
            sb.append("\r\n");
            sb.append("public interface " + initcapTabName + "Mapper extends BaseMapper<" + initcapTabName + ", Long> {\r\n");
            sb.append("}\r\n");

            writeToFile(sb.toString(), 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void genDaoXml() {
        try {
            String initcapTabName = initcap(tabnameToHump(tablename));
            StringBuffer sb = new StringBuffer();
            StringBuffer sbInsert = new StringBuffer();
            StringBuffer sbValues = new StringBuffer();
            StringBuffer sbUpdate = new StringBuffer();
            sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n" +
                    "        \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
            sb.append("<mapper namespace=\"com.rk.dao." + initcapTabName + "Mapper\">\n");
            sb.append("    <cache eviction=\"LRU\" type=\"com.rk.common.cache.MybatisRedisCache\" />\n");
            sb.append("    <resultMap id=\"BaseResultMap\" type=\"com.rk.entity." + initcapTabName + "\">\n");

            for (int i = 0; i < colnames.length; i++) {
                String colname = colnameToHump(colnames[i]);
                if (colnames[i].equalsIgnoreCase("id")) {
                    sb.append("        <id column=\"id\" property=\"id\" jdbcType=\"" + colTypes[i] + "\"/>\n");
                } else {
                    sb.append("        <result property=\"" + colname + "\" column=\"" + colnames[i] + "\" javaType=\"" + sqlType2JavaType(colTypes[i]) + "\" jdbcType=\"" + sqlType2JdbcType(colTypes[i]).toUpperCase() + "\"/>\n");
                    if (i == colnames.length - 1) {
                        sbInsert.append("            " + colnames[i] + "\n");
                        sbValues.append("            #{" + colname + "}\n");
                        sbUpdate.append("            " + colnames[i] + " = #{" + colname + "}\n");
                    } else {
                        sbInsert.append("            " + colnames[i] + ",\n");
                        sbValues.append("            #{" + colname + "},\n");
                        sbUpdate.append("            " + colnames[i] + " = #{" + colname + "},\n");
                    }

                }
            }
            sb.append("    </resultMap>\n");
            sb.append("    <select id=\"getByPrimaryKey\" resultMap=\"BaseResultMap\">\n" +
                    "        SELECT * FROM " + tablename + " WHERE id = #{id}\n" +
                    "    </select>\n\n");

            sb.append("    <sql id=\"pageWhere\">\n" +
                    "        <where>\n" +
                    "            <if test=\"queryString != null and queryString != ''\">\n" +
                    "                AND xxxx LIKE CONCAT('%', #{queryString}, '%')\n" +
                    "            </if>\n" +
                    "        </where>\n" +
                    "    </sql>\n\n");

            sb.append("    <select id=\"getPageList\" resultMap=\"BaseResultMap\">\n" +
                    "        <bind name=\"offest\" value=\"(page-1)*limit\"></bind>\n" +
                    "        SELECT * FROM " + tablename + "\n" +
                    "        <include refid=\"pageWhere\"></include>\n" +
                    "        ORDER BY id LIMIT #{offest}, #{limit}\n" +
                    "    </select>\n\n");

            sb.append("    <select id=\"getPageListTotalCount\" resultType=\"int\">\n" +
                    "        SELECT count(*) FROM product\n" +
                    "        <include refid=\"pageWhere\"></include>\n" +
                    "    </select>\n\n");

            //insert
            sb.append("    <insert id=\"insert\">\n" +
                    "        INSERT INTO " + tablename + " (\n");
            sb.append(sbInsert);
            sb.append("        )\n" +
                    "        VALUES\n" +
                    "        (\n");
            sb.append(sbValues);
            sb.append("        )\n" +
                    "    </insert>\n\n");
            sb.append("    <update id=\"update\">\n" +
                    "        UPDATE " + tablename + "\n" +
                    "        <set>\n");
            sb.append(sbUpdate);
            sb.append("        </set>\n" +
                    "        WHERE\n" +
                    "        id = #{id}\n" +
                    "    </update>\n\n");
            sb.append("    <delete id=\"delete\">\n" +
                    "        <choose>\n" +
                    "            <when test=\"null != array and array.length > 1\">\n" +
                    "                DELETE FROM " + tablename + " WHERE id IN\n" +
                    "                <foreach collection=\"array\" item=\"item\" index=\"index\" open=\"(\" separator=\",\" close=\")\">\n" +
                    "                    #{item}\n" +
                    "                </foreach>\n" +
                    "            </when>\n" +
                    "            <otherwise>\n" +
                    "                DELETE FROM " + tablename + " WHERE id = #{array[0]}\n" +
                    "            </otherwise>\n" +
                    "        </choose>\n" +
                    "    </delete>\n\n");
            if (Arrays.asList(colnames).contains("status")) {
                sb.append("    <update id=\"logicalDelete\">\n" +
                        "        <choose>\n" +
                        "            <when test=\"null != array and array.length > 1\">\n" +
                        "                UPDATE " + tablename + " SET status = -1 WHERE id IN\n" +
                        "                <foreach collection=\"array\" item=\"item\" index=\"index\" open=\"(\" separator=\",\" close=\")\">\n" +
                        "                    #{item}\n" +
                        "                </foreach>\n" +
                        "            </when>\n" +
                        "            <otherwise>\n" +
                        "                UPDATE " + tablename + " SET status = -1 WHERE id = #{array[0]}\n" +
                        "            </otherwise>\n" +
                        "        </choose>\n" +
                        "    </update>\n");
            }
            sb.append("</mapper>");
            writeToFile(sb.toString(), 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processClassComment(StringBuffer sb) {
        //注释部分
        sb.append("/**\r\n");
        sb.append(" * " + new Date() + " " + this.authorName + "\r\n");
        sb.append(" */ \r\n");
    }

    /**
     * 出口
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        new GenEntityMysql();
    }

}
