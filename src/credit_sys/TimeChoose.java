/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package credit_sys;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePanel;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author tantairs
 */
public class TimeChoose extends JDialog{
    public TimeChoose(JFrame jframe, boolean model,final JTextField jtextfield, int screen_x, int screen_y){
        final JDatePanel jp = JDateComponentFactory.createJDatePanel(new UtilDateModel(new Date()));
        jp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    jtextfield.setText(new SimpleDateFormat("yyyy-MM-dd").format(jp.getModel().getValue()));
                }catch(Exception ex){
                    jtextfield.setText("");
                }
            }
        });
        JPanel jpanel =(JPanel)jp;
        this.add(jpanel);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setBounds(screen_x, screen_y, 380, 200);
        this.setVisible(true);
    }
}
