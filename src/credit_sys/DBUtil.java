/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credit_sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.plaf.RootPaneUI;

/**
 *
 * @author tantairs
 */
public class DBUtil {

    public static ResultSet lookUpByIdCard(String idCard, String bank) {
        try {
            if (bank.equals("工商银行")) {
                String sql = "select * from icbc_info_his where 证件号码 = " + "\"" + idCard + "\"";
                DBConnection db = new DBConnection();
                Connection conn = db.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                return rs;
            } else if (bank.equals("交通银行")) {
            String sql = "select * from bcm_info_his where 身份证号码 = " + "\"" + idCard + "\"";
            DBConnection db = new DBConnection();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
            }
        } catch (SQLException ex) {
            return null;
        }
        
        return null;
    }

    public static ResultSet lookUpByDateTimeBank(String dateValueBegin, String dateValueEend, String time, String bank) {
        String bank2 = null;
        String time1 = null;
        String time2 = null;
        String[] timeValue = time.split("-");
        time1 = timeValue[0].trim();
        time2 = timeValue[1].trim();
        try {
            if (bank.equals("工商银行")) {
                bank2 = "icbc_info_his";
            } else if (bank.equals("交通银行")) {
                bank2 = "bcm_info_his";
            }
            String sql = "select * from " + bank2 + " where 日期 between " + "\"" + dateValueBegin + "\"" + "and" + "\"" + dateValueEend + "\"" + " and 时间 between  " + "\"" + time1 + "\"" + "and " + "\"" + time2 + "\"";
            DBConnection db = new DBConnection();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void addItemToList(JList list, String date, String bank) {
        String bank2 = null;
        try {
            if (bank.equals("工商银行")) {
                bank2 = "icbc_info_his";
            } else if (bank.equals("交通银行")) {
                bank2 = "bcm_info_his";
            }
            String sql = "select distinct 时间 from " + bank2 + " where 日期 = " + "\"" + date + "\"";
            DBConnection db = new DBConnection();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            DefaultListModel listModel = new DefaultListModel();
            int count = 0;
            while (rs.next()) {
                listModel.add(count++, rs.getString(1));
            }
            list.setModel(listModel);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean deleteDataByChoose(String date, String bank, String time) {
        String bank2 = null;
        PreparedStatement pstmt;
        try {
            if (bank.equals("工商银行")) {
                bank2 = "icbc_info_his";
            } else if (bank.equals("交通银行")) {
                bank2 = "bcm_info_his";
            }
            String sql = "delete from " + bank2 + " where 时间 = " + "\"" + time + "\"" + " and 日期 = " + "\"" + date + "\"";
            DBConnection db = new DBConnection();
            Connection conn = db.getConnection();
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

}
