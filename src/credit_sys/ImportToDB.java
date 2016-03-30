/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credit_sys;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Melons
 */
public class ImportToDB {

    public static boolean importData(ArrayList<String> data, short sign, String date, String time) {
        switch (sign) {
            case 0:
                return importICBCPopBasic(data, date, time);
            case 1:
                return importICBC803(data);
            case 2:
                return importBCMPopBasic(data, date, time);
            case 3:
                return importBCM803(data, date, time);
            default:
                return false;
        }
    }

    private static boolean importICBC803(ArrayList<String> data) {
        try {
            String crimeType = PoliceOffice.crimeType(data.get(2), data.get(3), data.get(4));
            String sql = "";
            DBConnection db = new DBConnection();
            Connection conn = db.getConnection();

            if (!"".equals(crimeType)) {
                switch (crimeType) {
                    case "A":
                        sql = "update icbc_info set 犯罪记录A = '是',信息完整='是' where 证件号码 ='"
                                + data.get(1) + "'";
                        break;
                    case "B":
                        sql = "update icbc_info set 犯罪记录B = '是',信息完整='是' where 证件号码 ='"
                                + data.get(1) + "'";
                        break;
                    case "C":
                        sql = "update icbc_info set 犯罪记录C='是',信息完整='是' where 证件号码='"
                                + data.get(1) + "'";
                        break;
                }
            }
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }

        return true;
    }

    private static boolean importICBCPopBasic(ArrayList<String> data, String date, String time) {
        try {
            DBConnection db = new DBConnection();
            Connection conn = db.getConnection();
            PreparedStatement pstatement
                    = conn.prepareStatement("insert into icbc_info(证件类型,证件号码,姓名,住宅地址市县,住宅地址区,"
                            + "住宅地址,住宅电话区号,住宅电话,手机,单位全称,"
                            + "本地户籍,本地居住证,已登记但未办证,其他,户籍地址一致,居住地址一致,登记宅电一致,登记手机一致,登记单位一致,"
                            + "社保缴费状态,申请人单位一致,拥有车辆,日期,时间) "
                            + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstatement.setString(1, data.get(0));
            pstatement.setString(2, data.get(1));
            pstatement.setString(3, data.get(2));
            pstatement.setString(4, data.get(3));
            pstatement.setString(5, data.get(4));
            pstatement.setString(6, data.get(5));
            pstatement.setString(7, data.get(6));
            pstatement.setString(8, data.get(7));
            pstatement.setString(9, data.get(8));
            pstatement.setString(10, data.get(9));
            pstatement.setString(11, data.get(13));
            pstatement.setString(12, data.get(14));
            pstatement.setString(13, data.get(15));
            pstatement.setString(14, data.get(16));
            pstatement.setString(15, data.get(17));
            pstatement.setString(16, data.get(18));
            pstatement.setString(17, data.get(19));
            pstatement.setString(18, data.get(20));
            pstatement.setString(19, data.get(21));
            pstatement.setString(20, data.get(22));
            pstatement.setString(21, data.get(23));
            pstatement.setString(22, data.get(24));
            pstatement.setString(23, date);
            pstatement.setString(24, time);
            pstatement.execute();
            pstatement.close();
            conn.close();

        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    private static boolean importBCMPopBasic(ArrayList<String> data, String date, String time) {
        try {
            DBConnection db = new DBConnection();
            Connection conn = db.getConnection();
            PreparedStatement pstatement
                    = conn.prepareStatement("insert into bcm_info(身份证号码,身份证姓名,手机号码,注册时间,驾驶证档案号,"
                            + "本地户籍,本地居住证,已登记但未办证,其他,登记宅电一致,登记手机一致,驾驶证档案号一致,日期,时间)"
                            + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstatement.setString(1, data.get(1));
            pstatement.setString(2, data.get(0));
            pstatement.setString(3, data.get(2));
            pstatement.setString(4, data.get(3));
            pstatement.setString(5, data.get(4));
            pstatement.setString(6, data.get(8));
            pstatement.setString(7, data.get(9));
            pstatement.setString(8, data.get(10));
            pstatement.setString(9, data.get(11));
            pstatement.setString(10, data.get(12));
            pstatement.setString(11, data.get(13));
            pstatement.setString(12, data.get(14));
            pstatement.setString(13, date);
            pstatement.setString(14, time);
            pstatement.execute();
            pstatement.close();
            conn.close();
            
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }

    private static boolean importBCM803(ArrayList<String> data, String date, String time) {
        try {
            String crimeType = PoliceOffice.crimeType(data.get(2), data.get(3), data.get(4));
            String sql = "";
            DBConnection db = new DBConnection();
            Connection conn = db.getConnection();

            if (!"".equals(crimeType)) {
                switch (crimeType) {
                    case "A":
                        sql = "update bcm_info set 犯罪记录A = '是',信息完整='是' where 身份证号码 ='"
                                + data.get(1) + "'";
                        break;
                    case "B":
                        sql = "update bcm_info set 犯罪记录B = '是',信息完整='是' where 身份证号码 ='"
                                + data.get(1) + "'";
                        break;
                    case "C":
                        sql = "update bcm_info set 犯罪记录C='是',信息完整='是' where 身份证号码='"
                                + data.get(1) + "'";
                        break;
                }
            }
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }

        return true;
    }
}
