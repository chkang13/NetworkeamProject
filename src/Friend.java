import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import java.awt.GridBagLayout;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.JEditorPane;
import java.awt.Font;
import java.awt.Color;

public class Friend{
   String serverAddress;
   static String userName;
   static Scanner in;
   static PrintWriter out;
   private static JFrame friend;
   private JTextField IDtext;
   private JTextField Passwordtext;
   private JButton LoginButton;
   private static JLabel information;
   private JButton info_change;
   private static JPanel Loginpanel;
   private static JPanel FriendPannel;
   private static JList list;
   private static JList Userlist;
   private JTextField SearchtextField;
   private JButton Find;
   private static String userinfo=null;
   private JTextArea publicData;
   static PopupMenu pm;
   static newWindow chat;
   private static String fine="";
   public Friend(String serverAddress) {
      
      chat.title = "NONE";

        this.serverAddress = serverAddress;
      friend = new JFrame();
      friend.setBounds(100, 100, 824, 414);
      friend.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      friend.getContentPane().setLayout(null);
      
      FriendPannel = new JPanel();
      FriendPannel.setBounds(0, 0, 806, 367);
      friend.getContentPane().add(FriendPannel);
      FriendPannel.setLayout(null);
      SearchtextField = new JTextField();
      SearchtextField.setBounds(416, 37, 255, 34);
      FriendPannel.add(SearchtextField);
      SearchtextField.setColumns(10);
      
      Find = new JButton("Find");
      Find.setFont(new Font("Times New Roman", Font.PLAIN, 18));
      Find.setBounds(685, 41, 86, 27);
      Find.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String search =SearchtextField.getText();
            out.println("SEARCH"+search);
         }
      });
      FriendPannel.add(Find);
      
      Userlist = new JList();
      Userlist.setBounds(416, 84, 355, 196);
      FriendPannel.add(Userlist);
      information = new JLabel("");
      information.setBounds(29, -1, 346, 72);
      FriendPannel.add(information);
      
       info_change = new JButton("Change");
       info_change.setFont(new Font("Times New Roman", Font.PLAIN, 18));
       info_change.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            String nick = JOptionPane.showInputDialog(friend, "���� ����", "", JOptionPane.PLAIN_MESSAGE);
             out.println("CHANGE /" + userName + "/" + nick);
          }
       });
       info_change.setBounds(268, 41, 105, 27);
       FriendPannel.add(info_change);
       
       
       System.out.println(fine);
       publicData = new JTextArea(fine);
       publicData.setBounds(29, 301, 742, 54);
       FriendPannel.add(publicData);
       publicData.setColumns(10);
       
       JLabel Friendlabel = new JLabel("Friends");
       Friendlabel.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
       Friendlabel.setBounds(29, 61, 346, 27);
       FriendPannel.add(Friendlabel);
       
       JLabel PublicLabel = new JLabel("Fine Dust");
       PublicLabel.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
       PublicLabel.setBounds(29, 278, 237, 27);
       FriendPannel.add(PublicLabel);
       
       
       FriendPannel.setVisible(false);
      Loginpanel = new JPanel();
      Loginpanel.setBounds(0, 0, 806, 367);
      friend.getContentPane().add(Loginpanel);
      Loginpanel.setLayout(null);
      
      LoginButton = new JButton("Log in");
      LoginButton.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
      LoginButton.setBounds(566, 111, 105, 104);
      //�α��� ��ư�� ������ ������ ���̵�� �����ȣ�� �Ѱ���
        LoginButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
             out.println(IDtext.getText());
             out.println(Passwordtext.getText());
             IDtext.setText("");
             Passwordtext.setText("");    
         }
      });
        Loginpanel.add(LoginButton);
        
        IDtext = new JTextField();
        IDtext.setBounds(300, 127, 143, 30);
        Loginpanel.add(IDtext);
        IDtext.setColumns(10);
        
        Passwordtext = new JPasswordField();
        Passwordtext.setBounds(300, 183, 143, 30);
        Passwordtext.setColumns(10);
        Loginpanel.add(Passwordtext);
        
        JLabel ID = new JLabel("ID");
        ID.setFont(new Font("Source Code Pro Black", Font.PLAIN, 17));
        ID.setBounds(213, 132, 62, 18);
        Loginpanel.add(ID);
        
        JLabel Password = new JLabel("Password");
        Password.setFont(new Font("Source Code Pro Black", Font.PLAIN, 17));
        Password.setBounds(213, 188, 99, 18);
        Loginpanel.add(Password);    
        JButton RegisterButton = new JButton("register");
        RegisterButton.setBackground(Color.YELLOW);
        RegisterButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
        RegisterButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
              JoinFrame frame = new JoinFrame();
           }
        });
        RegisterButton.setBounds(566, 287, 105, 27);
        Loginpanel.add(RegisterButton);
        
        pm = new PopupMenu();
        MenuItem pm_item1 = new MenuItem("send to message");
        MenuItem pm_item2 = new MenuItem("play to game");
        MenuItem pm_item3 = new MenuItem("information");
        MenuItem pm_item4 = new MenuItem("");
        
        pm.add(pm_item1);
        pm.addSeparator(); // ���м�
        pm.add(pm_item2);
        pm.add(pm_item3);
        pm.add(pm_item4);
        
        pm_item1.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               if(e.getSource() == pm_item1) {
                  newWindow.userName = userName;
                  chat = new newWindow();
                  out.println("CHAT " + list.getSelectedValuesList() + userName);
                  chat.title = list.getSelectedValuesList() + userName;
                  chat.in = in;
                  chat.out = out;
                  chat.textField2.setEditable(false);
               }
           }           
        });
        
        pm_item2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if(e.getSource() == pm_item2) {
                   System.out.println("DONE");
                }
            }           
         });
        
        pm_item3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if(e.getSource() == pm_item3) {      
                   out.println("INFO " + list.getSelectedValuesList());          
                }
            }           
         });    
   }
   
   private static int option() {
         return JOptionPane.showConfirmDialog(
                    friend, 
                    "�濡 �����Ͻðڽ��ϱ�?", 
                    "Confirm", 
                    JOptionPane.YES_NO_OPTION 
               );
   }
   
   public static void Log() {
      while (in.hasNextLine()) {
           //�����κ��� �о��
           String line = in.nextLine();
           System.out.println("1." + line);
           //�������
           
           if(line.startsWith("IDERROR"))  JOptionPane.showMessageDialog(friend, "Error : Non-existent ID");
           if(line.startsWith("PASSERROR"))  JOptionPane.showMessageDialog(friend, "Error : Invalid password");
           
            if (line.startsWith("SUBMIT")) {
                String[] friends=null;
                 String[] friends2 = null;
                line=line.substring(7);
                System.out.println(line);
                friends=line.split(" ");
                String friendLine = "";
                for(int i = 7; i < friends.length; i++) {
                   friendLine = friendLine + " " + friends[i];
                }
               
                friends2 = friendLine.split(" ");

                list = new JList(friends2);
                list.setBounds(30, 84, 274, 200);
                list.add(pm);
                
                //�˾� �޴�â ǥ��
                list.addMouseListener(new MouseAdapter() {                     
                    @Override
                    public void mousePressed(MouseEvent me) {                       
                       
                        // ������ ���콺 ��ư�� ������ PopupMenu�� ȭ�鿡 �����ش�.
                       if (me.getButton() == MouseEvent.BUTTON3) {
                          list.setSelectedIndex(getRow(me.getPoint()));
                          pm.show(list, me.getX(), me.getY());   
                        }
                    }

               private int getRow(Point point) {
                  return list.locationToIndex(point);
               }
                });
                
                FriendPannel.add(list);

             //������ ���� ǥ��
                information.setText("<html>"+"NAME : "+friends[3]+"<br>"+"E-mail : "+friends[4]+"<br>"+"Phone : "+friends[6]+"</html>");
                userinfo=line;
                userName=friends[2];
             
                FriendPannel.setVisible(true);
                Loginpanel.setVisible(false);
               }
            
            
            //���ο� ����ڸ� ã����
           if(line.startsWith("FOUND")) {
              DefaultListModel listmodel = null;
                String[] users=null;
                line=line.substring(5);
                users=line.split(" ");
                listmodel = new DefaultListModel();
                for(int i=0;i<users.length;i++) {
            
                   listmodel.addElement(users[i]);
                   //����Ʈ�� ����ڸ� ǥ��
                   Userlist.setModel(listmodel);
                      }
                //����Ʈ���� ����ڸ� �����ϸ� ģ���� �߰�
                Userlist.addListSelectionListener(new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                           out.println("ADD"+userinfo+Userlist.getSelectedValuesList());
                            System.out.println("ADD"+userinfo+Userlist.getSelectedValuesList());
                        }

                    }
                });
           }

           //���� ��ϵ� ģ���� ģ�� ����Ʈ�� ���
           if(line.startsWith("NEWFOUND")) {
              line=line.substring(9);
              String[] info=null;
              info=line.split(" ");
                //����Ʈ ������Ʈ ����
              DefaultListModel listmodel = new DefaultListModel();
              for(int i=0;i<info.length;i++) {
                 listmodel.addElement(info[i]);
                }
                list.setModel(listmodel);
           }
           
           //ä�ù� �����ϱ�
           if (line.startsWith("[" + userName + "]")) {
              int result = option();
              
              // ���α׷��� ������
               if(result == JOptionPane.CLOSED_OPTION) {
                  out.println("CLOSE / " + line);
               }
               
               // �ɼǿ��� yes�� Ŭ���ϸ�
               else if(result == JOptionPane.YES_OPTION) {
                  newWindow.userName = userName;
                  chat = new newWindow();
                  chat.title = line;
                  chat.in = in;
                  chat.out = out;
                  out.println("START / " + newWindow.title);
               }
               
               // �ɼǿ��� no�� Ŭ���ϸ�
               else {
                  out.println("CLOSE / " + line);
               }         
           }
           
           // ä���� �ź��ϰų�, �Ѹ��� ������
           if (line.startsWith("CLOSE")) {
              System.out.println(chat.title);
              System.out.println(line);
              if(line.endsWith(chat.title)) {
                 chat.messageField.setText("");
                 chat.dispose();
              }
           }
           
           // ä���� ���۵Ǹ�, ä��â Ȱ��ȭ
           if (line.startsWith("START")) {
              if(line.endsWith(chat.title)) {
                 chat.textField2.setEditable(true);
              }
           }
           
           // �޼��� ������ ���� ä�ù濡 ���
           if (line.startsWith("MESSAGE")) {
              if(line.endsWith(chat.title)) {
                 String array[] = line.split("/");
                 chat.messageField.append(array[1] + "\n");
              }
           }
           
           if (line.startsWith("INFO")) {
              String array[] = line.split("/");
              JOptionPane.showMessageDialog(friend, array[1] + "\n" + array[2] + "\n" + array[3], "Information", JOptionPane.PLAIN_MESSAGE);
           }
      }
            

   }

   private void run() throws Exception {
      try{
           Socket socket = new Socket(serverAddress, 59001);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);  
     
           Log();
      }//try ��
      finally {}

    }//run �Լ� ��
   
   public static void main(String[] args) throws Exception {     
 
                 // �̼����� �溸 ���� ��ȸ ���� - �ѱ�ȯ����� api ����
                    try {
                        // ����Ű
                        String serviceKey = "CzYhQo4IxF3EOauiocZ0%2B21CO9pHEmDOzvaY3CdyFuswL%2BjedKxQiTMDM9AcaYHxPKDfUuTTthVEtwebQAimPQ%3D%3D";
                        
                        String urlStr = "http://openapi.airkorea.or.kr/openapi/services/rest/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo";
                        urlStr += "?"+ URLEncoder.encode("ServiceKey","UTF-8") +"=" + serviceKey;
                        urlStr += "&"+ URLEncoder.encode("numOfRows","UTF-8") +"=200";
                        urlStr += "&"+ URLEncoder.encode("pageNo","UTF-8") +"=1";
                        urlStr += "&"+ URLEncoder.encode("year","UTF-8") +"=2020";
                        urlStr += "&"+ URLEncoder.encode("_returnType","UTF-8") +"=json";
                        
                        URL url = new URL(urlStr);
                        
                        String line = "";
                        String result = "";
                        
                        BufferedReader br;
                        br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
                        while ((line = br.readLine()) != null) {
                            result = result.concat(line);
                            //System.out.println(line);                
                        }      
                        // JSON parser ����� ���ڿ� �����͸� ��üȭ�Ѵ�.
                        JSONParser parser = new JSONParser();
                        JSONObject obj = (JSONObject)parser.parse(result);
                        
                        // list �Ʒ��� �迭���·�
                        // {"list" : [ {"returnType":"json","clearDate":"--",.......} ] 
                        JSONArray parse_listArr = (JSONArray)obj.get("list");
                        
                        String miseType = "";
                        
                        // ��ü���·�
                        // {"returnType":"json","clearDate":"--",.......},... 
                        int p=0;
                        for (int i=0;i< parse_listArr.size();i++) {
                            JSONObject weather = (JSONObject) parse_listArr.get(i);
                            String dataDate = (String) weather.get("dataDate");            // �߷ɳ�¥
                            String districtName = (String) weather.get("districtName");    // �߷�����
                            String moveName = (String) weather.get("moveName");            // �߷ɱǿ�
                            String issueDate = (String) weather.get("issueDate");        // �߷�����
                            String issueTime = (String) weather.get("issueTime");        // �߷ɽð�
                            String issueVal  = (String) weather.get("issueVal");        // �߷ɳ�
                            String itemCode  = (String) weather.get("itemCode");        // �̼����� ���� PM10, PM25
                            String issueGbn  = (String) weather.get("issueGbn");        // �溸�ܰ� : ���Ǻ�/�溸
                            String clearDate = (String) weather.get("clearDate");        // ��������
                            String clearTime = (String) weather.get("clearTime");        // �����ð�
                            String clearVal = (String) weather.get("clearVal");            // ������ �̼�������
                            
                            if (itemCode.equals("PM10")) {            
                                miseType = "";
                            } else if (itemCode.equals("PM25")) {    
                                miseType = "�ʹ̼�����";
                            }
                            StringBuffer sb = new StringBuffer();
                            sb.append("�߷ɳ�¥ : " + dataDate + ", ���� : " + districtName + " ("+ moveName +"), " + "�߷ɽð� : "+ issueDate + " " + issueTime + ", �� : " + issueVal + " ("+ issueGbn +") " + miseType);
                           
                            System.out.println(sb.toString());
                            
                           if(p<2) {fine=fine.concat(sb.toString()+"\n");p++;}
                        }

                        
                        br.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                    Friend client = new Friend ("127.0.0.1");
                    client.friend.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //�������� �������� ����
                    client.friend.setVisible(true);
                    client.run();
   }
}